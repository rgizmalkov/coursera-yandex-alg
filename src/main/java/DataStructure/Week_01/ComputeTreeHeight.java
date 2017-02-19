package DataStructure.Week_01;

import java.util.*;

/**
 * Created by elion on 12.02.2017.
 */
public class ComputeTreeHeight {
    private ArrayList<ArrayList<Node>> tree;
    private Node root;

    private class Node {
        int ref;
        int val;

        Node(){}

        Node(int ref, int val) {
            this.ref = ref;
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (ref != node.ref) return false;
            return val == node.val;
        }

        @Override
        public int hashCode() {
            int result = ref;
            result = 31 * result + val;
            return result;
        }

        @Override
        public String toString() {
            return "Node (" + ref + ";" + val + ")";
        }
    }

    public void init(){
        Scanner sc = new Scanner(System.in);
        build(sc);
    }

    private void build(Scanner sc){
        int len = sc.nextInt();
        this.tree = new ArrayList<>(len);
        for (int i = 0; i <len; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < len; i++) {
            int rval = sc.nextInt();
            if(rval == -1) {
                this.root = new Node(i, rval);
            }else {
                tree.get(rval).add(new Node(i, rval));
            }
        }
    }

    public int alg(){
        if(tree == null) throw new RuntimeException("No initialized tree!");
        if(tree.size() > 50000){
            return method1(root);
        }else {
            return method(root);
        }
    }

    private int method(Node node){
        ArrayList<Node> array = tree.get(node.ref);

        if(array.size() == 0) return 1;

        int max = 0;

        for (int i = 0; i < array.size(); i++) {
            int val = method(array.get(i));
            if(val >= max) max = val;
        }
        return max + 1;
    }

    private int method1(Node node){
        if(tree == null){ return 0;}
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        int counter = 1;
        int len = 1;
        int val = 0;
        while (!queue.isEmpty()) {
            val++;
            Node poll = queue.poll();
            int SIZE = tree.get(poll.ref).size();

            for (int i = 0; i < SIZE; i++){
                queue.add(tree.get(poll.ref).get(i));
            }
            if(counter == val) {
                counter = queue.size();
                if(counter != 0)len++;
                val = 0;
            }
        }
        return len;
    }


    public static void main(String[] args) {
        ComputeTreeHeight cth = new ComputeTreeHeight();
        cth.init();
        System.out.println(cth.alg());
    }


}
