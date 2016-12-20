import javax.print.attribute.standard.MediaSize.Other;

public class Tree {

    private TreeNode root;

    public Tree() {
        this.root = null;
    }

    public Tree(int[] a) {
        this.root = construct(a, 0, a.length - 1);
    }

    private static class TreeNode {
        TreeNode left;
        int data;
        TreeNode right;

        /*public TreeNode(int data) {
            this(data, null, null);
        }*/

        public TreeNode(int item, TreeNode left, TreeNode right) {
            this.left = left;
            this.right = right;
            this.data = item;
        }
    }

    private TreeNode construct(int[] data, int start, int stop) {
        if (start > stop) {
            return null;
        }
        int mid = (start + stop + 1) / 2;
        return new TreeNode(data[mid], construct(data, start, mid - 1),
                construct(data, mid + 1, stop));
    }

    public int count() {
        return count(this.root, 0);
    }

    private int count(TreeNode current, int i) {
        if (current != null) {
            if (current.data % 2 == 0) {
                return 0;
            } else if (current.data % 2 == 1) {
                return count(current.left, i + 1) + count(current.right, i);
            }
        }
        return i;
    }

    public void fight(Tree other) {
        this.root = fight(this.root, other.root);
    }

    private TreeNode fight(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 != null) {
            return root2;
        }
        if (root1 != null && root2 != null) {
            root1.left = fight(root1.left, root2.left);
            root1.right = fight(root1.right, root2.right);
            if (root1.data < root2.data) {
                root1 = new TreeNode(root2.data, root1.left, root1.right);
            }
        }
        return root1;
    }
    
    @Override
    public String toString(){
        return to(this.root);
    }

    private String to(TreeNode current) {
        // TODO Auto-generated method stub
        if (current != null){
           to(current.left);
           to(current.right);
           return " " + current.data;
        }
        return "";
    }

    public int nodesAtLevel(int i, int j) {
        return nodes(i, j, 1, this.root);
    }

    private int nodes(int i, int j, int n,TreeNode current) {
        if(current!=null){
            int sum = nodes(i,j, n+1, current.left) + nodes(i,j,n+1, current.right);
            if(n>=i){
                return 1+ sum;
            }
            return sum;
        }
        return 0;
    }

    /*
     * public void addSearch(String item) { if (root == null) { root = new
     * TreeNode(null, item, null); return; }
     * 
     * TreeNode node = root; while (true) { if (item.compareTo(node.item) < 0) {
     * if (node.left == null) { node.left = new TreeNode(null, item, null);
     * break; } node = node.left; } else { if (node.right == null) { node.right
     * = new TreeNode(null, item, null); break; } node = node.right; } } }
     */
}