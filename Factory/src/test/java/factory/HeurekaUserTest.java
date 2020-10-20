package factory;

import factory.UserTest;
import factory.product.impl.HeurekaProduct;
import factory.store.Store;
import factory.store.impl.Heureka;

public class HeurekaUserTest extends UserTest {

	// pick a store
	protected Store makeStore() {
		// Mall();
		return new Heureka();
	}

	@Override
	protected HeurekaProduct makeProduct(String name) {
		return new HeurekaProduct(name);
	}

}
