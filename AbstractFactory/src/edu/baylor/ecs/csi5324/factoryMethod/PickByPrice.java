package edu.baylor.ecs.csi5324.factoryMethod;

import edu.baylor.ecs.csi5324.factoryMethod.distributor.Distributor;
import edu.baylor.ecs.csi5324.factoryMethod.store.Store;

import java.math.BigDecimal;

public class PickByPrice extends Strategy{


    public int calculateStrategy(Store store) {

        int index = 0;
        BigDecimal charge = new BigDecimal(0);
        for (int i = 0; i < store.getDistributorList().size(); i++) {
            Distributor distributor = store.getDistributorList().get(i);
            if (distributor.getCharge().compareTo(charge)>0) {
                index = i;
                charge = distributor.getCharge();
            }
            System.out.println("* " + distributor.getClass().getSimpleName());
        }

        return index;
    }
}
