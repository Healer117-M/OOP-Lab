class HousingSociety {
    private String name;
    private Block[] blocks;
    private int blockCount;
    
    public HousingSociety(String name, int initialCapacity) {
        this.name = name;
        this.blocks = new Block[initialCapacity];
        this.blockCount = 0;
    }
    
    public void addBlock(Block block) {
        if (blockCount >= blocks.length) {
            // Manual array growth
            Block[] newBlocks = new Block[blocks.length * 2];
            for (int i = 0; i < blockCount; i++) {
                newBlocks[i] = blocks[i];
            }
            blocks = newBlocks;
        }
        blocks[blockCount++] = block;
    }
    
    public void addBlockWithCustomStreets(String blockName, int[] streetLengths) {
        Block block = new Block(blockName, streetLengths);
        addBlock(block);
    }
    
    public Block findBlock(String blockName) {
        for (int i = 0; i < blockCount; i++) {
            if (blocks[i].getName().equals(blockName)) {
                return blocks[i];
            }
        }
        return null;
    }
    
    public boolean bookPlot(String blockName, String plotId) {
        Block block = findBlock(blockName);
        if (block != null) {
            return block.bookPlot(plotId);
        }
        return false;
    }
    
    public boolean cancelBooking(String blockName, String plotId) {
        Block block = findBlock(blockName);
        if (block != null) {
            return block.cancelBooking(plotId);
        }
        return false;
    }
    
    public int getTotalsByType(PlotType type) {
        int count = 0;
        for (int i = 0; i < blockCount; i++) {
            Plot[][] plots = blocks[i].getPlots();
            for (int j = 0; j < plots.length; j++) {
                for (int k = 0; k < plots[j].length; k++) {
                    if (plots[j][k].getType() == type) count++;
                }
            }
        }
        return count;
    }
    
    public int getAvailableByType(PlotType type) {
        int count = 0;
        for (int i = 0; i < blockCount; i++) {
            count += blocks[i].countAvailableByType(type);
        }
        return count;
    }
    
    public void printReport() {
        System.out.println("\n========================================");
        System.out.println("SOCIETY: " + name);
        System.out.println("========================================");
        
        for (int i = 0; i < blockCount; i++) {
            System.out.println("\n--- " + blocks[i].getName() + " ---");
            blocks[i].printStreetLayout();
        }
    }
    
    public String getName() { return name; }
    public Block[] getBlocks() { return blocks; }
    public int getBlockCount() { return blockCount; }
}