package com.example.userservicemay24.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String value;
    @ManyToOne
    private User user;
    private Date expiresAt;
    private boolean isActive;
}
