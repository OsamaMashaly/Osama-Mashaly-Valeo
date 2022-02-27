package StepDefinition;


import org.openqa.selenium.chrome.ChromeDriver;

import PageObject.searchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class searchSteps {

	public static ChromeDriver driver ;
	public  searchPage page;

	
	@Given("user is on google page")
	public void user_is_on_google_page() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		 driver = new ChromeDriver();
			driver.manage().window().maximize();
			page = new searchPage(driver );
		driver.get("https://www.google.com");
	}

	@When("user enter cars in london")
	public void user_enter_cars_in_london() {
		page.fillElementSearch("cars in london").pressEnterSearch();
		
		

	}



	@Then("list of search items")
	public void list_of_search_items() {
		page.ValidateTheURL();

}
}
