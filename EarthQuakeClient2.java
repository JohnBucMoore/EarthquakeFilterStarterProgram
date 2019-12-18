import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> getData() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        return list;
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
        ArrayList<QuakeEntry> list  = getData();
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
        Filter f4 = new DistanceFilter(japan, 10000000.0, "Distance Filter");
        Filter f5 = new PhraseFilter("any", "Japan", "Phrase Filter");
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
        ArrayList<QuakeEntry> list  = getData();
        dumpCSV(list);
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

    public void testMatchAllFilter() {
        ArrayList<QuakeEntry> list = getData();
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0,2.0, "Magnitude Filter");
        Filter f2 = new DepthFilter(-100000.0, -10000.0, "Depth Filter");
        Filter f3 = new PhraseFilter("any", "a", "Phrase Filter");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> answer = filter(list, maf);
        for (QuakeEntry q : answer) {
            System.out.println(q);
        }
    }

    public void testMatchAllFilter2() {
        //ArrayList<QuakeEntry> answer = new ArrayList<>();
        ArrayList<QuakeEntry> list = getData();
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0,3.0, "Magnitude Filter");
        Location tulsa = new Location(36.1314,-95.9372);
        Filter f2 = new DistanceFilter(tulsa,10000000.0, "Distance Filter");
        Filter f3 = new PhraseFilter("any","Ca", "Phrase Filter");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> answer = filter(list, maf);
        /*
        for (QuakeEntry qe : list) {
            if (maf.satisfies(qe)) {
                answer.add(qe);
            }
        }

         */
        for (QuakeEntry q : answer) {
            System.out.println(q);
        }
        System.out.println(maf.getName());
    }



    public static void main(String[] args) {
        EarthQuakeClient2 ec = new EarthQuakeClient2();
        ec.testMatchAllFilter2();
    }
}
