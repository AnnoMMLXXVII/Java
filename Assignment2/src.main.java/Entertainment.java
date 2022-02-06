import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Entertainment extends Device {

	private int volume;

	public Entertainment(String powerStatus, String room) {
		super(powerStatus, room);
		this.volume = 0;
	}

	public void setVolume(int volumne) {
		this.volume = volumne;
	}

	public int getVolume() {
		return volume;
	}

	public abstract void increaseVolume();

	public void descreaseVolume() {
		if (getVolume() < 0) {
			// Do nothing
			return;
		}
		int vol = getVolume(); // temporary volume
		setVolume(vol--); // increase volume by 1
	}

	protected List<String> retrieveContent(String file) {
		List<String> content = new ArrayList<>();
		try (Scanner z = new Scanner(new FileReader(new File(file)))) {
			while (z.hasNextLine()) {
				content.add(z.nextLine().trim().toLowerCase());
			}
		} catch (FileNotFoundException ex) {
			System.err.printf("ERROR WHILE TRYING TO READ FILE %s : %s", file, ex.getMessage());
		}
		return content;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + "Volumne=" + getVolume();
	}

//	private String name;

//	private int vol2;

//	public Entertainment() {
//		System.out.println("You are in the Entertainment Zone");
//
//	}
//
//	public void PlaySong() {
//		System.out.println("Which Song would you like me to play?");
//		name = sc.nextLine();
//		System.out.println(name + " is playing in 3..2..1");
//		System.out.println("Song is playing");
//
//	}
//
//	public void SwitchOnTV() {
//		System.out.println("Television Switched ON");
//		System.out.println("Which Channel would you like me to Switch on?");
//		name = sc.nextLine();
//		System.out.println(name + " is playing");
//	}
//
//	public void changeVolume() {
//		System.out.println("Increase or Decrease?");
//		vol = sc.next();
//		if (vol.equals("Increase")) {
//			System.out.println("By How much?");
//			vol2 = sc.nextInt();
//			System.out.println("Volmume increased by " + vol2);
//		} else {
//			System.out.println("By How much?");
//			int vol2 = sc.nextInt();
//			System.out.println("Volmume decreased by " + vol2);
//		}
//
//	}
//
//	void TVoff() {
//		System.out.println("Television turned off");
//	}
}