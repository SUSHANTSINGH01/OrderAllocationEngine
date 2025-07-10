package main;

public class Inventory {
	
	    int readyQty;
	    int inTransitQty;

	    public Inventory(int readyQty, int inTransitQty) {
	        this.readyQty = readyQty;
	        this.inTransitQty = inTransitQty;
	    }

	    public int getTotalAvailable() {
	        return readyQty + inTransitQty;
	    }

		public int getReadyQty() {
			return readyQty;
		}

		public void setReadyQty(int readyQty) {
			this.readyQty = readyQty;
		}

		public int getInTransitQty() {
			return inTransitQty;
		}

		public void setInTransitQty(int inTransitQty) {
			this.inTransitQty = inTransitQty;
		}
	      
}


