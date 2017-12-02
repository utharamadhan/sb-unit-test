package com.rpramadhan.sbunittest.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author rpramadhan
 * Enter point for Unit Test Application
 */
@SpringBootApplication
@ComponentScan("com.rpramadhan.sbunittest")
public class SBUnitTestMain {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SBUnitTestMain.class, args);
	}

}
