package lv.tsi.java;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    //metod void nicego ne vozvrascaet
    public static void main(String[] args) {
        // write your code here
        String answer;
        int usernum;
        do {
            int myNum = rand.nextInt(100) + 1;
            boolean my_ok = false;
            System.out.println(" Random = " + myNum);

            for (int i = 1; i < 6; i++) {
                // int myNum = rand.nextInt(100)+1;
                System.out.println("Try nr # " + i);
                //int usernum = scan.nextInt();
                usernum = askNum();

                if (myNum == usernum) {
                    System.out.println("BINGO!!. Do You want to repeat? y/n");
                    my_ok = true;
                    break;
                } else if (myNum < usernum) {
                    System.out.println("Значение Random < " + usernum);
                } else {
                    System.out.println("Значение Random > " + usernum);
                }
            } // end for
            if (!my_ok) {
                System.out.println("You are lost. Do You want to repeat? y/n");
            }
            answer = askYN();
        } while (answer.equals("y"));
        System.out.println("Good bye");
        // end
    }

    //metod string vozvrascaet string
    static String askYN() {
        String answer;
        do {
            answer = scan.next();
            if (!answer.equals("y") && !answer.equals("n")) {
                System.out.println("You can enter only y or n");
                //continue; // continue - nacni cikl s nacala
            } else {
                break;
            } // zaversitj cikl

        }
        //while (!answer.equals("y") && !answer.equals("n") ) ;  // and
        while (true);  // beskonecnij ciks
        return answer;
    }

    static int askNum() {
        int  answer;
        do {
           try {
               answer = scan.nextInt();
               if (answer <1 || answer>100)  {
                   System.out.println(" You Number not correct. Insert a number between 1 - 100 ");
               }
               else {
                   return answer;
               }
           }
           catch (InputMismatchException e) {
               System.out.println("This isn't a number");
               scan.next();
               continue;
           }

           /*answer = scan.nextInt();
           if (answer <1 || answer>100)  {
               System.out.println(" You Number not correct. Insert a number between 1 - 100 ");
           }
           else {
               return answer;
           }*/
        }
        while (true);
    }
}
