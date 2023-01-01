import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
/*
 * CSCI 221, Summer 2022, Programming HW 1
 * Name: <Jalena Austin>
 * 
 * <movieReview>.java
 * 
 * This program analyzes movie review data to determine if words have a 
 * negative or positive meaning. If a word is used more often in positive 
 * reviews, it is assumed that the word is positive, and vice versa.
 * 
 * Certification of Authentication:
 * I certify that this assignment is entirely my own work.
 */
public class HW1{
    public static void outputToAFile(String lastUserFile) throws FileNotFoundException, IOException
    {
        /*
         * This method is to run through a user inputted file and determine which words from
         * within the indicated "movieReviews.txt" lie within the boundary of being positive
         * (above 2.1) and negative (below 1.9). While going through both of the files, the
         * method should then output the positive/negative words and its score into another file
         * indicated to be positive and negative for the user to access after the method is complete.
         */
        File reviewFile = new File("movieReviews.txt");
        File userFileReview = new File(lastUserFile);
        Scanner movieScan = new Scanner(reviewFile);
        Scanner userScan = new Scanner(userFileReview);
        
        String outPositiveFile = "positive.txt";
        File outPositive = new File(outPositiveFile);
        FileWriter posWriter = new FileWriter(outPositive);
        PrintWriter posPresent = new PrintWriter(posWriter);
        
        String outNegativeFile = "negative.txt";
        File outNegative = new File(outNegativeFile);
        FileWriter negWriter = new FileWriter(outNegative);
        PrintWriter negPresent = new PrintWriter(negWriter);
        
        String reviewText;
        String userText;
        String word;
        String posWord = "";
        String negWord = "";
        int reviewScore;
        double wordPresence = 0;
        double wordScore = 0;
        
        posPresent.println("This file contains the positive words recognized and their score: ");
        negPresent.println("This file contains the negative words recognized: ");
        
        while (userScan.hasNext())
        {
            userText = userScan.nextLine();
            word = userText;
            while(movieScan.hasNext())
            {
                reviewScore = movieScan.nextInt();
                reviewText = movieScan.nextLine();
                
                if(reviewText.contains(word))
                {
                   wordPresence += 1.0;
                   wordScore += (double)reviewScore;
                }
            }
            double avgScore = 0;
            avgScore = wordScore / wordPresence;
            
            if (avgScore > 2.1){
                posWord = word;
                posPresent.println(posWord + ": " + avgScore);
            }
            else if (avgScore < 1.9){
                negWord = word;
                negPresent.println(negWord + ": " + avgScore);
            }
            movieScan = new Scanner(reviewFile);
            avgScore = 0;
            wordPresence = 0;
            wordScore = 0;
        }
        movieScan.close();
        userScan.close();
        posPresent.close();
        negPresent.close();
        
        System.out.println("The words recognized as positive are written in " + outPositiveFile + ".");
        System.out.println("The words recognized as negative are written in " + outNegativeFile + ".");
    }
    
    public static void readingFilePosNeg(String anotherUserFile) throws FileNotFoundException, IOException
    {
        /*
         * This method is to take the inputted user file and read through each line of the file
         * to determine if each word in the user's file is positive or negative compared to the
         * movieReviews.txt provided. Once it is determined that it is positive/negative it should take
         * that value and compare it the the already positive or negative value indicated to replace
         * the best/worst word to present the new best/worse word scanned.
         */
        File reviewFile = new File("movieReviews.txt");
        File userFileReview = new File(anotherUserFile);
        Scanner movieScan = new Scanner(reviewFile);
        Scanner userScan = new Scanner(userFileReview);
        
        
        String reviewText;
        String userText;
        String word;
        String posWord = "";
        String negWord = "";
        int reviewScore;
        double wordPresence = 0;
        double wordScore = 0;
        double min = 1.99;
        double max = 0;
        
        
        while (userScan.hasNext())
        {
            userText = userScan.nextLine();
            word = userText;
            while(movieScan.hasNext())
            {
                reviewScore = movieScan.nextInt();
                reviewText = movieScan.nextLine();
                
                if(reviewText.contains(word))
                {
                   wordPresence += 1.0;
                   wordScore += (double)reviewScore;
                }
            }
            double avgScore = 0;
            avgScore = wordScore / wordPresence;
            
            if (avgScore > max){
                max = avgScore;
                posWord = word;
            }
            else if (avgScore < min){
                min = avgScore;
                negWord = word;
            }
            movieScan = new Scanner(reviewFile);
            avgScore = 0;
            wordPresence = 0;
            wordScore = 0;
        }
        movieScan.close();
        userScan.close();
        
        System.out.println("The most positive word, with a score of " + max + " is " + posWord);
        System.out.println("The most negative word, with a score of " + min + " is " + negWord);
    }
    
