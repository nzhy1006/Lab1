package lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import java.awt.*;

import javax.swing.JFrame;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Lab1 {
	static Graph a;
	public static void main(String[] args) {
		
		//Graph a = new Graph();
		//a.readFile("/home/hhk/Desktop/1.txt");
		
        //FunT.showGra(a);
		//System.out.println(FunT.newtext(a, "Seek to explore new and exciting synergies"));
		//System.out.println(FunT.shortestpath(a, "to", "new"));
		//System.out.println(FunT.random(a));
		//FunT.ranGo(a);
		while (true) {
			Scanner sc = new Scanner(System.in); 
            System.out.println("-r : read a file\n-s : show graph\n-b : birdge words\n-n : new text\n"
            		+ "-p : shortest path\n-g : random go");
            String ss = sc.nextLine();
            if (ss.equals("-r") == true) {
            	System.out.println("input file path");
            	String ns = sc.nextLine();
            	a = new Graph();
            	a.readFile(ns);
            	System.out.println("read over^");
            }
            if (ss.equals("-s") == true) {
            	FunT.showGra(a);
            }
            if (ss.equals("-b") == true) {
            	System.out.println("input string one");
            	String s1 = sc.nextLine();
            	System.out.println("input string two");
            	String s2 = sc.nextLine();
            	int ans = FunT.bridge(a, s1, s2);
            	System.out.println(FunT.strS);
            }
            if (ss.equals("-n") == true) {
            	System.out.println("input your words");
            	String ns = sc.nextLine();
            	System.out.println(FunT.newtext(a, ns));
            }
            if (ss.equals("-p") == true) {
            	System.out.println("input string one");
            	String s1 = sc.nextLine();
            	System.out.println("input string two");
            	String s2 = sc.nextLine();
            	System.out.println(FunT.shortestpath(a, s1, s2));
            }
            if (ss.equals("-g") == true) {
            	FunT.ranGo(a);
            }
		}
	}
}
