package lv.tsi.java;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
	// write your code here
       String answer;
     do {
         int myNum = rand.nextInt(100) + 1;
         boolean my_ok = false;
         System.out.println(" Random = " + myNum);

         for (int i = 1; i < 6; i++) {
             // int myNum = rand.nextInt(100)+1;
             System.out.println("Try nr # " + i);
             int usernum = scan.nextInt();

             if (myNum == usernum) {
                 System.out.println("BINGO!!. Do You want repeat? yes - Y");
                 my_ok = true;
                 break;
             } else if (myNum < usernum) {
                 System.out.println("Значение " + myNum + " < " + usernum);
             } else {
                 System.out.println("Значение " + myNum + " > " + usernum);
             }
         } // end for
         if (!my_ok) {
             System.out.println("You are lost. Do You want repeat? yes - Y");
         }

          answer=scan.next();
     } while (answer.equals("Y"));
    // end
    }
}
