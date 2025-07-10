package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderAllocationEngine {

	    public Map<String, List<AllocationRecord>> allocateOrder(Order order, List<Warehouse> warehouses, AllocationStrategy strategy) {
	       
	    	Map<String, List<AllocationRecord>> allocateOrderResponse = new HashMap<>();

	        for(Map.Entry<String, Integer> entry : order.items.entrySet()){
	        	
	            String skuId = entry.getKey();
	            int requiredQty = entry.getValue();
	            int remainingQty = requiredQty;
	            
	            List<Warehouse> sortedWareHouses = new ArrayList<>(warehouses);
	            sortedWareHouses.sort(strategy.getComparator(skuId));

	            List<AllocationRecord> skuAllocations = new ArrayList<>();

	            for(Warehouse wh : sortedWareHouses) {
	                
	            	if(remainingQty<=0) 
	            	break;

	                Inventory inv=wh.getInventory(skuId);
	                int ready=0;
	                int inTransit=0;

	                if(wh.type==WareHouseType.REAL){
	              
	                    ready=Math.min(inv.readyQty, remainingQty);
	                    remainingQty -= ready;

	                    if(ready>0){
	                         inv.setReadyQty(inv.readyQty-ready);
	                    }
	                    
	                    inTransit = Math.min(inv.inTransitQty, remainingQty);
	                    remainingQty -= inTransit;
	                    
	                    if(inTransit>0){
	                    	inv.setInTransitQty(inv.inTransitQty-inTransit);	                    
	                    }
	                    
	                    if(ready>0 || inTransit>0) {
	                    	skuAllocations.add(new AllocationRecord(skuId, wh.id, wh.type, ready, inTransit));
	                    }
	                 }

					 else if (wh.type==WareHouseType.POWER && remainingQty>0){
						skuAllocations.add(new AllocationRecord(skuId, wh.id, wh.type, remainingQty, 0));
	                    remainingQty=0;
	                    break;
	                }
	            }

	            allocateOrderResponse.put(skuId, skuAllocations);
	        }

	        return allocateOrderResponse;
	    }
}
