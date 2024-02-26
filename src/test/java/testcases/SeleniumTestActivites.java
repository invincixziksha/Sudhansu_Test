

package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import coreUtilities.testutils.ApiHelper;
import coreUtilities.utils.FileOperations;
import pages.HomePage;
import pages.InputPage;
import pages.StartupPage;
import testBase.AppTestBase;

public class SeleniumTestActivites extends AppTestBase {
	
	Map<String, String> configData;
	Map<String, String> loginCredentials;
	String expectedDataFilePath = testDataFilePath+"expected_data.json";
	StartupPage startupPage;
	HomePage homePage;
	InputPage inputPage;
	String numberToBeEntered="3432";
	
	@Parameters({"browser", "environment"})
	@BeforeClass(alwaysRun = true)
	public void initBrowser(String browser, String environment) throws Exception {
		configData = new FileOperations().readJson(config_filePath, environment);
		configData.put("url", configData.get("url").replaceAll("[\\\\]", ""));
		configData.put("browser", browser);
		
		boolean isValidUrl = new ApiHelper().isValidUrl(configData.get("url"));
		Assert.assertTrue(isValidUrl, configData.get("url")+" might be down at this moment. Please try after sometime.");
		initialize(configData);
		startupPage = new StartupPage(driver);
	}
	
	@Test(priority = 1, groups = {"sanity"}, description="handling inputs")
	public void navigateToDummySeleniumTest() throws Exception {
		//driver.get("https://www.amazon.com/");
		softAssert = new SoftAssert();
		Map<String, String> expectedData = new FileOperations().readJson(expectedDataFilePath, "HomePage_Title");
		homePage = new HomePage(driver);
		softAssert.assertEquals(homePage.getPageTitle(), expectedData.get("pageTitle"), 
				"page title is not matching please check manually");
		//clicking on Inputs link
		inputPage=homePage.clickOnInputLink();
		inputPage.sendKeysInNumberInputField(numberToBeEntered);
		
		softAssert.assertEquals(inputPage.getNumberInputBoxValue(), numberToBeEntered, 
				"entered text is not matching please check manually");		
		
		
	}
	
	@Test(priority = 2, groups = {"sanity"}, description="handling dropdowns")
	public void validateDropDownsInSelenium() throws Exception {
		driver.get("flipcard");	
	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("before closing the browser");
		browserTearDown();
	}
	
	@AfterMethod
	public void retryIfTestFails() throws Exception {
		startupPage.navigateToUrl(configData.get("url"));
		
	}
	
	
	
}
