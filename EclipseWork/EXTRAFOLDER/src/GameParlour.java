import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//8no. creating arraylist
public class GameParlour {
    static ArrayList<VideoGameStation> arr = new ArrayList<>();

    //9no. adding new attributes to the list of videogamestation
    public void add(String gameStation, String videoGame, int hourlyRate) {
        VideoGameStation obj = new VideoGameStation(gameStation, videoGame, hourlyRate);
        arr.add(obj);
    }

    //10no. methdd to remove object from array list
    public void remove(int index_no) {
        if (index_no < arr.size()) {
            arr.remove(index_no);
        } else
            System.out.println("index too high!");
    }

    //11 no .method for booking game station .
    public void bookingGameStation(int index_no, String CustomerName, String CustomerType, String BookingDate, int TimeDuration) {

        if (index_no < arr.size()) {
            for (int i = 0; i < arr.size(); i++) {

                //System.out.println("Game station:"+arr.get(i).gamestation());
                arr.get(i).BookingProcess(CustomerName, CustomerType, BookingDate, TimeDuration);
            }
        } else
            System.out.println("index is not valid");

    }

    //12 no .
    public void clearBooking(int index_no) {
        if (index_no < arr.size()) {
            arr.get(index_no).changingDetails();
            arr.get(index_no).showDetails();
        } else {
            System.out.println("index is not valid");
        }
    }

    //13 no.
    public void stationCurrentlyAvailable() {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(i);
            if (arr.get(i).availableStatus == true) {
                System.out.println("index no:" + i);
                //System.out.println("game station available:"+arr.get(i).gamestation());
                arr.get(i).showDetails();
            }
        }
    }

    //14 no
    public void searchVideoGameStation(String searchKeyword, int maxinumHourRate) {
        int count = 0;
        // don't need to use 'this' all the time.. since arr is a Data field or instance vairable, 
        // it is global and is always refered to the one in its class.. not anywhere else
        for (int i = 0; i < arr.size(); i++) {
            VideoGameStation station = arr.get(i);
            if (station.GameStation.contains(searchKeyword) && (station.HourlyRate <= maxinumHourRate)) {
                System.out.println("GameStation/Index: " + i);
                station.showDetails();
                count = count + 1;
            }
        }
        if (count == 0) {
            System.out.println("Sorry! Criteria did not match.");
            System.out.println("Try using different search keywords");
        }
    }
    //15 no .
//    public void ascendingcollect(){
//           Collections.sort(arr, this);
//       } // endae ascending Sort


    // NEW METHOD TO JUST RETURN ARRAY
    public ArrayList<VideoGameStation> getArray() {
        return arr;
    } // endae Accessor getArray()


}

    





