package ua.com.alevel.view.controller.admin;


import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.shop.DistributorFacade;
import ua.com.alevel.persistence.enums.DigitalMedia;
import ua.com.alevel.persistence.enums.Format;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.request.shop.DistributorRequestDto;
import ua.com.alevel.view.dto.response.shop.DistributorResponseDto;
import ua.com.alevel.view.dto.response.util.PageData;

import java.util.Map;

@Controller
@RequestMapping("/admin/distributors")
public class DistributorController extends BaseController {

    private final DistributorFacade distributorFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Дистрибьютор", "name", "name"),
            new HeaderName("Сайт", "address", "address"),
            new HeaderName("Релизы", null, null),
            new HeaderName("Подробнее", null, null),
            new HeaderName("Редактировать", null, null),
            new HeaderName("Удалить", null, null)
    };

    public DistributorController(DistributorFacade distributorFacade) {
        this.distributorFacade = distributorFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<DistributorResponseDto> response = distributorFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/distributors/all");
        model.addAttribute("createNew", "/admin/distributors/new");
        model.addAttribute("cardHeader", "Дистрибьюторы");
        return "pages/distributor/distributor_all";
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
        return new ModelAndView("redirect:/admin/distributors", model);
    }

    @GetMapping("/new")
    public String redirectToNewReleasePage(Model model) {
        model.addAttribute("media", DigitalMedia.values());
        model.addAttribute("format", Format.values());
        model.addAttribute("distributor", new DistributorRequestDto());
        return "pages/distributor/distributor_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("distributor") DistributorRequestDto dto) {
        distributorFacade.create(dto);
        return "redirect:/admin/distributors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        distributorFacade.delete(id);
        return "redirect:/admin/distributors";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        DistributorResponseDto distributorResponseDto = distributorFacade.findById(id);
        DistributorRequestDto distributorRequestDto = new DistributorRequestDto();
        distributorRequestDto.setWebsite(distributorResponseDto.getWebsite());
        distributorRequestDto.setName(distributorResponseDto.getName());
        distributorRequestDto.setDigitalMedia(distributorResponseDto.getDigitalMedia());
        distributorRequestDto.setFormat(distributorResponseDto.getFormat());
        model.addAttribute("id", id);
        model.addAttribute("distributor", distributorRequestDto);
        model.addAttribute("media", DigitalMedia.values());
        model.addAttribute("format", Format.values());
        return "pages/distributor/distributor_update";
    }

    @PostMapping("/update")
    public String update(@RequestParam Long id, @ModelAttribute("distributor") DistributorRequestDto dto) {
        distributorFacade.update(dto, id);
        return "redirect:/admin/distributors";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("distributor", distributorFacade.findById(id));
        model.addAttribute("releases", distributorFacade.findAllReleasesByDistributorId(id));
        return "pages/distributor/distributor_details";
    }
}
