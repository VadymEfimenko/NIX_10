package ua.com.alevel.view.controller.admin;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.shop.LabelFacade;
import ua.com.alevel.facade.shop.MusicianFacade;
import ua.com.alevel.facade.shop.ReleaseFacade;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.ReleaseRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ReleaseResponseDto;

import java.util.Map;

@Controller
@RequestMapping("/admin/releases")
public class ReleaseController extends BaseController {

    private final ReleaseFacade releaseFacade;
    private final LabelFacade labelFacade;
    private final MusicianFacade musicianFacade;

    public ReleaseController(ReleaseFacade releaseFacade, LabelFacade labelFacade, MusicianFacade musicianFacade) {
        this.releaseFacade = releaseFacade;
        this.labelFacade = labelFacade;
        this.musicianFacade = musicianFacade;
    }

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Label", "label", "label"),
            new HeaderName("Name", "releaseName", "releaseName"),
            new HeaderName("Price", "price", "price"),
            new HeaderName("Quantity", "quantity", "quantity"),
            new HeaderName("Labels", null, null),
            new HeaderName("Details", null, null),
            new HeaderName("Update", null, null),
            new HeaderName("Delete", null, null)
    };

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        PageData<ReleaseResponseDto> response = releaseFacade.findAll(webRequest);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/releases/all");
        model.addAttribute("createNew", "/admin/releases/new");
        model.addAttribute("cardHeader", "All releases");
        return "pages/release/admin/release_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach((key, value) -> {
                if (!key.equals("_csrf")) {
                    model.addAttribute(key, value);
                }
            });
        }
        return new ModelAndView("redirect:/admin/releases",model);
    }

    @GetMapping("/new")
    public String redirectToNewRelease(Model model) {
        model.addAttribute("release", new ReleaseRequestDto());
        model.addAttribute("labels", labelFacade.findAll());
        model.addAttribute("musicians", musicianFacade.findAll());
        return "pages/release/admin/release_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("release") ReleaseRequestDto requestDto) {
        releaseFacade.create(requestDto);
        return "redirect:/admin/releases";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        ReleaseResponseDto releaseResponseDto = releaseFacade.findById(id);
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto();
        releaseRequestDto.setPrice(releaseResponseDto.getPrice());
        releaseRequestDto.setReleaseName(releaseResponseDto.getReleaseName());
        releaseRequestDto.setDescription(releaseResponseDto.getDescription());
        releaseRequestDto.setImageUrl(releaseResponseDto.getImageUrl());
        releaseRequestDto.setYear(releaseResponseDto.getYear());
        releaseRequestDto.setGenre(releaseResponseDto.getGenre());
        releaseRequestDto.setQuantity(releaseResponseDto.getQuantity());
        releaseRequestDto.setMusician(releaseResponseDto.getMusicians().getId());
        releaseRequestDto.setLabelId(releaseRequestDto.getLabelId());
        model.addAttribute("id", id);
        model.addAttribute("release", releaseRequestDto);
        model.addAttribute("labels", labelFacade.findAll());
        model.addAttribute("musicians", musicianFacade.findAll());
        return "pages/release/admin/release_update";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @ModelAttribute("release") ReleaseRequestDto dto) {
        releaseFacade.update(dto, id);
        return "redirect:/admin/releases";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        releaseFacade.delete(id);
        return "redirect:/admin/releases";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("release", releaseFacade.findById(id));
        model.addAttribute("labels", releaseFacade.findAllLabelsByReleaseId(id));
        return "pages/release/admin/release_details";
    }

}
