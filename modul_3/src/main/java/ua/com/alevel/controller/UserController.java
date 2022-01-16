package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.request.UserRequestDto;
import ua.com.alevel.dto.response.UserResponseDto;
import ua.com.alevel.facade.UserFacade;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public String findAll(Model model) {
        List<UserResponseDto> users = userFacade.findAll();
        model.addAttribute("users", users);
        return "pages/user/user_all";
    }

    @GetMapping("/new")
    public String redirectToNewUserPage(Model model){
        model.addAttribute("user", new UserRequestDto());
        return "pages/user/user_new";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") UserRequestDto dto){
        userFacade.create(dto);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        userFacade.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userFacade.findById(id));
        model.addAttribute("accounts", userFacade.findAllAccountsByUserId(id));
        return "pages/user/user_details";
    }
}
