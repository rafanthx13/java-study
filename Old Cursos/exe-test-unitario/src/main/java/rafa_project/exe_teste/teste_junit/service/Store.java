package rafa_project.exe_teste.teste_junit.service;


import rafa_project.exe_teste.teste_junit.exceptions.InventoryNullException;
import rafa_project.exe_teste.teste_junit.exceptions.ProductNotFoundException;
import rafa_project.exe_teste.teste_junit.model.Product;

import java.util.List;

public class Store {

    private List<Product> inventory;


    public List<Product> getInventory() {
        return inventory;
    }

    public void setInventory(List<Product> inventory) {
        this.inventory = inventory;
    }

    /**
     *  The Store request a sell of one product with your quantity
     *  And return the value of sell.
     *
     * @author rafael
     * @param description
     * @param quantity
     * @return double
     * @throws Exception
     */
    public double requestStoreSell(String description, int quantity) throws Exception {
        if (inventory == null) {
            throw new InventoryNullException("Inventory Is Null");
        } else {
            if (inventory.isEmpty() == true) {
                throw new Exception("Inventory Is Empty");
            } else {
                for (Product product : inventory) {
                    if (product.getDescription().equals(description)) {
                        product.sell(quantity);
                        return product.getPrice() * quantity;
                    }
                }
                throw new ProductNotFoundException();
            }
        }
    }

    public void requestStoreReposition(String description, int quantity) throws Exception {
        boolean verifyFoundProduct = false;
        if (inventory == null) {
            throw new InventoryNullException("Inventory Is Null");
        } else {
            if (inventory.isEmpty() == true) {
                throw new Exception("Inventory Is Empty");
            } else {
                for (Product product : inventory) {
                    if (product.getDescription().equals(description)) {
                        product.reposition(quantity);
                        verifyFoundProduct = true;
                    }
                }
                if(verifyFoundProduct == false){
                    throw new ProductNotFoundException();
                }
            }
        }
    }

    public int requestQuantityOfProduct(String description) throws Exception {
        if (inventory == null) {
            throw new InventoryNullException();
        } else {
            if (inventory.isEmpty() == true) {
                throw new Exception("Inventory Is Empty");
            } else {
                for (Product product : inventory) {
                    if (product.getDescription().equals(description))
                        return product.getQuantity();
                }
                throw new ProductNotFoundException();
            }
        }

    }

    public void printAllProduct() throws Exception {
        if (inventory == null) {
            throw new InventoryNullException();
        } else {
            if (inventory.isEmpty() == true) {
                throw new Exception("Inventory Is Empty");
            } else {
                System.out.println("Prices of Products:");
                for (Product product : inventory) {
                    System.out.println(
                            "==> " + product.getDescription() +
                                    " R$ " + product.getPrice()
                    );
                }
                System.out.println();
            }
        }

    }

    public void printAllInventory() throws Exception {
        if (inventory == null) {
            throw new InventoryNullException();
        } else {
            if (inventory.isEmpty() == true) {
                throw new Exception("Inventory Is Empty");
            } else {
                System.out.println("Stock of Products:");
                for (Product product : inventory) {
                    System.out.println(
                            "==> " + product.getDescription() +
                                    " Stock: " + product.getQuantity()
                    );
                }
                System.out.println();
            }
        }

    }


}
