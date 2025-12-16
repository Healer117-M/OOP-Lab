class Shop {
    private String id;
    private double price;
    private boolean available;
    
    public Shop(String id) {
        this.id = id;
        this.price = PlotType.COMM_SHOP.getBasePrice();
        this.available = true;
    }
    
    public boolean book() {
        if (!available) return false;
        available = false;
        return true;
    }
    
    public void cancel() {
        available = true;
    }
    
    public String getSummary() {
        return String.format("%s | %,.0f PKR | %s", 
            id, price, available ? "Available" : "Booked");
    }
    
    public boolean isAvailable() { return available; }
    public String getId() { return id; }
}
