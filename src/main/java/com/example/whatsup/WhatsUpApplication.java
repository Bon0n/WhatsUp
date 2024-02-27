package com.example.whatsup;

import com.example.whatsup.dto.InstanceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@SpringBootApplication
public class WhatsUpApplication {
	private static final Logger log = LoggerFactory.getLogger(WhatsUpApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WhatsUpApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/*
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			HttpHeaders headers = new HttpHeaders();
			headers.set("apikey", "B6D711FCDE4D4FD5936544120E713976");
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<List<InstanceDTO>> instance = restTemplate.exchange(
					"http://localhost:8080/instance/fetchInstances", HttpMethod.GET, entity,
                    new ParameterizedTypeReference<>() {
                        @Override
                        public Type getType() {
                            return super.getType();
                        }
                    }
			);
			List<InstanceDTO> list = instance.getBody();
			log.info(instance.toString());
			System.out.print(list);

		};

	}
	 */
}
