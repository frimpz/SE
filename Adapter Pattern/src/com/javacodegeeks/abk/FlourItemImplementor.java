package com.javacodegeeks.abk;

public class FlourItemImplementor implements FlourItemInterface {
	
	FlourItem fItem = new FlourItem();
	@Override
	public Flour getQuintal() {
		Flour f = fItem.getFlourItem();
		f.setPrice(f.getPrice()*10);
		f.setWeight(100);
		return f;
	}

	@Override
	public Flour get10kg() {
		Flour f = fItem.getFlourItem();
		return f;
	}

	@Override
	public Flour get1kg() {
		Flour f = fItem.getFlourItem();
		f.setPrice(f.getPrice()/10);
		f.setWeight(1);
		return f;
	}

}
