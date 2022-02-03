package ua.com.alevel.view.controller.admin;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.shop.DistributorFacade;
import ua.com.alevel.facade.shop.MusicianFacade;
import ua.com.alevel.facade.shop.ReleaseFacade;
import ua.com.alevel.persistence.enums.ReleaseType;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.shop.ReleaseRequestDto;
import ua.com.alevel.view.dto.response.shop.ReleaseResponseDto;
import ua.com.alevel.view.dto.response.util.PageData;

import java.util.Map;

@Controller
@RequestMapping("/admin/releases")
public class ReleaseController extends BaseController {

    private final ReleaseFacade releaseFacade;
    private final DistributorFacade distributorFacade;
    private final MusicianFacade musicianFacade;

    public ReleaseController(ReleaseFacade releaseFacade,
                             DistributorFacade distributorFacade,
                             MusicianFacade musicianFacade) {
        this.releaseFacade = releaseFacade;
        this.distributorFacade = distributorFacade;
        this.musicianFacade = musicianFacade;
    }

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Музыкант", "musician", "musician"),
            new HeaderName("Релиз", "name", "name"),
            new HeaderName("Цена", "price", "price"),
            new HeaderName("Количество", "quantity", "quantity"),
            new HeaderName("Дистрибьюторы", null, null),
            new HeaderName("Подробнее", null, null),
            new HeaderName("Редактировать", null, null),
            new HeaderName("Удалить", null, null)
    };

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<ReleaseResponseDto> response = releaseFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/releases/all");
        model.addAttribute("createNew", "/admin/releases/new");
        model.addAttribute("cardHeader", "Все релизы");
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
        return new ModelAndView("redirect:/admin/releases", model);
    }

    @GetMapping("/new")
    public String redirectToNewReleasePage(Model model) {
        model.addAttribute("release", new ReleaseRequestDto());
        model.addAttribute("distributors", distributorFacade.findAll());
        model.addAttribute("musicians", musicianFacade.findAll());
        model.addAttribute("releaseType", ReleaseType.values());
        return "pages/release/admin/release_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("release") ReleaseRequestDto dto) {
        releaseFacade.create(dto);
        return "redirect:/admin/releases";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        releaseFacade.delete(id);
        return "redirect:/admin/releases";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        ReleaseResponseDto releaseResponseDto = releaseFacade.findById(id);
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto();
        releaseRequestDto.setQuantity(releaseResponseDto.getQuantity());
        releaseRequestDto.setMusicianId(releaseResponseDto.getMusician().getId());
        releaseRequestDto.setReleaseType(releaseResponseDto.getReleaseType());
        releaseRequestDto.setImageUrl(releaseResponseDto.getImage());
        releaseRequestDto.setDescription(releaseResponseDto.getDescription());
        releaseRequestDto.setPrice(releaseResponseDto.getPrice());
        releaseRequestDto.setReleaseName(releaseResponseDto.getName());
        releaseRequestDto.setDistributorsId(releaseRequestDto.getDistributorsId());
        model.addAttribute("id", id);
        model.addAttribute("release", releaseRequestDto);
        model.addAttribute("distributors", distributorFacade.findAll());
        model.addAttribute("musicians", musicianFacade.findAll());
        model.addAttribute("types", ReleaseType.values());
        return "pages/release/admin/release_update";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @ModelAttribute("release") ReleaseRequestDto dto) {
        releaseFacade.update(dto, id);
        return "redirect:/admin/releases";
    }

    @GetMapping("/details/{id}")
    public String redirectToNewReleasePage(@PathVariable Long id, Model model) {
        model.addAttribute("release", releaseFacade.findById(id));
        model.addAttribute("distributors", releaseFacade.findAllDistributorsByReleaseId(id));
        return "pages/release/admin/release_details";
    }
}
