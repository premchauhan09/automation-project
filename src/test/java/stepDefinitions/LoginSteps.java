package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginSteps {
    @Given("user is on login page")
    public void user_on_login_page() {
        System.out.println("User navigates  to login page");
    }

    @When("user enters valid credentials")
    public void enter_credentials() {
        System.out.println("User enters credentials");
    }

    @Then("user should be logged in")
    public void verify_login() {
        System.out.println("Login successful");
    }
}
