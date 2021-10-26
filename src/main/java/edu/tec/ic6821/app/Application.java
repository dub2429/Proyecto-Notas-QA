package edu.tec.ic6821.app;

import edu.tec.ic6821.app.identity.UI.UserInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	static UserInterface userInterface;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		userInterface = new UserInterface();
	}
}

