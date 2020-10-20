package factory;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;


import factory.PickByRank;
import org.junit.Before;
import org.junit.Test;

import factory.cart.Cart;
import factory.product.Product;
import factory.store.Store;
import factory.store.impl.Heureka;

public abstract class UserTest {

	private Product soap;
	private Product anotherSoap;
	private Product tabaco;
	private Product book;
	private Product lego;

	// pick a store
	protected Store makeStore() {
		// Mall();
		return new Heureka();
	}

	protected Product makeProduct(String name) {
		return new Product(name);
	}

	@Before
	public void setUp() {
		// pick product type
		// what if heureka product?
		soap = makeProduct("Soap").init("Nice protocol", new BigDecimal(30));
		anotherSoap = makeProduct("Soap").init("Nice protocol", new BigDecimal(30));
		tabaco = makeProduct("Tabaco").init("Dont smoke", new BigDecimal(20));
		book = makeProduct("Book").init("Read me", new BigDecimal(25));
		lego = makeProduct("Lego").init("Play me", new BigDecimal(35));

	}

	@Test
	public void test() throws Exception {
		Store store = makeStore();
		Cart cart = makeAnOrder();
		selectDistributorBasedOnRank(store);

		// process
		store.process(cart);

	}

	@Test(expected = Exception.class)
	public void testFail() throws Exception {
		
		Store store = makeStore();
		Cart cart = makeAnOrder();
		// try to process
		store.process(cart);

	}
	@Test
	public void testAggregation() throws Exception {
		Cart cart = makeAnOrder();
		assert(cart.getOrderList().size() == 4);
	}
	@Test
	public void testTotal() throws Exception {
		Cart cart = makeAnOrder();
		assertTrue(cart.getTotal().equals(new BigDecimal("190")));
	}

	private Cart makeAnOrder() {
		// make an order
		//Fluent Interface
		Cart cart = new Cart();
		cart.addLine(soap, 2)
		.addLine(anotherSoap, 1)
		.addLine(tabaco, 2)
		.addLine(lego, 1)
		.addLine(book, 1);
		return cart;
	}
	
	private void selectDistributorBasedOnRank(Store store) {

		store.selectDistributor(new PickByRank().calculateStrategy(store));
	}

	public Cart getOrder(){
		return this.makeAnOrder();
	}

}
