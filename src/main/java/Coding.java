import cn.gl.bean.HuffmanTree;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coding {

    private String readFile(String fileName) {
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))
                ){
            byte[] bytes = bis.readAllBytes();
            String str = new String(bytes, "UTF-8");
            return str;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void fileCoding(String fileName) {
        String str = readFile(fileName);
        int[] frequences = getFrequences(str);
        HuffmanTree huffmanTree = new HuffmanTree();
        HuffmanTree.Node root = huffmanTree.getHuffmanTree(frequences);
        writeTree(root);
        HashMap<Byte, String> code = huffmanTree.getCode();
        String coding = replaceString(str, code);

        // 打印代码文件，并输入到codeprint中
        outer:
        for (int i = 0; i < coding.length(); i++) {
            for (int j = 0; j < 50; j++) {
                if (i*50 + j <coding.length()){
                    System.out.print(coding.charAt(i*50+j));
                }else {
                    break outer;
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.flush();
        write2CodePrint(coding);

        // 打印huffman树
        printTree(huffmanTree);

        byte[] codeBytes = str2ByteArray(coding);
        int surplus = getSurplus(coding);
        writeBytes(codeBytes, surplus);
    }

    private void printTree(HuffmanTree huffmanTree){
        huffmanTree.printTree();
        PrintStream ps = System.out;
        try {
            PrintStream new_ps = new PrintStream("treeprint");
            System.setOut(new_ps);
            huffmanTree.printTree();
            System.setOut(ps);
            new_ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void write2CodePrint(String coding) {
        try(
                FileWriter fw = new FileWriter("codeprint")
                ){
            fw.write(coding);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void writeBytes(byte[] codeBytes, int surplus) {
          try(
                  FileOutputStream fos = new FileOutputStream("codefile");
                  DataOutputStream dos = new DataOutputStream(fos);
          ){
              dos.writeInt(surplus);
              dos.write(codeBytes);
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
    }

//    public String readFile(String fileName){
//        StringBuilder result = new StringBuilder();
//        byte[] bytes = new byte[1024];
//        int length;
//        try (
//             FileInputStream stream = new FileInputStream(fileName)
//                ){
//             while (stream.read(bytes)!=-1){
//                 String str = new String(bytes,"UTF-8");
//                 result.append(str);
//             }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result.toString();
//    }

//    public void fileCoding(String fileName){
//        String str = readFile(fileName);
//        int[] frequences = getFrequences(str);
//        HuffmanTree huffmanTree = new HuffmanTree();
//        HuffmanTree.Node root = huffmanTree.getHuffmanTree(frequences);
//
//        writeTree(root);
//        int nodeSize = huffmanTree.getNodeSize();
//        System.out.println(nodeSize);


//        HashMap<Byte, String> code = huffmanTree.getCode();
//
//        String string = replaceString(str,code);
//
//        byte[] bytes = str2ByteArray(string);
//        int surplus = getSurplus(string);
//        writeFile(bytes,surplus,fileName,code);
//    }

    private void writeTree(HuffmanTree.Node root) {
        try(
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("huffman"))
                ){
            oos.writeObject(root);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeTree(HuffmanTree.Node node,ObjectOutputStream oos) {
        if (node != null) {
            writeNode(node, oos);
            writeTree(node.left, oos);
            writeTree(node.right, oos);
        }
    }

    /**
     * node 具有的属性<br/>
     *         byte element;<br/>
     *         int weight;<br/>
     *         Node left;<br/>
     *         Node right;<br/>
     *         String code = "";<br/>
     */
    private void writeNode(HuffmanTree.Node node,ObjectOutputStream oos) {
        if (node==null) {
            return;
        }
        try {
            oos.writeObject(node);
            oos.writeObject(node.left);
            oos.writeObject(node.right);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // surplus byte后面补0的个数
    private int getSurplus(String str){
        int surplus = 8 - str.length()%8;
        if (surplus == 8)
            return 0;
        else
            return surplus;
    }

    private byte[] str2ByteArray(String str){
        if (str == null || str.length()==0){
            return null;
        }

        int length = str.length();
        int arrLength;
        int surplus = 0;
        if (length%8==0){
            arrLength = length/8;
        }else {
            surplus = 8 - length % 8;
            arrLength = length/8 + 1;
        }

        byte[] bytes = new byte[arrLength];
        StringBuilder sb = new StringBuilder(str);
        for (int i=0; i<bytes.length; i++){
            if (i==bytes.length-1){
                String string = sb.toString();
                bytes[i] = str2Byte(string, surplus);
                break;
            }
            String substring = sb.substring(0, 8);
            sb.delete(0,8);
            bytes[i] = str2Byte(substring,0);
        }

        return bytes;
    }

    public byte str2Byte(String str,int surplus){
        if (str.length()>8)
            throw new RuntimeException("字符串必须在八位以内");
        byte b = 0;
        while (str.length()>0){
            byte b1 = (byte) str.charAt(0);
            str = str.substring(1);
            if (b1 == 48){
                b = (byte) (b | 0);
            }else if (b1 == 49) {
                b = (byte) (b | 1);
            }
            // 最后一位不移动
            if (str.length() != 0) {
                b = (byte) (b << 1);
            }
        }
        while (surplus>0){
            b = (byte) (b<<1);
            surplus--;
        }

        return b;
    }

    private String replaceString(String str, HashMap<Byte,String> code) {
        StringBuilder stringBuilder = new StringBuilder(str);
        Set<Map.Entry<Byte, String>> entries = code.entrySet();
        for (Map.Entry<Byte, String> entry : entries){
            Byte key = entry.getKey();
            String value = entry.getValue();
            char ch = (char) key.byteValue();

            Pattern pattern = null;
            if (ch=='?'){
                pattern = Pattern.compile("\\?");
            }else if (ch=='.'){
                pattern = Pattern.compile("\\.");
            } else {
                pattern = Pattern.compile(ch + "");
            }

            Matcher matcher = pattern.matcher(stringBuilder);
            if (matcher.find()){
                String string = matcher.replaceAll(value);
                stringBuilder = new StringBuilder(string);
            }
        }
        return stringBuilder.toString();
    }

    private int[] getFrequences(String str){
        int[] frequences = new int[256];
        for (int i = 0; i<str.length();i++){
            char ch = str.charAt(i);
            frequences[ch]++;
        }
        return frequences;
    }

    @Deprecated
    private void writeFile(byte[] bytes,int surplus,String fileName,HashMap<Byte,String> code){
        String[] split = fileName.split("\\.");
        fileName = split[0] + ".huffman";
        Set<HashMap.Entry<Byte,String>> entries = code.entrySet();
        try(
                FileOutputStream outputStream = new FileOutputStream(fileName);
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        ){
            dataOutputStream.writeInt(bytes.length);
            outputStream.write('\n');
            outputStream.write(bytes);
            outputStream.write('\n');
            outputStream.write(surplus);
            outputStream.write('\n');
            for (HashMap.Entry<Byte,String> entry : entries){
                outputStream.write(entry.getKey());
                dataOutputStream.writeInt(entry.getValue().getBytes().length);
                outputStream.write(entry.getValue().getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
