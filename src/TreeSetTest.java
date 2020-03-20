import com.sun.source.tree.Tree;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class TreeSetTest {

    public static class ReversedIntegerComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return -(((Integer) o1).compareTo((Integer) o2));
        }
        public boolean equals(Object o1, Object o2) {
            return ((Integer) o1).compareTo((Integer) o2) == 0;
        }
    }

    TreeSet<Integer> ts = new TreeSet<>();
    Object objArray[] = new Object[1000];

    ArrayList<Integer> list = new ArrayList<>();
    Random random = new Random();

    @Test
    public void test_Constructor() {
        // Test for method java.util.TreeSet()
        assertTrue(new TreeSet().isEmpty(), "Did not construct correct TreeSet");
    }

    @Test
    public void test_ConstructorLjava_util_Collection() {
        // Test for method java.util.TreeSet(java.util.Collection)

        list.clear();
        for (int i = 0; i < 100; i++) {
            Integer inte = random.nextInt(100);
            if (!list.contains(inte))
                list.add(inte);
        }
        TreeSet myTreeSet = new TreeSet();
        myTreeSet.addAll(list);
        assertTrue(myTreeSet.size() == list.size(),
                "TreeSet incorrect size");
        for (int counter = 0; counter < list.size(); counter++)
            assertTrue(myTreeSet
                    .contains(list.get(counter)), "TreeSet does not contain correct elements");
    }

    @Test
    public void test_addLjava_lang_Object() {
        // Test for method boolean java.util.TreeSet.add(java.lang.Object)
        ts.add(-8);
        list.clear();

        assertTrue(ts.contains(new Integer(-8)), "Failed to add Object");

        assertFalse(ts.add(-8));
    }

    @Test
    public void test_clear() {
        // Test for method void java.util.TreeSet.clear()
        list.clear();
        ts.clear();
        list.add(2);
        ts.addAll(list);
        ts.clear();
        assertEquals(0, ts.size());
        assertTrue(!ts.contains(list.get(0)), "Found element in cleared set");
    }

    @Test
    void subSet() {
        // Creating an empty TreeSet
        TreeSet<Integer> tree_set = new TreeSet<Integer>();

        // Adding the elements using add()
        tree_set.add(5);
        tree_set.add(1);
        tree_set.add(50);
        tree_set.add(10);
        tree_set.add(20);
        tree_set.add(6);
        tree_set.add(20);
        tree_set.add(18);
        tree_set.add(9);
        tree_set.add(30);

        // Creating the subset tree
        TreeSet<Integer> sub_set = new TreeSet<Integer>();

        // Limiting the values till 5
        sub_set = (TreeSet<Integer>)tree_set.subSet(6, 30);

        // Creating an Iterator
        Iterator iterate;
        iterate = sub_set.iterator();

        // Displaying the tree set data
        System.out.println("The resultant values within the sub set: ");

        // Iterating through the subset
        while (iterate.hasNext()) {
            System.out.println(iterate.next() + " ");
        }
    }

    @Test
    void test_clone() {
        list.clear();
        for (int i = 0; i < 100; i++) {
            Integer inte = random.nextInt(100);
            if (!list.contains(inte))
                list.add(inte);
        }
        ts.addAll(list);
        TreeSet s = (TreeSet) ts.clone();
        Iterator i = ts.iterator();
        while (i.hasNext())
            assertTrue(s
                    .contains(i.next()), "Clone failed to copy all elements");
    }

    @Test
    void test_comparator() {
        // Test for method java.util.Comparator java.util.TreeSet.comparator()
        ReversedIntegerComparator comp = new ReversedIntegerComparator();
        TreeSet myTreeSet = new TreeSet(comp);
        assertTrue(myTreeSet.comparator() == comp,
                "Answered incorrect comparator");
    }

    @Test
    void headSet() {
        // Test for method java.util.SortedSet
        // java.util.TreeSet.headSet(java.lang.Object)
        list.clear();
        ts.clear();
        for (int i = 0; i < 200; i++) {
            list.add(i);
        }
        ts.addAll(list);
        Set s = ts.headSet(100);
        assertEquals(100, s.size());
        for (int i = 0; i < 100; i++)
            assertTrue(s.contains(list.get(i)), "Returned incorrect set");

    }

    @Test
    void tailSet() {
        list.clear();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        ts.addAll(list);

        Set s = ts.tailSet(new Integer(900));
        System.out.println(list);
        assertEquals(100, s.size());
        for (int i = 900; i < list.size(); i++)
            assertTrue(s.contains(list.get(i)), "Returned incorrect set");
    }

    @Test
    void first() {
        // Test for method java.lang.Object java.util.TreeSet.first()
        list.clear();
        for (int i = 0; i < 100; i++) {
            Integer inte = random.nextInt(100);
            if (!list.contains(inte))
                list.add(inte);
        }
        Collections.sort(list);
        ts.addAll(list);
        assertEquals(ts.first(), list.get(0),
                "Returned incorrect first element");
    }

    @Test
    void last() {
        list.clear();
        ts.clear();
        for (int i = 0; i < 100; i++) {
            Integer inte = random.nextInt(100);
            if (!list.contains(inte))
                list.add(inte);
        }
        ts.addAll(list);
        assertEquals(ts.last() == list.size() - 1, false);
    }

    @Test
    void size() {
        list.clear();
        ts.clear();
        for (int i = 0; i < 100; i++) {
            Integer inte = random.nextInt(100);
            if (!list.contains(inte))
                list.add(inte);
        }
        ts.addAll(list);
        assertEquals(ts.size() == list.size(), true);
    }

    @Test
    void isEmpty() {
        list.clear();
        ts.clear();
        ts.addAll(list);
        assertTrue(ts.isEmpty(), "Returned false");
    }

    @Test
    void contains() {
        // Test for method boolean java.util.TreeSet.contains(java.lang.Object)
        list.clear();
        ts.clear();
        for (int i = 0; i < 100; i++) {
            Integer inte = random.nextInt(100);
            if (!list.contains(inte))
                list.add(inte);
        }
        ts.addAll(list);
        assertTrue(ts
                .contains(list.get(list.size() / 2)), "Returned false for valid Object");
        assertTrue(!ts
                .contains(-9), "Returned true for invalid Object");
        try {
            ts.contains(new Object());
        } catch (ClassCastException e) {
            // Correct
            return;
        }
        fail("Failed to throw exception when passed invalid element");
    }

    @Test
    void iterator() {
        list.clear();
        for (int i = 0; i < 100; i++) {
            Integer inte = random.nextInt(100);
            if (!list.contains(inte))
                list.add(inte);
        }
        ts.clear();
        ts.addAll(list);
        Collections.sort(list);

        Iterator<Integer> i = ts.iterator();

        while (i.hasNext())
            list.remove(i.next());
        assertEquals(list.size(), 0);
    }


    @Test
    void toArray() {
        Integer[] arr = new Integer[100];
        int j = 0;
        for (int i = 0; i < 200; i += 2) {
            arr[j] = i;
            j++;
        }
        ts.clear();
        List<Integer> lista = Arrays.asList(arr);
        ts.addAll(lista);
        Object[] newArr = ts.toArray();
        for (int i = 0; i < 100; i++) {
            assertEquals(newArr[i], arr[i]);
        }


    }

    @Test
    void testToArray() {
        TreeSet<String>
                set = new TreeSet<String>();

        // Use add() method to add
        // elements into the TreeSet
        set.add("Welcome");
        set.add("To");
        set.add("Geeks");
        set.add("For");
        set.add("Geeks");
        Object[] arr = new String[5];
        arr = set.toArray(arr);
        for (int j = 0; j < arr.length; j++)
            System.out.println(arr[j]);
    }

    @Test
    void add() {
        ts.clear();
        list.clear();
        ts.add(5);
        assertTrue(ts.contains(5), "Failed to add object");
    }

    @Test
    void remove() {
        ts.clear();
        list.clear();
        ts.add(15);
        ts.remove(15);
        assertTrue(ts.isEmpty(), "Failed to remove object");
    }

    @Test
    void containsAll() {
        // Creating an empty set
        TreeSet<String>
                set = new TreeSet<String>();

        // Use add() method to
        // add elements in the set
        set.add("Geeks");
        set.add("for");
        set.add("Geeks");
        set.add("10");
        set.add("20");

        // prints the set
        System.out.println("TreeSet 1: "
                + set);

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
                + set2);

        // Check if the set
        // contains same elements
        assertTrue(set.containsAll(set2));
    }



    @Test
    void addAll() {
        TreeSet s = new TreeSet();
        list.clear();
        for(int i = 0; i < 100; i++){
            Integer inte = random.nextInt(100);
            if(!list.contains(inte))
                list.add(inte);
        }
        s.addAll(list);
        assertTrue(s.size() == list.size(), "Incorrect size after add");
        Iterator i = list.iterator();
        while (i.hasNext())
            assertTrue(s.contains(i.next()), "Returned incorrect set");
    }

    @Test
    void retainAll() {
        try {

            // Creating object of TreeSet<Integer>
            TreeSet<Integer>
                    set1 = new TreeSet<Integer>();

            // Populating set1
            set1.add(1);
            set1.add(2);
            set1.add(3);
            set1.add(4);
            set1.add(5);

            // print set1
            System.out.println("TreeSet before "
                    + "retainAll() operation : "
                    + set1);

            // Creating another object of  TreeSet<Integer>
            TreeSet<Integer>
                    set2 = new TreeSet<Integer>();
            set2.add(1);
            set2.add(2);
            set2.add(3);

            // print set2
            System.out.println("Collection Elements"
                    + " to be retained : "
                    + set2);

            // Removing elements from set
            // specified in set2
            // using retainAll() method
            set1.retainAll(set2);

            // print set1
            System.out.println("TreeSet after "
                    + "retainAll() operation : "
                    + set1);
        }

        catch (NullPointerException e) {
            System.out.println("Exception thrown : " + e);
        }
    }

    @Test
    void removeAll() {
        try {
            // Creating object of TreeSet<Integer>
            TreeSet<Integer>
                    set1 = new TreeSet<Integer>();
            // Populating set1
            set1.add(1);
            set1.add(2);
            set1.add(3);
            set1.add(4);
            set1.add(5);
            // print set1
            System.out.println("TreeSet before "
                    + "removeAll() operation : "
                    + set1);
            // Creating another object of  TreeSet<Integer>
            TreeSet<Integer>
                    set2 = new TreeSet<Integer>();
            set2.add(1);
            set2.add(2);
            set2.add(3);
            // print set2
            System.out.println("Collection Elements"
                    + " to be removed : "
                    + set2);
            // Removing elements from set
            // specified in set2
            // using removeAll() method
            set1.removeAll(set2);
            // print set1
            System.out.println("TreeSet after "
                    + "removeAll() operation : "
                    + set1);
        }
        catch (NullPointerException e) {
            System.out.println("Exception thrown : " + e);
        }
    }




    @Test
    void clear() {
        ts.clear();
        list.clear();
        assertEquals(ts.size(), 0, "Returned non-zero size after clear");
    }

   /* @Test
    void descendingSet(){
        try {

            // create tree set object
            TreeSet<String> treeadd = new TreeSet<String>();

            // populate the TreeSet using add() method
            treeadd.add("A");
            treeadd.add("B");
            treeadd.add("C");
            treeadd.add("D");

            // Print the TreeSet
            System.out.println("TreeSet: " + treeadd);

            // getting the reverse order view of element
            // using descendingSet() method
            TreeSet<String>
                    treereverse = (TreeSet<String>) treeadd.descendingSet();

            // getting iterated view of NavigableSet
            Iterator<String> iterator = treereverse.descendingIterator();

            System.out.println("\nValues using DescendingSet:");

            // printing the interated value
            while (iterator.hasNext()) {
                System.out.println("Value : "
                        + iterator.next());
            }
        }

        catch (NullPointerException e) {

            System.out.println("Exception thrown : " + e);
        }
    }


    */
    @Test
    void floor(){
        try {

            // create tree set object
            TreeSet<Integer> treeadd = new TreeSet<Integer>();

            // populate the TreeSet using add() method
            treeadd.add(10);
            treeadd.add(20);
            treeadd.add(30);
            treeadd.add(40);

            // Print the TreeSet
            System.out.println("TreeSet: " + treeadd);

            // getting the floor value for 25
            // using floor() method
            int value = treeadd.floor(25);

            // printing the floor value
            System.out.println("Floor value for 25: "
                    + value);
        }

        catch (NullPointerException e) {
            System.out.println("Exception thrown : " + e);
        }
    }

    @Test
    void ceiling(){
        try {

            // create tree set object
            TreeSet<Integer> treeadd = new TreeSet<Integer>();

            // populate the TreeSet
            treeadd.add(10);
            treeadd.add(20);
            treeadd.add(30);
            treeadd.add(40);

            // Print the TreeSet
            System.out.println("TreeSet: " + treeadd);
            // getting ceiling value for 25
            // using ceiling() method
            int value = treeadd.ceiling(25);

            // printing  the ceiling value
            System.out.println("Ceiling value for 25: "
                    + value);
        }

        catch (NullPointerException e) {
            System.out.println("Exception thrown : " + e);
        }
    }
    @Test
    void higher() {
        TreeSet<Integer> tree = new TreeSet<>();
        tree.add(10);
        tree.add(5);
        tree.add(8);
        tree.add(1);
        tree.add(11);
        tree.add(3);
        assertTrue(tree.higher(10) == 11);
    }


}