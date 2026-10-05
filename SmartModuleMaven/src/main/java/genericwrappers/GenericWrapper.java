package genericwrappers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import webutils.LoadProperties;

public class GenericWrapper {

	public static WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor jsExecutor;
	public Select dropdown;
	public Actions action;
	public Robot robot;
	public TakesScreenshot screenshot;
	public Alert alert;
	private static final Logger logger = LogManager.getLogger(GenericWrapper.class);

	public GenericWrapper(WebDriver driver) {
		this.driver=driver;
	}

	public void initializeDriver(String browserName) {
		if (driver == null) {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else {
				logger.error("Unsupported browser: {}", browserName);
				throw new IllegalArgumentException("Unsupported browser: " + browserName);
			}
		}
	}

	public void setImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void openUrl(String url) {
		driver.get(url);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void enterText(WebElement element, String text) {
		webdriverWaitElementToBeVisible(element);
		element.sendKeys(text);
	}

	public void clickElement(WebElement element) {
		waitForElementToBeClickable(element);
		element.click();
	}

	public void takeScreenshot(String fileName) throws IOException {
		screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File("Screenshots/" + fileName + ".jpeg");
		FileUtils.copyFile(src, dest);
	}

	public void scrollToElement(WebElement element) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void clickUsingJS(WebElement element) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void highlightElement(WebElement element) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('style','color:Red')", element);
	}

	public String getPageTitleUsingJS() {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript("return document.title;").toString();
	}

	public void selectDropdownOption(WebElement element, String method, String value) {
		dropdown = new Select(element);
		if (method.equalsIgnoreCase("value")) {
			dropdown.selectByValue(value);
		} else if (method.equalsIgnoreCase("visibletext")) {
			dropdown.selectByVisibleText(value);
		} else if (method.equalsIgnoreCase("index")) {
			int index = Integer.parseInt(value);
			dropdown.selectByIndex(index);
		} else {
			logger.warn("Invalid selection method. Use 'value', 'visibletext', or 'index'");
		}
	}

	public WebElement getSelectedOption(WebElement element) {
		dropdown = new Select(element);
		return dropdown.getFirstSelectedOption();
	}

	public List<WebElement> getAllDropdownOptions(WebElement element) {
		dropdown = new Select(element);
		return dropdown.getOptions();
	}

	public void deselectByValue(WebElement element, String value) {
		dropdown = new Select(element);
		dropdown.deselectByValue(value);
	}

	public void deselectAllOptions(WebElement element) {
		dropdown = new Select(element);
		dropdown.deselectAll();
	}

	public void performMouseAction(String actionType, WebElement element) {
		action = new Actions(driver);
		if (actionType.equalsIgnoreCase("hover")) {
			action.moveToElement(element).perform();
		} else if (actionType.equalsIgnoreCase("doubleclick")) {
			action.doubleClick(element).perform();
		} else if (actionType.equalsIgnoreCase("hold")) {
			action.clickAndHold(element).perform();
		} else {
			logger.warn("Unsupported mouse action: {}", actionType);
		}
	}

	public void rightClickElement(WebElement element) {
		action = new Actions(driver);
		action.contextClick(element).perform();
	}

	public void dragAndDropElement(WebElement source, WebElement target) {
		action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
	}

	public void handleAlert(String actionType, String inputText) {
		alert = driver.switchTo().alert();
		if (actionType.equalsIgnoreCase("OK")) {
			alert.accept();
		} else if (actionType.equalsIgnoreCase("Cancel")) {
			alert.dismiss();
		} else if (actionType.equalsIgnoreCase("Input")) {
			alert.sendKeys(inputText);
			alert.accept();
		} else {
			logger.warn("Invalid alert action: {}", actionType);
		}
	}

	public void pressEnterKey() throws AWTException {
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void pressTabKey() throws AWTException {
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	public void pressDownArrowKey() throws AWTException {
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
	}

	public void pressUpArrowKey() throws AWTException {
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_UP);
		robot.keyRelease(KeyEvent.VK_UP);
	}

	public void waitForElementToBeClickable(WebElement element) {
		int timeOutSeconds = Integer.parseInt(LoadProperties.prop.getProperty("explicitTimeOut"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void webdriverWaitElementToBeVisible(WebElement element) {
		int time = Integer.parseInt(LoadProperties.prop.getProperty("explicitTimeOut"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForAlertPresence() {
		wait.until(ExpectedConditions.alertIsPresent());
	}

	// Static method to quit driver
	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
