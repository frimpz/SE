package factory.distributor.impl;

import factory.cart.Cart;
import factory.distributor.Distributor;

import java.math.BigDecimal;
import java.net.URL;

public class DHL extends Distributor {

	@Override
	public BigDecimal getCharge() {
		// location estimate
		return new BigDecimal(120);
	}

	@Override
	public double getRank() {
		// some more detailed calculation
		return 4.5;
	}

	@Override
	public URL getTrackingLink() throws Exception {
		return new URL("http://www.dhl.com/tracking");
	}

	@Override
	public void ship(Cart order) throws Exception {
		ship("DHL", order);
		System.out.println("# Pickup at vendor");
		System.out.println("# Send to Uvaly");
		System.out.println("# Distribute locally");
		System.out.println("# Send to Customer");
	}

}
