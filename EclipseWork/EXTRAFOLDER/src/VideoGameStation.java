

public class VideoGameStation {
    //1. Creating and declaring the variables
    String GameStation;
    String VideoGame;
    String CustomerName;
    String CustomerType;
    String BookingDate;
    int TimeDuration, HourlyRate;
    boolean availableStatus;


    //2.Using constructor to initialize the variables
    public VideoGameStation(String GameStation, String VideoGame, int HourlyRate) {
        this.GameStation = GameStation;
        this.VideoGame = VideoGame;
        this.HourlyRate = HourlyRate;
        this.CustomerName = "";
        this.BookingDate = "";
        this.TimeDuration = 0;
        this.availableStatus = true;
    }


    //3.Using getter to return the values of the Instance variables
    public String getGameStation() {
        return GameStation;
    }

    public String getVideoGame() {
        return VideoGame;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getCustomerType() {
        return CustomerType;
    }

    public String getBookingdate() {
        return BookingDate;
    }

    public int getHourlyRate() {
        return HourlyRate;
    }

    public int getTimeDuration() {
        return TimeDuration;
    }

    public boolean getAvailableStatus() {
        return availableStatus;
    }

    //(METHODS)
    //4.Using setter to set the new values to key attributes
    public void setCustomerName(String customerName) {
        this.CustomerName = customerName;
    }

    public void setCustomerType(String customerType) {
        this.CustomerType = customerType;
    }

    public void setTimeDuration(int timeDuration) {
        this.TimeDuration = timeDuration;
    }


    //5.Creating a method that would allow the customer to book video game station
    public void BookingProcess(String CustomerName, String CustomerType, String BookingDate, int TimeDuration) {
        if (availableStatus = true) {
            this.BookingDate = BookingDate;
            this.CustomerName = CustomerName;
            this.TimeDuration = TimeDuration;
            this.CustomerType = CustomerType;
            this.availableStatus = false;
        } else {
            System.out.println("we are extremely sorry,the game you are trying to book is already booked please try another time");
            System.out.println("Game Station is only available after" + this.TimeDuration + "at" + this.BookingDate);
        }
    }


    //6.To show the values after the booking time is over
    public void changingDetails() {
        if (availableStatus == false) {
            this.CustomerName = "";
            this.CustomerType = "";
            this.BookingDate = "";
            this.TimeDuration = 0;
            this.availableStatus = true;
        } else {
            System.out.println("The game station is available, Please Book!! ");
        }
    }


    //7.Showing the values passed
    public void showDetails() {
        System.out.println("The discription of the game station you booked :" + this.GameStation);
        System.out.println("The hourly rate of the game Station" + this.HourlyRate);
    }


    public int compareTo(VideoGameStation compare) {
        int names = 0;
        int GameStation = this.GameStation.compareToIgnoreCase(compare.GameStation);
        if (GameStation != 0) {
            names = GameStation;
        }
        return (names);

    }
}












  
