package cucumber.steps;

import cucumber.models.User;
import cucumber.utils.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class Steps {
    private final ScenarioContext context;

    public Steps(ScenarioContext context) {
        this.context = context;
    }

    @Given("say {string}")
    public void saySomething(String text) {
        System.out.println(text);
    }

    @Given("create user with name {string} and password {string}")
    public void createUser(String name, String password) {
        User user = new User(name, password);
        context.set("user", user);
    }

    @Given("create user via DataTable")
    public void createUserViaDataTable(DataTable dataTable) {
        Map<String, String> row = dataTable.asMap(String.class, String.class);

        User user = new User(row.get("name"), row.get("password"));
        context.set("user", user);
    }

    @When("change created user name to {string}")
    public void changeCreatedUserName(String name) {
        User user = context.get("user");
        user.setName(name);
        context.set("user", user);
    }

    @When("change created user password to {string}")
    public void changeCreatedUserPasswordToNewUserPassword(String password) {
        User user = context.get("user");
        user.setPassword(password);
        context.set("user", user);
    }

    @Then("check that user has name {string} and password {string}")
    public void checkThatUserHasNameNewUserNameAndPasswordNewUserPassword(String name, String password) {
        User user = context.get("user");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(user.getName(), name);
        softAssert.assertEquals(user.getPassword(), password);
        softAssert.assertAll();
    }
}
