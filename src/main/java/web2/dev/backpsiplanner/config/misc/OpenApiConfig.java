package web2.dev.backpsiplanner.config.misc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Psi Planner API",
                version = "1.0.0",
                description = "API that provides endpoints for the fullstack \\\"Psi Planner\\\" project.",
                contact = @Contact(
                        name = "Project on GitHub",
                        url = "https://github.com/igorzig13/back-psiplanner"
                )
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer")
public class OpenApiConfig {
}
