package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Demo8Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Demo8Application.class, args);

		VerifyWifiLogin verifyWifiLogin = new VerifyWifiLogin();

		verifyWifiLogin.wifiAutoLogin();
	}
}
