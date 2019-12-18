public class DistanceFilter {
    private Location loc;
    private double maxDist;
    public DistanceFilter(Location l, double dist) {
        loc = l;
        maxDist = dist;
    }
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getLocation().distanceTo(loc) < maxDist);
    }
}
