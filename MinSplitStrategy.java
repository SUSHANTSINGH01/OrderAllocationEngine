package main;

import java.util.Comparator;

public class MinSplitStrategy implements AllocationStrategy {
	
	@Override
    public Comparator<Warehouse> getComparator(String skuId) {
        return (w1, w2)->{
            int w1Total=w1.getInventory(skuId).getTotalAvailable();
            int w2Total=w2.getInventory(skuId).getTotalAvailable();
            return Integer.compare(w2Total, w1Total);
        };
    }
}


