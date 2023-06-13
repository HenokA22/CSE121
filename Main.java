// Henok Assalif
// 05/09/2023
// Prioritizing Patients

// This class runs a series of methods that determine the order of how
// patients should be ranked based on data sampled from the "user".
import java.util.*;
public class Main {
    public static final int HOSPITAL_ZIP = 12345;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int patientCounter = 0;
        int maxScore = 0;

        introduction();

        //inital task must be pulled out of while loop
        System.out.println();
        String nameReturn = name(scan);

        while(!nameReturn.equals("quit")) {

            int scoreReturn = patientInfo(scan);

            System.out.println();
            printPriority(nameReturn, scoreReturn);

            //for daily stats, we must use a cumulative variable
            patientCounter += 1;
            maxScore = Math.max(maxScore, scoreReturn);

            //to reprompt inital task and using its return as the test for this loop
            //the fencepost technique was used here.
            System.out.println();
            nameReturn = name(scan);
        }

        stats(patientCounter,maxScore);
    }

    // This method prints the introduction to my program.
    public static void introduction() {
        System.out.println("Hello! We value you and your time, so we will help");
        System.out.println("you prioritize which patients to see next!");
        System.out.println("Please answer the following questions about the next patient so");
        System.out.println("we can help you do your best work :)");
    }

    // This method prompts the user to input a name for the patient using Scanner object
    // Parameters:
    //  - Scanner scan: Scanner object being passed to be used in method to read input from user
    // Returns:
    //  - String pName = The name of the patient
    public static String name(Scanner scan) {
        System.out.println("Please enter the next patient's name or \"quit\" to end the program.");
        System.out.print("Patient's name: ");
        String pName = scan.next();

        return pName;
    }

    // This method prompts the user various information about the patient
    // in order for a score to be calculated. It also checks if the patient
    // has put in a valid input. Scanner object again is used to achieve this.
    // the console using the Scanner
    // Parameters:
    //  - Scanner scan: Scanner class being passed to be used in method
    // Returns:
    //  - String pScore = The score of patient
    public static int patientInfo(Scanner scan) {
        System.out.print("Patient age: ");
        int pAge = scan.nextInt();

        System.out.print("Patient zip code: ");
        int pZipCode = scan.nextInt();

        //this uses the while statement test case in order to check the return of fiveDigits
        //We want to access the method only if it evaluates to false.
        while (!fiveDigits(pZipCode)) {
            System.out.print("Invalid zip code, enter valid zip code: ");
            pZipCode = scan.nextInt();
        }

        System.out.print("Is our hospital \"in network\" for the patient's insurance? ");
        String pInsurance = scan.next();

        System.out.print("Patient pain level (1-10): ");
        int pPainLevel = scan.nextInt();

        while(!(1 <= pPainLevel && pPainLevel <=10)) {
            System.out.print("Invalid pain level, enter valid pain level (1-10): ");
            pPainLevel = scan.nextInt();
        }

        System.out.print("Patient temperature (in degrees Fahrenheit): ");
        double pTemp = scan.nextDouble();

        int pScore = priorityScoreCalc(pAge,pZipCode,pInsurance, pPainLevel, pTemp);

        return pScore;
    }

    // This method does the calculations for a patients score.
    // It uses the information such as age, pain level, temperature, etc
    // from the patient in order to do so.
    // Parameters:
    //  - int age = The age of the patient
    //  - int zipCode = The zipcode of the patient
    //  - String insurance = The response to the question: "Is our hospital "in network"
    //                       for the patient's insurance?
    //  - int painLevel = The painLevel of the patient from the scale 1 to 10 inclusive
    //  - double temperature = The temperature of the patient
    // Returns:
    //  - String score = The score of patient
    public static int priorityScoreCalc(int age, int zipCode, String insurance,
                                        int painLevel, double temperature) {
        //check this code quality later
        int score = 100;
        //ask someone later why this doesn't work with else ifs
        if (age < 12 || age >= 75) {
            score += 50;
        } if (zipCode / 10000 == HOSPITAL_ZIP / 10000) { // Sunny note: fix indentation
            // if the if statements are seperate they should start on different lines.
            score += 25;
            if ( zipCode / 1000 == HOSPITAL_ZIP / 1000) {
                score += 15;
            }
        } if (insurance.equals("yes") || insurance.equals("y")) {
            score += 50;
        } if (temperature > 99.5) {
            score += 8;
        }

        score += (painLevel * 10);

        return score;
    }

    // This method prints out a statement according to the "priority" score of the
    // patient. It tells hospital workers what to do with patient depending on the
    // patients score.
    // Parameters:
    //  - String name: The name of the patient
    //  - int score: The score of the patient
    public static void printPriority(String name, int score) {
        System.out.print("We have found patient " + name);
        System.out.println(" to have a priority score of: " + score);

        if (score >= 328) {
            System.out.println("We have determined this patient is high priority,");
            System.out.println("and it is advised to call an appropriate medical provider ASAP.");

        } else if (score < 163) {
            System.out.println("We have determined this patient is low priority.");
            System.out.println("Please put them on the waitlist for when a medical"
                    + " provider becomes available.");
        } else {
            System.out.println("We have determined this patient is medium priority.");
            System.out.println("Please assign an appropriate medical provider to their case");
            System.out.println("and check back in with the patient's condition in a"
                    + " little while.");
        }
        System.out.println();
        System.out.println("Thank you for using our system!");
        System.out.println("We hope we have helped you do your best!");
    }

    // This method prints the stats of patients who have came to the hospital
    // In partiuclar the number of patients and the highest priority score
    // Parameters:
    //  - int numOfPatients: The number of patients in one program run
    //  - int maxScore: The highest score a patient recieved in one program run.
    // Returns:
    //  - None
    public static void stats(int numOfPatients, int maxScore) {
        System.out.println("Statistics for the day:");
        System.out.println("..." + numOfPatients + " patients were helped");
        System.out.println("...the highest priority patient we saw had a score of " + maxScore);
        System.out.println("Good job today!");
    }

    // Takes in an integer input.
    // Returns true if the integer has 5 digits, and false otherwise.
    public static boolean fiveDigits(int val) {
        // val = val / 10000; // get first digit
        // if (val == 0) { // has less than 5 digits
        //     return false;
        // } else if (val / 10 == 0) { // has 5 digits
        //     return true;
        // } else { // has more than 5 digits
        //     return false;
        // } // fix this method later

        // NOTE: the above can be written with improved "boolean zen" as follows:
        // I made a minor tweak to the pre-existing sample boolean
        return val != 0 && (val / 10000 != 0 && val <= 99999);
    }
}