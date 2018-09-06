package cn.gl;

public class IntegerTest {
    private static void change(Integer i){
        i = 2;
    }

    public static void main(String[] args) {
        Integer i = 1;
        change(null);
        System.out.println(i);
    }
}
