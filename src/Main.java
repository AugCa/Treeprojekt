import java.util.Iterator;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        TreeSet<Integer> mts = new TreeSet<>();
        Random random = new Random();
        for(int i = 0; i < 20; i++){
            Integer num = random.nextInt(20);
            mts.add( num);
        }
        Iterator<Integer> itr = mts.iterator();
        while(!itr.hasNext()){
            System.out.println(itr.next());
        }

    }
}
