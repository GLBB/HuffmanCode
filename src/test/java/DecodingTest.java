import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DecodingTest {
    @Test
     public void test1(){
        DeCoding decoding = new DeCoding();
        decoding.decode();
    }

    @Test
    public void test2() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("test.dat");
        fileOutputStream.write('\n');
        byte b = 97;
        fileOutputStream.write(b);
        fileOutputStream.close();
    }

    @Test
    public void test3() throws IOException {
        FileInputStream inputStream = new FileInputStream("test.dat");
        byte b1 = (byte) inputStream.read();
        byte b = (byte) inputStream.read();
        System.out.println(b);
        System.out.println(b1);
    }

    @Test
    public void test4(){
        byte b = 0x7f;
        int z = b&1;
        byte b1 = (byte) 0xf8;
        int z1 = b1&1;
        System.out.println(z +" " + z1);
    }

    @Test
    public void test5(){
        byte b = (byte) 0xfc;
        DeCoding decoding = new DeCoding();
        String string = decoding.byte2Str(b, 2);
        System.out.println(string);

    }

    @Test
    public void test6(){
        StringBuilder sb = new StringBuilder("bcd");
        System.out.println(sb);
        sb.insert(0,'a');
        System.out.println(sb);

    }

    private void change(Integer i){
        i = 2;
    }

    @Test
    public void test7(){
        Integer i = 1;
        change(i);
        System.out.println(i);
    }
}
