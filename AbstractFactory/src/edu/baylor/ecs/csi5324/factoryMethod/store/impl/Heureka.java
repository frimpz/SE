package edu.baylor.ecs.csi5324.factoryMethod.store.impl;

import java.util.Arrays;
import java.util.List;

import edu.baylor.ecs.csi5324.factoryMethod.cart.Cart;
import edu.baylor.ecs.csi5324.factoryMethod.cart.CartLineItem;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.Distributor;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.impl.DHL;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.impl.DPD;
import edu.baylor.ecs.csi5324.factoryMethod.distributor.impl.UPS;
import edu.baylor.ecs.csi5324.factoryMethod.product.Product;
import edu.baylor.ecs.csi5324.factoryMethod.product.impl.HeurekaProduct;
import edu.baylor.ecs.csi5324.factoryMethod.store.Store;

public class Heureka extends Store {

	private List<Distributor> distributorList = null;
	
	/*public Heureka() {
		Distributor[] distributors = { new DHL(), new DPD(),new UPS() };
		distributorList = Arrays.asList(distributors);
	}*/

	public Heureka(List<Distributor> distributorList) {
		this.distributorList = distributorList;
	}
	
	@Override
	public List<Distributor> getDistributorList() {
		return this.distributorList;
	}

	@Override
	protected void hookProcess(Cart order) throws Exception {
		System.out.println(Heureka.class.getSimpleName() + " is happy for your order");
		for (CartLineItem line : order.getOrderList()) {
			Product product = line.getProduct();
			System.out.println("* " + product.getName() + " " + line.getQuantity() + "x " + product.getPrice());
			if (product instanceof HeurekaProduct) {
				System.out.println("- Rank... " + ((HeurekaProduct) product).getRank());
			} else {
				throw new Exception("Not a heureka product");
			}

		}
		System.out.println("Total: " + order.getTotal());

	}

}
