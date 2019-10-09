package dev.codenation.criptografiajuliocesar.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dev.codenation.criptografiajuliocesar.service.CriptografiaCesarException;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

public class CriptografiaControllerTest {

	private CriptografiaController controller = new CriptografiaController();
	
	@Test
	public void PegarMensagemESalvarArquivoJsonTest() {
		try {
			assertNotNull(controller.getMensagemAndSaveToFile());
		} catch (CriptografiaCesarException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void enviarArquivoJsonTest(){
		try{
			controller.enviarArquivoJson();
		} catch (CriptografiaCesarException cce) {
			cce.printStackTrace();
		} catch (HttpClientErrorException hcee){
			hcee.printStackTrace();
		}
	}

}
