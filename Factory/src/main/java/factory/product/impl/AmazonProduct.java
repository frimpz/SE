package factory.product.impl;

import factory.product.Product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//Amazon product
public class AmazonProduct extends Product {

    private static final Map<String, Double> productRank;
    static {
        Map<String, Double> temporary = new HashMap<String, Double>();
        temporary.put("Soap", 2.4);
        temporary.put("Lego", 5.0);
        temporary.put("Tabaco", 4.1);
        temporary.put("Book", 2.1);
        productRank = Collections.unmodifiableMap(temporary);
    }

    private double rank = 0.1;
    private double reduction =0.1;


    public AmazonProduct(String name, String describtion, BigDecimal price, double rank) {
        super(name, describtion, price);
        this.rank = rank;
    }

    public AmazonProduct(String name) {
        super(name);
        if (productRank.containsKey(name)) {
            rank = productRank.get(name);
        }
    }

    public AmazonProduct() {
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public static AmazonProduct make(String name) {
        return new AmazonProduct(name);
    }
    public AmazonProduct init(String describtion, BigDecimal price, double rank) {
        super.init(describtion, price);
        this.rank = rank;
        return this;
    }

    @Override
    public BigDecimal getPrice() {
        //The only fix that worked for me
        //still dont know why ?
        //Apparently calling without super calls the overriding method.
        BigDecimal price = super.getPrice();
        return price.subtract(new BigDecimal( 0.1).multiply(price).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    }


}
