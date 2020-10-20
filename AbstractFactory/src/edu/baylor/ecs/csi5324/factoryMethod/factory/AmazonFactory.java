package edu.baylor.ecs.csi5324.factoryMethod.factory;

import edu.baylor.ecs.csi5324.factoryMethod.AbstractFactory;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.Distributor;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.impl.UPS;
import edu.baylor.ecs.csi5324.factoryMethod.product.Product;
import edu.baylor.ecs.csi5324.factoryMethod.product.impl.AmazonProduct;
import edu.baylor.ecs.csi5324.factoryMethod.store.Store;
import edu.baylor.ecs.csi5324.factoryMethod.store.impl.Amazon;

import java.util.Arrays;
import java.util.List;

public class AmazonFactory extends AbstractFactory {

    private List<Distributor> distributorList = null;

    protected List<Distributor> makeDistributorList() {
        Distributor[] distributors = { new UPS()};
        distributorList = Arrays.asList(distributors);
        return distributorList;
    }

    public Product doMakeProduct(String name) {
        return new AmazonProduct();
    }

    public Store makeStore() {
        return new Amazon(makeDistributorList());
    }
}
