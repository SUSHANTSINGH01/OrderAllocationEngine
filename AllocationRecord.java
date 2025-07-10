package main;

public class AllocationRecord {

	    String skuId;
	    String whId;
	    WareHouseType type;
	    int readyQty;
	    int inTransitQty;

	    public AllocationRecord(String skuId, String whId, WareHouseType type, int readyQty, int inTransitQty) {
	        this.skuId = skuId;
	        this.whId = whId;
	        this.type = type;
	        this.readyQty = readyQty;
	        this.inTransitQty = inTransitQty;
	    }
}


