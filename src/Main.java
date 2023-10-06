import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/*
Name: Alexys Octavio Veloz
Course: CNT 4714 Fall 2023
Assignment title: Project 2 – Multi-threaded programming in Java
Date: October 8, 2023
Class: Main
Description: a description of what the class provides would normally be expected.
*/

public class Main {
     static ArrayList<LinkedList> masterYardList = new ArrayList<>();
     static ArrayList<LinkedList> masterIncoming = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {
        //extract the yard file csv and add into an arraylist
        //LinkedList<Integer> YardConfig = new LinkedList<Integer>();
        File file1 = new File("in\\theYardFile.csv");
        Scanner scan1 = new Scanner(file1);
        scan1.useDelimiter("\n");
        String str;
        while (scan1.hasNextLine()){
            str = scan1.nextLine();
            String[] ArrayofString = str.split(",", 5);
            LinkedList<Integer> YardConfig = new LinkedList<Integer>();
            for (int i = 0; i< 5; i++) {
                YardConfig.add(Integer.parseInt(ArrayofString[i]));
            }
            //YardConfig.clear();
            masterYardList.add(YardConfig);
            //YardConfig.clear();

        }
        //extract the fleet file csv and add into an arraylist

        File file2 = new File("in\\theFleetFile.csv");
        Scanner scan2 = new Scanner(file2);
        scan2.useDelimiter("\n");
        while (scan2.hasNextLine()){
            str = scan2.nextLine();
            String[] ArrayofString = str.split(",", 3);
            LinkedList<Integer> incoming = new LinkedList<Integer>();
            for (int i = 0; i< 3; i++) {
                incoming.add(Integer.parseInt(ArrayofString[i]));
            }
            masterIncoming.add(incoming);
            //incoming.clear();

        }

        /* Testing to make sure info stayed where it needed
    for (int i = 0; i < masterYardList.size();i++) {
        for (int k = 0; k < masterYardList.get(i).size(); k++) {
            System.out.println(masterYardList.get(i).get(k));
        }
        System.out.printf(" masteryard size: %d , linkedList size: %d \n", masterYardList.size(),masterYardList.get(0).size());
    }

        for (int i = 0; i < masterIncoming.size();i++) {
            for (int k = 0; k < masterIncoming.get(0).size(); k++) {
                System.out.println(masterIncoming.get(i).get(k));
            }
            System.out.printf(" masterincoming size: %d , linkedList size: %d \n", masterIncoming.size(),masterIncoming.get(0).size());
        }
        */


    }
}