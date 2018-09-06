package cn.gl.bean;

import java.io.Serializable;
import java.util.HashMap;

public class HuffmanTree {

    private Node root;
    /**
     * 统计节点个数
     */
    private int COUNT = 0;

    public static class Node implements Comparable<Node>, Serializable {
        public byte element;
        public int weight;
        public Node left;
        public Node right;
        public String code = "";

        @Override
        public int compareTo(Node o) {
            if (this.weight < o.weight)
                return 1;
            else if (this.weight == o.weight)
                return 0;
            else return -1;
        }
    }



    public Node getHuffmanTree(int[] frequences){
        Heap<Node> heap = new Heap<>();

        for (int i=0;i<frequences.length;i++){
            if (frequences[i]!=0){
                Node node = new Node();
                node.weight = frequences[i];
                node.element = (byte) i;
                heap.add(node);
            }
        }

        while (heap.getSize()>1){
            Node min = heap.remove();
            Node secondMin = heap.remove();
            Node newlyNode = new Node();
            newlyNode.weight = min.weight + secondMin.weight;
            newlyNode.left = min;
            newlyNode.right = secondMin;
            heap.add(newlyNode);
        }

        root = heap.remove();
        assignCode(root);
        return root;
    }

    public void assignCode(){
        if (root==null){
            return;
        }
        assignCode(root);
    }

    private void assignCode(Node node){
        // 只需要判断一边，因为有孩子那么必定左右孩子都有，没有孩子左右孩子都没有。
        if (node.left!=null){
            node.left.code = node.code + 0;
            assignCode(node.left);

            node.right.code = node.code + 1;
            assignCode(node.right);
        }
    }

    public HashMap<Byte,String> getCode(){
        HashMap<Byte,String> map = new HashMap<>();
        order(root,map);
        return map;
    }

    private void order(Node node, HashMap<Byte,String> map){
        if (node!=null){
            if (node.left==null&&node.right==null){
                map.put(node.element,node.code);
            }
            order(node.left,map);
            order(node.right,map);
        }

    }

    public int getNodeSize(){
        orderToGetSize(root);
        return COUNT;
    }

    private void orderToGetSize(Node node){
        if (node!=null){
            COUNT++;
            orderToGetSize(node.left);
            orderToGetSize(node.right);
        }
    }

    public Node search(Node node) {
       if (node != null) {
            if (root == null) {
                return null;
            }else if (root == node){
                return root;
            }else {
                return assistSearch(root, node);
            }
       }else {
           return null;
       }
    }

    private Node assistSearch(Node crt, Node des){
        if (crt!=null) {
            if (crt == des) {
                return crt;
            }
            assistSearch(crt.left, des);
            assistSearch(crt.right, des);
        }else {
            return null;
        }
        return null;
    }

    public void printTree() {
        int i = 0;
        printTree(root, i);
    }

    private void printTree(Node curent, int i){
        if (curent!=null) {
            for (int j =0; j<i; j++) {
                System.out.print("  ");
            }
            System.out.println(curent.element+" "+curent.weight);
            printTree(curent.left,i+1);
            printTree(curent.right, i+1);
        }
    }
}
