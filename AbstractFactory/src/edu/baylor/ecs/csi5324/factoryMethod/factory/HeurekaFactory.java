package edu.baylor.ecs.csi5324.factoryMethod.factory;

import edu.baylor.ecs.csi5324.factoryMethod.AbstractFactory;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.Distributor;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.impl.DHL;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.impl.DPD;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.impl.UPS;
import edu.baylor.ecs.csi5324.factoryMethod.product.Product;
import edu.baylor.ecs.csi5324.factoryMethod.product.impl.HeurekaProduct;
import edu.baylor.ecs.csi5324.factoryMethod.store.Store;
import edu.baylor.ecs.csi5324.factoryMethod.store.impl.Heureka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeurekaFactory  extends AbstractFactory {

    private List<Distributor> distributorList = null;

    @Override
    protected List<Distributor> makeDistributorList() {
        Distributor[] distributors = { new UPS(),new DPD(),new DHL()};
        distributorList = Arrays.asList(distributors);
        return distributorList;
    }

    @Override
    public Product doMakeProduct(String name) {
        return new HeurekaProduct();
    }

    @Override
    public Store makeStore() {
        return new Heureka(makeDistributorList());
    }


}
