package com.techzone.springbootswagger2;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.techzone.springbootswagger2.entities.Customer;
import com.techzone.springbootswagger2.repositories.CustomerRepository;

@SpringBootApplication
public class SpringBootSwagger2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSwagger2Application.class, args);
	}
	
	@Bean
	public CommandLineRunner initializeDataOnStartUp(CustomerRepository customerRepository) {
		return (String... args) -> {
//			getCustomers().stream().map(c -> c.getCustomerName()).collect(Collectors.toList()).forEach(System.out::println);
			getCustomers().stream().forEach(customerRepository::save);
		};
	}
	
	private List<Customer> getCustomers() {
		Properties ymlProperties = loadCustomersFromYmlFile();
		ConfigurationPropertySource propertySource = new MapConfigurationPropertySource(ymlProperties);
		return new Binder(propertySource).bind("customer", Bindable.listOf(Customer.class)).get();
	}
	
	private Properties loadCustomersFromYmlFile() {
		YamlPropertiesFactoryBean yamlPropertiesFactory = new YamlPropertiesFactoryBean();
		yamlPropertiesFactory.setResources(new ClassPathResource("customers.yml"));
		return yamlPropertiesFactory.getObject();
	}
}