    public static void readingFile(String userFile) throws FileNotFoundException, IOException
    {
        /*
         * This method reads through each line of the user's inputted file
         * and compares it to the provided file to find the average score of words
         * of the user's file from that of the provided file.
         * It should then follow with outputting the average score of the file
         * alongside the indication if that file has an overall sentiment of
         * being positive or negative determined by the overall avergage to be
         * above 2.01 (positive) or below 1.99 (negative) and output the results to
         * the user.
         */
        File reviewFile = new File("movieReviews.txt");
        File userFileReview = new File(userFile);
        Scanner movieScan = new Scanner(reviewFile);
        Scanner userScan = new Scanner(userFileReview);
        
        String sentimentStatus;
        String reviewText;
        String userText;
        String word;
        double avgScore = 0;
        double totalScoreAvg = 0;
        int reviewScore;
        double wordPresence = 0;
        double wordScore = 0;
        int numWords = 0;
        double sumScoreAvg = 0;
        double completeAvg = 0;
        
        while (userScan.hasNext()){
            userText = userScan.nextLine();
            word = userText;
            numWords += 1;
            while(movieScan.hasNext())
                    {
                        reviewScore = movieScan.nextInt();
                        reviewText = movieScan.nextLine();
                        if(reviewText.contains(word))
                        {   
                           wordPresence += 1;
                           wordScore += (double)reviewScore;
                        }
                    }
            avgScore = 0;
            avgScore = wordScore / wordPresence;
            sumScoreAvg += avgScore;
            wordPresence = 0;
            wordScore = 0;
            movieScan = new Scanner(reviewFile);
        }
        completeAvg = sumScoreAvg / numWords;
        if (completeAvg > 2.01){
            sentimentStatus = "positive";
        }
        else if (completeAvg < 1.99){
            sentimentStatus = "negative";
        }
        else {
            sentimentStatus = "neutral";
        }
        
        System.out.println("The average score of words in " + userFile + " is " + completeAvg);
        System.out.println("The overall sentiment of " + userFile + " is " + sentimentStatus);
        
        movieScan.close();
        userScan.close();
    }
    
    public static void srchWord(String word) throws FileNotFoundException, IOException 
    {
        /*
         * This method takes the user input of a word chosen by them and runs through the 
         * default file (in this case movieReviews.txt) and counts how many times that word
         * appears alongside its' score for that review. It will continue to read through the
         * file till the last line to which it will then calculate the average and times that
         * chosen word appeard within the file.
         */
        File reviewFile = new File("movieReviews.txt");
        Scanner reviewScanner = new Scanner(reviewFile);
        
        int userChoice;
        int reviewScore;
        float wordPresence = 0;
        double scoreTotal = 0;
        double avgScore = 0;
        String reviewText;
        
        while(reviewScanner.hasNext())
        {
            reviewScore = reviewScanner.nextInt();
            reviewText = reviewScanner.nextLine();
            
            
            if(reviewText.contains(word))
            {
               wordPresence += 1.0;
               scoreTotal += (double)reviewScore;
            }
        }
        System.out.println(word + " appears " + wordPresence + " times.");
        avgScore = scoreTotal / wordPresence;
        System.out.println("The average score for reviews containing the word "
            + word + " is " + avgScore);
        
        reviewScanner.close();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        /*
         * This main method is used to be the main user interaction with using a switch to
         * go between the different selections to take with the file(s). This method is the 
         * main determinant of where the program will go after the user input and call to 
         * another method if needed due to the option chosen.
         */
        Scanner keyboard = new Scanner(System.in);
        Scanner choice = new Scanner(System.in);
        
        int userChoice;
        
        do {
            System.out.println("What would you like to do?");
            System.out.println("1: Get the score of a word");
            System.out.println("2: Get the average score of words in a file (one word per line)");
            System.out.println("3: Find the highest/lowest scoring words in a file");
            System.out.println("4: Sort words from a file into positive.txt and negative.txt");
            System.out.println("5: Exit the program");
            System.out.println("\nEnter an integer between 1 and 5: ");
            
            userChoice = choice.nextInt();
            System.out.println();
            
            switch (userChoice) {
                case 1:
                    String word;
                    System.out.println("Enter a word.");
                    word = keyboard.nextLine();
                    
                    srchWord(word);
                    break;
                    
                case 2:
                    String userFile;
                    System.out.println("Enter the name of the file with words you want to score: ");
                    userFile = keyboard.nextLine();
                    
                    readingFile(userFile);
                    break;
                    
                case 3:
                    String anotherUserFile;
                    System.out.println("Enter the name of the file with words you want to score: ");
                    anotherUserFile = keyboard.nextLine();
                    
                    readingFilePosNeg(anotherUserFile);
                    break;
                    
                case 4:
                    String lastUserFile;
                    System.out.println("Enter the name of the file with words you want to score: ");
                    lastUserFile = keyboard.nextLine();
                    
                    outputToAFile(lastUserFile);
                    break;
                
                case 5:
                    System.out.println("Goodbye");
                    break;
                    
                default:
                    System.out.println("You must enter an integer between 1 and 5");
                
            }
            System.out.println();
        } while (userChoice != 5);
    }
}