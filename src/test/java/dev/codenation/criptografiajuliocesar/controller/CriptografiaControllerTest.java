package dev.codenation.criptografiajuliocesar.controller;

import dev.codenation.criptografiajuliocesar.service.CriptografiaCesarException;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CriptografiaControllerTest {

	@Autowired
	private CriptografiaController controller;
	
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
		} catch (HttpClientErrorException hcee){
			hcee.printStackTrace();
		}
	}

}
