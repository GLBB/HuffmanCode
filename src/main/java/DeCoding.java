import cn.gl.bean.HuffmanTree;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DeCoding {

    private HuffmanTree.Node readTree(){
        HuffmanTree.Node root = null;
        try(
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("huffman"));
        ){
            root = (HuffmanTree.Node) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return root;
    }

    private ArrayList readCodes(){
        byte[] codes = null;
        ArrayList list = new ArrayList();
        try(
            FileInputStream fis = new FileInputStream("codefile");
            DataInputStream dis = new DataInputStream(fis);
                ){
            Integer surplus = dis.readInt();
            codes = dis.readAllBytes();
            list.add(surplus);
            list.add(codes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void decode(){
        HuffmanTree.Node root = readTree();
        ArrayList list = readCodes();
        int surplus = (int) list.get(0);
        byte[] codeBytes = (byte[]) list.get(1);
        String codes = bytes2Str(codeBytes, surplus);
        String result = getResult(codes, root);
        writeFile(result);
    }

    private void writeFile(String result){
        try(
            FileWriter fw = new FileWriter("textfile");
                ){
            fw.write(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getResult(String codes, HuffmanTree.Node root){
        char[] codesChar = codes.toCharArray();
        StringBuffer result = new StringBuffer();
        HuffmanTree.Node node = root;
        // 为什么 i<= codesChar.length
        // 因为 先判断node.left==null && node.right==null，所以要添加最后一个字符就要 i<=codesChar.length
        for (int i=0; i<=codesChar.length; i++) {
            if (node.left==null && node.right==null) {
                char c = (char) node.element;
                result.append(c);
                node=root;
                if (i==codesChar.length) {
                    break;
                }
            }
            if (codesChar[i] == '0' && node.left != null) {
                node = node.left;
            }else if(codesChar[i] == '1' && node.right != null){
                node = node.right;
            }
        }
        return result.toString();

    }


//    public void decode(String fileName){
//        byte[] bytes = null;
//        byte surplus = 0;
//        HashMap<Byte,String> codes = new HashMap<>();
//        try(
//            FileInputStream inputStream = new FileInputStream(fileName);
//            DataInputStream dataOutputStream = new DataInputStream(inputStream)
//        ){
//            int length = dataOutputStream.readInt();
//            // 读取 '\n'
//            inputStream.read();
//            bytes = new byte[length];
//            inputStream.read(bytes);
//            // 读取 '\n'
//            inputStream.read();
//            surplus = (byte) inputStream.read();
//            inputStream.read();
//            while (inputStream.available() !=0) {
//                byte key = (byte) inputStream.read();
//                int valueLength = dataOutputStream.readInt();
//                byte[] value = new byte[valueLength];
//                inputStream.read(value);
//                String strValue = new String(value);
//                codes.put(key,strValue);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String string = bytes2Str(bytes,surplus);
//        String result = decodeString(string, codes);
//        System.out.println(result);
//    }

    public String bytes2Str(byte[] bytes, int surplus){
        StringBuilder sb = new StringBuilder();
        String str = null;
        for (int i=0; i<bytes.length; i++){
            if (i == bytes.length -1){
                str = byte2Str(bytes[i],surplus);
                sb.append(str);
                break;
            }
            str = byte2Str(bytes[i],0);
            sb.append(str);
        }
        return sb.toString();
    }

    public String byte2Str(byte b,int surplus){
        int x = surplus;
        while (x!=0){
            x--;
            b = (byte) (b >>1);
        }
        StringBuilder sb = new StringBuilder();
        // 移位七次
        for (int i=0; i<8-surplus; i++){
            int zeroOrOne = b & 1;
            if (zeroOrOne==0){
                sb.insert(0,'0');
            }else if (zeroOrOne == 1){
                sb.insert(0,'1');
            }
            b = (byte) (b>>1);
        }
        return sb.toString();
    }


    private String decodeString(String str,HashMap<Byte,String> code){
        Set<Map.Entry<Byte, String>> entries = code.entrySet();

        StringBuilder stringBuilder = new StringBuilder();

        StringBuilder result = new StringBuilder();

        while (str.length()!=0){
            stringBuilder.append(str.charAt(0));
            str = str.substring(1);

            while (!code.containsValue(stringBuilder.toString())){
                stringBuilder.append(str.charAt(0));
                str = str.substring(1);
            }

            for (Map.Entry<Byte, String> entry : entries){
                Byte key = entry.getKey();
                String value = entry.getValue();

                char ch = (char) key.byteValue();


                if (value.equals(stringBuilder.toString())){
                    result.append(ch);
                    stringBuilder = new StringBuilder();
                    break;
                }
            }
        }
        return result.toString();
    }
}
