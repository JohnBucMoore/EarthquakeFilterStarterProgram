public class DistanceFilter implements Filter{
    private Location loc;
    private double maxDist;
    private String name;
    public DistanceFilter(Location l, double dist, String n) {
        loc = l;
        maxDist = dist;
        name = n;
    }
    public boolean satisfies(QuakeEntry qe) {
        System.out.println();
        return (qe.getLocation().distanceTo(loc) < maxDist);
    }
    public String getName() {
        return name;
    }
}
