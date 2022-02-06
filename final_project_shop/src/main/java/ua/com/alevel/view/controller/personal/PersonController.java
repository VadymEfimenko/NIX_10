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
import ua.com.alevel.facade.shop.*;
import ua.com.alevel.persistence.entity.shop.Distributor;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.view.dto.request.shop.OrderRequestDto;
import ua.com.alevel.view.dto.response.shop.ReleasePLPDto;
import ua.com.alevel.view.dto.response.util.PageData;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/personal")
public class PersonController {

    private final PLPFacade plpFacade;
    private final ReleaseFacade releaseFacade;
    private final DistributorFacade distributorFacade;
    private final MusicianFacade musicianFacade;
    private final OrderFacade orderFacade;

    public PersonController(PLPFacade plpFacade,
                            ReleaseFacade releaseFacade,
                            DistributorFacade distributorFacade,
                            MusicianFacade musicianFacade,
                            OrderFacade orderFacade) {
        this.plpFacade = plpFacade;
        this.releaseFacade = releaseFacade;
        this.distributorFacade = distributorFacade;
        this.musicianFacade = musicianFacade;
        this.orderFacade = orderFacade;
    }

    @GetMapping
    private String allReleases(Model model, WebRequest webRequest) {
        PageData<ReleasePLPDto> response = plpFacade.search(webRequest);
        model.addAttribute("releases", response.getItems());
        model.addAttribute("pageData", response);
        model.addAttribute("musicians", musicianFacade.findAll());
        return "pages/release/personal/releases_all";
    }

    @GetMapping("/dashboard")
    private String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Personal user = orderFacade.findUserByEmail(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("orders", user.getOrders());
        return "pages/personal/dashboard";
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
        return new ModelAndView("redirect:/personal", model);
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
        if (plpFacade.getCart().stream().filter(release -> Objects.equals(release.getId(), id)).count() > 0) {
            return new ModelAndView("redirect:/personal/cart", model);
        }
        plpFacade.addToCart(id);
        return new ModelAndView("redirect:/personal", model);
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("order") OrderRequestDto dto) {
        List<Release> list = new ArrayList<>();
        plpFacade.getCart().stream().forEach(release -> {
            release.setQuantity(release.getQuantity() - 1);
            list.add(release);
        });
        dto.setReleases(list);
        orderFacade.create(dto);
        plpFacade.setCart(new ArrayList<>());
        return "redirect:/personal";
    }

    @GetMapping("/cart")
    public String redirectToCartPage(Model model) {
        if (plpFacade.getCart().size() == 0) {
            return "pages/empty_cart";
        }
        List<Distributor> distributorList = distributorFacade.findAll().stream().filter(distributor -> includes(plpFacade.getCart(),
                distributor.getReleases())).collect(Collectors.toList());
        model.addAttribute("distributors", distributorList);
        model.addAttribute("releases", plpFacade.getCart());
        model.addAttribute("order", new OrderRequestDto());
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication());
        return "pages/order/order_new";
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteFromCart(@PathVariable Long id) {
        List<Long> newId = new ArrayList<>();
        plpFacade.getCartId().stream().filter(i -> !Objects.equals(i, id)).forEach(i -> newId.add(i));
        plpFacade.setCart(newId);
        return "redirect:/personal/cart";
    }

    @GetMapping("/{id}")
    public String redirectToNewReleasePage(@PathVariable Long id, Model model) {
        model.addAttribute("release", releaseFacade.findReleaseById(id));
        model.addAttribute("distributors", releaseFacade.findAllDistributorsByReleaseId(id));
        return "pages/release/personal/release_details";
    }

    private boolean includes(List<Release> list, Set<Release> set) {
        List<Release> releaseList = new ArrayList<>(set);
        List<Long> listIDs = new ArrayList<>();
        List<Long> idListFromSet = new ArrayList<>();
        list.stream().forEach(release -> listIDs.add(release.getId()));
        releaseList.stream().forEach(release -> idListFromSet.add(release.getId()));
        for (Long id : listIDs) {
            if (!idListFromSet.contains(id)) {
                return false;
            }
        }
        return true;
    }
}
