package edu.baylor.ecs.csi5324.factoryMethod;

import edu.baylor.ecs.csi5324.factoryMethod.distributor.Distributor;
import edu.baylor.ecs.csi5324.factoryMethod.product.Product;
import edu.baylor.ecs.csi5324.factoryMethod.store.Store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractFactory {

    private Map<String, Product> cache = new HashMap<>();

    protected abstract List<Distributor> makeDistributorList();

    protected abstract Product doMakeProduct(String name);

    public abstract Store makeStore();


    public final Product makeProduct(String name) {
        if (cache.containsKey(name)) {
            return cache.get(name);
        } else {
            Product product = doMakeProduct(name);
            cache.put(name, product);
            return product;
        }
    }

}
