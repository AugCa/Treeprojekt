import java.util.Iterator;
import java.util.Random;
import java.util.SortedSet;

public class Main {

    public static void main(String args[])
    {

        // Creating an empty set
        TreeSet<String>
                set = new TreeSet<String>();

        // Use add() method to
        // add elements in the set
        set.add("Geeks");
        System.out.println(set.toString());
        set.add("for");
        set.add("Geeks");
        set.add("10");
        set.add("20");

        // prints the set
        System.out.println("TreeSet 1: "
                + set.toString());

        // Creating another empty set
        TreeSet<String>
                set2 = new TreeSet<String>();

        // Use add() method to
        // add elements in the set
        set2.add("Geeks");
        set2.add("for");
        set2.add("Geeks");
        set2.add("10");
        set2.add("20");

        // prints the set
        System.out.println("TreeSet 2: "
                + set2.toString());

        // Check if the set
        // contains same elements
        System.out.println("\nDoes set 1 contains set 2: "
                + set.containsAll(set2));
    }
}
