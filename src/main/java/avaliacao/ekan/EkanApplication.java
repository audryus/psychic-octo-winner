package avaliacao.ekan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EkanApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkanApplication.class, args);
	}

}
