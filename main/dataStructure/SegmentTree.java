package dataStructure;

public class SegmentTree {
    public static class SegmentTreeNode{
        int start;
        int end;
        int max;// sum or max or min, etc. Example is max
        SegmentTreeNode left;
        SegmentTreeNode right;

        //Constructor
        public SegmentTreeNode(int start, int end, int val, SegmentTreeNode left, SegmentTreeNode right) {
            this.start = start;
            this.end = end;
            this.max = val;
            this.left = left;
            this.right = right;
        }
    }

    public SegmentTreeNode root;

    public SegmentTree(int[] array) {
        root = buildTree(0, array.length - 1, array);
    }
    //build tree with recursion
    public static SegmentTreeNode buildTree(int start, int end, int[] array) {
        if(start == end) {
            return new SegmentTreeNode(start, end, array[start], null, null);
        }

        int mid = start + (end - start) / 2;
        SegmentTreeNode leftNode = buildTree(start, mid, array);
        SegmentTreeNode rightNode = buildTree(mid + 1, end, array);
        return new SegmentTreeNode(start, end, Math.max(leftNode.max, rightNode.max), leftNode, rightNode);
    }

    private static int queryMax(int start, int end, SegmentTreeNode root) {
        if(root.left == null && root.right == null) {
            return root.max;
        }
        if(start == root.start && end == root.end) {
            return root.max;
        }
        int mid = start + (end - start) / 2;
        if(end <= root.end) {
            return queryMax(start, end, root.left);
        }else if (start >= root.start) {
            return queryMax(start, end, root.right);
        }else{
            return Math.max(queryMax(start, mid, root.left), queryMax(mid + 1, end, root.right));
        }
    }

    public int findMax(int start, int end) {
        return queryMax(start, end, this.root);
    }

    public static void main(String[] arg) {
        int[] array = {1, 4, 7, 8, 2, 5, 6, 9, 4, 2};
        SegmentTree sol = new SegmentTree(array);
        int a = sol.findMax(1,4);
        System.out.println(a);
    }
}

