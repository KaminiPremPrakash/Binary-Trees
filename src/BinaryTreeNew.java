
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kamini Prakash
 */
class BinaryTreeNew {

    static Node root;

    // function to create BST by recursion
    public Node createBST(int arr[], int start, int end) {

        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);

        node.left = createBST(arr, start, mid - 1);
        node.right = createBST(arr, mid + 1, end);

        return node;
    }

    public Node searchNode(Node node, int target) {
        if (node == null) {
            return null;
        }

        if (node.data == target) {
            System.out.println("Node found:" + node.data);
            return node;
        } else if (target < node.data) {
            System.out.println(node.data);
            return searchNode(node.left, target);
        } else {
            System.out.println(node.data);
            return searchNode(node.right, target);
        }

    }

    void printInorder(Node node) {
        if (node == null) {
            return;
        }

        printInorder(node.left);

        System.out.print(node.data + " ");

        printInorder(node.right);
    }

    void printInorder() {
        printInorder(root);
        System.out.println();
    }

    public List<Integer> generateRandomArray(int n) {
        ArrayList<Integer> list = new ArrayList<Integer>(n);
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(50));
        }
        return list;
    }

    public int findHeight(Node temp) {
        if (root == null) {
            System.out.println("Tree is empty");
            return 0;
        } else {
            int leftHeight = 0, rightHeight = 0;

            if (temp.left != null) {
                leftHeight = findHeight(temp.left);
            }

            if (temp.right != null) {
                rightHeight = findHeight(temp.right);
            }

            // Compare heights & store max of two
            int max = (leftHeight > rightHeight) ? leftHeight : rightHeight;

            // Calculate the total height of tree by adding height of root
            return (max + 1);
        }
    }

    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] searchArr = new int[]{3, 7, 10, 2, 22, 99, 100, 44, 1, 66,
            33, 29, 63, 70, 17, 54, 34, 76, 334, 255, 65, 88, 20, 1001};
        //sorting the static array and creating a binary tree to search for target
        Arrays.sort(searchArr);
        System.out.println("Static Array :" + Arrays.toString(searchArr));
        BinaryTreeNew bTree = new BinaryTreeNew();
        int m = searchArr.length;
        root = bTree.createBST(searchArr, 0, m - 1);
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the value to be searched: ");
        int target = scan.nextInt();
      //  scan.close();
        System.out.println("Nodes visited are:");
        Node check = bTree.searchNode(root, target);
        if (check == null) {
            System.out.println("Node not found");
        }

        //creating a random array integers and search for a target
        //for second part
        //creating an array with random integers & construct BST and search target
        //System.out.println(bTree.generateRandomArray(10));
        List<Integer> l = bTree.generateRandomArray(20);
        Collections.sort(l);
        System.out.println("\n");
        System.out.println("Randomly generated Array " + l);
        int listSize = l.size();
        root = bTree.createBST(convertIntegers(l), 0, listSize - 1);
        System.out.println("Enter the value to be searched: ");
        int randomTarget = scan.nextInt();
        scan.close();
        System.out.println("Nodes visited are:");
        check = bTree.searchNode(root, randomTarget);
        if (check == null) {
            System.out.println("Node not found");
        }

        //for 2nd part
        Integer[] nums = new Integer[]{3, 7, 10, 2, 22, 99, 100, 44, 1, 66,
            33, 29, 63, 70, 17, 54, 34, 76, 334, 255, 65, 88, 20, 1001};
        ArrayList<Integer> heights = new ArrayList<Integer>();
        Arrays.sort(nums);
        int n = nums.length;
        Random rand = new Random();
        System.out.println("\n");
        for (int gen_k = 1; gen_k < 11; gen_k++) {
            int k = rand.nextInt(nums.length);
            if (k != 0) {
                System.out.println("Random value of k : " + k);
                int height = 0;
                int array[] = new int[k];
                // for (int tries = 1; tries < 10; tries++) {
                List<Integer> newList = new LinkedList<Integer>(
                        Arrays.asList(nums));
                for (int i = 0; i < k; i++) {
                    int random = rand.nextInt(newList.size());
                    array[i] = newList.get(random);
                    newList.remove(random);
                }
                Arrays.sort(array);
                root = bTree.createBST(array, 0, array.length - 1);
                heights.add(bTree.findHeight(root));
                System.out.println("Height K" + gen_k + " is : " + bTree.findHeight(root));
                bTree.printInorder();
                System.out.println("----------------------------------------------------------------------------------------------------------------------------");

                // }
            }
        }
        System.out.print("Heights generated for 10 different values of K : ");
        for (int i = 0; i < heights.size(); i++) {
            System.out.print(heights.get(i) + ", ");
        }
        System.out.print("\n");
        System.out.println("Calculating the difference in heights : ");
        int totalHeightDifference = 0;
        for (int i = 0; i < heights.size() - 1; i++) {
            System.out.println(heights.get(i + 1) + "-" + heights.get(i) + " : " + Math.abs(heights.get(i + 1) - heights.get(i)));
            totalHeightDifference = totalHeightDifference + Math.abs(heights.get(i + 1) - heights.get(i));
        }
        double rate = totalHeightDifference * 1.0 / (heights.size() - 1);
        System.out.print("Rate of change of height : " + rate);

    }
}
