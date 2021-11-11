package main.java.com.anno.driver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

public class WebScraper {

//	public static void main(String args[]) {
//		// https://annommlxxvii.wixsite.com/nothing-201-slides
//		Map<String, String> images = parsingImagesFromSite(args[0]);
//		writeToFile(images, args[1].trim() + ".txt");
//	}

	private static String dimensions = "";

	public static Map<String, String> parsingImagesFromSite(String url) {
		Map<String, String> images = new HashMap<>();
		InputStream input;
		try {
			input = new URL(url).openStream();
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.getMessage();
			}
			org.w3c.dom.Document document = new Tidy().parseDOM(input, null);
			NodeList imgs = document.getElementsByTagName("img");
			for (int i = 0; i < imgs.getLength(); i++) {
				String title = imgs.item(i).getAttributes().getNamedItem("alt").getNodeValue();
				String src = imgs.item(i).getAttributes().getNamedItem("src").getNodeValue();
				String[] splitSrc = src.split("/");
				scaleDown(splitSrc[7]);
				src = buildSrc(splitSrc);
				images.put(title, src);
			}
		} catch (MalformedURLException e) {
//			e.printStackTrace();
		} catch (IOException ioe) {
//			ioe.printStackTrace();
		}
		return images;
	}

	private static void scaleDown(String dimension) {
		String dim = dimension.replaceAll("[A-z]_", "");
		String[] split = dim.split(",");
		scaleDownPicture(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
	}

	private static void scaleDownPicture(int x, int y) {
//		int width = Math.floorDiv(x, 2);
//		int height = Math.floorDiv(y, 2);
		dimensions = String.format("w_%s,h_%s,q_%s", 906, 830, 100);
	}

	private static String buildSrc(String[] src) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < src.length; i++) {
			if (i == 7) {
				src[i] = dimensions;
				sb.append(dimensions + "/");
			} else {
				sb.append(src[i] + "/");
			}
			if (i == src.length - 1) {
				sb.append(src[i]);
			}
		}
		return sb.toString();
	}

	private static synchronized void writeToFile(Map<String, String> images, String fileName) {
		File file = new File(fileName);
		try (FileWriter fw = new FileWriter(file, true)) {
			images.forEach((e, v) -> {
				try {
					fw.write(String.format("%s,%s\n", e, v));
				} catch (IOException e1) {
					System.err.println("UNABLE TO WRITE LINE!");
				}
			});
		} catch (IOException e) {
			System.err.println("CANNOT WRITE TO  THE FILE!");
		}
	}

}
