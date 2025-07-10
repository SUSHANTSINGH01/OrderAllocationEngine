package main;

import java.util.Comparator;

	public class MinShipmentCostStrategy implements AllocationStrategy{
		
		@Override
	    public Comparator<Warehouse> getComparator(String skuId){
	        return Comparator
	            .comparingInt((Warehouse w) -> w.cost)
	            .thenComparingDouble(w -> getInventoryScore(w,skuId));
	    }

	    private double getInventoryScore(Warehouse w, String skuId){
	        Inventory inv = w.getInventory(skuId);
	        return (inv.readyQty*1.0)+(inv.inTransitQty*0.5);
	    }
}


