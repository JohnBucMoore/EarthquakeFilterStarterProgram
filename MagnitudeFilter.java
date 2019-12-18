public class MagnitudeFilter implements Filter{
    private double minMag;
    private double maxMag;
    private String name;
    public MagnitudeFilter(double min, double max, String n) {
        minMag = min;
        maxMag = max;
        name = n;
    }

    public boolean satisfies(QuakeEntry qe) {
        return (qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag);
    }

    public String getName() {
        return name;
    }
}
