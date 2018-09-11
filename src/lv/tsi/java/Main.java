package lv.tsi.java;

import java.util.*;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static List<GameReult> results= new ArrayList<>();

    //metod void nicego ne vozvrascaet
    public static void main(String[] args) {
        // write your code here
        String answer;
        int usernum;
        String myname;
        int trcnt =5;
        do {
            int myNum = rand.nextInt(100) + 1;
            boolean my_ok = false;
            System.out.println(" Random = " + myNum);
            System.out.println(" Insert you name :" );
            myname = scan.next();
            long t1 = System.currentTimeMillis();
            //System.out.println(" Helou " +myname+ "!!!");
            for (int i = 1; i < trcnt +1; i++) {
                // int myNum = rand.nextInt(100)+1;

                //System.out.println("Try nr # " + i);
                //int usernum = scan.nextInt();
                usernum = askNum();

                if (myNum == usernum) {
                    System.out.println(myname+" - BINGO!!. Do You want to repeat? y/n");
                    GameReult r =new GameReult();
                    trcnt = i;
                    long t2 = System.currentTimeMillis();
                    r.name=myname;
                    r.triesCnt=i;
                    r.triestime=(t2-t1)/1000;
                    results.add(r);
                    my_ok = true;
                    break;
                } else if (myNum < usernum) {
                    System.out.println(" Random < " + usernum);
                } else {
                    System.out.println(" Random > " + usernum);
                }
            } // end for
            if (!my_ok) {
                System.out.println("You are lost. Do You want to repeat? y/n");
            }
            answer = askYN();
        } while (answer.equals("y"));
        showresult();
        //System.out.println("Good bye " +myname+ ". You try count = "+trcnt);
        // end
    }

    private static void showresult() {
        for (GameReult r : results)
        {
            System.out.println("Player "+ r.name+ ". You try count = "+r.triesCnt+" , game time = "+r.triestime+ " sec.");
        }
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
