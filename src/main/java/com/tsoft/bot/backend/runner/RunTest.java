package com.tsoft.bot.backend.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(
		features={"src//main//resources//features"},
		glue={
				"com.tsoft.bot.backend.steps"
		},
		//plugin = {"pretty", "html:target/cucumber"},
		plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:report/cucumber-report/report.html"},
	    tags = {"@Create, @Register , @Update, @GetResource"},
		strict =  false,
		monochrome = true
	)

@Test
public class RunTest extends AbstractTestNGCucumberTests{ }

