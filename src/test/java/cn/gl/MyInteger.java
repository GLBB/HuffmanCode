package cn.gl;

import java.util.ArrayList;

public class MyInteger implements Comparable<MyInteger>{
    int value;

    public MyInteger(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(MyInteger o) {
        if (value<o.value)
            return 1;
        else if (value == o.value)
            return 0;
        else return -1;
    }

    @Override
    public String toString() {
        return value+"";
    }
}
