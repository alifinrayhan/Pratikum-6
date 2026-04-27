package com.example.Pratikum6.controller;

import org.springframework.ui.Model;
import com.example.Pratikum6.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private static List<User> listMahasiswa = new ArrayList<>();

    // Path yang lebih fleksibel untuk lingkungan Docker/Local
    private static String UPLOAD_DIR = "src/main/resources/static/images/";

    // FIX: Tambahkan ini supaya saat buka localhost:PORT tidak Error 404
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password) {
        // Gunakan .equals() untuk keamanan string
        if ("admin".equals(username) && "20230140091".equals(password)) {
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
    public String submitData(@ModelAttribute User user, @RequestParam("fileFoto") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path path = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), path);

                user.setFoto(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        listMahasiswa.add(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        listMahasiswa.clear();
        return "redirect:/login";
    }
}