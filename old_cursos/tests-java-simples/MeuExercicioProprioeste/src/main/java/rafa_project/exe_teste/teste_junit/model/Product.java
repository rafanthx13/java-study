package rafa_project.exe_teste.teste_junit.model;

import rafa_project.exe_teste.teste_junit.exceptions.QuantityInvalidException;

public class Product {

    private String description;

    private double price;

    private int quantity;

    public void sell(int quantity) throws QuantityInvalidException {
        if(quantity <= 0){
            throw new QuantityInvalidException("Invalid Quantity To Sell");
        }else{
            if(this.quantity - quantity < 0){
                throw new QuantityInvalidException("Value Higher Error");
            }else{
                this.quantity = this.quantity - quantity;
            }
        }
    }

    public void reposition(int quantity) throws QuantityInvalidException {
        if(quantity <= 0){
            throw new QuantityInvalidException("Invalid Quantity To Reposition");
        }else{
            this.quantity += quantity;
        }
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
