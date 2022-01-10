package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.CustomerFacade;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.view.dto.request.CustomerRequestDto;
import ua.com.alevel.view.dto.response.CustomerResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Controller
@RequestMapping("/customers")
public class CustomerController extends BaseController {

    private Long updateId = 0L;
    private final CustomerFacade customerFacade;
    private final ProductFacade productFacade;
    private final HeaderName[] columnNames;

    {
        columnNames = new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("first name", "firstName", "firstName"),
                new HeaderName("last name", "secondName", "secondName"),
                new HeaderName("products", null, null),
                new HeaderName("details", null, null),
                new HeaderName("update", null, null),
                new HeaderName("delete", null, null)
        };
    }

    public CustomerController(CustomerFacade customerFacade, ProductFacade productFacade) {
        this.customerFacade = customerFacade;
        this.productFacade = productFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<CustomerResponseDto> response = customerFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "customers/all");
        model.addAttribute("createNew", "customers/new");
        model.addAttribute("cardHeader", "All customers");
        return "pages/customer/customer_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "customers");
    }

    @GetMapping("/new")
    public String redirectToNewCustomerPage(Model model, WebRequest request) {
        model.addAttribute("customer", new CustomerRequestDto());
        model.addAttribute("products", productFacade.findAll(request));
        return "pages/customer/customer_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("customer") CustomerRequestDto requestDto) {
        customerFacade.create(requestDto);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        customerFacade.delete(id);
        return "redirect:/customers";
    }

    @GetMapping("/update/{id}")
    public String update(@ModelAttribute("customer") CustomerRequestDto requestDto, @PathVariable Long id) {
        updateId = id;
        return "pages/customer/customer_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("customer") CustomerRequestDto requestDto) {
        customerFacade.update(requestDto, updateId);
        return "redirect:/customers";
    }

    @GetMapping("/details/{id}")
    public String redirectToNewCustomerPage(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerFacade.findById(id));
        model.addAttribute("products", customerFacade.findAllProductsByCustomerId(id));
        return "pages/customer/customer_details";
    }
}
