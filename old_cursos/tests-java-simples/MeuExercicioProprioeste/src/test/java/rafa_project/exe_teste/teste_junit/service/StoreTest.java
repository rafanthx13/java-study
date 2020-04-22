package rafa_project.exe_teste.teste_junit.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import rafa_project.exe_teste.teste_junit.exceptions.InventoryNullException;
import rafa_project.exe_teste.teste_junit.exceptions.ProductNotFoundException;
import rafa_project.exe_teste.teste_junit.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static rafa_project.exe_teste.teste_junit.builder.ProductBuilder.oneProduct;

public class StoreTest {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();
    @Rule
    public ExpectedException exceptionCaptor = ExpectedException.none();
    private Store store;
    private List<Product> inventory;

    @Before
    public void prepareScenario() {
        store = new Store();
    }

    @Test
    public void makeRequestSell() throws Exception {
        inventory = Arrays.asList(oneProduct().setDescription("Product1").now(), oneProduct().now());
        store.setInventory(inventory);

        store.requestStoreSell("Product1", 5);

        Assert.assertThat(store.getInventory().get(0).getQuantity(), is(0));
    }

    @Test
    public void notMakeRequestSellBecauseNull() throws Exception {
        inventory = null;
        store.setInventory(inventory);

        exceptionCaptor.expect(InventoryNullException.class);
        exceptionCaptor.expectMessage("Inventory Is Null");

        store.requestStoreSell("ProductNull", 6);
    }

    @Test
    public void notMakeRequestSellBecauseIsEmpty() throws Exception {
        inventory = new ArrayList<Product>();
        store.setInventory(inventory);

        exceptionCaptor.expect(Exception.class);
        exceptionCaptor.expectMessage("Inventory Is Empty");

        store.requestStoreSell("ProductEmpty", 8);
    }

    @Test
    public void notMakeRequestSellBecauseNotExist() throws Exception {
        inventory = new ArrayList<Product>();
        inventory.add(oneProduct().now());
        inventory.add(oneProduct().now());
        store.setInventory(inventory);

        exceptionCaptor.expect(ProductNotFoundException.class);

        store.requestStoreSell("ProductNotExist", 9);
    }

    @Test
    public void notMakeRequestSellBecauseValueIsHigh() throws Exception {
        inventory = Arrays.asList(oneProduct().setDescription("Product1").now(), oneProduct().now());
        store.setInventory(inventory);

        try {
            store.requestStoreSell("Product1", 80);
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), is("Value Higher Error"));
        }
    }

    @Test
    public void makeRequestReposition() throws Exception {
        inventory = Arrays.asList(oneProduct().now(),
                oneProduct().setDescription("ProductToReposition").setQuantity(6).now(),
                oneProduct().now()
        );
        store.setInventory(inventory);

        store.requestStoreReposition("ProductToReposition", 10);

        Assert.assertThat(store.getInventory().get(1).getQuantity(), is(16));
    }

    @Test
    public void notMakeRequestRepositionBecauseNull() throws Exception {
        inventory = null;
        store.setInventory(inventory);

        exceptionCaptor.expect(InventoryNullException.class);
        store.requestStoreReposition("ProductNull", 8);

    }

    @Test
    public void notMakeRequestRepositionBecauseIsEmpty() {
        inventory = new ArrayList<Product>();
        store.setInventory(inventory);

        try {
            store.requestStoreReposition("ProductEmpty", 1);
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), is("Inventory Is Empty"));
        }
    }

    @Test
    public void notMakeRequestRepositionBecauseNotExist() throws Exception {
        inventory = new ArrayList<Product>();
        inventory.add(oneProduct().setDescription("Product1").now());
        inventory.add(oneProduct().setDescription("Product3").now());
        store.setInventory(inventory);

        exceptionCaptor.expect(ProductNotFoundException.class);
        store.requestStoreReposition("Product2", 5);
    }

    @Test
    public void makeRequestOfQuantityOfManyProductsWithSuccess() throws Exception {
        inventory = Arrays.asList(oneProduct().now(), oneProduct().setDescription("Product2").now(),
                oneProduct().setDescription("Product3").setQuantity(7).now(),
                oneProduct().setDescription("Product4").setQuantity(6).now()
        );
        store.setInventory(inventory);

        int quantity1 = store.requestQuantityOfProduct("Product2");
        int quantity2 = store.requestQuantityOfProduct("Product3");
        int quantity3 = store.requestQuantityOfProduct("Product4");

        errorCollector.checkThat(quantity1, is(5));
        errorCollector.checkThat(quantity2, is(7));
        errorCollector.checkThat(quantity3, is(6));
    }


}
