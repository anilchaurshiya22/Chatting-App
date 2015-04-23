/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springchat.domain;

import com.google.gson.annotations.Expose;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author 984171
 */
@Entity 
public class ChatMessage {
    @Id
    @GeneratedValue
    @Expose
    private int id;
    
    @Expose
    @NotNull
    @ManyToOne
    private User sender;
    
    @Expose
    @NotBlank
    private String message;
    
    @Expose
    private Date sentDate;
    
    private String status;
    
    @ManyToOne
    private Chat chat;

    public ChatMessage() {
    }

    public ChatMessage(User sender, String message) {
        this.sender = sender;
        this.message = message;
        sentDate = new Date();
        status = "Sent!";
    }
    
    public ChatMessage(User sender, String message, Chat chat) {
        this.sender = sender;
        this.message = message;
        this.chat = chat;
        sentDate = new Date();
        status = "Sent!";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
    
}
