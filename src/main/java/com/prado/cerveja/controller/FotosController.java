package com.prado.cerveja.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.prado.cerveja.dto.FotoDTO;
import com.prado.cerveja.storage.FotoStorageRunnable;

@RestController

public class FotosController {
	
	@RequestMapping("/fotos")
	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new FotoStorageRunnable(files, resultado) );
		thread.start();
		return resultado;
	}
	
}
