package main;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class MainClass{
	
	public static void main(String[] args){

		Map<String, Integer> skuItems = Map.of("SKU1", 5, "SKU2", 50);
		Order order = new Order("ORD00001", skuItems);

		
		List<Warehouse> warehouses = List.of(
				new Warehouse("WH1", WareHouseType.REAL, Map.of("SKU1", new Inventory(3,1), "SKU2", new Inventory(2,0)), 2, 100),
				new Warehouse("WH2", WareHouseType.POWER, Map.of(), 7, 500),
				new Warehouse("WH3", WareHouseType.REAL, Map.of("SKU1", new Inventory(2,25), "SKU2", new Inventory(25,5)), 3, 100));
				

		AllocationStrategy strategy =new MinEddStrategy(); 
	  //AllocationStrategy strategy =new MinShipmentCostStrategy();
	  //AllocationStrategy strategy =new MinSplitStrategy();
		
		
		OrderAllocationEngine orderAllocationEngine = new OrderAllocationEngine();
		Map<String, List<AllocationRecord>> result = orderAllocationEngine.allocateOrder(order,warehouses,strategy);


		for(String skuId : skuItems.keySet()) {
			
			List<AllocationRecord> records = result.getOrDefault(skuId,List.of());
			
			System.out.print(skuId + ": ");
			List<String> parts = new ArrayList<>();
			
			for(AllocationRecord r : records){
				
				String detail = r.whId + " - ";
				List<String> qty = new ArrayList<>();
				
				if(r.type==WareHouseType.POWER){
					qty.add((r.readyQty) + "");
				}
				else{
					if(r.readyQty>0){
						qty.add(r.readyQty + " Ready");
					}
					if(r.inTransitQty>0){
						qty.add(r.inTransitQty + " InTransit");
					}
				}
				
				detail+=String.join(", ", qty);
				parts.add(detail);
			}
	
			System.out.println(String.join("; ", parts));
		}
	}

}
