import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner user = new Scanner(System.in);
        introduction();
        System.out.println();
        int count = randomGame(rand, user);
        System.out.print(" and it took you " + count + " tries");
    }
    public static void introduction() {
        System.out.println("Hello Berhanu, welcome to RandomGuessGame");
    }

    public static int randomGame(Random rand, Scanner user) {
        int count = 1;
        int randNum = rand.nextInt(100) + 1;
        System.out.println("Please choose a number between 1 and 100 ");
        int userNum = user.nextInt();

        while (!(userNum <= 100 && userNum >= 1)) {
            System.out.println("Invalid input try again");
            System.out.println("Please choose a number between 1 and 100 ");
            userNum = user.nextInt();
        }
        while(!(randNum == userNum)) {
            if(userNum < randNum) {
                System.out.println("Not correct, higher");
                System.out.println("Please choose a number between 1 and 100 ");
                userNum = user.nextInt();
                while (!(userNum <= 100 && userNum >= 1)) {
                    System.out.println("Invalid input try again");
                    System.out.println("Please choose a number between 1 and 100 ");
                    userNum = user.nextInt();
                }
                count += 1;
            } else if(userNum > randNum) {
                System.out.println("Not correct, lower");
                System.out.println("Please choose a number between 1 and 100");
                userNum = user.nextInt();
                while (!(userNum <= 100 && userNum >= 1)) {
                    System.out.println("Invalid input try again");
                    System.out.println("Please choose a number between 1 and 100 ");
                    userNum = user.nextInt();
                }
                count += 1;
            }

        }

        System.out.println("Congratulation you selected the correct number Berhanu");
        System.out.print("The number was " + randNum);

        return count;
    }
}