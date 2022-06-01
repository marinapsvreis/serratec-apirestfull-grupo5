package com.residencia.ecommerce.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Arquivo2Service {

	@Value("${pasta.arquivos.imagem}")
    private Path path;

    private String diretorioArquivos;

    public void criarArquivo(String fileName, MultipartFile file) {

        try {
            try {
                Files.copy(file.getInputStream(), path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Não possível salvar o arquivo.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
