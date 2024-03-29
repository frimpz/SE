package factory.store;



import factory.cart.Cart;
import factory.distributor.Distributor;

import java.util.ArrayList;
import java.util.List;

public abstract class Store {

	private List<Cart> orderList = new ArrayList<Cart>();

	private Cart order = null;
	private Distributor distributor = null;

	public abstract List<Distributor> getDistributorList(); // force to override
	protected void hookProcess(Cart order)  throws Exception {} // voluntary override

	final protected Distributor getSelectedDistributor() {
		return distributor;
	}

	final public void selectDistributor(int index) {
		distributor = getDistributorList().get(index);

	}

	final public void process(Cart order) throws Exception {
		if(distributor == null) {
			throw new Exception("Select distributor");
		}
		hookProcess(order);
		
		this.order = order;
		distributor.ship(this.order);
		orderList.add(this.order);
	}

}
