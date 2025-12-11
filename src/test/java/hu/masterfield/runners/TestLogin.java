package hu.masterfield.runners;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;


@Suite

@IncludeEngines("cucumber")

@ConfigurationParameter(key = "cucumber.plugin", value="rerun:target/cucumber_rerun.txt")

@SelectClasspathResource("features/login.feature")

@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "hu.masterfield.steps")

@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty," + "html:target/cucumber_report.html," + "json:target/cucumber_report.json")


public class
TestLogin {
}
