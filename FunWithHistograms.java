import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner user = new Scanner(System.in);
        introduction();
        String userContinue = "yes";

        while(userContinue.equalsIgnoreCase("yes")) {
            System.out.println();
            System.out.print("Input file name? ");
            String inFileName = user.nextLine();
            System.out.print("Output file name? ");
            String outFileName = user.nextLine();

            PrintStream output = new PrintStream(new File(outFileName));
            Scanner fileScan = new Scanner(new File(inFileName));

            //scans the file selected by user and create a array that stores the data
            int[] dataArray = userInput(fileScan);

            //takes newly made array and arranges it into historgram format
            int[] countDataArray = getCounts(dataArray);

            //visualization for the historgram
            output.println("Histogram for " + inFileName);
            output.println();
            printHistogram(countDataArray, output);

            // printing the stats
            stats(dataArray, countDataArray, output);

            //final task
            System.out.println();
            System.out.println("Histogram Created!");
            System.out.print("Do you want to create another histogram? ");
            userContinue = user.nextLine();
        }

        System.out.println();
        System.out.println("Thanks for having fun with histograms!");

    }

    // This method prints the introduction to this program to the console
    public static void introduction() {
        System.out.println("The program will analyze data from a dataset of");
        System.out.println("non-negative integer values. It will produce a");
        System.out.println("histogram of the data and some statistics.");
        System.out.println("The result will be written to an output file.");
    }

    // This method takes in data from user selected file and creates an
    // array that stores the data.
    // Parameters:
    //  - Scanner fileScan: Scanner class being passed to return tokens from file
    // Returns:
    //  - int[] = An array that now contains the data from the file from user.
    public static int[] userInput(Scanner fileScan) throws FileNotFoundException {
        int length = fileScan.nextInt();
        int[] dataset = new int[length];
        for(int i = 0; i < dataset.length; i += 1) {
            dataset[i] = fileScan.nextInt();
        }
        return dataset;
    }

    // This method finds an index of the maximum value of a integer array.
    // Parameters:
    //  - int[] data: A integer array that is passed in to be proccesed.
    // Returns:
    //  - int = The index of the maximum value contained in a array.
    public static int findMaxIndex(int[] data) {
        // TODO: implement this method--edit this return statement appropriately
        int indexCounterMax = 0;
        int maxValue = 0;

        for(int i = 0; i < data.length; i += 1) {
            if(data[i] > maxValue) {
                maxValue = data[i];
                indexCounterMax = i;
            }
        }
        return indexCounterMax;
    }

    // This method takes a array of intergers and rearranges them into a array
    // that length is the maximum value of the original array. The elements of the array
    // have the value of the the amount of times the element/index number appears in the
    // original array.
    // Parameters:
    //  - int[] originalData: An array contaning integer data
    // Returns:
    //  - int[] = An array that has been reorganized into histogram format.
    public static int[] getCounts(int[] originalData) {
        // TODO: implement this method--edit this return statement appropriately
        int maxIndexValue = findMaxIndex(originalData);
        int[] countData = new int[originalData[maxIndexValue] +1];

        for(int i = 0; i < originalData.length; i += 1) {
            int valueTransfer = originalData[i];
            countData[valueTransfer] += 1;
        }
        return countData;
    }

    // This method takes in data from an integer array and calcuates the mean
    // Parameters:
    //  - int[] originalData: An array of integer data.
    // Returns:
    //  - double = The mean of the values in the array that was passed.
    public static double mean(int[] originalData) {
        double sum = 0;
        for(int i = 0; i < originalData.length; i += 1){
            sum += originalData[i];
        }
        double mean = sum / originalData.length;

        return mean;
    }

    // This method takes in the reorganized array and prints out a histogram
    // to a file of the users choice.
    // Parameters:
    //  - int[] countData: A reorganized array that elements represent the count of data
    //                     from original data.
    //  - PrintStream output: Object passed to be used to print to a file.
    public static void printHistogram(int[] countData, PrintStream output) {
        for(int i = 0; i < countData.length; i += 1) {
            output.print(i + "| ");

            for(int j = 0; j < countData[i]; j += 1) {
                output.print("*");
            }
            output.println();
        }
        output.println();
    }

    // This method prints statistics from the data that was passed to program from the user
    // This include total data points, the mean of data, and mode of data. This information
    // it outputed to the file that the user selects.
    // Parameters:
    //  - int[] originalData: data from the file that the user imported from file and made into an
    //                        array
    //  - int[] countData: originalData array that that has been rearrange into histogram format
    //  - PrintStream output: Object passed to be used to print to a file.
    public static void stats(int originalData[], int countData[], PrintStream output) {
        double mean = mean(originalData);

        int indexOfMaxValue = findMaxIndex(countData);
        int mode = indexOfMaxValue;

        output.println("Num. Values: " + originalData.length);
        output.println("Mean: " + mean);
        output.println("Mode: " + mode);
    }
}
