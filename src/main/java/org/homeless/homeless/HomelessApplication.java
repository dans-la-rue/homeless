package org.homeless.homeless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HomelessApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomelessApplication.class, args);
    }

}
