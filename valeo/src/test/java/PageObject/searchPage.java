package PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class searchPage {
	
	ChromeDriver driver ;
	By search = By.name("q");
	By results = By.xpath("//*[@class='NJo7tc Z26q7c jGGQ5e']");

	
	public searchPage (ChromeDriver driver ) {
	
		this.driver = driver ;
	}
	public searchPage fillElementSearch(String input   ) {
		driver.findElement(search).clear();
		driver.findElement(search).sendKeys(input);
		return this;
	}
	
	public searchPage clickOnElementSearch () {
		driver.findElement(search).click();
		return this;
		
	}
	public searchPage pressEnterSearch( ) {
		driver.findElement(search).sendKeys(Keys.ENTER);
		return this;
	}
	public void ValidateTheURL() {
		List <WebElement> l = driver.findElements(results);
		

		
		List <String> href  = new ArrayList <String>();
		for(int i =0 ; i<l.size();i++) {
			href.add(l.get(i).findElement(By.xpath("div/a")).getAttribute("href"));
			WebElement f = l.get(i).findElement(By.xpath("div/a/h3/span"));
			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(f));
			
					f.click();
			Assert.assertEquals(((driver.getCurrentUrl().contains(href.get(i)))), true);
			System.out.println(href.get(i));

			driver.navigate().back();
	}
	}

}
