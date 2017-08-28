package com.memes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Melbert on 24/08/2017.
 */
public class VPanel extends JPanel {

    public static java.util.List<Integer> redrawIndex = new ArrayList<>();
    public static Integer width = 0;
    public static int pivot;
    public static int swapL;
    public static int swapR;
    static Integer offset = 25;
    Timer timer;
    private Integer x = offset;
    private Integer y = offset;

    public VPanel() {
        updater();
    }

    static void calculateAndSetWidth() {
        width = (450 - (offset * 2)) / Main.main.list.size();
        System.out.println("vPanel X: " + Visualiser.gui.vPanel.getX());
        //System.out.println("WIDTH: " + width);
    }

    public void updater() {
        timer = new Timer(1000 / 60, e -> repaint());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        int count = 0;
        g2d.drawLine(449, 20, 449, 280);
        //System.out.println("repainting" + redrawIndex.size());
        //System.out.println(width);
        while (count < Main.main.list.size()) {
            //System.out.println("Redrawing" + redrawIndex.size() + count);
            //g2d.clearRect(x + count * width, y, width, - 250 );
            g2d.fillRect(x + count * width, y, width, y + (((300 - offset * 2) / (Main.main.list.size() * 2) * Main.main.list.get(count))));
            count++;
        }
        if (pivot != 0) {
            g2d.setColor(Color.green);
            g2d.fillRect(x + pivot * width + 1, y, width , y + (((300 - offset * 2) / (Main.main.list.size() * 2) * Main.main.list.get(pivot))));
            //pivot = 0;
        }
        if (swapL != 0) {
            g2d.setColor(Color.red);
            g2d.fillRect(x + swapL * width, y, width, y + (((300 - offset * 2) / (Main.main.list.size() * 2) * Main.main.list.get(swapL))));
            //swapL = 0;
        }
        if (swapR != 0) {
            g2d.setColor(Color.red);
            g2d.fillRect(x + swapR * width, y, width, y + (((300 - offset * 2) / (Main.main.list.size() * 2) * Main.main.list.get(swapR))));
            //swapR = 0;
        }

    }
}

