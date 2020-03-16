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

    /**
     * java.util.TreeSet#TreeSet()
     */
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
        for(int i = 0; i < 100; i++){
            Integer inte = random.nextInt(100);
            if(!list.contains(inte))
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
        assertEquals( 0, ts.size());
        assertTrue(!ts.contains(list.get(0)), "Found element in cleared set");
    }
    void subSet() {
    }

    @Test
    void headSet() {
    }

    @Test
    void tailSet() {
    }

    @Test
    void first() {
    }

    @Test
    void last() {
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void contains() {
    }

    @Test
    void iterator() {
        list.clear();
        for(int i = 0; i < 100; i++){
            Integer inte = random.nextInt(100);
            if(!list.contains(inte))
                list.add(inte);
        }
        ts.clear();
        ts.addAll(list);
        Iterator i = ts.iterator();
        while (i.hasNext())
            list.remove(i.next());
        assertEquals(list.size()==0, "Returned incorrect iterator");
    }

    /**
     * java.util.TreeSet#iterator()

    public void test_iterator() {
        // Test for method java.util.Iterator java.util.TreeSet.iterator()
        TreeSet s = new TreeSet();
        s.addAll(ts);
        Iterator i = ts.iterator();
        Set as = new HashSet(Arrays.asList(objArray));
        while (i.hasNext())
            as.remove(i.next());
        assertEquals("Returned incorrect iterator", 0, as.size());
    }
     */


    @Test
    void toArray() {
    }

    @Test
    void testToArray() {
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }

    @Test
    void containsAll() {
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

    /**
     * java.util.TreeSet#addAll(java.util.Collection)

    public void test_addAllLjava_util_Collection() {
        // Test for method boolean
        // java.util.TreeSet.addAll(java.util.Collection)
        TreeSet s = new TreeSet();
        s.addAll(ts);
        assertTrue("Incorrect size after add", s.size() == ts.size());
        Iterator i = ts.iterator();
        while (i.hasNext())
            assertTrue("Returned incorrect set", s.contains(i.next()));
    }
     */

    @Test
    void retainAll() {
    }

    @Test
    void removeAll() {

    }


    @Test
    void clear() {
    }


}