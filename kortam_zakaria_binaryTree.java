/*
 * Zakaria Kortam - 4 May, 2023
 * 1. A binary standard tree is created and 8 integers are placed into it using the
 *    insert() method.
 * 2. Preorder, inorder, and postorder traversals are used on the tree using each
 *    of their methods.
 * 3. The size of the tree is printed.
 * 4. 40 is deleted from the BST and then it prints a confirmation
 *    if the deletion was indeed successful.
 * 5. The size of the tree is printed again to demonstrate to the user that the
 *    deletion was indeed successful, once again.
 * 6. The tree is traversed inorder after the deletion.
 * 7. To utilize the search function, a for loop calls a total of 5 values and
 *    checks to see if they hold an instance within the tree.
 * 8. This program contains the main class, BST class, as well as the tree interface 
 *    and the binaryTree interface which extends the tree.
 */
import java.util.*;

public class kortam_zakaria_binaryTree{
    public static void main(String[] args) {
        header("Zakaria Kortam - COMSC 76");
        BST<Integer> bst = new BST<>();
        
        bst.insert(10);
        bst.insert(20);
        bst.insert(30);
        bst.insert(70);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        bst.insert(90);
    
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>> Traverals >>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>\n");
        System.out.println("Pre-order:");
        bst.preorder();
        System.out.println();
        System.out.println("In-order:");
        bst.inorder();
        System.out.println();
        System.out.println("Post-order:");
        bst.postorder();
        System.out.println();

        System.out.print("\nSize of the tree: " + bst.getSize());
        int element = 40;
        if (bst.delete(element)) {
            System.out.println("\nElement " + element + " deleted from the tree.");
        } else {
            System.out.println("Element " + element + " not found in the tree.");
        }
        System.out.println("New tree size: " + bst.getSize() + "\n");

        System.out.println("Inorder traversal after deleting " + element + ":");
        bst.inorder();
        System.out.println();
    
        element = 10;
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>> Element Location >>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        
        for(element=10; element<100; element+=20){
            if (bst.search(element)) {
                System.out.println("Element " + element + " was located within the tree.");
            } else {
                System.out.println("Element " + element + " was not located in the tree.");
            }
        }
        System.out.println("\n\n");
    }

    public static void header(String input) {
        System.out.println("\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.printf("<<<<<<< %s <<<<<<<<%n", input);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n");
    }
}

interface Tree<E> {
    public boolean insert(E e);
    public boolean delete(E e);
    public boolean search(E e);
    public void preorder();
    public void inorder();
    public void postorder();
    public int getSize();
    public boolean isEmpty();
}    

interface BinaryTree<E> extends Tree<E> {
    public int getSize();
    public TreeNode<E> getRoot();
    public java.util.ArrayList<TreeNode<E>> path(E e);
    public boolean delete(E e);
    public java.util.Iterator<E> iterator();
}

class BST < E extends Comparable <E>> implements Tree <E> {
    protected TreeNode < E > root;
    protected int size = 0;

    public BST() {}

    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }
    
    @Override
    public boolean search(E e) {
        TreeNode < E > current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else 
                return true;
        }
        return false;
    }

    @Override
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e);
        else {
            TreeNode < E > parent = null;
            TreeNode < E > current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else{
                return false;
            }
            if (e.compareTo(parent.element) < 0){
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true;
    }

    protected TreeNode <E> createNewNode(E e) {
        return new TreeNode <> (e);
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    protected void inorder(TreeNode < E > root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    protected void postorder(TreeNode < E > root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    protected void preorder(TreeNode < E > root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static class TreeNode < E > {
        protected E element;
        protected TreeNode < E > left;
        protected TreeNode < E > right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override 
    public int getSize() {
        return size;
    }

    public TreeNode < E > getRoot() {
        return root;
    }

    public java.util.ArrayList < TreeNode < E >> path(E e) {
        java.util.ArrayList < TreeNode < E >> list =
            new java.util.ArrayList < > ();
        TreeNode < E > current = root;
        while (current != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else
                break;
        }
        return list; 
    }

    @Override
    public boolean delete(E e) {
        TreeNode < E > parent = null;
        TreeNode < E > current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }
        if (current == null){
            return false;
        }
        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else {
            TreeNode < E > parentOfRightMost = current;
            TreeNode < E > rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; 
            }

            current.element = rightMost.element;

            if (parentOfRightMost.right == rightMost){
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true;
    }
    @Override
    public java.util.Iterator < E > iterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements java.util.Iterator < E > {
        private java.util.ArrayList < E > list =
        new java.util.ArrayList < > ();
        private int current = 0;

        public InorderIterator() {
            inorder();
        }

        private void inorder() {
            inorder(root);
        }

        private void inorder(TreeNode < E > root) {
            if (root == null){
                return;
            } 
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            if (current < list.size())
                return true;

            return false;
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            if (current == 0)
                throw new IllegalStateException();

            delete(list.get(--current));
            list.clear();
            inorder();
        }

        @Override
        public void clear() {
            root = null;
         size = 0;
        }
    }
}

