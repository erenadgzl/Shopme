package com.shopme.shopmebackend.user.controller;

import java.io.IOException;

import com.shopme.shopmebackend.security.ShopmeUserDetails;
import com.shopme.shopmebackend.user.UserService;
import com.shopme.shopmebackend.util.FileUploadUtil;
import com.shopme.shopmecommon.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService service;

    @GetMapping
    public String viewDetails(@AuthenticationPrincipal ShopmeUserDetails loggedUser,
                              Model model) {
        String email = loggedUser.getUsername();
        User user = service.getByEmail(email);
        model.addAttribute("user", user);

        return "users/account_form";

    }

    @PostMapping("/update")
    public String saveDetails(User user, RedirectAttributes redirectAttributes,
                              @AuthenticationPrincipal ShopmeUserDetails loggedUser,
                              @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhotos(fileName);
            User savedUser = service.updateAccount(user);

            String uploadDir = "user-photos/" + savedUser.getId();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        } else {
            if (user.getPhotos().isEmpty()) user.setPhotos(null);
            service.updateAccount(user);
        }

        loggedUser.setFirstName(user.getFirstName());
        loggedUser.setLastName(user.getLastName());

        redirectAttributes.addFlashAttribute("message", "Your account details have been updated.");

        return "redirect:/account";
    }
}