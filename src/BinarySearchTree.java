import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

    private BinarySearchTreeNode<T> root;
    ArrayList<T> sortedList;

    public boolean add(T data) {
        if (root == null) {
            root = new BinarySearchTreeNode<T>(data);
            return true;
        } else {
            return root.add(data);
        }
    }

    public boolean remove(T data) {
        int originalSize = size();
        if (root != null)
            root = root.remove(data);
        return size() < originalSize;

    }
    public List<T> makeList(){
        List<T> list = root.getAll();
        Collections.sort(list);
        return list;
    }

    public ArrayList<T> getSortedList(){
        return sortedList;
    }

    public void clearSortedList(){
        sortedList.clear();
    }

    public boolean contains(T data) {

        return root == null ? false : root.contains(data);
    }

    public void clear() {
        root = null;
    }

    public int size() {
        if (root == null)
            return 0;
        else
            return root.size();


    }

    public BinarySearchTreeNode<T> getRoot(){
        return root;
    }

    public int depth() {
        if (root == null)
            return -1;
        else
            return root.depth();
    }

    public String toString() {
        return "[" + (root == null ? "" : root.toString()) + "]";
    }

}


