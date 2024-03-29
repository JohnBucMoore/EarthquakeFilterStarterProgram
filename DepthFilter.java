public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    private String name;
    public DepthFilter(double min, double max, String n) {
        minDepth = min;
        maxDepth = max;
        name = n;
    }
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth);
    }
    public String getName() {
        return name;
    }
}
