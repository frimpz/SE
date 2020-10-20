package factory.distributor.impl;

import factory.cart.Cart;
import factory.distributor.Distributor;

import java.math.BigDecimal;
import java.net.URL;

//UPS delivery
public class UPS extends Distributor {

    @Override
    public BigDecimal getCharge() {
        // location estimate
        return new BigDecimal(130);
    }

    @Override
    public double getRank() {
        // some more detailed calculation
        return 4.6;
    }

    @Override
    public URL getTrackingLink() throws Exception {
        return new URL("http://www.ups.com/tracking");
    }

    @Override
    public void ship(Cart order) throws Exception {
        ship("UPS", order);
        System.out.println("# Pickup at vendor");
        System.out.println("# Send to Customer");
    }

}
