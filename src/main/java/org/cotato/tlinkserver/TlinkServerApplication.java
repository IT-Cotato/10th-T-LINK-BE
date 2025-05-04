package org.cotato.tlinkserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TlinkServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlinkServerApplication.class, args);
    }

}
