package eu.lsmyrak.invisiblesoftware.Devicegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Device Gateway API",
        version = "1.0",
        description = "MQTT gateway for smart room devices"
    )
)
@SpringBootApplication
public class DevicegatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevicegatewayApplication.class, args);
	}

}
