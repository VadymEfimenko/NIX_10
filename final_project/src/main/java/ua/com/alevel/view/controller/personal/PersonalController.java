package ua.com.alevel.view.controller.personal;

import org.apache.commons.collections4.MapUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.PLPFacade;
import ua.com.alevel.facade.shop.LabelFacade;
import ua.com.alevel.facade.shop.MusicianFacade;
import ua.com.alevel.facade.shop.OrderFacade;
import ua.com.alevel.facade.shop.ReleaseFacade;
import ua.com.alevel.persistence.entity.label.Label;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.view.dto.request.OrderRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ReleasePLPDto;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/personal")
public class PersonalController {

    private final PLPFacade plpFacade;
    private final ReleaseFacade releaseFacade;
    private final MusicianFacade musicianFacade;
    private final OrderFacade orderFacade;
    private final LabelFacade labelFacade;

    public PersonalController(PLPFacade plpFacade, ReleaseFacade releaseFacade, MusicianFacade musicianFacade, OrderFacade orderFacade, LabelFacade labelFacade) {
        this.plpFacade = plpFacade;
        this.releaseFacade = releaseFacade;
        this.musicianFacade = musicianFacade;
        this.orderFacade = orderFacade;
        this.labelFacade = labelFacade;
    }

    @GetMapping
    private String findAllReleases(Model model, WebRequest webRequest) {
        PageData<ReleasePLPDto> pageData = plpFacade.search(webRequest);
        model.addAttribute("releases", pageData.getItems());
        model.addAttribute("pageData", pageData);
        model.addAttribute("musicians", musicianFacade.findAll());
        return "pages/release/personal/releases_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest webRequest, ModelMap model) {
        Map<String, String[]> paramMap = webRequest.getParameterMap();
        if (MapUtils.isNotEmpty(paramMap)) {
            paramMap.forEach((key, value) -> {
                if (!key.equals("_csrf")) {
                    model.addAttribute(key, value);
                }
            });
        }
        return new ModelAndView("redirect:/personal", model);
    }

    @GetMapping("/dashboard")
    private String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Personal user = orderFacade.findUserByEmail(auth.getName());
        model.addAttribute("user",user);
        model.addAttribute("orders",user.getOrders());
        return "pages/personal/dashboard";
    }

    @GetMapping("/buy/{id}")
    public ModelAndView addToCart(WebRequest request, ModelMap model, @PathVariable Long id) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach((key, value) -> {
                if (!key.equals("_csrf")) {
                    model.addAttribute(key, value);
                }
            });
        }
        if (plpFacade.getCart().stream().filter(release -> Objects.equals(release.getId(), id)).count() >0){
            return new ModelAndView("redirect:/personal/cart", model);
        }
        plpFacade.addToCart(id);
        return new ModelAndView("redirect:/personal", model);
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("order") OrderRequestDto dto) {
        List<Release> newList = new ArrayList<>();
        plpFacade.getCart().stream().forEach(release -> {release.setQuantity(release.getQuantity()-1); newList.add(release);});
        dto.setList(newList);
        orderFacade.create(dto);
        plpFacade.setCart(new ArrayList<>());
        return "redirect:/personal";
    }

    @GetMapping("/cart")
    public String redirectToCartPage(Model model) {

        if (plpFacade.getCart().size()==0){
            return "pages/cart";
        }

        List<Label> labels = labelFacade.findAll().stream().filter(label ->  contain(plpFacade.getCart(),label.getReleases())).collect(Collectors.toList());
        model.addAttribute("labels", labels);
        model.addAttribute("releases", plpFacade.getCart());
        model.addAttribute("order", new OrderRequestDto());
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication());
        return "pages/order/order_new";
    }
    @GetMapping("/cart/delete/{id}")
    public String deleteFromCart(@PathVariable Long id) {
        List<Long> newCartId = new ArrayList<>();
        plpFacade.getCartId().stream().filter(i -> !Objects.equals(i, id)).forEach(i -> newCartId.add(i));
        plpFacade.setCart(newCartId);
        return "redirect:/personal/cart";
    }

    @GetMapping("/{id}")
    public String redirectToNewReleasePage(@PathVariable Long id, Model model) {
        model.addAttribute("release", releaseFacade.findById(id));
        model.addAttribute("labels", releaseFacade.findAllLabelsByReleaseId(id));
        return "pages/release/personal/release_details";
    }

    private boolean contain(List<Release> list, Set<Release> set){
        List<Release> listFromSet = new ArrayList<>(set);
        List<Long> idList = new ArrayList<>();
        List<Long> idListFromSet = new ArrayList<>();
        list.stream().forEach(release -> idList.add(release.getId()));
        listFromSet.stream().forEach(release -> idListFromSet.add(release.getId()));

        for (Long id:idList) {
            if (!idListFromSet.contains(id)){
                return false;
            }
        }
        return true;
    }
}
