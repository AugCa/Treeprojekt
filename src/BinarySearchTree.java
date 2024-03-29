import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

    private BinarySearchTreeNode<T> root;
    ArrayList<T> sortedList;
    private BinarySearchTreeNode<T> prev;

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
        return root.getAll();
    }



    public BinarySearchTreeNode<T> getNode(T data){
        return getRoot().getNode(data);
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


