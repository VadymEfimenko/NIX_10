package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.CustomerFacade;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.view.dto.request.ProductRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ProductResponseDto;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {

    private final ProductFacade productFacade;
    private final CustomerFacade customerFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("product name", "name", "name"),
            new HeaderName("product price", "price", "price"),
            new HeaderName("customers", null, null),
            new HeaderName("update", null, null),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };
    private Long updateId = 0L;

    public ProductController(ProductFacade productFacade, CustomerFacade customerFacade) {
        this.productFacade = productFacade;
        this.customerFacade = customerFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<ProductResponseDto> response = productFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/products/all");
        model.addAttribute("createNew", "/products/new");
        model.addAttribute("cardHeader", "All Products");
        return "pages/product/product_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "products");
    }

    @GetMapping("/new")
    public String redirectToNewProductPage(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        return "pages/product/product_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("product") ProductRequestDto dto) {
        productFacade.create(dto);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productFacade.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("product") ProductRequestDto productRequestDto) {
        updateId = id;
        return "pages/product/product_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("products") ProductRequestDto requestDto) {
        productFacade.update(requestDto, updateId);
        return "redirect:/products";
    }

    @GetMapping("details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("product", productFacade.findById(id));
        model.addAttribute("customers", productFacade.findAllCustomersByProductId(id));
        return "pages/product/product_details";
    }
}
