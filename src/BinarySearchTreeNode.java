// Adan Anwar, Adan9862
// August Carlsson, Auca4478


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused") // Denna rad ska plockas bort. Den finns här
// tillfälligt för att vi inte ska tro att det är
// fel i koden. Varningar ska normalt inte döljas på
// detta sätt, de är (oftast) fel som ska fixas.
public class BinarySearchTreeNode<T extends Comparable<T>> {

    private T data;
    private int index;
    public BinarySearchTreeNode<T> left;
    public BinarySearchTreeNode<T> right;

    public BinarySearchTreeNode(T data) {
        this.data = data;
    }

    public boolean add(T data) {
        int comp = this.data.compareTo(data);
        if(comp > 0)
            if(left == null)
                left = new BinarySearchTreeNode<>(data);
            else
                return left.add(data);
        else if(comp < 0 )
            if(right == null)
                right = new BinarySearchTreeNode<>(data);
            else
                return right.add(data);
        return comp != 0;
    }

    public T findMin() {
        return (left != null) ? left.findMin() : this.data;
    }

    public BinarySearchTreeNode<T> getMinNode(){
        return (left != null) ? left.getMinNode() : this;
    }

    public T findMax(){
        return (right != null) ? right.findMax() : this.data;
    }


    public BinarySearchTreeNode<T> remove(T data) {
        int comp = data.compareTo(this.data);
        if(comp < 0) {
            if(left != null)
                left = left.remove(data);
        }else if(comp > 0 ) {
            if(right != null)
                right = right.remove(data);
        }else if(left != null && right != null) {
            this.data = right.findMin();
            right = right.remove(this.data);
        }else
            return (left != null) ? left : right;
        return this;

    }

    public boolean contains(T data) {
        int comp = this.data.compareTo(data);
        return (comp == 0) || ((comp > 0) ? (left != null) && left.contains(data) : (right != null) && right.contains(data));
    }

    public int size() {
        return 1 +((left != null) ? left.size(): 0) + ((right != null) ? right.size(): 0);
    }



    public int depth() {
        int r = (right != null) ? 1 + right.depth() : 0;
        int l = (left!=null) ? 1 + left.depth() : 0;
        return Math.max(r, l);
    }
    public T getData(){
        return data;
    }



    public ArrayList<T> mergeList(ArrayList<T> l1, ArrayList<T> l2){
        l1.addAll(l2);
        return l1;
    }



    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        if (data != null) list.add(data);
        if (left != null) list.addAll(left.getAll());
        if (right != null) list.addAll(right.getAll());
        return list;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("");
        if(left != null && right != null)
            return builder.append(left.toString()).append(", ").append(data).append(", ").append(right.toString()).toString();
        else if(left != null)
            return builder.append(left.toString()).append(", ").append(data).toString();
        else if(right != null)
            return builder.append(data).append(", ").append(right.toString()).toString();

        return builder.append(data).toString();
    }
}
