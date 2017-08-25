package com.memes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Melbert on 24/08/2017.
 */
public class Visualiser {
    public static Visualiser gui;
    JButton button1;
    JButton button2;
    JButton button3;
    JPanel Jpanel;
    VPanel vPanel;

    public Visualiser() {

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main.size = 50;
                Main.main.input = 0;
                Main.mainWake();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main.size = 100;
                Main.main.input = 0;
                Main.mainWake();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main.input = 1;
                Main.mainWake();
            }
        });
    }

    public static void main(String[] args) {
        gui = new Visualiser();
        JFrame frame = new JFrame("Visualiser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("LFEx");
        }
        frame.setContentPane(new Visualiser().Jpanel);
        //gui.vPanel.add(new vPanel());
        frame.setUndecorated(true);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        //gui.vPanel.repaint();

    }


}
