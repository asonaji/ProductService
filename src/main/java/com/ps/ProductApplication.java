package com.ps;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ProductApplication {

	public static void main(String[] args) {
		opendeFaultBrowser();

		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public Docket demoApi() {
		return new Docket(DocumentationType.SWAGGER_2)// <3>
				.select()// <4>
				.apis(RequestHandlerSelectors.any())// <5>
				.paths(Predicates.not(PathSelectors.regex("/error.*")))// avoid												// basic-error-controll
				.build();
		// http://localhost:8085/swagger-ui.html
	}

	private static void opendeFaultBrowser() {
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			try {
				Desktop.getDesktop().browse(new URI("http://localhost:8085/swagger-ui.html"));
				Desktop.getDesktop().browse(new URI("http://localhost:8085/h2-console"));
				
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}
}
