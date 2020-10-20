package factory.store.impl;

import factory.cart.Cart;
import factory.cart.CartLineItem;
import factory.distributor.Distributor;
import factory.distributor.impl.CzechPost;
import factory.distributor.impl.DPD;
import factory.distributor.impl.UPS;
import factory.product.Product;
import factory.store.Store;

import java.util.Arrays;
import java.util.List;

public class Mall extends Store {

	private List<Distributor> distributorList = null;

	public Mall() {
		Distributor[] distributors = { new CzechPost(), new DPD(),new UPS() };
		distributorList = Arrays.asList(distributors);
	}

	public Mall(List<Distributor> distributorList) {
		this.distributorList = distributorList;
	}

	@Override
	public List<Distributor> getDistributorList() {
		return distributorList;
	}

	@Override
	protected void hookProcess(Cart order) throws Exception {
		System.out.println(Mall.class.getSimpleName() + " is happy for your order");
		for (CartLineItem line : order.getOrderList()) {
			Product product = line.getProduct();
			System.out.println("+ " + product.getName() + " " + line.getQuantity() + "x " + product.getPrice());

		}
		System.out.println("Total: " + order.getTotal());

	}

}
