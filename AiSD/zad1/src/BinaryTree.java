import java.util.*;

public class BinaryTree {
    static class Node {
        Node left;
        Node right;
        int val;

        public Node(int key) {
            left = null;
            right = null;
            val = key;
        }
    }

    Node root;

    public BinaryTree() {
        root = null;
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            _insert(key, root);
        }
    }

    private void _insert(int key, Node node) {
        if (key < node.val) {
            if (node.left == null) {
                node.left = new Node(key);
            } else {
                _insert(key, node.left);
            }
        } else if (key > node.val) {
            if (node.right == null) {
                node.right = new Node(key);
            } else {
                _insert(key, node.right);
            }
        }
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            int left_height = height(node.left);
            int right_height = height(node.right);
            return Math.max(left_height, right_height) + 1;
        }
    }

    private void print_given_level(Node root, int level) {
        if (root == null) {
            System.out.print("X ");
            return;
        }
        if (level == 1) {
            System.out.print(root.val + " ");
        } else if (level > 1) {
            print_given_level(root.left, level - 1);
            print_given_level(root.right, level - 1);
        }
    }

    public void print_tree(Node root) {
        int h = height(root);
        for (int i = 1; i <= h; i++) {
            print_given_level(root, i);
            System.out.println();
        }
    }


    private Node deleteRec(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.val) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.val) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.val = minValue(root.right);

            root.right = deleteRec(root.right, root.val);
        }

        return root;
    }

    private int minValue(Node root) {
        int minv = root.val;
        while (root.left != null) {
            minv = root.left.val;
            root = root.left;
        }
        return minv;
    }

    public static void permute(int[] a, int k, List<int[]> output) {
        if (k == a.length) {
            output.add(a.clone());
        } else {
            for (int i = k; i < a.length; i++) {
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
                permute(a, k + 1, output);
                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {20, 40, 60, 80, 100, 120};
        List<int[]> output = new ArrayList<>();
        permute(arr, 0, output);
        for (int[] permutation : output) {
            BinaryTree tree = new BinaryTree();
            for (int i = 0; i < permutation.length; i++) {
                tree.insert(permutation[i]);
            }
            System.out.println("Tree for permutation: " + Arrays.toString(permutation));
            tree.print_tree(tree.root);

            // Sprawdzanie, jaki klucz należy usunąć, aby zmniejszyć wysokość drzewa
            int originalHeight = tree.height(tree.root);
            System.out.println("Original Height: " + originalHeight);

            int minHeight = originalHeight;
            int keyToRemove = -1;

            // Iterowanie przez każdy klucz w drzewie i sprawdzanie, czy usunięcie go zmniejsza wysokość drzewa
            for (int key : permutation) {
                BinaryTree tempTree = new BinaryTree();
                for (int j : permutation) {
                    if (j != key) {
                        tempTree.insert(j);
                    }
                }

                int tempHeight = tempTree.height(tempTree.root);
                if (tempHeight < minHeight) {
                    minHeight = tempHeight;
                    keyToRemove = key;
                }
            }

            if (keyToRemove != -1) {
                System.out.println("Remove node: " + keyToRemove + " to reduce height");
            } else {
                System.out.println("No node can be removed to reduce height");
            }

            System.out.println("\n");
        }
    }
}
