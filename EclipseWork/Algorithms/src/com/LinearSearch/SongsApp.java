package com.LinearSearch;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class SongsApp {
    private String name;
    private int min, sec, num, numSongs = 0, totTime = 0;
    private Scanner z;

    public static void main(String args[]) {
        new SongsApp();
    }

    public SongsApp() {
        z = new Scanner(System.in);
        DecimalFormat fmt = new DecimalFormat("00");
        ArrayList<Song> songList = new ArrayList<Song>(20);
        Song song;
        String skip;
        System.out.println("Song name (press Enter to end): ");
        name = z.nextLine();
        while (!name.equals("")) {
            System.out.println("Minutes: ");
            min = z.nextInt();
            skip = z.nextLine();
            System.out.println("Seconds: ");
            sec = z.nextInt();
            skip = z.nextLine();
            totTime = totTime + (min * 60) + sec;
            song = new Song(name, min, sec);

            System.out.println("Song number between 0 and " + songList.size() + ":");
            num = z.nextInt();
            skip = z.nextLine();
            songList.add(num, song);
            System.out.println();
            System.out.println(songList);
            System.out.println("Total time: " + (totTime / 60) + ":" + fmt.format(totTime % 60));
            System.out.println();

            System.out.println("Song name (Press Enter to end): ");
            name = z.nextLine();
        }
        System.out.println();
        System.out.println("The final Result is\n");
        System.out.println(songList);
        System.out.println("Total time: " + (totTime / 60) + ":" + fmt.format(totTime % 60));
        System.out.println();
        z.close();
    }


}
