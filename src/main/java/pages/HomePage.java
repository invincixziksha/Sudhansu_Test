package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends StartupPage {
	
	//Web elements
		By pageTitleText = By.xpath("//div[@id='content']/h1");
		By inputLink_xpath = By.xpath("//a[text()='Inputs']");
		
		//Getting the page name
		String pageName = this.getClass().getSimpleName();

		public HomePage(WebDriver driver) {
			super(driver);
			commonEvents.waitTillElementLocated(pageTitleText, 120)
						.waitTillElementVisible(pageTitleText, 30);
		}
		
		public String getPageTitle() {
			try {
				return commonEvents.waitTillElementLocated(pageTitleText, 30)
									.waitTillElementVisible(pageTitleText, 30)
									.getText(pageTitleText);
			}catch(Exception e) {
				throw e;
			}
		}
		public InputPage clickOnInputLink() {
			try {
				 commonEvents.click(inputLink_xpath);
			}catch(Exception e) {
				throw e;
			}
			return new InputPage(driver);
		}

}
