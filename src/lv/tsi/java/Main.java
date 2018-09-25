package lv.tsi.java;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static List<GameReult> results= new ArrayList<>();
    public static int max_len = 1;
    //metod void nicego ne vozvrascaet
    public static void main(String[] args) {
        // write your code here
        loadrezult();
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
            for (int i = 1; i <6; i++) {
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
                    max_len = Math.max(max_len, myname.length());

                    results.sort(Comparator.<GameReult>comparingInt(rr ->rr.triesCnt)
                            .thenComparingLong( rr -> rr.triestime)); //peresortiruem spisok
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
        saveresult();
        //System.out.println("Good bye " +myname+ ". You try count = "+trcnt);
        // end
    }
    // zagruzaem dannie iz faila v list
    private static void loadrezult() {
        File mfile = new File("top_ss.txt");
        try (Scanner in = new Scanner(mfile)) {
            while (in.hasNext()) {
            GameReult result = new GameReult();
            result.name = in.next();
            result.triesCnt = in.nextInt();
            result.triestime = in.nextLong();
            results.add(result);
             max_len = Math.max(max_len,result.name.length());

            }
        } catch (IOException e) {
            System.out.println("Cannot load file");
        }
    }
    // spisok sohranjaem v fail
    private static void saveresult() {
        File mfile = new File("top_ss.txt");
        try (PrintWriter out = new PrintWriter(mfile)) {
           // out.println("Hello world ");
            for (GameReult r : results)
            {
                out.printf("%-"+max_len+"s %3d %5d\n", r.name, r.triesCnt, r.triestime); // vmesto s - pervij p-tr; f - double; d - int i long; \n - perevod stroki
            }
        } catch (IOException e) {
            System.out.println("Cannot save to file"+e);
        }
    }
    private static int findmaxlen() {
        return results.stream()
                .map(r -> r.name)
                .map(n -> n.length())
                .max(Comparator.naturalOrder())
                .get();
    }
    private static void showresult() {
        //findmaxlen();
        results.stream()
                //.filter(r -> r.name.equals("Dima"))
               // .sorted(Comparator.<GameReult>comparingInt(r -> r.triesCnt)
               //                    .thenComparingLong( r -> r.triestime))
                .limit(5)
                .forEach(r -> {
            //System.out.printf("Player %s, you try counts - %d, game time - %d sec.\n", r.name, r.triesCnt, r.triestime);
            //        System.out.println("max_len="+max_len);
            System.out.printf("%-"+max_len+"s %3d %5d sec.\n", r.name, r.triesCnt, r.triestime);

                }); //spisok v stream i protalkivaet elementi i dlja kazdogo elementa cto delatj
    }
    private static void showresult_o () {
        int cnt = Math.min(3, results.size()); // vmesto niznego if
        //if (results.size() <3 )   { cnt = results.size();  }
        //for (GameReult r : results)
        for  (int i = 0; i < cnt; i++)
        {
            GameReult r = results.get(i);
           // System.out.println("Player "+ r.name+ ". You try counts = "+r.triesCnt+" , game time = "+r.triestime+ " sec.");
            System.out.printf("Player %s, you try counts - %d, game time - %d sec.\n", r.name, r.triesCnt, r.triestime);
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
