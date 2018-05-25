package com.inredec.ATutorBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        ATutorBackendApplication.class,
		Jsr310JpaConverters.class
})
public class ATutorBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(ATutorBackendApplication.class, args);
	}
}
