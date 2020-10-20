package factory.cart;

import factory.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<CartLineItem> orderList = new ArrayList<CartLineItem>();

	public Cart addLine(Product product, int amount) {
		// aggregation
		for(CartLineItem line : orderList) {
			//.equal has been overridden in product
			if (line.getProduct() .equals(product)) { // left == on purpose!
				line.setQuantity(line.getQuantity()+amount);
				return this;
			}
		}
		
		orderList.add(CartLineItem.make(product, amount));
		//Fluent Interface
		return  this;
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CartLineItem orderLine : orderList) {
			total = total.add(orderLine.getSubTotal());
		}
		return total;
	}

	public List<CartLineItem> getOrderList() {
		return orderList;
	}
}
