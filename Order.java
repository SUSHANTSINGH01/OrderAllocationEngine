package main;

import java.util.Map;

public class Order{
	
	String orderId;
    Map<String, Integer> items;

    public Order(String orderId, Map<String, Integer> items){
        this.orderId = orderId;
        this.items = items;
    }

    public Map<String, Integer> getItems(){
        return items;
    }
    
}
