package cn.gl;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileTest {

    @Test
    public void testReadFile() throws IOException {
        FileInputStream stream = new FileInputStream("temp.txt");
        byte[] bytes = new byte[1024];
        int result;
        String str ="";
        while ((result=stream.read(bytes))!=-1){
            String string = new String(bytes,"GBK");
            str+=string;
        }
        System.out.println(str);
    }

    @Test
    public void testReadFile2() throws IOException {
        FileReader reader = new FileReader("temp.txt");
        char[] chs = new char[1024];
        String result = "";
        while (reader.read(chs)!=-1){
            String string = new String(chs);
            result += string;
        }
        System.out.println(result);
    }

    @Test
    public void testReadFile3() throws IOException {
        FileInputStream stream = new FileInputStream("chinese");
        byte[] bytes = new byte[1024];
        int result;
        String str ="";
        while ((result=stream.read(bytes))!=-1){
            String string = new String(bytes,"UTF-8");
            str+=string;
        }
        System.out.println(str);
    }
}
