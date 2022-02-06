package main.java.com.anno.page;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.com.anno.shared.CC_Constant;
import net.serenitybdd.core.pages.WebElementFacade;

public class WixNursingNotesStepLib {

	private WixNursingNotesPage wixNursingNotesPage;

	private List<WebElement> imgs;
	private Map<String, String> images;
	private static String dimensions = "";

	public WixNursingNotesStepLib() {
		imgs = new ArrayList<>();
		images = new HashMap<>();
	}

	public void setWixPage(WixNursingNotesPage wixNursingNotesPage) {
		this.wixNursingNotesPage = wixNursingNotesPage;
	}

	public void startPageParsing(boolean toOpen) {
		if (!toOpen) {
			wixNursingNotesPage.getDriver().manage().window().minimize();
		} else {
			wixNursingNotesPage.getDriver().manage().window().maximize();
		}
		wixNursingNotesPage.getDriver().manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
	}

	public Map<String, String> getAllImagesFromParse(String title, int bound) {
		WebDriverWait wait = new WebDriverWait(wixNursingNotesPage.getDriver(), Duration.ofSeconds(25000));
		wait.withMessage("WAITING...");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(wixNursingNotesPage.getGalleryContainer()));
		JavascriptExecutor js = (JavascriptExecutor) wixNursingNotesPage.getDriver();
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		WebElementFacade img = null;
//			imgs.forEach(e -> {
//		String title = null;
		String src = null;
		String tempTitle = null;
		Actions a = new Actions(wixNursingNotesPage.getDriver());
		for (int i = bound; i >= 0; i--) {
			try {
				img = wixNursingNotesPage.getImage(title, i, bound);
//				a.moveToElement(img).perform();
				tempTitle = img.getAttribute("alt");
				src = img.getAttribute("src");
//				System.out.printf("INFO : %s-%s\n", title, src);
			} catch (StaleElementReferenceException | NoSuchElementException e) {
				System.out.printf("STALE IMG : %s - %s\n", tempTitle, src);

//				
//				title = imgs.get(i).getAttribute("alt");
//				src = imgs.get(i).getAttribute("src");
			}
			String[] splitSrc = src.split("/");
			scaleDown(splitSrc[7]);
			src = buildSrc(splitSrc);
			images.put(tempTitle, src);
		}
//			});
//		for (int i = 0; i < imgs.size(); i++) {
//			String title = imgs.item(i).getAttributes().getNamedItem("alt").getNodeValue();
//			String src = imgs.item(i).getAttributes().getNamedItem("src").getNodeValue();
//			String[] splitSrc = src.split("/");
//			scaleDown(splitSrc[7]);
//			src = buildSrc(splitSrc);
//			images.put(title, src);
//		}
		return images;
	}

	public void closeWindow() {
		wixNursingNotesPage.getDriver().close();
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

	public static WebDriver configDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.setBinary("C:\\Program Files (x86)\\chrome-win-96\\chrome.exe");
		return new ChromeDriver(opt);
	}
}
