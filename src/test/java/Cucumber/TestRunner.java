package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",
glue={"Stepdefinations"},
monochrome=true, tags="@SubmitOrder",plugin= {"html:src/test/java/Cucumber/target.html"})
public class TestRunner extends AbstractTestNGCucumberTests {

}
