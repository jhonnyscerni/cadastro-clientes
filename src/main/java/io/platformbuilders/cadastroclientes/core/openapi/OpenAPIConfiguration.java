package io.platformbuilders.cadastroclientes.core.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
            .title("API de sistema Cadastro de Clientes")
            .version("1.0")
            .contact(apiContact())
            .license(apiLicence());
    }

    private License apiLicence() {
        return new License()
            .name("MIT Licence")
            .url("https://opensource.org/licenses/mit-license.php");
    }

    private Contact apiContact() {
        return new Contact().name("Cadastro de Clientes").email("jhonnyscerni@gmail.com");
    }
}
