package org.github.haschi.kata.axonblueprint.kontext.taschenrechner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@CucumberOptions(monochrome = false,
        strict = true,
        plugin = {"pretty:target/cucumber/integration.txt", "html:target/site/cucumber-integration-pretty",
                "json:target/cucumber/integration.json", "junit:target/cucumber/integration.xml"},
        tags = {"~@ignore"})
@RunWith(Cucumber.class)
public class RunCukesTest {
}

