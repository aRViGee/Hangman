import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;


public class Hangman {
    
    public static void main(String[] args) {
        String pickedWord = randomWord(); //Pick a random word from wordLibrary        
        char[] seperated = seperateWord(pickedWord); //Seperate the individual letters
        char[] updatedWord = convertWord(seperated); //updateCharArray(seperated, convertedToUpdate, guess);
        int chancesLeft = 10; //Amount of chances at the start 
        boolean wordIsGuessed = false;
        ArrayList<Character> guessedChars = new ArrayList<Character>(); 
        Scanner input = new Scanner(System.in); // Create a Scanner object    
        
        System.out.println("");
        System.out.println("Welcome to Hangman.");
        System.out.println("You have 10 chances to guess the secret word.");
        System.out.println("Good luck!");
        System.out.println(""); 
        charToString(updatedWord);        
        
        do {
            System.out.println("");
            //Initiate Player input
            char guess = getPlayerInput(input);            
            //Check input against list of guessed letters
                if (checkInputInList(guessedChars,guess)) {
                    System.out.println("You already guessed the letter " + guess);            
                }
                else if ((!checkInputInList(guessedChars,guess)) && (checkInputInWord(seperated, guess))) {                    
                    // Hier komt de code wat er gebeurt als de letter goed is.
                    updatedWord = updateCharArray(seperated,updatedWord, guess);
                    charToString(updatedWord);                    
                    
                }
                
                else if (!checkInputInList(guessedChars,guess) && (!checkInputInWord(seperated, guess))) {
                    guessedChars.add(guess); 
                    chancesLeft--;
                }
            
            checkInputInList(guessedChars,guess);
            wordIsGuessed = isGuessed(pickedWord, updatedWord);
            isGuessed(pickedWord, updatedWord);
            
            System.out.println("Amount of chances left: " + chancesLeft); 
            System.out.println("Your guessed letters: " + guessedChars);
            System.out.print("Your word: "); 
            charToString(updatedWord);                        
            
        } 
        
        while ((chancesLeft > 0) && (!wordIsGuessed));
        
        //Check if the word has been guessed
            if (wordIsGuessed) {
                System.out.println("Congratulations, you guessed the word: " + pickedWord);
                System.out.println("Thank you for playing!");
            }
            else if (chancesLeft == 0) {
                System.out.println("Game over, out of chances!");
                System.out.println("Your word was: " + pickedWord);
            }
            input.close();
    }
    
    
    //Pick a random word from the array. Return the picked word.
    public static String randomWord() {
        String[] wordLibrary = {"quizzes","coding","jazz","icebox","java","gossip","wristwatch","quota","dull","programme"};
        Random random = new Random();
        int index = random.nextInt(wordLibrary.length);
        return wordLibrary[index];
    }
    //Seperate the word into different char's.
    public static char[] seperateWord(String word) {
        char[] seperated = word.toCharArray();              
        return seperated;
    }
    
    //Convert the individual char's to underscores ("_")
    public static char[] convertWord(char[] seperated) {
        char[] converted = new char[seperated.length];
        for (int i = 0; i < seperated.length; i++) {
            converted[i] = '_';            
        }         
        return converted;
    }
     
    //For output readability, add spaces in between the char's.
    public static void charToString(char[] converted){        
        for (int i = 0; i < converted.length; i++) {
            System.out.print(converted[i] + " ");            
        }
        System.out.println("");        
    }    
    
    
    //Player input
    public static char getPlayerInput(Scanner input) {
        System.out.print("Type your guess: ");
        
        String currentGuess = input.next().toLowerCase();
        char currentGuessChar = currentGuess.charAt(0); // Read user input
        
        return currentGuessChar; //Update the previous input with the new input        
    }
    
    //A check of the player's input against earlier input.
    public static boolean checkInputInList(ArrayList<Character> guessedChars, char currentGuessChar) {
        boolean isAlreadyGuessed = false;
        for (int i = 0; i < guessedChars.size(); i++) {
            if (currentGuessChar == guessedChars.get(i)) {                
                isAlreadyGuessed = true;             
            }            
        }
        return isAlreadyGuessed;
    }
    
    //A check of the player's input against the characters in the picked word.
    public static boolean checkInputInWord(char[] pickedWord,char currentGuessChar) {
        boolean isInWord = false;
        for (int i = 0; i < pickedWord.length; i++) {
            if (currentGuessChar == pickedWord[i]) {
                isInWord = true;
            }
        }       
        return isInWord;
    }
    
    //Update the correct letter on the converted Char array
    public static char[] updateCharArray( char[] seperateWord, char[] updatedWord, char currentGuessChar) {
        for (int i = 0; i < seperateWord.length; i++) {
            if (currentGuessChar == seperateWord[i]) {
                updatedWord[i] = currentGuessChar;
            }
        }
        return updatedWord;
    }
    
    //Check if the complete word has been guessed
    public static boolean isGuessed(String pickedWord,char[] updatedWord) {
        boolean wordIsGuessed = false;
        String updatedWordToString = new String(updatedWord);        
        if (updatedWordToString.equals(pickedWord)) {
            wordIsGuessed = true;
        }
        return wordIsGuessed;
    }
}