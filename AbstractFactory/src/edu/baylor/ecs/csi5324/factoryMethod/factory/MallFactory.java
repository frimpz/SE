package edu.baylor.ecs.csi5324.factoryMethod.factory;

import edu.baylor.ecs.csi5324.factoryMethod.AbstractFactory;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.Distributor;
import edu.baylor.ecs.csi5324.factoryMethod.product.Product;
import edu.baylor.ecs.csi5324.factoryMethod.store.Store;
import edu.baylor.ecs.csi5324.factoryMethod.store.impl.Mall;

import java.util.List;

public class MallFactory extends AbstractFactory {

    @Override
    protected List<Distributor> makeDistributorList() {
        return this.makeStore().getDistributorList();
    }

    @Override
    public Product doMakeProduct(String name) {
        return new Product();
    }

    @Override
    public Store makeStore() {
        return new Mall();
    }
}
