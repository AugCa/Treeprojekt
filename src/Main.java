import java.util.Iterator;
import java.util.Random;
import java.util.SortedSet;

public class Main {

    public static void main(String[] args){
        TreeSet<Integer> mts = new TreeSet<>();
        Random random = new Random();
        for(int i = 0; i < 20; i++){
            Integer num = random.nextInt(20);
            mts.add( num);
        }
        mts.toArray();
        Iterator<Integer> itr = mts.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        System.out.println("Subset test");
        SortedSet<Integer> subset = mts.subSet(4, 15);
        Iterator<Integer> itr2 = subset.iterator();
        while(itr2.hasNext()){
            System.out.println(itr2.next());
        }

    }
}
