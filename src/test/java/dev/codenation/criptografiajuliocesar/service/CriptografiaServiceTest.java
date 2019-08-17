package dev.codenation.criptografiajuliocesar.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import dev.codenation.criptografiajuliocesar.model.Resposta;

public class CriptografiaServiceTest {

	private CriptografiaService service;

	private Resposta msgEncriptada1;

	private Resposta msgRecebida;

	private Resposta msgDecifrada;

	@Before
	public void setUp() {
		msgEncriptada1 = new Resposta();
		msgEncriptada1.setCifrado(
				"oxldb xw fqh rwbcnjm xo fqjc rw hxda lxmn fruu vjtn hxd j knccna mnenuxyna sxamr kxpprjwx");
		msgEncriptada1.setNumero_casas(9);

		msgRecebida = new Resposta();
		msgRecebida.setNumero_casas(3);

		service = new CriptografiaService();
	}

	@Test
	public void EncriptografarTest() {
		msgRecebida.setDecifrado("a ligeira raposa marrom saltou sobre o cachorro cansado");
		
		String esperado = "d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr";

		Resposta msgCifrada;
		try {
			msgCifrada = service.encriptar(msgRecebida);
			assertEquals(esperado, msgCifrada.getCifrado());
			assertNotNull(msgCifrada.getResumo_criptografico());
		} catch (CriptografiaCesarException e) {
			fail();
		}
	}
	
	@Test
	public void EncriptografarComNumeroEPontoTest() {
		msgRecebida.setDecifrado("foram 160 pessoas.");
		
		String esperado = "irudp 160 shvvrdv.";

		Resposta msgCifrada;
		try {
			msgCifrada = service.encriptar(msgRecebida);
			assertEquals(esperado, msgCifrada.getCifrado());
			assertNotNull(msgCifrada.getResumo_criptografico());
		} catch (CriptografiaCesarException e) {
			fail();
		}
	}
	
	@Test
	public void EncriptografarComMaiusculaTest() {
		msgRecebida.setDecifrado("MARIA");
		
		String esperado = "pduld";

		Resposta msgCifrada;
		try {
			msgCifrada = service.encriptar(msgRecebida);
			assertEquals(esperado, msgCifrada.getCifrado());
			assertNotNull(msgCifrada.getResumo_criptografico());
		} catch (CriptografiaCesarException e) {
			fail();
		}
	}

	@Test
	public void DecriptografarTest() {
		msgRecebida.setCifrado("d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr");
		
		String esperado = "a ligeira raposa marrom saltou sobre o cachorro cansado";

		Resposta msgDecifrada;
		try {
			msgDecifrada = service.decriptar(msgRecebida);
			assertEquals(esperado, msgDecifrada.getDecifrado());
			assertNotNull(msgDecifrada.getResumo_criptografico());
		} catch (CriptografiaCesarException e) {
			fail();
		}

	}
	
	@Test
	public void DecriptografarComNumeroEPontoTest() {
		msgRecebida.setCifrado("irudp 160 shvvrdv.");
		
		String esperado = "foram 160 pessoas.";

		Resposta msgDecifrada;
		try {
			msgDecifrada = service.decriptar(msgRecebida);
			assertEquals(esperado, msgDecifrada.getDecifrado());
			assertNotNull(msgDecifrada.getResumo_criptografico());
		} catch (CriptografiaCesarException e) {
			fail();
		}

	}
	
	@Test
	public void DecriptografarComMaiusculaTest() {
		msgRecebida.setCifrado("pduld");
		
		String esperado = "maria";

		Resposta msgDecifrada;
		try {
			msgDecifrada = service.decriptar(msgRecebida);
			assertEquals(esperado, msgDecifrada.getDecifrado());
			assertNotNull(msgDecifrada.getResumo_criptografico());
		} catch (CriptografiaCesarException e) {
			fail();
		}

	}

	@Test
	public void DecriptografarComNumeroCasaNegativoTest() {
		msgRecebida.setCifrado("d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr");
		msgRecebida.setNumero_casas(-1);

		Resposta msgDecifrada;

		try {
			msgDecifrada = service.decriptar(msgRecebida);
		} catch (CriptografiaCesarException e) {
			String esperado = "Número de casas do deslocamento deve ser maior que zero\n";
			assertEquals(esperado, e.getMessage());

		}
	}
	
	@Test
	public void DecriptografarComMensagemNullTest() {
		msgRecebida.setCifrado(null);

		Resposta msgDecifrada;

		try {
			msgDecifrada = service.decriptar(msgRecebida);
		} catch (CriptografiaCesarException e) {
			String esperado = "Mensagem inválida\n";
			assertEquals(esperado, e.getMessage());

		}
	}
	
	@Test
	public void DecriptografarComMensagemVaziaTest() {
		msgRecebida.setCifrado("");

		Resposta msgDecifrada;

		try {
			msgDecifrada = service.decriptar(msgRecebida);
		} catch (CriptografiaCesarException e) {
			String esperado = "Mensagem inválida\n";
			assertEquals(esperado, e.getMessage());

		}
	}
	
	@Test
	public void DecriptografarComMensagemENumeroCasaInválidosVaziaTest() {
		msgRecebida.setCifrado("");
		msgRecebida.setNumero_casas(-1);

		try {
			msgDecifrada = service.decriptar(msgRecebida);
		} catch (CriptografiaCesarException e) {
			String esperado = "Mensagem inválida\nNúmero de casas do deslocamento deve ser maior que zero\n";
			assertEquals(esperado, e.getMessage());

		}
	}
	
	@Test
	public void miniTestes() {
		
	}
	
}
