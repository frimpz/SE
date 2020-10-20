package factory;


import factory.cart.Cart;
import factory.product.impl.AmazonProduct;
import factory.store.Store;
import factory.store.impl.Amazon;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class AmazonUserTest extends UserTest {

    // pick a store
    protected Store makeStore() {
        // Mall();
        return new Amazon();
    }

    @Override
    protected AmazonProduct makeProduct(String name) {
        return new AmazonProduct(name);
    }


    @Override
    public void testTotal() throws Exception {
        Cart cart = super.getOrder();
        assertTrue(cart.getTotal().equals(new BigDecimal("171.00")));
    }






}
