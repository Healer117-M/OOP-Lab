class PlotDemo {
    public static void main(String[] args) {
        System.out.println("=== PLOT DEMO ===\n");
        
        // Regular plot
        Plot regularPlot = new Plot("1-001", PlotType.RES_5_MARLA, 
            ShapeType.RECTANGLE, new double[]{25, 50});
        System.out.println("Regular Plot: " + regularPlot.getSummary());
        
        // Corner plot
        CornerPlot cornerPlot = new CornerPlot("3-004", PlotType.RES_1_KANAL, 
            ShapeType.TRAPEZOID, new double[]{40, 50, 35}, 10);
        System.out.println("Corner Plot: " + cornerPlot.getSummary());
        
        // Booking
        System.out.println("\nBooking regular plot...");
        regularPlot.book();
        System.out.println("After booking: " + regularPlot.getSummary());
        
        // Try to book again
        System.out.println("\nTrying to book again...");
        boolean success = regularPlot.book();
        System.out.println("Booking " + (success ? "successful" : "failed"));
        
        // Cancel
        System.out.println("\nCancelling booking...");
        regularPlot.cancel();
        System.out.println("After cancellation: " + regularPlot.getSummary());
    }
}

class CityDemo {
    public static void main(String[] args) {
        System.out.println("=== LAHORE HOUSING SOCIETY MANAGEMENT SYSTEM ===\n");
        
        // Create Lahore city
        CityHousing lahore = new CityHousing("Lahore", 5);
        
        // Create LDA Avenue 1
        HousingSociety lda1 = new HousingSociety("LDA Avenue 1", 5);
        int[] defaultStreets = {10, 11, 12, 13, 14};
        
        // Add blocks to LDA Avenue 1
        Block blockA1 = new Block("Block A", defaultStreets);
        blockA1.addPark(new Park("Central Park", ShapeType.RECTANGLE, new double[]{100, 150}));
        blockA1.addPark(new Park("Kids Park", ShapeType.RECTANGLE, new double[]{50, 75}));
        for (int i = 1; i <= 15; i++) {
            blockA1.getMarket().addShop(new Shop("Shop-" + String.format("%02d", i)));
        }
        lda1.addBlock(blockA1);
        
        Block blockB1 = new Block("Block B", defaultStreets);
        blockB1.addPark(new Park("Green Park", ShapeType.RECTANGLE, new double[]{80, 120}));
        for (int i = 1; i <= 12; i++) {
            blockB1.getMarket().addShop(new Shop("Shop-" + String.format("%02d", i)));
        }
        lda1.addBlock(blockB1);
        
        Block blockC1 = new Block("Block C", defaultStreets);
        blockC1.addPark(new Park("Family Park", ShapeType.RECTANGLE, new double[]{90, 130}));
        for (int i = 1; i <= 18; i++) {
            blockC1.getMarket().addShop(new Shop("Shop-" + String.format("%02d", i)));
        }
        lda1.addBlock(blockC1);
        
        // Create LDA Avenue 2
        HousingSociety lda2 = new HousingSociety("LDA Avenue 2", 5);
        
        Block blockA2 = new Block("Block A", defaultStreets);
        blockA2.addPark(new Park("Main Park", ShapeType.RECTANGLE, new double[]{110, 160}));
        for (int i = 1; i <= 14; i++) {
            blockA2.getMarket().addShop(new Shop("Shop-" + String.format("%02d", i)));
        }
        lda2.addBlock(blockA2);
        
        Block blockB2 = new Block("Block B", defaultStreets);
        blockB2.addPark(new Park("Community Park", ShapeType.RECTANGLE, new double[]{85, 125}));
        for (int i = 1; i <= 16; i++) {
            blockB2.getMarket().addShop(new Shop("Shop-" + String.format("%02d", i)));
        }
        lda2.addBlock(blockB2);
        
        Block blockC2 = new Block("Block C", defaultStreets);
        blockC2.addPark(new Park("Sports Park", ShapeType.RECTANGLE, new double[]{95, 140}));
        for (int i = 1; i <= 20; i++) {
            blockC2.getMarket().addShop(new Shop("Shop-" + String.format("%02d", i)));
        }
        lda2.addBlock(blockC2);
        
        // Add societies to city
        lahore.addSociety(lda1);
        lahore.addSociety(lda2);
        
        // Print initial reports
        lahore.printCityReport();
        
        // Workflow demonstration
        System.out.println("\n\n=== WORKFLOW DEMONSTRATION ===");
        
        // 1. Book plot 3-007 in LDA Avenue 1, Block A
        System.out.println("\n1. Booking plot 3-007 in LDA Avenue 1, Block A...");
        boolean booked = lahore.bookPlot("LDA Avenue 1", "Block A", "3-007");
        System.out.println("Booking " + (booked ? "successful" : "failed"));
        blockA1.printStreetLayout();
        
        // 2. Try to book same plot again
        System.out.println("\n2. Attempting to book the same plot again...");
        booked = lahore.bookPlot("LDA Avenue 1", "Block A", "3-007");
        System.out.println("Booking " + (booked ? "successful (ERROR!)" : "failed (correct)"));
        
        // 3. Cancel booking
        System.out.println("\n3. Cancelling booking for plot 3-007...");
        boolean cancelled = lahore.cancelBooking("LDA Avenue 1", "Block A", "3-007");
        System.out.println("Cancellation " + (cancelled ? "successful" : "failed"));
        blockA1.printStreetLayout();
        
        // 4. Search for first available RES_1_KANAL corner plot in LDA Avenue 2
        System.out.println("\n4. Searching for first available RES_1_KANAL corner plot in LDA Avenue 2...");
        String result = lahore.findFirstAvailableKanalCorner();
        System.out.println("Result: " + result);
        
        // 5. List first three vacant shops in Block C market of LDA Avenue 1
        System.out.println("\n5. First three vacant shops in LDA Avenue 1, Block C market:");
        Shop[] vacantShops = blockC1.getMarket().findVacantShops();
        for (int i = 0; i < Math.min(3, vacantShops.length); i++) {
            System.out.println("   " + vacantShops[i].getSummary());
        }
        
        // Print amenities for a block
        System.out.println("\n6. Amenities in LDA Avenue 1, Block A:");
        blockA1.printAmenities();
        
        System.out.println("\n\n=== DEMO COMPLETE ===");
    }
}