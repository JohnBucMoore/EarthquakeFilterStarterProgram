public class DistanceFilter implements Filter{
    private Location loc;
    private double maxDist;
    public DistanceFilter(Location l, double dist) {
        loc = l;
        maxDist = dist;
    }
    public boolean satisfies(QuakeEntry qe) {
        System.out.println();
        return (qe.getLocation().distanceTo(loc) < maxDist);
    }
}
