import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadFromSample {

	public ReadFromSample(String path) {
		try (Scanner z = new Scanner(new FileReader(new File(path)))) {
			int x = 0;
			while (z.hasNextLine() && x < 1002) {
				String[] lines = new String[6];
				String[] temp = z.nextLine().split(",");
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < temp.length; i++) {

//					if (i == 1) {
//						sb.append("\'" + lines[i] + "\'" + ",");
//					} else {
//					System.out.printf("%s", temp[i]);
//					lines[i] = temp[i];
//					if (i == 1) {
//						sb.append("\'" + lines[i] + "\'" + ",");
////						if (lines[3] == null || lines[3].equals("")) {
////							sb.append(",");
////						}
//					} else {
//						sb.append(lines[i] + ",");
//					}
//					}
					System.out.println(z.nextLine());
					x++;
				}
//				System.out.printf("(%s),\n", sb.toString().substring(0, sb.length() - 1));
			}
		} catch (FileNotFoundException ex) {

		}
	}

	public static void main(String[] args) {
		new ReadFromSample("C:\\Users\\Haku Wei\\Desktop\\trash\\MODULES\\workoursQueryInsert.sql");
	}

}
