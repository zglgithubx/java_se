import java.util.*;

public class Eliminate<T> {
    public static <T> List<T> uquenlist(Class<T> tClass,int x) throws IllegalAccessException, InstantiationException {
        List<T> t=new ArrayList<T>();
        for(int i=0;i<x;i++){
            t.add(tClass.newInstance());
        }
//        List<T> newlist = new ArrayList<T>();
//        Set<T> set = new HashSet<T>();
//        for(Iterator<T> iter = list.iterator(); iter.hasNext(); ){
//            T element = iter.next();
//            if(set.add(element)){
//                newlist.add(element);
//            }
//        }
//        return  newlist;
        return t;
    }

}
