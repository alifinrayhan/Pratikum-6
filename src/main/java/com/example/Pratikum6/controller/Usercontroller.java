package com.example.Pratikum6.controller;

import org.springframework.ui.Model;
import com.example.Pratikum6.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Usercontroller {
    private static List<User> listMahasiswa = new ArrayList<>();

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password) {
        if (username.equals("admin") && password.equals("20230140091")) {
            return "redirect:/home";
        }
        return "redirect:/login?error";
    }
    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("listMhs", listMahasiswa);
        return "home";
    }

    @GetMapping("/form")
    public String formPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/submitForm")
    public String submitData(@ModelAttribute User user) {
        listMahasiswa.add(user); // Menambah data ke list temporary
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        listMahasiswa.clear(); // Opsional: hapus data saat logout
        return "redirect:/login";
    }

}