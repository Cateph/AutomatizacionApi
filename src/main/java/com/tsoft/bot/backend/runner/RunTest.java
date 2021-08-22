package com.tsoft.bot.backend.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
		features={"src//main//resources//features"},
		glue={
				"com.tsoft.bot.backend.steps"
		},
		plugin = {"pretty", "html:target/cucumber"},
	    tags = {"@Create"},
		strict =  false,
		monochrome = true
	)

@Test
public class RunTest extends AbstractTestNGCucumberTests{ }

