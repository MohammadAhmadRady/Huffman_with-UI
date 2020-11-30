package huffman;

import java.io.*;
import java.util.*;

public class Decompress
{
    private static Map<String,Character> values = new HashMap<>();
    public Decompress() throws Exception // Constructor
    {
        Divide(takeInput());
    }
    private static String takeInput() throws Exception  // Take input from file
    {
        File file = new File("compress.txt");
        BufferedReader READ = new BufferedReader(new FileReader(file));
        int q=READ.read();
        String data = new String("");
        while(q != -1)
        {
            data+=(char)q;
            q=READ.read();
        }
        return data;
    }
    private static void Divide(String data) throws Exception // Divide the input from file
    {
        String[] allInput = data.split("/");            // A0 B11 C10 /0001011000
        String[] dictionary = allInput[0].split(" ");  //  A0 B11 C10
        char[]  code = allInput[1].toCharArray();           //   0001011000
        for(int i=0;i<dictionary.length;i++)
        {
            char first = dictionary[i].charAt(0);
            values.put(dictionary[i].substring(1),first);
        }
        originalData(code);
    }
    private static void originalData(char[] code) throws Exception // Convert the code to the original data
    {
        String willSent = "";
        String temp = "";
        for(int i=0;i<code.length;i++)
        {
            temp+=code[i];
            if(values.containsKey(temp))
            {
                willSent+=values.get(temp);
                temp="";
            }
        }
        System.out.println("The Original data: " + willSent );
        WriteToFile(willSent);
    }
    private static void WriteToFile(String data) throws Exception // Send the original data to the file
    {
        File obj1 = new File("decompress.txt");
        BufferedWriter w= new BufferedWriter(new FileWriter(obj1));
        w.write(data);
        w.close();
    }
}
//B111 C110 a10 A0 /00010110111100
//AAAaCBaA