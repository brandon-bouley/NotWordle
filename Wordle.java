/*
 * Wordle.java 
 * 
 * An console-based implementation of a popular word-guessing game
 * 
 * starter code: Computer Science 112 staff (cs112-staff@cs.bu.edu)
 *
 * completed by: Brandon Bouley, bbouley@bu.edu
 */

import java.util.*;

public class Wordle {
    // the name of a file containing a collection of English words, one word per line
    public static final String WORD_FILE = "words.txt";

    /*
     * printWelcome - prints the message that greets the user at the beginning of the game
     */  
    public static void printWelcome() {
        System.out.println();   
        System.out.println("Welcome to Wordle!");
        System.out.println("The mystery word is a 5-letter English word.");
        System.out.println("You have 6 chances to guess it.");
        System.out.println();
    }
    
    /*
     * initWordList - creates the WordList object that will be used to select
     * the mystery work. Takes the array of strings passed into main(),
     * since that array may contain a random seed specified by the user 
     * from the command line.
     */
    public static WordList initWordList(String[] args) {
        int seed = -1;
        if (args.length > 0) {
            seed = Integer.parseInt(args[0]);
        }

        return new WordList(WORD_FILE, seed);
    }

    /*
     * readGuess - reads a single guess from the user and returns it
     * inputs:
     *   guessNum - the number of the guess (1, 2, ..., 6) that is being read
     *   console - the Scanner object that will be used to get the user's inputs
     */
    public static String readGuess(int guessNum, Scanner console) {
        String guess;
        do {
            System.out.print("guess " + guessNum + ": ");
            guess = console.next();
        } while (! isValidGuess(guess));

        return guess.toLowerCase();
    }

    

    public static boolean includes(String s, char c){
        String c1= "" + c;
        return(s.contains(c1));
    }

    public static boolean isAlpha(String s){
        

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!Character.isAlphabetic(c)){
                return false;
            }

        }
        return true;
    }

    public static int numOccur(char c, String s){
        int count=0;
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            if(c1==c){
                count++;
            }
    }

    return count;
}

    public static int numInSamePosn(char c, String s1, String s2){
        int count=0;
        for (int i = 0; i < s1.length(); i++) {

            if (s1.charAt(i)==c && s2.charAt(i)==c){
                count++;
            }        
        }
        return count;
    }
     


    /*
     * isValidGuess -  takes an arbitrary string guess and returns true
     * if it is a valid guess for Wordle, and false otherwise
     */

    public static boolean isValidGuess(String guess) {
        if ( guess.length() == 5 && isAlpha(guess) ){

            return true;

        }

        else if ( guess.length() != 5) {

            System.out.println("Your guess must be 5 letters long.");
            return false;

        }

        else {
            System.out.println("Your guess must only contain letters of the alphabet.");
            return false;
        }

        


    }

    

    public static boolean processGuess(String guess, String mystery){

        String result ="  ";
        boolean rightanswer=true;
            

            
        
        for (int i = 0; i < guess.length(); i++) {
            char c1 = guess.charAt(i);
            char c2 = mystery.charAt(i);

                

            if (c1==c2){
                 result+=c1+" ";
             }

            else if (includes(mystery, c1) && c1!=c2){
                result += "["+c1+"] ";
                rightanswer = false;

            }

            else{
                result +="_ ";
                rightanswer = false;
            }

            }

            System.out.println(result);
            System.out.println();
            return rightanswer;
            


        }
    

    
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        printWelcome();

        // Create the WordList object for the collection of possible words.
        WordList words= initWordList(args);

        // Choose one of the words as the mystery word.
        String mystery = words.getRandomWord();
        
        /*** TASK 4: Implement the rest of the main method below. ***/
        
        int count=1;
        boolean finish=false;

        while (count<= 6 && !finish){
            
            String guess = readGuess(count, console);
            finish = processGuess(guess, mystery);
            count++;
        }

        if (finish){
            count=1;
            System.out.println("Congrats! You guessed it!");
        }

        else if (count>6){
            count=1;
            System.out.println("Sorry! Better luck next time!");
            System.out.println("The word was "+mystery+".");

        }



        console.close();
    }
}