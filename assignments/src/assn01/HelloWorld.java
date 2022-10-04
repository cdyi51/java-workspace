package assn01;

import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

//public class HelloWorld {
//    public static void main(String[] args) {
//        System.out.println("Hello, World");
//    }
//}

class FactRecD {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("What number would you like to start with? ");
        int k = s.nextInt();
        System.out.println("The factorial of " + k + " is " + factorial(k) + ".");
        System.out.println("Thanks for running the program! Come again soon :)");
    }

    public static double factorial(int n) {
        double result = 1;
        for (int i = 1; i <= n; i ++) {
            result = result * i;
        }
        return result;
    }
}
