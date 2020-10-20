package edu.baylor.ecs.csi5324.factoryMethod.test;

import edu.baylor.ecs.csi5324.factoryMethod.AbstractFactory;
import edu.baylor.ecs.csi5324.factoryMethod.factory.AmazonFactory;
import edu.baylor.ecs.csi5324.factoryMethod.product.impl.AmazonProduct;
import edu.baylor.ecs.csi5324.factoryMethod.store.Store;

public class AmazonUserTest extends UserTest {

    /*public AmazonUserTest() {
        super(new AmazonFactory());
    }*/

    AbstractFactory amazonFactory;

    // pick a store
    protected Store makeStore() {
        return makeFactory().makeStore();
    }

    @Override
    protected AmazonProduct makeProduct(String name) {
        return new AmazonProduct(name);
    }

    @Override
    protected AbstractFactory makeFactory() {
        amazonFactory = new AmazonFactory();
        return amazonFactory;
    }


}
