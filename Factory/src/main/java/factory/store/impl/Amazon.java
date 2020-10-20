package factory.store.impl;

import factory.cart.Cart;
import factory.cart.CartLineItem;
import factory.distributor.Distributor;
import factory.distributor.impl.UPS;
import factory.product.Product;
import factory.product.impl.AmazonProduct;
import factory.store.Store;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

//Amazon Store
public class Amazon extends Store {

    private List<Distributor> distributorList = null;

    public Amazon() {
        Distributor[] distributors = { new UPS()};
        distributorList = Arrays.asList(distributors);
    }

    public Amazon(List<Distributor> distributorList) {
        this.distributorList = distributorList;
    }

    @Override
    public List<Distributor> getDistributorList() {
        return distributorList;
    }

    @Override
    protected void hookProcess(Cart order) throws Exception {
        System.out.println(Amazon.class.getSimpleName() + " is happy for your order");
        for (CartLineItem line : order.getOrderList()) {
            Product product = line.getProduct();
            System.out.println("* " + product.getName() + " " + line.getQuantity() + "x " + product.getPrice());
            if (product instanceof AmazonProduct) {
                System.out.println("- Rank... " + ((AmazonProduct) product).getRank());
                //BigDecimal price =product.getPrice();
                //10% discount for amazon
                //with rounding up
                //product.setPrice();
            } else {
                throw new Exception("Not an Amazon product");
            }

        }
        order.getOrderList();

    }

}
