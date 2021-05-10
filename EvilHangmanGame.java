package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Jeong Suk(Jerry) Han on 2018-02-03.
 */

public class EvilHangmanGame implements IEvilHangmanGame{
    private Set<String> dictionary = new TreeSet<>();
    private TreeSet<String> wordList = new TreeSet<>();
    public ArrayList<Character> charList = new ArrayList<>();

    int currentGuesses = 0;
    char C, c;

    Pattern word = new Pattern();
    Scanner sg = new Scanner(System.in);


    @Override
    public void startGame(File dictionary, int wordLength) throws IOException{
        String DFN = dictionary.toString();

        readFile(DFN);
        do{
            getWordList(wordLength);
            if(wordList.size()==0){
                System.out.println("");
                Scanner sc = new Scanner(System.in);
                wordLength = sc.nextInt();
            }

        }while(wordList.size()==0);
    }

    public void readFile(String DFN){
        try(Scanner s = new Scanner(new File(DFN))){
            while(s.hasNext()){
                dictionary.add(s.next().toLowerCase());
            }
            s.close();
        }catch (IOException e){
            System.out.println("");
        }
    }

    public void getWordList(int wordLength){
        for(String s : dictionary){
            if(s.length()==wordLength){
                wordList.add(s);
            }
        }
    }

    public int getGuesses(int guesses){
        currentGuesses = guesses;
        return currentGuesses;
    }




    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        int largest = 0;
        Map<Pattern, TreeSet<String>> wordMap = new HashMap<>();
        TreeSet<String> newList = new TreeSet<>();
        ArrayList<Pattern> list = new ArrayList<>();

        for(String s : wordList){
            Pattern ptn = new Pattern(guess, s);
            if(!wordMap.containsKey(ptn)){
                wordMap.put(ptn, new TreeSet<String>());
                wordMap.get(ptn).add(s);
            }
            else{
                wordMap.get(ptn).add(s);
            }
        }

        for(Pattern ptn : wordMap.keySet()){
            if(wordMap.get(ptn).size() > largest){
                largest = wordMap.get(ptn).size();
            }
        }

        for(Pattern ptn : wordMap.keySet()){
            if(wordMap.get(ptn).size() == largest){
                list.add(ptn);
            }
        }

        Collections.sort(list);

        for(String s : wordMap.get(list.get(0))){
            newList.add(s);
        }

        word.wordCombine(list.get(0));

        if(word.findChar(guess)){

        }
        else{
            System.out.println("Sorry, there are no "+guess+ "'s");
            currentGuesses--;
        }

        wordList = newList;

        return wordList;
    }
}
