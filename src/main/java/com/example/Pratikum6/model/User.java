package com.example.Pratikum6.model;

import lombok.Data;

@Data
public class User {
    private String username;
    private String email;
    private String password;
    private String foto; // Menyimpan nama file hasil upload
    private String kataKata;
}