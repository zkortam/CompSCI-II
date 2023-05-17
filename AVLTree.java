/*
 * Zakaria Kortam - 10 May 2023
 * COMSC 76 - AVL Trees - Professor Estrada
 * 1. An ArrayList is created with 500,000 random values.
 * 2. A BS Tree is created with no values.
 * 3. It is run through an enhanced for loop where each value
 * from the array list is added into the tree.
 * 4. The time that it took is recorded and declared.
 * 5. The numbers list is shuffled.
 * 6. The search function is commenced ands the duration that it took
 * to complete is recorded.
 * 7. It's shuffled again and then the delete function is tested.
 * 8. The numbers list is reset and repopulated with 500,000 random values, due to
 * the prior deletion.
 * 9. The previous steps are repeated but with an AVL tree.
 * 10. The results are posted and compared.
 */

import java.util.*;

public class kortam_zakaria_AVLTree {
    public static void main(String[] args) {

        header("Zakaria Kortam - COMSC 76");

        ArrayList<Integer> numbers = random(500000);

        //////////////////////////////////////////////////////////////////////////
        /////////////////////////// Binary Standard Tree /////////////////////////
        //////////////////////////////////////////////////////////////////////////

        header("   Binary Search Tree    ");
        BST<Integer> bst = new BST<>();
        long start = System.currentTimeMillis();

        // Insertion
        for (Integer number : numbers) {
            bst.insert(number);
        }
        long stop = System.currentTimeMillis();
        long bstIT = stop - start;
        System.out.println("BST insertion time: " + bstIT + " ms");

        // Shuffle
        Collections.shuffle(numbers);

        // Search
        start = System.currentTimeMillis();
        for (Integer number : numbers) {
            bst.search(number);
        }
        stop = System.currentTimeMillis();
        long bstSearchTime = stop - start;
        System.out.println("BST search time: " + bstSearchTime + " ms");

        // Shuffle
        Collections.shuffle(numbers);

        // Deletion
        start = System.currentTimeMillis();
        for (Integer number : numbers) {
            bst.delete(number);
        }
        stop = System.currentTimeMillis();
        long bstDeleteTime = stop - start;
        System.out.println("BST deletion time: " + bstDeleteTime + " ms");

        //////////////////////////////////////////////////////////////////////////
        ///////////////////////////// AVL-BS Tree //////////////////////////////
        //////////////////////////////////////////////////////////////////////////

        numbers = random(500000);

        header(" AdelsonVelskii & Landis ");
        AVLTree<Integer> avlTree = new AVLTree<>();

        // Insertion
        start = System.currentTimeMillis();
        for (Integer number : numbers) {
            avlTree.insert(number);
        }
        stop = System.currentTimeMillis();
        long avlInsertTime = stop - start;
        System.out.println("AVL insertion time: " + avlInsertTime + " ms");

        // Shuffle
        Collections.shuffle(numbers);

        // Search
        start = System.currentTimeMillis();
        for (Integer number : numbers) {
            avlTree.search(number);
        }
        stop = System.currentTimeMillis();
        long avlSearchTime = stop - start;
        System.out.println("AVL search time: " + avlSearchTime + " ms");

        // Shuffle
        Collections.shuffle(numbers);

        // Deletion
        start = System.currentTimeMillis();
        for (Integer number : numbers) {
            avlTree.delete(number);
        }
        stop = System.currentTimeMillis();
        long avlDeleteTime = stop - start;
        System.out.println("AVL deletion time: " + avlDeleteTime + " ms");

        header("   Comparison Results    ");
        System.out.print("Insertion: ");
        if (avlInsertTime > bstIT) {
            System.out.println("AVL is " + (avlInsertTime - bstIT) + " ms faster than BST.");
        } else if (avlInsertTime < bstIT) {
            System.out.println("BST is " + (bstIT - avlInsertTime) + " ms faster than AVL.");
        } else {
            System.out.println("Both AVL and BST took the same.");
        }

        System.out.print("Search: ");
        if (avlSearchTime > bstSearchTime) {
            System.out.println("AVL is " + (avlSearchTime - bstSearchTime) + " ms faster than BST.");
        } else if (avlSearchTime < bstSearchTime) {
            System.out.println("BST is " + (bstSearchTime - avlSearchTime) + " ms faster than AVL.");
        } else {
            System.out.println("Both AVL and BST took the same time.");
        }

        System.out.print("Deletion: ");
        if (avlDeleteTime > bstDeleteTime) {
            System.out.println("AVL is " + (avlDeleteTime - bstDeleteTime) + " ms faster than BST.");
        } else if (avlDeleteTime < bstDeleteTime) {
            System.out.println("BST is " + (bstDeleteTime - avlDeleteTime) + " ms faster than AVL.");
        } else {
            System.out.println("Both AVL and BST took the same time.");
        }

        double avgInsertLead = (double) (avlInsertTime - bstIT) / numbers.size();
        double avgSearchLead = (double) (avlSearchTime - bstSearchTime) / numbers.size();
        double avgDeleteLead = (double) (avlDeleteTime - bstDeleteTime) / numbers.size();

        if (avgInsertLead > 0 && avgSearchLead > 0 && avgDeleteLead > 0) {
            System.out.println("\nOverall, AVL is faster than BST.");
        } else if (avgInsertLead < 0 && avgSearchLead < 0 && avgDeleteLead < 0) {
            System.out.println("\nOverall, BST is faster than AVL .");
        } else {
            System.out.println("\nOverall, AVL and BST have similar performance.");
        }
    }

