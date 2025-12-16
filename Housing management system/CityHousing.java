class CityHousing {
    private String cityName;
    private HousingSociety[] societies;
    private int societyCount;
    
    public CityHousing(String cityName, int initialCapacity) {
        this.cityName = cityName;
        this.societies = new HousingSociety[initialCapacity];
        this.societyCount = 0;
    }
    
    public void addSociety(HousingSociety society) {
        if (societyCount >= societies.length) {
            // Manual array growth
            HousingSociety[] newSocieties = new HousingSociety[societies.length * 2];
            for (int i = 0; i < societyCount; i++) {
                newSocieties[i] = societies[i];
            }
            societies = newSocieties;
        }
        societies[societyCount++] = society;
    }
    
    public HousingSociety findSociety(String name) {
        for (int i = 0; i < societyCount; i++) {
            if (societies[i].getName().equals(name)) {
                return societies[i];
            }
        }
        return null;
    }
    
    public boolean bookPlot(String societyName, String blockName, String plotId) {
        HousingSociety society = findSociety(societyName);
        if (society != null) {
            return society.bookPlot(blockName, plotId);
        }
        return false;
    }
    
    public boolean cancelBooking(String societyName, String blockName, String plotId) {
        HousingSociety society = findSociety(societyName);
        if (society != null) {
            return society.cancelBooking(blockName, plotId);
        }
        return false;
    }
    
    public String findFirstAvailableKanalCorner() {
        for (int i = 0; i < societyCount; i++) {
            HousingSociety society = societies[i];
            for (int j = 0; j < society.getBlockCount(); j++) {
                Block block = society.getBlocks()[j];
                Plot[][] plots = block.getPlots();
                
                for (int k = 0; k < plots.length; k++) {
                    for (int l = 0; l < plots[k].length; l++) {
                        Plot plot = plots[k][l];
                        if (plot instanceof CornerPlot && 
                            plot.getType() == PlotType.RES_1_KANAL && 
                            plot.isAvailable()) {
                            return String.format("%s > %s > Plot %s (%s CORNER, area %.1f sq, %,.0f PKR)",
                                society.getName(), block.getName(), plot.getId(), 
                                plot.getType(), plot.getArea(), plot.price);
                        }
                    }
                }
            }
        }
        return "No available RES_1_KANAL corner plots found.";
    }
    
    public void printCityReport() {
        System.out.println("\n########################################");
        System.out.println("CITY: " + cityName);
        System.out.println("########################################");
        
        for (int i = 0; i < societyCount; i++) {
            societies[i].printReport();
        }
    }
    
    public String getCityName() { return cityName; }
}