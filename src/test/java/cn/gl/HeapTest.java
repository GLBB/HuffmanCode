package cn.gl;

import cn.gl.bean.Heap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HeapTest {

    public MyInteger[] generate(int[] values){
        MyInteger[] myIntegers = new MyInteger[values.length];
        for (int i=0;i<values.length;i++){
            myIntegers[i] = new MyInteger(values[i]);
        }
        return myIntegers;
    }

    public List heapSort(Heap heap){
        var list = new ArrayList<MyInteger>();
        while (!heap.isEmpty()){
            MyInteger myInteger = (MyInteger) heap.remove();
            list.add(myInteger);
        }
        return list;
    }

    public List heapSort2(Heap heap){
        var list = new ArrayList<Integer>();
        while (!heap.isEmpty()){
            int myInteger = (int) heap.remove();
            list.add(myInteger);
        }
        return list;
    }

    @Test
    public void test1(){
        int[] list = {-44,-5,-3,3,3,1,-4,0,1,2,4,5,53};
        MyInteger[] arr = generate(list);
        var heap = new Heap<MyInteger>(arr);
        List list1 = heapSort(heap);
        System.out.println(list1);

        Integer[] list3 = {-44,-5,-3,3,3,1,-4,0,1,2,4,5,53};
        var heap1 = new Heap<Integer>(list3);
        List<Integer> myIntegers = heapSort2(heap1);
        System.out.println(myIntegers);

    }

    @Test
    public void test(){
        String str = "sss";
        String str1 = "sss";
        System.out.println(str == str1);
        boolean testrue = true^true;
        System.out.println(testrue);
        str += "s";
        System.out.println(str);

        byte b1 = 0x7f;
        byte b2 = -0x7f - 1;
        byte result = (byte) (b1+b2);
    }


}
