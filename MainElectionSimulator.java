// Henok Assalif
// 04/21/2023
// Election Simulator



//This class runs mutiple  simulation of an election. It can give the vote tally
//simulation percentage of votes for a candiate as well as a average
//vote tally for a selected candiate across all simulations.
import java.util.*;
public class Main {
    public static final double POLL_ERR = .05;
    public static final double POLL_AVG = .52;
    public static final int NUM_SIMS = 5;
    public static final int NUM_DISTS = 10;

    public static void main(String[] args) {
        Random r = new Random();
        intro();

        double percentCandSimSum = 0;

        // loop for simulations
        for (int i = 1; i <= NUM_SIMS; i += 1 ) {
            int totalVotes = 0;
            int ourVotes = 0;
            int opponentVotes = 0;

            System.out.println("Running simulation #" + i +":");

            // 10 loops for each district (one simulation)
            for (int j = 1; j <= NUM_DISTS; j += 1) {
                int numOfVoters = r.nextInt(1000)+1;
                double votePctCand = r.nextGaussian() * 0.5 * POLL_ERR + POLL_AVG;

                //must make into int b/c you can't have a percent of vote
                int numOfVotesCand = (int) (numOfVoters * votePctCand);
                int numOfVotesCandOpp = numOfVoters - numOfVotesCand;

                //these variables are for the running tally after each district simulation
                totalVotes += numOfVoters;
                ourVotes += numOfVotesCand;
                opponentVotes += numOfVotesCandOpp;
            }

            //for avg vote tally after each simulation
            // must convert either ourVotes or totalVotes to double
            //b/c both are int and we want a double result
            percentCandSimSum += (double) ourVotes/totalVotes * 100;

            //passed these "tally" variables in order to use in my print method
            printSingleSimResult(totalVotes, ourVotes, opponentVotes);
            System.out.println();
        }

        // code for avgpercent after set of simulations
        double avgPercentCandSimSum = percentCandSimSum / NUM_SIMS;
        double avgPercentCandSimSumRound = (double) Math.round(avgPercentCandSimSum * 100) / 100;
        System.out.println();
        System.out.println("Average vote percentage: " + avgPercentCandSimSumRound + "%");
    }





    // this method prints the simulation results to the user.
    // It has parameters of paraTotalvote, paraCandidate votes and paraOpponentVotes
    // to distingush from totalVotes, ourVotes, and opponentVotes from the main method.
    // E.x. totalVotes gets calculated from main method loop -> becomes
    // input for this method: paraTotalvote
    // These variables have been passed in order to take data from each simulation
    // and make its respective visualization to the output.

    public static void printSingleSimResult(int paraTotalVotes,
                                            int paraOurVotes, int paraOpponentVotes) {
        // boolean for win or loss statement
        boolean winStatement = paraOurVotes > (paraTotalVotes/2);

        double percentCand = (double) paraOurVotes / paraTotalVotes * 100;
        double percentOpp = (double) paraOpponentVotes / paraTotalVotes * 100;

        int ourVotesRound =  paraOurVotes;
        int opponentVotesRound =  paraOpponentVotes;

        double percentCandRound = (double) Math.round(percentCand * 100) / 100;
        double percentOppRound = (double) Math.round(percentOpp * 100) / 100;

        // printing
        System.out.println("  Win? " + winStatement);
        System.out.print("  Results: " + ourVotesRound + " (" + percentCandRound + "%)");
        System.out.println(" - " + opponentVotesRound + " (" + percentOppRound + "%)");
        System.out.print("  Visualization: ");
        //code for +'s and -'s
        for (int i = 1; i <= paraOurVotes/100 ; i += 1){
            System.out.print("+");
        }
        System.out.println();
        System.out.print("                 ");

        for (int i = 1; i <= paraOpponentVotes/100 ; i += 1){
            System.out.print("-");
        }
    }

    //This method prints out the intro for my election simulator
    //It has no parameters or return values.
    public static void intro() {
        System.out.println("Welcome to the Election Simulator!");
        System.out.println("Running " + NUM_SIMS +" simulations of " + NUM_DISTS + " districts.");
        System.out.println("Our candidate is polling at "+ (POLL_AVG *100)
                + "% with a " + (POLL_ERR *100) + "% margin of error.");
        System.out.println();
    }
}
