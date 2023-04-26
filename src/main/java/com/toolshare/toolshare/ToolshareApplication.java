
package com.toolshare.toolshare;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Toolshare application.
 */

@SpringBootApplication
@OpenAPIDefinition
public class ToolshareApplication {

	/**
	 * The main method for running the Toolshare application.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ToolshareApplication.class, args);
	}
}
