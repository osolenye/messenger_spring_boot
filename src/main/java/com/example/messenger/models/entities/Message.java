package com.example.messenger.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne
    private AppUser sender;
    @OneToOne
    private Message answeredTo;
    @ManyToOne
    private Chat chat;
}