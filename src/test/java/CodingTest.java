import org.junit.Test;

import java.beans.BeanProperty;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodingTest {

    @Test
    public void test1(){
        Coding coding = new Coding();
        coding.fileCoding("test.txt");
    }

    @Test
    public void test2(){
        String string = "acdede";
        String result = "";
        Pattern pattern = Pattern.compile("d");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()){
            result = matcher.replaceAll("0111");
        }
        System.out.println(result);


    }

    @Test
    public void test3(){
        String string = "aaa";
        String substring = string.substring(1);
        System.out.println(substring);
    }

    @Test
    public void test4(){
        String string = "01101010";
        String substring = string.substring(1);
        System.out.println(substring);
    }

    @Test
    public void test5(){
        String str = "11111001";
        char c = str.charAt(0);
        byte b = (byte) c;
        System.out.println(b);
    }

    @Test
    public void test6(){
        String str = "1";
        Coding coding = new Coding();
        byte b = coding.str2Byte(str, 0);
        String string = Integer.toBinaryString(b);
        System.out.println(string);

        var i = 1;
        System.out.println(((Integer)i).intValue());
    }

    @Test
    public void test7(){
        String str = "01100010";

        Coding coding = new Coding();
        byte b = coding.str2Byte(str, 0);
        System.out.println(Integer.toBinaryString(b));
        System.out.println(b);

        String str2 = "10010101";
        byte b1 = coding.str2Byte(str2, 0);
        System.out.println(Integer.toBinaryString(b1));
        System.out.println(b1);

        String str3 = "00010";
        byte b2 = coding.str2Byte(str3, 3);
        System.out.println(Integer.toBinaryString(b2));
        System.out.println(b2);
    }

    @Test
    public void test8(){
        String str = "0101101010101";
        StringBuilder stringBuilder = new StringBuilder(str);

        String substring = stringBuilder.substring(0, 8);
        stringBuilder.delete(0,8);
        System.out.println(substring);
        System.out.println(stringBuilder);
    }

    @Test
    public void test9() throws IOException {
        byte[] bs = new byte[6];
        byte b = 99;
        for (int i=0; i<bs.length; i++){
            bs[i] = b;
            b--;
        }

        try (
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                FileOutputStream fileOutputStream = new FileOutputStream("test.dat")
        ){
            outputStream.write(bs,0,bs.length);
            outputStream.writeTo(fileOutputStream);
        }
    }

    @Test
    public void test10() throws IOException {
        byte[] bytes = new byte[6];
        try(
            FileInputStream inputStream = new FileInputStream("test.dat")
        ){
            inputStream.read(bytes);
        }
        for (int i=0;i<bytes.length;i++){
            System.out.println(bytes[i]);
        }
    }

    @Test
    public void test11() throws IOException {
        byte[] bs = new byte[6];
        byte b = 98;
        for (int i=0; i<bs.length; i++){
            bs[i] = b;
            b--;
        }
        try(
            FileOutputStream outputStream = new FileOutputStream("test.dat");
        ){
            outputStream.write(bs);
        }
    }

    @Test
    public void test12(){
        for (int i=0,j=5 ;i<10;i++){}
    }

    @Test
    public void test13(){
        double[] doubs = new double[10];
        Random random = new Random(47);
        for (int i=0;i<doubs.length;i++){
            doubs[i] = random.nextDouble();
        }

        for (double d : doubs){
            System.out.println(d);
        }
    }

    @Test
    public void test14(){
        int i=0;
        for (;i<4;i++){
            if (i==2){
                break;
            }
        }
        System.out.println(i);
    }

    // 测试label标签
    @Test
    public void test15(){
        int i = 0;
        outer:
            for (; true;){
                inner:
                    for (;i<10;i++){
                        System.out.println("i = " + i);
                        if (i == 2) {
                            System.out.println("continue");
                            continue;
                        }
                        if (i == 3) {
                            System.out.println("break");
                            i  ++;
                        }
                    }
            }
    }

    static class Fruit{
        public Fruit(){}
    }
    static class Apple extends Fruit implements Serializable{
        String name;

        @Override
        public String toString() {
            return "Apple{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    @Test
    public void test16() throws IOException, ClassNotFoundException {


        Apple a = new Apple();
        a.name = "a";
        System.out.println(a);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("apple_test"));
        oos.writeObject(a);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("apple_test"));
        Object o = ois.readObject();
        System.out.println(o);

    }
}
