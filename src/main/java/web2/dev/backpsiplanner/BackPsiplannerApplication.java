package web2.dev.backpsiplanner;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackPsiplannerApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_USER", dotenv.get("DB_USER"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.setProperty("SECURITY_CONFIG_KEY", dotenv.get("SECURITY_CONFIG_KEY"));

		SpringApplication.run(BackPsiplannerApplication.class, args);
	}

}
