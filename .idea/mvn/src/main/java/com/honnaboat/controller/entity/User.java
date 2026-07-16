package com.HonnaBot.SagaraMitra.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String userName;

    @Column(nullable = false)
    private int userAge;

    @Column(nullable = false, unique = true, length = 15)
    private String userPhone;


    @Column(nullable = false, length = 10)
    private String userPassword;
}