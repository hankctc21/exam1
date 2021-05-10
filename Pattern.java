package game;


import java.util.ArrayList;

/**
 * Created by Jeong Suk(Jerry) Han on 2018-02-04.
 */

public class Pattern implements Comparable<Pattern>{
    private ArrayList<Character> pattern;

    public Pattern(){
        pattern = new ArrayList<>();
    }

    public Pattern(char letter, String word){
        pattern = new ArrayList<>();
        for(char c : word.toCharArray()){
            if(letter == c){
                pattern.add(c);
            }
            else{
                pattern.add('-');
            }
        }
    }

    public void wordCombine(Pattern newP){
        if(this.pattern.size() == 0){
            this.pattern = new ArrayList<>();
            this.pattern.addAll(newP.pattern);
            return;
        }

        for(int i = 0; i < newP.pattern.size(); i++){
            if(newP.pattern.get(i) != '-'){
                this.pattern.set(i, newP.pattern.get(i));
            }
        }
    }

    public boolean findDashes(){
        for(int i = 0; i < pattern.size(); i++){
            if(pattern.get(i)=='-'){
                return true;
            }
        }
        return false;
    }

    public boolean findChar(char c){
        int count = 0;
        for(int i = 0; i < pattern.size(); i++){
            if(pattern.get(i)==c){
                count++;
            }
        }
        if(count > 0){
            System.out.println("Yes, there is "+count+ " "+c+"'s");
            return true;
        }

        return false;
    }

    public boolean allDashes(){
        boolean all = true;
        for(char c : pattern){
            if(c != '-'){
                all = false;
                break;
            }
        }
        return all;
    }

    public int occurenceOfLetter(){
        int count = 0;
        for(char c : pattern){
            if(c != '-'){
                count++;
            }
        }
        return count;
    }

    public int indexOfFirstLetter(){
        int i = 0;

        for(char c : pattern){
            if(c != '-'){
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public String toString(){
        return pattern.toString();
    }

    @Override
    public int hashCode(){
        return pattern.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }

        if(o == this){
            return true;
        }

        if(o.getClass() != this.getClass()){
            return false;
        }

        Pattern p = (Pattern) o;

        if(this.pattern.equals(p.pattern)){
            return true;
        }

        return false;
    }


    @Override
    public int compareTo( Pattern p) {
        if(this.equals(p)){
            return 0;
        }

        if(allDashes()){
            return -1;
        }

        int thisCount = this.occurenceOfLetter();
        int otherCount = p.occurenceOfLetter();

        if(thisCount < otherCount){
            return -1;
        }

        else if(thisCount > otherCount){
            return 1;
        }

        int thisFirst = this.indexOfFirstLetter();
        int otherFirst = p.indexOfFirstLetter();

        if(thisFirst > otherFirst){
            return -1;
        }

        else if(thisFirst < otherFirst){
            return 1;
        }

        return 0;
    }
}
