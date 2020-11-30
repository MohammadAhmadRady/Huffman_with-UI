package huffman;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.*;

class Node implements Comparator<Node>
{
    float freq;
    char sym;
    Node left;
    Node right;
    @Override
    public int compare(Node F,Node S)
    {
        return (int) F.freq - (int) S.freq ;
    }
}
public class Compress
{
    private static Map<Character,Integer> values = new HashMap<>();
    private static ArrayList<Character> alpha = new ArrayList<>();
    private static ArrayList<Integer> freq = new ArrayList<>();
    private static Map<Character,String> LAST = new HashMap<>();

    private static String input = "";
    private static String willSent = "";

    public Compress() throws Exception // Constructor
    {
        input = takeInput();
        Building();
        finish();
    }
    private static String takeInput() throws Exception  //2
    {
        File file = new File("data.txt");
        BufferedReader READ = new BufferedReader(new FileReader(file));
        int q=READ.read();
        String data = new String("");
        while(q != -1)
        {
            data+=(char)q;
            q=READ.read();
        }
        for(int i=0;i<data.length();i++)
        {
            char Ctemp = data.charAt(i);
            if(!values.containsKey(Ctemp))
                values.put(Ctemp,1);
            else
            {
                int Itemp = values.get(Ctemp);
                values.put(Ctemp,++Itemp);
            }
            if(!alpha.contains(Ctemp))
                alpha.add(Ctemp);
        }
        for (int i=0;i<values.size();i++)
        {
            char Ctemp = alpha.get(i);
            int Itemp = values.get(Ctemp);
            freq.add(Itemp);
        }
        return data;
    }
    private static void Building()
    {
        PriorityQueue<Node> Q = new PriorityQueue<Node>(freq.size(),new Node());
        for(int i=0;i<freq.size();i++)
        {
            Node temp = new Node();
            temp.sym = alpha.get(i);
            temp.freq = freq.get(i);
            temp.left = null;
            temp.right = null;
            Q.add(temp);
        }
        Node root = new Node();
        while (Q.size()!=1)
        {
            Node Left = Q.poll();
            Node Right = Q.poll();
            Node temp = new Node();
            temp.freq = Left.freq + Right.freq;
            temp.left = Left;
            temp.right = Right;
            temp.sym = '%';
            root = temp;
            Q.add(temp);
        }
        StoringCode(root,"");
        willSent+='/';
    }
    private static void StoringCode(Node root, String code)
    {
        if (root.left  == null && root.right == null && Character.isLetter(root.sym) && root.sym!='%')
        {
            willSent += root.sym+code+" ";
            LAST.put(root.sym,code);
            return;
        }
        StoringCode(root.right, code + "1");
        StoringCode(root.left, code + "0");
    }
    private static void finish() throws Exception
    {
        for (int i=0;i<input.length();i++)
        {
            char c = input.charAt(i);
            willSent+=LAST.get(c);
        }
        System.out.println("The Compressed data: " + willSent);
        WriteToFile(willSent);
    }
    private static void WriteToFile(String data) throws Exception
    {
        File obj1 = new File("compress.txt");
        BufferedWriter w= new BufferedWriter(new FileWriter(obj1));
        w.write(data);
        w.close();
    }
}