    private static ArrayList<Integer> random(int count) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            numbers.add(random.nextInt());
        }
        return numbers;
    }

    public static void header(String input) {
        System.out.println("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.printf("<<<<<<< %s <<<<<<<<%n", input);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
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

interface TreeNode<E> {
    E getElement();

    void setElement(E element);

    TreeNode<E> getLeft();

    void setLeft(TreeNode<E> left);

    TreeNode<E> getRight();

    void setRight(TreeNode<E> right);
}

interface BinaryTree<E> extends Tree<E> {
    public int getSize();

    public TreeNode<E> getRoot();

    public ArrayList<TreeNode<E>> path(E e);

    public boolean delete(E e);

    public Iterator<E> iterator();
}

class BST<E extends Comparable<E>> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    public BST() {
    }

    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;

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
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(E e) {
        if (root == null) {
            root = new TreeNode<>(e);
            size++;
            return true;
        }

        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                // Duplicate value, do not add
                return false;
            }
        }

        TreeNode<E> newNode = new TreeNode<>(e);
        if (e.compareTo(parent.element) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        size++;
        return true;
    }

    @Override
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e);
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false;
                }
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    protected void inorder(TreeNode<E> root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    protected void postorder(TreeNode<E> root) {
        if (root == null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    protected void preorder(TreeNode<E> root) {
        if (root == null)
            return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<>();
        TreeNode<E> current = root;
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
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
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
        if (current == null) {
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
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true;
    }

    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    private class InorderIterator implements java.util.Iterator<E> {
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private int current = 0;

        public InorderIterator() {
            inorder();
        }

        private void inorder() {
            inorder(root);
        }

        private void inorder(TreeNode<E> root) {
            if (root == null) {
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

    }
}

class AVLTree<E extends Comparable<E>> extends BST<E> {

    public AVLTree() {
    }

    public AVLTree(E[] objects) {
        super(objects);
    }

    @Override
    protected AVLTreeNode<E> createNewNode(E e) {
        return new AVLTreeNode<E>(e);
    }

    @Override
    public boolean insert(E e) {
        boolean successful = super.insert(e);
        if (!successful)
            return false;
        else {
            balancePath(e);
        }
        return true;
    }

    private void updateHeight(AVLTreeNode<E> node) {
        if (node.left == null && node.right == null)
            node.height = 0;
        else if (node.left == null)
            node.height = 1 + ((AVLTreeNode<E>) (node.right)).height;
        else if (node.right == null)
            node.height = 1 + ((AVLTreeNode<E>) (node.left)).height;
        else
            node.height = 1 +
                    Math.max(((AVLTreeNode<E>) (node.right)).height,
                            ((AVLTreeNode<E>) (node.left)).height);
    }

    private void balancePath(E e) {
        ArrayList<TreeNode<E>> path = path(e);
        for (int i = path.size() - 1; i >= 0; i--) {
            AVLTreeNode<E> A = (AVLTreeNode<E>) (path.get(i));
            updateHeight(A);
            AVLTreeNode<E> parentOfA = (A == root) ? null : (AVLTreeNode<E>) (path.get(i - 1));

            switch (balanceFactor(A)) {
                case -2:
                    if (balanceFactor((AVLTreeNode<E>) A.left) <= 0) {
                        balanceLL(A, parentOfA);
                    } else {
                        balanceLR(A, parentOfA);
                    }
                    break;
                case +2:
                    if (balanceFactor((AVLTreeNode<E>) A.right) >= 0) {
                        balanceRR(A, parentOfA);
                    } else {
                        balanceRL(A, parentOfA);
                    }
            }
        }
    }

    private int balanceFactor(AVLTreeNode<E> node) {
        if (node.right == null)
            return -node.height;
        else if (node.left == null)
            return +node.height;
        else
            return ((AVLTreeNode<E>) node.right).height - ((AVLTreeNode<E>) node.left).height;
    }

    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left;

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        A.left = B.right;
        B.right = A;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
    }

    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left;
        TreeNode<E> C = B.right;

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.left = C.right;
        B.right = C.left;
        C.left = B;
        C.right = A;

        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }

    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right;

        if (A == root) {
            root = B;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = B;
            } else {
                parentOfA.right = B;
            }
        }

        A.right = B.left;
        B.left = A;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
    }

    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right;
        TreeNode<E> C = B.left;

        if (A == root) {
            root = C;
        } else {
            if (parentOfA.left == A) {
                parentOfA.left = C;
            } else {
                parentOfA.right = C;
            }
        }

        A.right = C.left;
        B.left = C.right;
        C.left = A;
        C.right = B;

        // Adjust heights
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);
    }

    @Override
    public boolean delete(E element) {
        if (root == null)
            return false;

        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (element.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (element.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                break;
        }

        if (current == null)
            return false;

        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else {
                if (element.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
                balancePath(parent.element);
            }
        } else {
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
            current.element = rightMost.element;

            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                parentOfRightMost.left = rightMost.left;
            balancePath(parentOfRightMost.element);
        }

        size--;
        return true;
    }

    protected static class AVLTreeNode<E> extends BST.TreeNode<E> {
        protected int height = 0;

        public AVLTreeNode(E e) {
            super(e);
        }
    }
}
