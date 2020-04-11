package rafa_project.exe_teste.teste_junit.builder;

import rafa_project.exe_teste.teste_junit.model.Product;

public class ProductBuilder {

    private Product product;

    public static ProductBuilder oneProduct(){
            ProductBuilder productBuilder = new ProductBuilder();
            productBuilder.product = new Product();
            productBuilder.product.setDescription("Product Stand");
            productBuilder.product.setPrice(5.0);
            productBuilder.product.setQuantity(5);
            return productBuilder;
    }

    public ProductBuilder setQuantity(int value){
        product.setQuantity(value);
        return this;
    }

    public ProductBuilder setPrice(double value){
        product.setPrice(value);
        return this;
    }

    public ProductBuilder setDescription(String description){
        product.setDescription(description);
        return this;
    }

    public Product now(){
        return product;
    }




}
