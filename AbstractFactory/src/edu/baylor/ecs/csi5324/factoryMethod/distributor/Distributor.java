package edu.baylor.ecs.csi5324.factoryMethod.distributor;

import java.math.BigDecimal;
import java.net.URL;

import edu.baylor.ecs.csi5324.factoryMethod.cart.Cart;

public abstract class Distributor {
	public abstract BigDecimal getCharge();

	public abstract double getRank();

	public abstract URL getTrackingLink() throws Exception;

	public abstract void ship(Cart o) throws Exception;

	protected void ship(String name, Cart o) throws Exception {
		System.out.println(name + " ships (" + o.getTotal() + ") for charge " + getCharge() + " track at "
				+ getTrackingLink().toString());
	}
}
