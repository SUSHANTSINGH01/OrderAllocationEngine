package main;

import java.util.Map;

public class Warehouse {
	
	String id;
    WareHouseType type;
    Map<String, Inventory> inventory;
    int edd;  
    int cost;

    public Warehouse(String id, WareHouseType type, Map<String, Inventory> inventory, int edd, int cost) {
        this.id = id;
        this.type = type;
        this.inventory = inventory;
        this.edd = edd;
        this.cost = cost;
    }

    public Inventory getInventory(String prdId) {
        return inventory.getOrDefault(prdId, new Inventory(0, 0));
    }

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}
    
    
}

