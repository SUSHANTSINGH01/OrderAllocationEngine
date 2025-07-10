package main;

import java.util.Comparator;

public class MinEddStrategy implements AllocationStrategy {
	    
	    @Override
	    public Comparator<Warehouse> getComparator(String skuId) {
	        return Comparator
	            .comparingInt((Warehouse w) -> w.edd)
	            .thenComparingDouble(w->getInventoryScore(w, skuId)); 
	    }

	    private double getInventoryScore(Warehouse w, String skuId) {
	        Inventory inv = w.getInventory(skuId);
	        return (1.0 * inv.readyQty) + (0.5 * inv.inTransitQty);
	    }
}


