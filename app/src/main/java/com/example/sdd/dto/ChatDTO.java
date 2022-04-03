package com.example.sdd.dto;

public class ChatDTO {
    public String message;
    public String id;

    public ChatDTO() {
    }

    public ChatDTO(String message, String id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ChatDTO{" +
                "message='" + message + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
