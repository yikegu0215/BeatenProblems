package moBanMen;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeTraversal {
    //Iterative Inorder Traversal
    public List<Integer> inOrderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            //Operation
            result.add(root.val);
            //
            root = root.right;
        }
        return result;

    }
}
