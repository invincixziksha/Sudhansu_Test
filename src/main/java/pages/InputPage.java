package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputPage extends StartupPage {
	
	//Web elements
		By pageTitleText = By.xpath("//div[@id='content']//h3");
		By number_input_box_xpath = By.xpath("//input[@type='number']");
		//Getting the page name
		String pageName = this.getClass().getSimpleName();

		public InputPage(WebDriver driver) {
			super(driver);
			commonEvents.waitTillElementLocated(pageTitleText, 120)
						.waitTillElementVisible(pageTitleText, 30);
		}
		
		public InputPage sendKeysInNumberInputField(String data) {
			try {
				 commonEvents.sendKeys(number_input_box_xpath,data);
			}catch(Exception e) {
				throw e;
			}
			return new InputPage(driver);
		}
		
		public String getNumberInputBoxValue() {
			try {
				return commonEvents.getAttribute(number_input_box_xpath, "value");
			}catch(Exception e) {
				throw e;
			}
		}

}
