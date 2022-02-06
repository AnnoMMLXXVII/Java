package main.java.com.anno.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import main.java.com.anno.shared.CC_Constant;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class WixNursingNotesPage extends PageObject {

	WebDriver driver;

	@FindBy(id = "pro-gallery-margin-container")
	private WebElementFacade galleryContainer;

	public WixNursingNotesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.driver.get(CC_Constant.IMAGE_REPOSITORY);
	}

	public WebElementFacade getGalleryContainer() {
		return galleryContainer;
	}

	public WebElementFacade getImage(String title, int count, int bound) {
		return getGalleryContainer().findBy(By.xpath("//img[@alt='" + title + "-" + count + "-" + bound + ".png']"));
	}

}
