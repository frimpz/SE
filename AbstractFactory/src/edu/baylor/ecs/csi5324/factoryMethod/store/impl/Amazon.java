package edu.baylor.ecs.csi5324.factoryMethod.store.impl;

import java.util.Arrays;
import java.util.List;

import edu.baylor.ecs.csi5324.factoryMethod.cart.Cart;
import edu.baylor.ecs.csi5324.factoryMethod.cart.CartLineItem;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.Distributor;
import edu.baylor.ecs.csi5324.factoryMethod.product.Product;
import edu.baylor.ecs.csi5324.factoryMethod.product.impl.AmazonProduct;
import edu.baylor.ecs.csi5324.factoryMethod.store.Store;

public class Amazon extends Store {

    private List<Distributor> distributorList = null;

    /*public Amazon() {
        Distributor[] distributors = { new UPS()};
        distributorList = Arrays.asList(distributors);
    }*/

    public Amazon(List<Distributor> distributorList) {
        this.distributorList = distributorList;
    }

    @Override
    public List<Distributor> getDistributorList() {
        return this.distributorList ;
    }

    @Override
    protected void hookProcess(Cart order) throws Exception {
        System.out.println(Amazon.class.getSimpleName() + " is happy for your order");
        for (CartLineItem line : order.getOrderList()) {
            Product product = line.getProduct();
            System.out.println("* " + product.getName() + " " + line.getQuantity() + "x " + product.getPrice());
            if (product instanceof AmazonProduct) {
                System.out.println("- Rank... " + ((AmazonProduct) product).getRank());
            } else {
                throw new Exception("Not an Amazon product");
            }

        }
        order.getOrderList();
        System.out.println("Total: " + order.getTotal());

    }

}
