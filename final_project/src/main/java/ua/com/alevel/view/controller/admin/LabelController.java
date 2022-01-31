package ua.com.alevel.view.controller.admin;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.shop.LabelFacade;
import ua.com.alevel.facade.shop.ReleaseFacade;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.LabelRequestDto;
import ua.com.alevel.view.dto.response.LabelResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.Map;

@Controller
@RequestMapping("/admin/labels")
public class LabelController extends BaseController {
    private final ReleaseFacade releaseFacade;
    private final LabelFacade labelFacade;

    public LabelController(ReleaseFacade releaseFacade, LabelFacade labelFacade) {
        this.releaseFacade = releaseFacade;
        this.labelFacade = labelFacade;
    }

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Label", "name", "name"),
            new HeaderName("Releases", null, null),
            new HeaderName("Details", null, null),
            new HeaderName("Update", null, null),
            new HeaderName("Delete", null, null)
    };

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<LabelResponseDto> response = labelFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/labels/all");
        model.addAttribute("createNew", "/admin/labels/new");
        model.addAttribute("cardHeader", "All Labels");
        return "pages/label/label_all";
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
        return new ModelAndView("redirect:/admin/labels", model);
    }

    @GetMapping("/new")
    public String redirectToNewLabel(Model model) {
        model.addAttribute("label", new LabelRequestDto());
        return "pages/label/label_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("label") LabelRequestDto requestDto) {
        labelFacade.create(requestDto);
        return "redirect:/admin/labels";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        LabelResponseDto labelResponseDto = labelFacade.findById(id);
        LabelRequestDto labelRequestDto = new LabelRequestDto();
        labelRequestDto.setLabelName(labelResponseDto.getName());
        model.addAttribute("id", id);
        model.addAttribute("label", labelRequestDto);
        return "pages/label/label_update";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @ModelAttribute("label") LabelRequestDto dto) {
        labelFacade.update(dto, id);
        return "redirect:/admin/labels";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        labelFacade.delete(id);
        return "redirect:/admin/labels";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("label", labelFacade.findById(id));
        model.addAttribute("releases", labelFacade.findAllReleasesByLabelId(id));
        return "pages/label/label_details";
    }
}
