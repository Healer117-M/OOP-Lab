class Park {
    private String name;
    private ShapeType shape;
    private double[] dimensions;
    private double area;
    
    public Park(String name, ShapeType shape, double[] dimensions) {
        this.name = name;
        this.shape = shape;
        this.dimensions = dimensions;
        this.area = computeArea();
    }
    
    public double computeArea() {
        switch (shape) {
            case RECTANGLE:
                return dimensions[0] * dimensions[1];
            case TRAPEZOID:
                return ((dimensions[0] + dimensions[1]) / 2.0) * dimensions[2];
            case L_SHAPE:
                return (dimensions[0] * dimensions[1]) + (dimensions[2] * dimensions[3]);
            default:
                return 0;
        }
    }
    
    public String getSummary() {
        return String.format("%s (%s, %.1f sq)", name, shape, area);
    }
}
