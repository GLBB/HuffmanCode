package cn.gl;

import org.junit.Test;

import java.io.*;

public class ObjectWriteTest {
    @Test
    public void test1() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("oostest"));
        String ss = null;
        oos.writeObject(ss);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("oostest"));
        int available = ois.available();
        System.out.println(available);

        String str = (String) ois.readObject();

        ois.close();

        System.out.println(str);
    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("oostest"));
        String s1 = null;
        String s2 = "111";
        oos.writeObject(s1);
        oos.writeObject(s2);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("oostest"));
        String st1 = (String) ois.readObject();
        String st2 = (String) ois.readObject();
        ois.close();

        System.out.println(st1);
        System.out.println(st2);
    }

    static class People implements Serializable{
        int age;
        People people;
    }
    @Test
    public void test3() throws IOException, ClassNotFoundException {


        People people = new People();
        people.age = 1;
        people.people = new People();
        people.people.age = 2;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("people_test"));
        oos.writeObject(people);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("people_test"));
        People p = (People) ois.readObject();
        ois.close();

        System.out.println(p.age);
        System.out.println(p.people.age);
    }

    static class Addr implements Serializable{
        String addr;
    }
    static class Peopel implements Serializable{
        int age;
        Addr addr;
    }
    @Test
    public void test4() throws IOException, ClassNotFoundException {


        Peopel p = new Peopel();
        p.age = 1;
        p.addr = new Addr();
        p.addr.addr = "beego";

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("p2_test"));
        oos.writeObject(p);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("p2_test"));
        Peopel pr = (Peopel) ois.readObject();
        ois.close();

        System.out.println(pr.age);
        System.out.println(pr.addr.addr);
    }
}
