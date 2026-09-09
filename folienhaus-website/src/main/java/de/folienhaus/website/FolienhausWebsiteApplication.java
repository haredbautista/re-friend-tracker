package de.folienhaus.website;

import de.folienhaus.website.config.CompanyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Einstiegspunkt der Website von "Das Folienhaus".
 */
@SpringBootApplication
@EnableConfigurationProperties(CompanyProperties.class)
public class FolienhausWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(FolienhausWebsiteApplication.class, args);
    }
}
