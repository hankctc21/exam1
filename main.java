package game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Jeong Suk(Jerry) Han on 2018-02-03.
 */

public class main {
    public static void main(String[] args) throws IOException {
        File dFileName = new File(args[0]);
        int wordLength = Integer.parseInt(args[1]);
        int guesses = Integer.parseInt(args[2]);
        EvilHangmanGame EHG = new EvilHangmanGame();
        Set<String> currentList = new TreeSet<>();
        String w = " ";
        char C;
        char c = ' ';
        Pattern word = new Pattern();
        Scanner gs = new Scanner(System.in);

        do{
            do{
                if(wordLength < 2){
                    System.out.print("Enter higher word length: ");
                    Scanner s = new Scanner(System.in);
                    wordLength = s.nextInt();
                }
                EHG.startGame(dFileName,wordLength);
                if(EHG.currentGuesses==0){
                    EHG.getGuesses(guesses);
                }
            }while(!(wordLength > 1));

            if(guesses < 1){
                System.out.print("Enter higer guesses: ");
                Scanner s = new Scanner(System.in);
                guesses = s.nextInt();
            }

            do{
                //gueeses
                //charList
                //EHG.word
                //Enter
                w = gs.nextLine();
            }while(!(Character.isLetter(c)) || EHG.word.findDashes());
        }while(EHG.currentGuesses!=0);




    }


}
