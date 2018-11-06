package dta.spring.tpSpringAngular.poneyRace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "dta.spring")
@EntityScan(basePackages = "dta.spring")
@EnableJpaRepositories(basePackages = "dta.spring")

public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
