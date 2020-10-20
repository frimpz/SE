package factory.store.impl;

import factory.cart.Cart;
import factory.cart.CartLineItem;
import factory.distributor.Distributor;
import factory.distributor.impl.DHL;
import factory.distributor.impl.DPD;
import factory.distributor.impl.UPS;
import factory.product.Product;
import factory.product.impl.HeurekaProduct;
import factory.store.Store;

import java.util.Arrays;
import java.util.List;

public class Heureka extends Store {

	private List<Distributor> distributorList = null;
	
	public Heureka() {
		Distributor[] distributors = { new DHL(), new DPD(),new UPS() };
		distributorList = Arrays.asList(distributors);
	}

	public Heureka(List<Distributor> distributorList) {
		this.distributorList = distributorList;
	}
	
	@Override
	public List<Distributor> getDistributorList() {
		return distributorList;
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
