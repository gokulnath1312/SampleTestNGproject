package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
		features="src\\test\\resources\\feature\\GoogleSearchPage.feature",
		glue= {"stepDefs"},
		monochrome=true
		)


public class GoogleTestRunner extends AbstractTestNGCucumberTests {

}
