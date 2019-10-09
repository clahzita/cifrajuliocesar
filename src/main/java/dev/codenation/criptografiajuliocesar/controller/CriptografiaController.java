package dev.codenation.criptografiajuliocesar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dev.codenation.criptografiajuliocesar.model.Resposta;
import dev.codenation.criptografiajuliocesar.service.CriptografiaCesarException;
import dev.codenation.criptografiajuliocesar.service.CriptografiaService;

import java.io.IOException;

@RestController
public class CriptografiaController {

	private static final Logger logger = LoggerFactory.getLogger(CriptografiaController.class);

	private static final String URL_GET = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=e500bcae50765e50e1008ecc857ac5aebc5fe34e";
	private static final String URL_POST = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=e500bcae50765e50e1008ecc857ac5aebc5fe34e";

	private CriptografiaService service = new CriptografiaService();

	/**
	 * Pega uma mensagem encriptografada, decriptografa e adiona em um arquivo o
	 * json.
	 * 
	 * @throws CriptografiaCesarException
	 */
	@GetMapping("/receber")
	public Resposta getMensagemAndSaveToFile() throws CriptografiaCesarException, IOException {

		RestTemplate restTemplate = new RestTemplate();
		Resposta recebido = restTemplate.getForObject(URL_GET, Resposta.class);

		RespostaJsonWriter writer = new RespostaJsonWriter();

		logger.info("Mensagem recebida e salva no arquivo answer.json");

		Resposta completa;

		if (recebido.getCifrado() != null || !recebido.getCifrado().isEmpty()) {

			completa = service.decriptar(recebido);

		} else {
			completa = service.encriptar(recebido);
		}

		if (completa != null) {
			writer.escreverNoArquivo(completa);
		}
		
		return completa;

	}

	@RequestMapping(
			value="/enviar", 
			method= RequestMethod.GET,
			produces="multpart/form-data")	
	public void enviarArquivoJson() throws CriptografiaCesarException {

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.MULTIPART_FORM_DATA);
		logger.info("Header ok");
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("answer", new FileSystemResource("answer.json"));
		logger.info("body ok");
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, header);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(URL_POST, requestEntity, String.class);
		
		logger.info("Codigo do Status da Resposta: "+ response.getStatusCodeValue());

	}
}
