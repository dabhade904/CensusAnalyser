package censusanalyser;

import java.util.Comparator;

public class LoadObjectCampratorEx implements Comparator {
    @Override
    public int compare(Object t, Object o) {
        if(t.toString().compareTo(o.toString())<0) {
            return -1;
        }
        if(t.toString().compareTo(o.toString())>0) {
            return 1;
        }
        if(t.toString().compareTo(o.toString())==0) {
            return 0;
        }
    return 0;
    }
}
