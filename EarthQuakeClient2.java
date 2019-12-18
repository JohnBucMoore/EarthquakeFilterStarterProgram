import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        /*
        Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }

        Filter f2 = new MagnitudeFilter(4.0, 5.0);
        Filter f3 = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> magMatch = filter(list, f2);
        ArrayList<QuakeEntry> magDepMatch = filter(magMatch, f3);
        for (QuakeEntry quake : magDepMatch) {
            System.out.println(quake);
        }
        */
        Location japan = new Location(35.42, 139.43);
        Filter f4 = new DistanceFilter(japan, 10000000.0);
        Filter f5 = new PhraseFilter("any", "Japan");
        ArrayList<QuakeEntry> locDistQuakes = filter(list, f4);
        ArrayList<QuakeEntry> phraseLocDist = filter(locDistQuakes, f5);
        for (QuakeEntry qe : phraseLocDist) {
            System.out.println(qe);
        }
        /*
        for (QuakeEntry q : list) {
            System.out.println(q.getLocation().distanceTo(japan));
        }
         */
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void test() {
        quakesWithFilter();
    }
/*
    public void testMatchAllFilter() {
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter();
    }


 */
    public static void main(String[] args) {
        EarthQuakeClient2 ec = new EarthQuakeClient2();
        ec.test();
    }
}
