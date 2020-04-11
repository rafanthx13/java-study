package rafa_project.exe_teste.teste_junit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import rafa_project.exe_teste.teste_junit.exceptions.QuantityInvalidException;

import static org.hamcrest.CoreMatchers.is;
import static rafa_project.exe_teste.teste_junit.builder.ProductBuilder.*;

public class ProductTest {

    private Product product;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void prepareScenarioforTest(){
        //scenario centralized for all tests
        product = oneProduct().setQuantity(5).now();
        product = oneProduct().now();
    }

    @Test
    public void decreaseQuantityWhenSellSuccess() throws Exception{
        //action
        product.sell(4);

        //verification
        Assert.assertThat(product.getQuantity(), is(1));
    }

    @Test
    public void notDecreaseQuantityWhenSellCusInvalidValue(){
        //action and verification
        try {
            product.sell(-3);
            Assert.fail();
        } catch (QuantityInvalidException e) {
            Assert.assertThat(e.getMessage(), is("Invalid Quantity To Sell"));
        }
    }

    @Test
    public void notDecreaseQuantityWhyHigherValue(){
        try{
            product.sell(600);
        } catch( QuantityInvalidException e){
            Assert.assertThat(e.getMessage(), is("Value Higher Error"));
        }
    }

    @Test
    public void increaseQuantity() throws QuantityInvalidException {
        //action
        product.reposition(5);

        //verification
        Assert.assertThat(product.getQuantity(), is(10));
    }

    @Test
    public void notIncreaseQuantityInvalidValue(){
        //action
        try {
            product.reposition(-1);
        } catch (QuantityInvalidException e) {
            Assert.assertThat(e.getMessage(), is("Invalid Quantity To Reposition"));
        }
    }



}