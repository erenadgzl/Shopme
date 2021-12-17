package com.shopme.shopmebackend.user;


import com.shopme.shopmecommon.entity.Role;
import com.shopme.shopmecommon.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listAll(Model model) {
        List<User> users = userService.listAll();
        model.addAttribute("userList", users);
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        List<Role> roles = userService.listRoles();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "user_form";
    }

    @PostMapping("/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "Success");
        return "redirect:/users";
    }
}
