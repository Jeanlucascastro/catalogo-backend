package com.sociedade.catalogoback;


import com.netflix.discovery.EurekaClient;
import com.sociedade.catalogoback.controllers.GreetingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Lazy;



@EnableDiscoveryClient
@SpringBootApplication
public class CatalogoApplication implements GreetingController {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}


	@Autowired
	@Lazy
	private EurekaClient eurekaClient;

	@Value("${spring.application.name}")
	private String appName;
	@Override
	public String greeting() {
		return String.format(
				"Hello from '%s'!", eurekaClient.getApplication(appName).getName());
	}

//	@RestController
//	class ServiceInstanceRestController {
//
//		@Autowired
//		private DiscoveryClient discoveryClient;
//
//		@RequestMapping("/service-instances/{applicationName}")
//		public List<ServiceInstance> serviceInstancesByApplicationName(
//				@PathVariable String applicationName) {
//			return this.discoveryClient.getInstances(applicationName);
//		}
//	}

}
