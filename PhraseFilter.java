public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    public PhraseFilter(String w, String p) {
        where = w;
        phrase = p;
    }
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        if (where.equals("any") && title.contains(phrase)) {
            return true;
        } else if (where.equals("start") && title.substring(0, title.length()/2).contains(phrase)) {
            return true;
        } else if (where.equals("end") && title.substring(title.length()/2).contains(phrase)) {
            return true;
        } else {
            return false;
        }
    }
}
