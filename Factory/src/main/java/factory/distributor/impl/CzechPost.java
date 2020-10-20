package factory.distributor.impl;

import factory.cart.Cart;
import factory.distributor.Distributor;

import java.math.BigDecimal;
import java.net.URL;

public class CzechPost extends Distributor {

	@Override
	public BigDecimal getCharge() {
		// location estimate
		return new BigDecimal(150);
	}

	@Override
	public double getRank() {
		// some more detailed calculation
		return 2.5;
	}

	@Override
	public URL getTrackingLink() throws Exception {
		return new URL("http://www.cpost.com/tracking");
	}

	@Override
	public void ship(Cart order) throws Exception {
		ship("cpost", order);
		System.out.println("# Send to Prague");
		System.out.println("# Distribute locally");
		System.out.println("# Distribute locally");
		System.out.println("# Distribute locally");
		System.out.println("# Send to Customer");
	}

}
