package com.example.bookshop;

import java.time.LocalDate;

public class BillSummary {
    private String bookName;
    private float price;
    private int quantity;
    private int vatRate;
    private LocalDate date;


    public BillSummary(String bookName, float price, int quantity, int vatRate, LocalDate date) {
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
        this.vatRate = vatRate;
        this.date = date;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getVatRate() {
        return vatRate;
    }

    public void setVatRate(int vatRate) {
        this.vatRate = vatRate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getVatAmount(){
        return price * vatRate /100;
    }

    public float getTotalAmount(){
        return price + getVatAmount() * quantity;
    }


    @Override
    public String toString() {
        return "bookName='" + bookName + '\n' +
                ", quantity=" + quantity ;
    }
}
