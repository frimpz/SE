package edu.baylor.ecs.csi5324.factoryMethod.test;
import edu.baylor.ecs.csi5324.factoryMethod.AbstractFactory;
import edu.baylor.ecs.csi5324.factoryMethod.factory.HeurekaFactory;
import edu.baylor.ecs.csi5324.factoryMethod.product.impl.HeurekaProduct;
import edu.baylor.ecs.csi5324.factoryMethod.store.Store;

public class HeurekaUserTest extends UserTest {

	/*public HeurekaUserTest() {
		super(new HeurekaFactory());
	}*/

	AbstractFactory heurekaFactory;

	// pick a store
	protected Store makeStore() {
		// Mall();
		//return new Heureka();
		return makeFactory().makeStore();
	}

	@Override
	protected HeurekaProduct makeProduct(String name) {
		return new HeurekaProduct(name);
	}

	@Override
	protected AbstractFactory makeFactory() {
		 heurekaFactory  = new HeurekaFactory();
		 return  heurekaFactory;
	}

}
