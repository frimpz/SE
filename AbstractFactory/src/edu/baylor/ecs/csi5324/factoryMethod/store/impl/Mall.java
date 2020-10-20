package edu.baylor.ecs.csi5324.factoryMethod.store.impl;

import java.util.Arrays;
import java.util.List;

import edu.baylor.ecs.csi5324.factoryMethod.cart.Cart;
import edu.baylor.ecs.csi5324.factoryMethod.cart.CartLineItem;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.Distributor;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.impl.CzechPost;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.impl.DPD;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.impl.UPS;
import edu.baylor.ecs.csi5324.factoryMethod.product.Product;
import edu.baylor.ecs.csi5324.factoryMethod.store.Store;

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
