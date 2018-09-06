package cn.gl.bean;

import java.util.ArrayList;

public class Heap<E extends Comparable<E>>{
    private ArrayList<E> list = new ArrayList<>();

    public Heap() {
    }

    public Heap(E[] objs) {
        for (int i=0;i<objs.length;i++)
            add(objs[i]);
    }



    public void add(E e){
        list.add(e);
        int curentIndex = list.size() - 1;
        int parentIndex = (curentIndex-1)/2;
        while (parentIndex>=0){
            if (e.compareTo(list.get(parentIndex))>0){
                list.set(curentIndex,list.get(parentIndex));
                list.set(parentIndex,e);
                curentIndex = parentIndex;
                parentIndex = (parentIndex - 1)/2;
            }else break;
        }
    }

    public E remove(){
        if (list.size()==0){
            return null;
        }

        E result = list.get(0);
        list.set(0,list.get(list.size()-1));
        list.remove(list.size()-1);

        int currentIndex = 0;
        while (currentIndex < list.size()){
            int leftChild = (currentIndex*2)+1;
            int rightChild = (currentIndex*2)+2;

            int maxIndex;
            if (leftChild > list.size()-1)
                break;
            else if (rightChild > list.size()-1){
                maxIndex = leftChild;
            }else {
                E left = list.get(leftChild);
                E right = list.get(rightChild);

                if (left.compareTo(right)>=0){
                    maxIndex = leftChild;
                }else {
                    maxIndex = rightChild;
                }
            }

            if (list.get(currentIndex).compareTo(list.get(maxIndex))<0){
                E temp = list.get(maxIndex);
                list.set(maxIndex,list.get(currentIndex));
                list.set(currentIndex,temp);

                currentIndex = maxIndex;
            }else break;

        }
        return result;
    }

    public int getSize(){
        return list.size();
    }

    public boolean isEmpty(){
        if (list.size()==0){
            return true;
        }else return false;
    }

    public void display(){
        for (E e: list){
            System.out.print(e+" ");
        }
        System.out.println();
    }
}
