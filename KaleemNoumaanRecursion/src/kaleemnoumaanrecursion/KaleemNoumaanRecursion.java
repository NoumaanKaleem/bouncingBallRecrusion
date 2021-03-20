// Noumaan Kaleem - ICS4U - Recursion Assignment

/* This program simulates the bouncing pattern of a meatball and provides the height for each bounce. The user can choose whether to input a certain number of bounces, or simulate to a certain threshold. 
 * 
 */
package kaleemnoumaanrecursion;

// import scanner for input
import java.util.*;

/**
 *
 * @author Noumaan Kaleem
 */
public class KaleemNoumaanRecursion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // initialize scanner
        Scanner keyboard = new Scanner(System.in);
        // initialize variables for the current bounce, number of bounces, initial height, decay factor, and threshold
        int bounceCount = 0;
        int currentBounce = 0;
        double initialHeight = 0;
        double factorDecay = 0;
        double thresholdBounce = 0;

        // ask for users choice
        System.out.println("1. Simulate n times\n2. Simulate to threshold\n3. Exit");
        // store users choice 
        int userChoice = keyboard.nextInt();
        // string to store empty input
        String goo = keyboard.nextLine();

        // while user has not quit the program
        while (userChoice != 3) {
            // if the user wishes to simulate to n times
            if (userChoice == 1) {
                
                // prompt user for input
                System.out.println("Enter the starting height of the meatball in metres, the decay factor, and the number of times the meatball will bounce, separated by a comma.");
                // store user input
                String userInput = keyboard.nextLine();

                // use string tokenizer to separate the string by commas
                StringTokenizer st = new StringTokenizer(userInput, ",");

                // set each of the variables to the next token in the string
                initialHeight = Double.parseDouble(st.nextToken());
                factorDecay = Double.parseDouble(st.nextToken());
                bounceCount = Integer.parseInt(st.nextToken());

                // empty line for aesthetic
                System.out.println("");
                // display information given
                System.out.println("Current Bounce " + currentBounce + ", Number of Bounces " + bounceCount + ", Current Height " + initialHeight + ", Decay Factor " + factorDecay);
                System.out.println("");
                // display table headings
                
                System.out.println("Current Height\tBounce");
                // display all the results
                System.out.println(Math.round(decayingBounce(initialHeight - initialHeight * factorDecay, factorDecay, bounceCount, currentBounce) * 100000d) / 100000d + "\t\t" + bounceCount);
                System.out.println("done");
                System.out.println("");

                // if user wishes to simulate to threshold
            } else if (userChoice == 2) {
                // set bounce to 0
                bounceCount = 0;
                
                // prompt user for input
                System.out.println("Enter the starting height of the meatball in metres, the decay factor, and the height at which the meatball should stop bouncing, separated by a comma.");
                // store user input
                String userInput = keyboard.nextLine();

                // use string tokenizer to separate the string by commas
                StringTokenizer st = new StringTokenizer(userInput, ",");

                // set each of the variables to the next token in the string
                initialHeight = Double.parseDouble(st.nextToken());
                factorDecay = Double.parseDouble(st.nextToken());
                thresholdBounce = Double.parseDouble(st.nextToken());

                System.out.println("");
                // display all information
                System.out.println("Current Height " + initialHeight + ", Bounce " + bounceCount + ", Decay Factor " + factorDecay + ", Threshold " + thresholdBounce);
                System.out.println("");
                
                // display table headings                
                System.out.println("Current Height\tBounce");
                // array to store output of the threshold method
                double[] output = thresholdBounce(initialHeight - initialHeight * factorDecay, factorDecay, thresholdBounce, currentBounce);
                // result of the threshold function
                output[0] = Math.round(output[0] * 100000d) / 100000d;
                // bounce count
                bounceCount = (int) output[1];
                // print all results
                System.out.println(output[0] + "\t\t" + bounceCount);
                System.out.println("done");
                System.out.println("");
                
            // if user does not enter proper input
            } else if (userChoice != 3) {
                // prompt user to only enter correct input
                System.out.println("Only enter either 1, 2, or 3.");
            }
            // prompt user to enter choice
            System.out.println("1. Simulate n times\n2. Simulate to threshold\n3. Exit");
            // store user choice
            userChoice = keyboard.nextInt();
            // string to store empty input
            goo = keyboard.nextLine();
        }
        // display end of program message
        System.out.println("Thanks for using the program!");

    }

    /**
     * Bounce for n times
     *
     * @param currentHeight the current height
     * @param decayFactor the amount of decay for each bounce
     * @param n the number of times the meatball will bounce
     * @param currentBounce the current bounce of the ball
     * @return the height of the meatball at each bounce
     */
    public static double decayingBounce(double currentHeight, double decayFactor, int n, int currentBounce) {
        // if the last bounce is done
        if (n == 0) {
            // return the final height
            return currentHeight;
            // if the last bounce is not done
        } else {
            // print the current height
            System.out.println(Math.round(currentHeight * 100000d) / 100000d + "\t\t" + (currentBounce));
            // add one to current bounce
            currentBounce++;
            // lower the bounce and remove it from the bounces remaining
            return decayingBounce(currentHeight - currentHeight * decayFactor, decayFactor, n - 1, currentBounce);
        }
    }

    /**
     * Bounce to a certain threshold
     *
     * @param currentHeight the current height
     * @param decayFactor the amount of decay for each bounce
     * @param thresholdBounce the minimum height threshold
     * @param currentBounce the current bounce of the meatball
     * @return the current height of the meatball
     */
    public static double[] thresholdBounce(double currentHeight, double decayFactor, double thresholdBounce, int currentBounce) {
        double[] output = new double[2];
        // if the threshold is met
        if (currentHeight <= thresholdBounce) {
            output[0] = currentHeight;
            output[1] = currentBounce;
            // return final height
            return output;

            // if threshold is not met
        } else {
            // print current height
            System.out.println(Math.round(currentHeight * 100000d) / 100000d + "\t\t" + currentBounce);
            // add one to current bounce
            currentBounce++;
            // recursion with the new height
            return (thresholdBounce(currentHeight - currentHeight * decayFactor, decayFactor, thresholdBounce, currentBounce));
        }
    }

}
