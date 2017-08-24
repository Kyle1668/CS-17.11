package edu.srjc.aX.OBrien.Kyle.package_name;

//import java.util.Scanner;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String testString = "\nWhat's your name? ";
        Scanner kbd = new Scanner(System.in);

        System.out.print(testString);
        String yourName = kbd.nextLine();

        System.out.print("\nHello " + yourName + "!");

    }
}
