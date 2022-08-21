package tester2.airplance;

import java.util.Scanner;

public class AirplaneSeats {

 private  int seatNumber;
 private boolean reserved;

 public AirplaneSeats(int seatNumber, boolean reserved) {
		super();
		this.seatNumber = seatNumber;
		this.reserved = reserved;
	}

public int getSeatNumber() {
	return seatNumber;
}
public void setSeatNumber(int seatNumber) {
	this.seatNumber = seatNumber;
}
public boolean isReserved() {
	return reserved;
}
public void setReserved(boolean reserved) {
	this.reserved = reserved;
}

public static boolean isValid ( int seatNumber ) {

	Scanner in= new Scanner (System.in);
	
	System.out.println (" Please choose the seat number you want ~ :");

	int number = in.nextInt();

	if (seatNumber == 11 || seatNumber == 12 || seatNumber == 21 || seatNumber == 22 || seatNumber == 31
			|| seatNumber == 32 || seatNumber == 41 || seatNumber == 42 || seatNumber == 51
			|| seatNumber ==52||
        seatNumber ==53||seatNumber ==54||seatNumber ==61||seatNumber ==62||seatNumber ==63||
        seatNumber ==64||seatNumber ==71||seatNumber ==72||seatNumber ==73||seatNumber ==74||
        seatNumber ==81||seatNumber ==82||seatNumber ==83||seatNumber ==84||seatNumber ==91||
        seatNumber ==92||seatNumber ==93||seatNumber ==94){
        System.out.println("Seat " +seatNumber+ " is Available ~");
        return true;
}
        else {
        	System.out.println ("Seat "+seatNumber+ " is not Available ~");
            return false;
            
        }
  
    
}

public void  printInfo () {
	  
}

public String toString() {
	return "AirplaneSeats [seatNumber=" + seatNumber + ", reserved=" + reserved + "]";
}}
