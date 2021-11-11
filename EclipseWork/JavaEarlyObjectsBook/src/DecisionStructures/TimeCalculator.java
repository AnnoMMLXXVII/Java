package DecisionStructures;

import java.util.Scanner;

public class TimeCalculator {
    int second;
    int minute; // 60 seconds in hour minute
    int hour; // 3600 seconds in one hour
    int day;// 86,400 seconds in one day

    public TimeCalculator(int userSec) {
        setSecond(userSec);
        calculateTime();
    } // endae CONSTRUCTOR

    public void setSecond(int userSec) {
        this.second = userSec;
    }

    public int getSecond() {
        return (int) second;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMinute() {
        return (int) minute;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getHour() {
        return (int) hour;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return (int) day;
    }

    private void calculateTime() {
        if (second < 60 && second >= 0) {
            setSecond((int) second);
        } else if (second >= 60 && second < 3600) {
            minute = second / 60;
            second = second % 60;
            setSecond((int) second);
            setMinute((int) minute);
        } else if (second >= 3600 && second < 86400) {
            hour = second / 3600;
            minute = (hour % 1) * 60;
            second = second % 60;
            setHour((int) hour);
            setMinute((int) minute);
            setSecond((int) second);
        } else if (second >= 86400) {
            day = second / 86400;
            hour = (day % 1) * 24;
            minute = (hour % 1) * 60;
            second = (minute % 1) * 60;
            setDay((int) day);
            setHour((int) hour);
            setMinute((int) minute);
            setSecond((int) second);
        } //
    }

    public String toString() {
        String result = "Seconds: " + getSecond() + "\nMinutes: "
                + getMinute() + "\nHours: " + getHour() + "\nDays: "
                + getDay();
        return result;
    }

    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.println("Enter number of Seconds: ");
        int userSec = z.nextInt();
        TimeCalculator tc = new TimeCalculator(userSec);
        System.out.println(tc.toString());


        z.close();
    }

}
