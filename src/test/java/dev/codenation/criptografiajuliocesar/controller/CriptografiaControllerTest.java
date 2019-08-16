package dev.codenation.criptografiajuliocesar.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dev.codenation.criptografiajuliocesar.service.CriptografiaCesarException;

public class CriptografiaControllerTest {

	private CriptografiaController controller = new CriptografiaController();
	
	@Test
	public void PegarMensagemESalvarArquivoJsonTest() {
		try {
			assertNotNull(controller.getMensagemAndSaveToFile());
		} catch (CriptografiaCesarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void enviarArquivoJson() {
//		try {
//		//	assertEquals(200, controller.enviarArquivoJson());
//		} catch (CriptografiaCesarException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
