package com.prado.cerveja.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;



public class FotoStorageLocal implements FotoStorage {
	
	private static final Logger logger = LoggerFactory.getLogger(FotoStorageLocal.class);
	
	private Path local;
	private Path localTeporario;	
	
	public FotoStorageLocal() {
		this.local = getDefault().getPath(System.getenv("USERPROFILE"),".cervejasFotos" );
		criarPastas();
	}

	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		String novoNome = null;
		if(files != null  && files.length > 0){
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.localTeporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro ao gravar arquivos", e);
			}
		}	
		return novoNome;
	}
	
	@Override
	public void salvar(String foto) {
		try {
			Files.move(this.localTeporario.resolve(foto), this.local.resolve(foto));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao salvar a foto e m local final", e);
		}
		try {
			Thumbnails.of(this.local.resolve(foto).toString()).size(40,68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao gerar thubnails", e);
		}
	}
	
	@Override
	public byte[] recuperarFotoTemporaria(String nome) {
		try {
			return Files.readAllBytes(this.localTeporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler a foto", e);
		}
	}
	
	@Override
	public byte[] recuperar(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao recuperar a foto", e);
		}
	}

	
	private String renomearArquivo(String nomeOriginal){
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
		return novoNome;
	}
	
	
	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTeporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTeporario);
			
			if(logger.isDebugEnabled()){
				logger.debug("Pasta criadas com sucesso");
				logger.debug("Pasta default " + this.local.toAbsolutePath());
				logger.debug("Pasta temporária " + this.localTeporario.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("Erro a criar pasta de fotos", e);
			
		}
		
	}
	
	
}
