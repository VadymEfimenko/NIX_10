package ua.com.alevel.view.controller.admin;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.shop.PersonalFacade;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PersonalResponseDto;

import java.util.Map;

@Controller
@RequestMapping("/admin/personals")
public class PersonController extends BaseController {

    private final PersonalFacade personalFacade;

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("name", null, null),
            new HeaderName("mail", "email", "email"),
            new HeaderName("details", null, null),
    };

    public PersonController(PersonalFacade personalFacade) {
        this.personalFacade = personalFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<PersonalResponseDto> response = personalFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/personals/all");
        model.addAttribute("cardHeader", "users");
        return "pages/personal/personal_all";
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
        return new ModelAndView("redirect:/admin/personals",model);
    }
    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("user", personalFacade.findById(id));
        return "pages/personal/personal_details";
    }
}
