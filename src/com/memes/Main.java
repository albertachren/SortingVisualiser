package com.memes;

import javax.sound.midi.Synthesizer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static com.memes.Visualiser.gui;

//TODO DEBUG
public class Main {
    private static final long SORTING_DELAY = 5;
    public static Main main;
    public List<Integer> list = new ArrayList<>();
    Integer size;
    Scanner scan = new Scanner(System.in);
    File results = new File("C:\\Users\\Melbert\\Desktop\\results.txt");
    FileWriter fw;
    Synthesizer synth;
    int recursion = 0;
    int input = 0;


    public static void main(String[] args) {
        main = new Main();
        Visualiser.main(args);
        main.waitInput();

       /* try {
            main.synth  = MidiSystem.getSynthesizer();
            main.synth.open();
            MidiChannel[] channels = main.synth.getChannels();
            MidiChannel channel = channels[0];
            channel.noteOn(60, 10);
            Thread.sleep(500);
            channel.noteOff(60);
            main.synth.close();
        } catch (MidiUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }*/


    }

    static synchronized void mainWait() {

        try {
            Main.class.wait();
        } catch (InterruptedException e) {
        }
    }

    static synchronized void mainWake() {
        Main.class.notify();

    }

    private void bubbleSort(List<Integer> list) {
        addRandom(list, 100, false);
        boolean swapped = true;
        int lenght = list.size();


        //System.out.println("Bubble iteration: " + k);
        do {

            swapped = false;
            for (int i = 1; i < lenght; i++) {
                try {
                    Thread.sleep(SORTING_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (list.get(i - 1) > list.get(i)) {
                    Collections.swap(list, i - 1, i);
                    VPanel.swapL = i;
                    System.out.println(i - 1);
                    swapped = true;
                }
                if (swapped) {
                    //VPanel.pivot = i;
                }
            }

        } while (swapped);
        VPanel.swapL = 0;
        //VPanel.pivot = 0;
    }

    private void quicksortSetup() {
        int reset = 0;
        do {

            addRandom(list, size, true);


            for (int i = 0; i < main.list.size(); i++) {
                VPanel.redrawIndex.add(i);
                //System.out.println(i + " added to redrawIndex");
            }


            long timeStart = System.currentTimeMillis();

            quicksort(list, 0, list.size() - 1, false);

            VPanel.swapL = 0;
            VPanel.swapR = 0;
            VPanel.pivot = 0;

            long time = System.currentTimeMillis() - timeStart;
            System.out.println("Millis: " + time);
            System.out.println("Millis per size: " + time / list.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sorted list: ");
            printList(list);

            System.out.println("Confirmed sorted: " + isSorted(list));
            //System.out.println("");
            list.clear();

        } while (reset == 1);
    }

    private void waitInput() {
        boolean reset = true;
        do {
            gui.button1.setEnabled(true);
            gui.button3.setEnabled(true);
            gui.button2.setEnabled(true);
            main.input = 0;
            list.clear();


            mainWait();
            switch (main.input) {
                case 1:
                    System.out.println("Bubble started");
                    main.bubbleSort(main.list);
                    System.out.println("Bubble finished");
                    System.out.println("Confirmed sorted: " + isSorted(list));
                    break;
                case 0:
                    main.quicksortSetup();
                    break;
                default:
                    break;
            }
        } while (reset);
    }

    private void addRandom(List<Integer> list, Integer i, boolean verbose) {
        int count = 0;
        do {
            Integer randomNumber = (int) (Math.random() * i * 2);
            list.add(randomNumber);
            if (verbose) {
                System.out.println(randomNumber);
            }
            count++;
        } while (count < i);
        VPanel.calculateAndSetWidth();
    }

    private void speedTest(List<Integer> list) {
        String resultString = "";

        List<Integer> sizes = new ArrayList<>();
        sizes.add(10);
        sizes.add(20);
        sizes.add(50);
        sizes.add(100);
        sizes.add(300);
        sizes.add(500);
        sizes.add(1000);
        sizes.add(3000);
        try {
            fw = new FileWriter(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Integer i : sizes) {
            addRandom(list, i, false);
            long timeStart = System.currentTimeMillis();
            quicksort(list, 0, list.size() - 1, false);
            long time = System.currentTimeMillis() - timeStart;
            resultString = resultString + "[" + i + "]: " + time + " " + isSorted(list) + " ";
            list.clear();
        }
        System.out.println("Test finished");
        try {
            System.out.println("Writing to file: " + results.getPath());
            System.out.println(resultString);
            fw.append(resultString);
            fw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void quicksort(List<Integer> list, int leftBound, int rightBound, boolean verbose) {
        int memLeftBound = leftBound;
        int memRightBound = rightBound;
        int lPointer;
        int rPointer;
        Random random = new Random();
        int pivot = rightBound;
        VPanel.pivot = pivot;
        boolean loop = true;

        lPointer = leftBound;
        rPointer = rightBound;

        try {
            Thread.sleep(SORTING_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (verbose) {
            System.out.println("pointers: " + lPointer + " & " + rPointer);
            System.out.println("pivot: " + pivot + " pivot element: " + list.get(pivot));
        }
        int lPointerElement = list.get(lPointer);
        int rPointerElement = list.get(rPointer);
        if (lPointer < rPointer) {
            int p = partition(list, lPointer, rPointer);

            /*Thread sorter = new Thread(() -> quicksort(list, memLeftBound, p, verbose));
            sorter.run();

            Thread sorter2 = new Thread(() -> quicksort(list, p + 1, memRightBound, verbose));
            sorter2.run();
*/
            quicksort(list, memLeftBound, p, verbose);
            quicksort(list, p + 1, memRightBound, verbose);

            if (verbose) {
                System.out.println("swapped elements" + " " + list.get(lPointer) + "&" + list.get(rPointer));
            }
        }
    }

    private int partition(List<Integer> list, Integer lPointer, Integer rPointer) {
        Integer pivotElement = list.get(lPointer);
        Integer pivot = rPointer;
        VPanel.pivot = rPointer;
        int i = lPointer - 1;
        int j = rPointer + 1;
        while (true) {
            do {
                i++;
            } while (list.get(i) < pivotElement);
            do {
                j--;
            } while (list.get(j) > pivotElement);
            if (i >= j) {
                return j;
            }
            int lPointerElement = list.get(i);
            int rPointerElement = list.get(j);
            list.set(i, rPointerElement);
            list.set(j, lPointerElement);
            VPanel.swapL = i;
            VPanel.swapR = j;

            try {
                Thread.sleep(SORTING_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


    private boolean isSorted(List<Integer> list) {
        boolean sorted = true;
        for (int i = 0; i < list.size() - 1; i++) {
            if (!(list.get(i) <= list.get(i + 1))) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }
}
