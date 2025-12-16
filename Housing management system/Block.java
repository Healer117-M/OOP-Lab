class Block {
    private String name;
    private Plot[][] plots;
    private Park[] parks;
    private int parkCount;
    private CommercialMarket market;
    
    public Block(String name, int[] streetLengths) {
        this.name = name;
        this.plots = new Plot[streetLengths.length][];
        this.parks = new Park[2];
        this.parkCount = 0;
        this.market = new CommercialMarket(20);
        
        initializePlots(streetLengths);
    }
    
    private void initializePlots(int[] streetLengths) {
        for (int street = 0; street < streetLengths.length; street++) {
            int length = streetLengths[street];
            plots[street] = new Plot[length];
            
            // Determine plot type based on street (1-indexed)
            PlotType plotType;
            ShapeType shape;
            double[] dims;
            
            switch (street + 1) {
                case 1:
                    plotType = PlotType.RES_5_MARLA;
                    shape = ShapeType.RECTANGLE;
                    dims = new double[]{25, 50};
                    break;
                case 2:
                    plotType = PlotType.RES_10_MARLA;
                    shape = ShapeType.RECTANGLE;
                    dims = new double[]{30, 60};
                    break;
                case 3:
                    plotType = PlotType.RES_1_KANAL;
                    shape = ShapeType.TRAPEZOID;
                    dims = new double[]{40, 50, 35};
                    break;
                case 4:
                    plotType = PlotType.COMM_SHOP;
                    shape = ShapeType.RECTANGLE;
                    dims = new double[]{20, 40};
                    break;
                case 5:
                    plotType = PlotType.COMM_OFFICE;
                    shape = ShapeType.RECTANGLE;
                    dims = new double[]{25, 50};
                    break;
                default:
                    plotType = PlotType.RES_5_MARLA;
                    shape = ShapeType.RECTANGLE;
                    dims = new double[]{25, 50};
            }
            
            for (int plotNum = 0; plotNum < length; plotNum++) {
                String id = String.format("%d-%03d", street + 1, plotNum + 1);
                
                // Every 5th plot is PARKING
                if ((plotNum + 1) % 5 == 0) {
                    plots[street][plotNum] = new Plot(id, PlotType.PARKING, 
                        ShapeType.RECTANGLE, new double[]{10, 20});
                }
                // Every 4th plot on streets 1-3 is CornerPlot
                else if (street < 3 && (plotNum + 1) % 4 == 0) {
                    plots[street][plotNum] = new CornerPlot(id, plotType, shape, dims, 10);
                }
                else {
                    plots[street][plotNum] = new Plot(id, plotType, shape, dims);
                }
            }
        }
    }
    
    public void addPark(Park park) {
        if (parkCount < parks.length) {
            parks[parkCount++] = park;
        }
    }
    
    public Plot findPlot(String id) {
        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                if (plots[i][j].getId().equals(id)) {
                    return plots[i][j];
                }
            }
        }
        return null;
    }
    
    public boolean bookPlot(String id) {
        Plot plot = findPlot(id);
        if (plot != null) {
            return plot.book();
        }
        return false;
    }
    
    public boolean cancelBooking(String id) {
        Plot plot = findPlot(id);
        if (plot != null) {
            plot.cancel();
            return true;
        }
        return false;
    }
    
    public int countAvailable() {
        int count = 0;
        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                if (plots[i][j].isAvailable()) count++;
            }
        }
        return count;
    }
    
    public int countAvailableByType(PlotType type) {
        int count = 0;
        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                if (plots[i][j].getType() == type && plots[i][j].isAvailable()) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public void printStreetLayout() {
        System.out.println("Street Layout for " + name + ":");
        for (int i = 0; i < plots.length; i++) {
            System.out.print("  Street " + (i + 1) + ": ");
            for (int j = 0; j < plots[i].length; j++) {
                System.out.print("[" + (plots[i][j].isAvailable() ? "A" : "X") + "] ");
            }
            System.out.println();
        }
    }
    
    public void printPlotDetails() {
        System.out.println("Plot Details for " + name + ":");
        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                System.out.println("  " + plots[i][j].getSummary());
            }
        }
    }
    
    public void printAmenities() {
        System.out.println("Amenities in " + name + ":");
        System.out.println("  Parks:");
        for (int i = 0; i < parkCount; i++) {
            System.out.println("    " + parks[i].getSummary());
        }
        System.out.println("  Commercial Market: " + market.getShopCount() + 
            " shops, " + market.countVacant() + " vacant");
    }
    
    public String getName() { return name; }
    public CommercialMarket getMarket() { return market; }
    public Plot[][] getPlots() { return plots; }
}
