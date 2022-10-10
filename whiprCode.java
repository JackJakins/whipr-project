/** A program to determine the predicted distance travelled over a set time on a rowing machine. 
 * While using my whipr rowing machine, I realised the predicted total distance travelled was not aligning to my final result.  *
 * The simple solution implemented by whipr is along this lines of: *** (targetTime/rp500) * 500 *** This does not account for
 * time remaining or distance already travelled.

 * Here's my solution:
 * 
 * The user inputs a target time, and then a countdown begins.
 * Distance travelled is based on stroke per minute rate (cadence) as well as time per 500m (split), both of which can change each stroke.
 * The split tells us the speed and distance travelled per stroke, while the cadence tells us how many strokes to expect. 
 * For every stroke, the programme needs to identify how many strokes are left based on the current cadence and remaining time, as well as 
 * how many meters that will result in based on the user continuing to row at the cuurent split.
 * 
 * ACTIONS
 * Method: Determine seconds elapsed per stroke based on cadence
 * Method: Determine meters gained per stroke based on split
 * Method: Add meters to the total tally and then display total tally, rounded up to nearest int
 * Method: Predict the final total meters:    
 *      Divide remaining time by seconds elapsed per stroke (at the current spm rate)
 *      Multiply result by meters gained per stroke (based on current split)
 *      Add result to new variable called "predicted outcome" (this will change every stroke, depending on split and spm)
 *      Return predicted outcome
 * While loop:
 * Ask user to input spm and split (this would come from data from the machine in reality)
 * Substract seconds elapsed per stroke from remaining time
 * Call the above methods
 * Display predicted distance, rounded to nearest int
 * Continue until the countdown hits zero
 * */ 

import java.util.Scanner;

public class whiprCode {


   // Method to work out secs per stroke - need to call later to remove these from the time
   static double spm(double cadence){
      double secsPerStroke = 60/cadence;
      return secsPerStroke;
   }

   //Method to work out meters gained per stroke based on split
   static double fiveHunSplit(double split, double secsPerStroke){
      double mins = Math.floor(split);
      double secs = split - mins;
      double totalSecs = (mins * 60) + (secs*100);
      double metersMovedPerSec = 500/totalSecs;
      double metersMoved = metersMovedPerSec * secsPerStroke;
      return metersMoved;
   }
   
   static double stroke (double metersMoved, double distanceTraveled){
      distanceTraveled += metersMoved;
      return distanceTraveled;
   }

   static double predictedDistance (double distanceTraveled, double countDownInSecs, double secsPerStroke, double metersMoved){
      double remainingStrokes = countDownInSecs/secsPerStroke;
      double remainingDistance = remainingStrokes*metersMoved;
      double predictedDistance = remainingDistance + distanceTraveled;
      return predictedDistance;
   }

    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
       
        double distanceTraveled = 0;

        System.out.println("What is your target time?");
        double targetTime = keyboard.nextDouble();

        // get targetTime in seconds to see how much is left as countdown continues
        double countdownInSecs = targetTime*60;

        while(countdownInSecs > 0){
        //Get user input for split (would come from the device itself in reality)
        System.out.println("Enter 500m split: ");
        double split = keyboard.nextDouble();

        // Get user input for cadence (would come from the device itself in reality)
        System.out.println("Enter stroke per minute: ");
        double cadence = keyboard.nextDouble();

        double secsPerStroke = spm(cadence);
        double metersMoved = fiveHunSplit(split, secsPerStroke);

        // now call the stroke method based on user inputs
        countdownInSecs -= secsPerStroke;
        distanceTraveled = stroke(metersMoved, distanceTraveled);
        System.out.println("Predicted total meters: " + Math.round(predictedDistance(distanceTraveled, countdownInSecs, secsPerStroke, metersMoved)));
        System.out.println("Meters gained this stroke: " + Math.round(metersMoved));
        System.out.println("Total meters moved: " + Math.round(distanceTraveled)); 
        System.out.println("Remaining time in secs: " + Math.round(countdownInSecs));
        if (countdownInSecs <= 0){
         break;
        }
        }
    }




        






    }

