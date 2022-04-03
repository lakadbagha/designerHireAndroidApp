package com.example.sdd.dto;

public class HireDTO {
    private String hiredEmail;
    private String designerEmail;

    public HireDTO() {
    }

    public HireDTO(String hiredEmail, String designerEmail) {
        this.hiredEmail = hiredEmail;
        this.designerEmail = designerEmail;
    }

    public String getHiredEmail() {
        return hiredEmail;
    }

    public void setHiredEmail(String hiredEmail) {
        this.hiredEmail = hiredEmail;
    }

    public String getDesignerEmail() {
        return designerEmail;
    }

    public void setDesignerEmail(String designerEmail) {
        this.designerEmail = designerEmail;
    }
}
