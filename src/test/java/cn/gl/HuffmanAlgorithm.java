package cn.gl;

import cn.gl.bean.Heap;
import cn.gl.bean.HuffmanTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HuffmanAlgorithm {
    @Test
    public void test1(){
        String str = "Mississippi";
        int[] frequences = getFrequences(str);
        for (int i = 0; i<256; i++){
            if (frequences[i]!=0){
                char ch = (char) i;
                System.out.println(ch+" "+frequences[i]);
            }
        }
        HuffmanTree huffmanTree = new HuffmanTree();
        HuffmanTree.Node node = huffmanTree.getHuffmanTree(frequences);
        HashMap<Byte, String> map = huffmanTree.getCode();
        Set<Map.Entry<Byte, String>> entries = map.entrySet();
        for (Map.Entry<Byte, String> entry:entries){
            Byte key = entry.getKey();
            char ch = (char) key.byteValue();
            String value = entry.getValue();
            System.out.println(ch+" "+value);
        }
    }

    public int[] getFrequences(String str){
        int[] frequences = new int[256];
        for (int i = 0; i<str.length();i++){
            int ch = str.charAt(i);
            frequences[ch]++;
        }
        return frequences;
    }

    @Test
    public void test2(){
        char a = 'a';
        int n = a;
        byte b = (byte) n;
        System.out.println(n);
        System.out.println(b);
    }

    @Test
    public void test3(){
        var list = new ArrayList<String>();
        if (list instanceof ArrayList)
            System.out.println(true);

        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        System.out.println(list);
        list.remove(1);
        System.out.println(list);

        list.add(1,"bbb");
        System.out.println(list);

        list.set(1,"bb");
        System.out.println(list);

        char a = 'a';
        int a1 = a;
        byte a2 = (byte) a1;
        char a3 = (char) a2;
        System.out.println(a3);
    }

    @Test
    public void test4(){
        var heap = new Heap<Integer>();
        heap.add(10);
        heap.add(8);
        heap.add(7);
        heap.add(11);
        heap.add(15);
        heap.add(1);
        heap.add(5);
        heap.add(4);
        heap.display();

        heap.remove();
        System.out.println();
        heap.display();
        heap.remove();
        heap.display();

        heap.add(7);
        heap.display();
        heap.add(9);
        heap.display();
        heap.add(10);
        heap.display();
        System.out.println();
        while (!heap.isEmpty()){
            heap.remove();
            heap.display();
        }
    }
}
