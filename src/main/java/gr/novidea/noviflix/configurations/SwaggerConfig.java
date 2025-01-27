package gr.novidea.noviflix.configurations;

import gr.novidea.noviflix.constants.VersionConstant;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NoviFlix API")
                        .version(VersionConstant.SERVICE_VERSION)
                        .description("API for managing movies in the NoviFlix application.")
                        .contact(new Contact()
                                .name("Support Team")
                                .email("konstantinos.dimitriou@novidea.gr")
                                .url("https://noviflix.com")));
    }
}
