package com.itsraelx.l07.configurations;

import com.itsraelx.l07.models.Cat;
import com.itsraelx.l07.repositories.CatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CatRepository repository) {
        return args -> {
            log.info("Preloading {}", repository.save(new Cat("Felix", "Mieszaniec")));
            log.info("Preloading {}", repository.save(new Cat("Filemon", "Maine Coon")));
        };
    }
}