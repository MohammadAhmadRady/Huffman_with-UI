package huffman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Huffman
{
    private JButton compress;
    private JPanel panel1;
    private JButton decompress;

    public Huffman() {
        decompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    JOptionPane.showMessageDialog(null,"DeCompress will be performed");
                    Decompress ob = new Decompress();
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        compress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    JOptionPane.showMessageDialog(null,"Compress will be performed");
                    Compress ob = new Compress();
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("STANDARD HUFFMAN APP");
        frame.setContentPane(new Huffman().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
