package dev.codenation.criptografiajuliocesar.controller;

import dev.codenation.criptografiajuliocesar.model.Resposta;
import dev.codenation.criptografiajuliocesar.service.CriptografiaCesarException;
import dev.codenation.criptografiajuliocesar.service.CriptografiaService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CriptografiaController {

    private String urlGet;
    private String urlPost;

    private CriptografiaService service;

    CriptografiaController(@Value("${service.url.get}") String urlGet, @Value("${service.url.post}") String urlPost,
                           CriptografiaService criptografiaService) {
        this.urlGet = urlGet;
        this.urlPost = urlPost;
        this.service = criptografiaService;
    }

	/**
	 * Pega uma mensagem encriptografada, decriptografa e adiona em um arquivo o
	 * json.
	 * 
	 * @throws CriptografiaCesarException
	 */
	@GetMapping("/receber")
	public Resposta getMensagemAndSaveToFile() throws CriptografiaCesarException, IOException {
        RestTemplate restTemplate = new RestTemplate();

        Resposta recebido = restTemplate.getForObject(urlGet, Resposta.class);

        RespostaJsonWriter writer = new RespostaJsonWriter();

        log.info("Mensagem recebida e salva no arquivo answer.json");

        Resposta completa = service.encriptar(recebido);

        if (Objects.nonNull(recebido) || Objects.nonNull(recebido.getCifrado()) || !recebido.getCifrado().isEmpty()) {
            completa = service.decriptar(recebido);
        }

        if (Objects.nonNull(completa)) {
            writer.escreverNoArquivo(completa);
        }
        return completa;
    }

    @GetMapping(value = "/enviar", produces = "multpart/form-data")
    public void enviarArquivoJson() {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.MULTIPART_FORM_DATA);
        log.info("Header ok");

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("answer", new FileSystemResource("answer.json"));
        log.info("body ok");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, header);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(urlPost, requestEntity, String.class);

        log.info("Codigo do Status da Resposta: {}", response.getStatusCodeValue());
    }
}
