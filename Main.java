import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);
        String time = introduction(user);
        double output = calculation(time);

        System.out.println("Angle between the minute and hour hand is " + output + " degrees");

    }
    public static String introduction(Scanner user) {
        System.out.println("This program will return the acute angle between two hands of a clock");
        System.out.println("First give me the hour ");
        int hour = user.nextInt();

        while(!(hour <= 12 && hour >= 1)) {
            System.out.println("Invalid input try again");
            System.out.println("Give me a correct hour ");
            hour = user.nextInt();

        }
        System.out.println("Next give me the first minute digit ");
        int minutes1 = user.nextInt();

        while(!(minutes1 >= 0 && minutes1 <= 5)) {
            System.out.println("Invalid input try again");
            System.out.println("Give me the correct first minute digit ");
            minutes1 = user.nextInt();
        }

        System.out.println("Finally give me the second minute digit ");
        int minutes2 = user.nextInt();

        while(!(minutes2 >= 0 && minutes2 <= 9)) {
            System.out.println("Invalid input try again");
            System.out.println("Give me the correct second minute digit");
            minutes2 = user.nextInt();
        }

        String minutes = "";
        minutes += minutes1;
        minutes += minutes2;
        System.out.println("Your time is " + hour + ":" + minutes);

        String timeFormat = hour + " " + minutes;
        System.out.println(timeFormat);
        System.out.println();

        return timeFormat;
    }

    public static double calculation(String time) {
        Scanner linescan = new Scanner(time);

        int hour = linescan.nextInt();
        int minute = linescan.nextInt();

        double hourHandDeg = hour * 30.0;
        double minHandDeg = minute * 6.0;

        hourHandDeg = hourHandDeg + (minute / 60.0 * 30.0);

        double startDeg = 90.0;

        //sweep angles for individual hands
        double hourSweepDeg = Math.toRadians(90.0 - hourHandDeg);
        double minuteSweepDeg = Math.toRadians(90.0 - minHandDeg);

        //using unit circle and magnitude formulas to set up dot product
        double xCoordHour = Math.cos(hourSweepDeg);
        double yCoordHour = Math.sin(hourSweepDeg);

        double lengthHour = Math.sqrt(xCoordHour * xCoordHour + yCoordHour * yCoordHour);

        double xCoordMin = Math.cos(minuteSweepDeg);
        double yCoordMin = Math.sin(minuteSweepDeg);

        double lengthMin = Math.sqrt(xCoordMin * xCoordMin + yCoordMin * yCoordMin);

        double input = (xCoordHour * xCoordMin + yCoordHour * yCoordMin) / (lengthMin * lengthHour);

        double radianOutput = Math.acos(input);
        double degreeOutput = Math.toDegrees(radianOutput);

        return degreeOutput;

    }

}