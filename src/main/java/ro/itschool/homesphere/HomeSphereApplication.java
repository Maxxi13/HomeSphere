package ro.itschool.homesphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HomeSphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeSphereApplication.class, args);
	}

}
