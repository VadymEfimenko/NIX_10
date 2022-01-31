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
import ua.com.alevel.view.dto.request.MusicianRequestDto;
import ua.com.alevel.view.dto.response.MusicianResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.Map;

@Controller
@RequestMapping("/admin/musicians")
public class MusicianController extends BaseController {

    private Long updateId = 0L;
    private final ReleaseFacade releaseFacade;
    private final LabelFacade labelFacade;
    private final MusicianFacade musicianFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Name", "nickName", "nickName"),
            new HeaderName("Quantity", null, null),
            new HeaderName("Details", null, null),
            new HeaderName("Update", null, null),
            new HeaderName("Delete", null, null)
    };

    public MusicianController(ReleaseFacade releaseFacade, LabelFacade labelFacade, MusicianFacade musicianFacade) {
        this.releaseFacade = releaseFacade;
        this.labelFacade = labelFacade;
        this.musicianFacade = musicianFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        PageData<MusicianResponseDto> response = musicianFacade.findAll(webRequest);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/musicians/all");
        model.addAttribute("createNew", "/admin/musicians/new");
        model.addAttribute("cardHeader", "All Musicians");
        return "pages/musician/musician_all";
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
        return new ModelAndView("redirect:/admin/musicians", model);
    }

    @GetMapping("/new")
    public String redirectToNewMusician(Model model) {
        model.addAttribute("musician", new MusicianRequestDto());
        return "pages/musician/musician_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("musician") MusicianRequestDto dto) {
        musicianFacade.create(dto);
        return "redirect:/admin/musicians";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id, @ModelAttribute("musician") MusicianRequestDto dto) {
        model.addAttribute("musician", musicianFacade.findById(id));
        updateId = id;
        return "pages/musician/musician_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("musician") MusicianRequestDto dto) {
        musicianFacade.update(dto, updateId);
        return "redirect:/admin/musicians";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        musicianFacade.delete(id);
        return "redirect:/admin/musicians";
    }

    @GetMapping("/details/{id}")
    public String redirectToNewMusician(@PathVariable Long id, Model model) {
        model.addAttribute("musician", musicianFacade.findById(id));
        return "pages/musician/musician_details";
    }
}
