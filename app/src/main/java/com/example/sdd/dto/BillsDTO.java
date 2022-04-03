package com.example.sdd.dto;


public class BillsDTO {
    private String amount;
    private String dueDate;
    private String email;

    public BillsDTO(String amount, String dueDate, String email) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.email = email;
    }

    public BillsDTO() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "BillDTO{" +
                "amount=" + amount +
                ", dueDate='" + dueDate + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
