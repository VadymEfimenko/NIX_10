package ua.com.alevel.view.controller.open;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.shop.MusicianFacade;
import ua.com.alevel.facade.shop.PLPFacade;
import ua.com.alevel.facade.shop.ReleaseFacade;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.response.shop.ReleasePLPDto;
import ua.com.alevel.view.dto.response.util.PageData;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/releases")
public class PLPController {

    private final PLPFacade plpFacade;
    private final ReleaseFacade releaseFacade;
    private final MusicianFacade musicianFacade;

    public PLPController(PLPFacade plpFacade, ReleaseFacade releaseFacade, MusicianFacade musicianFacade) {
        this.plpFacade = plpFacade;
        this.releaseFacade = releaseFacade;
        this.musicianFacade = musicianFacade;
    }

    @GetMapping
    private String allReleases(Model model, WebRequest webRequest) {
        PageData<ReleasePLPDto> response = plpFacade.search(webRequest);
        model.addAttribute("pageData", response);
        model.addAttribute("musicians", musicianFacade.findAll());
        return "pages/open/plp";
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
        return new ModelAndView("redirect:/releases", model);
    }

    @GetMapping("/suggestions")
    private @ResponseBody
    List<String> allSearchReleases(@RequestParam String query) {
        return plpFacade.searchReleaseName(query);
    }

    @GetMapping("/{id}")
    public String redirectToNewReleasePage(@PathVariable Long id, Model model) {
        model.addAttribute("release", releaseFacade.findReleaseById(id));
        model.addAttribute("distributors", releaseFacade.findAllDistributorsByReleaseId(id));
        return "pages/open/release_details";
    }

    @PostMapping("/search")
    private String searchReleases(@RequestParam String query, RedirectAttributes attributes) {
        attributes.addAttribute(WebRequestUtil.SEARCH_RELEASE_PARAM, query);
        return "redirect:/releases";
    }
}
