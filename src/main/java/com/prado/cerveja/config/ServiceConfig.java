package com.prado.cerveja.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.prado.cerveja.service.CadastroCervejaService;
import com.prado.cerveja.storage.local.FotoStorage;
import com.prado.cerveja.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroCervejaService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage(){
		return new FotoStorageLocal();
	}

}
