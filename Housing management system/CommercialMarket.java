class CommercialMarket {
    private Shop[] shops;
    private int shopCount;
    
    public CommercialMarket(int capacity) {
        shops = new Shop[capacity];
        shopCount = 0;
    }
    
    public void addShop(Shop shop) {
        if (shopCount >= shops.length) {
            // Manual array growth
            Shop[] newShops = new Shop[shops.length * 2];
            for (int i = 0; i < shopCount; i++) {
                newShops[i] = shops[i];
            }
            shops = newShops;
        }
        shops[shopCount++] = shop;
    }
    
    public Shop[] findVacantShops() {
        // Two-pass filtering
        int count = 0;
        for (int i = 0; i < shopCount; i++) {
            if (shops[i].isAvailable()) count++;
        }
        
        Shop[] vacant = new Shop[count];
        int idx = 0;
        for (int i = 0; i < shopCount; i++) {
            if (shops[i].isAvailable()) {
                vacant[idx++] = shops[i];
            }
        }
        return vacant;
    }
    
    public int countVacant() {
        int count = 0;
        for (int i = 0; i < shopCount; i++) {
            if (shops[i].isAvailable()) count++;
        }
        return count;
    }
    
    public void printShops() {
        System.out.println("  Market Shops:");
        for (int i = 0; i < shopCount; i++) {
            System.out.println("    " + shops[i].getSummary());
        }
    }
    
    public int getShopCount() { return shopCount; }
}