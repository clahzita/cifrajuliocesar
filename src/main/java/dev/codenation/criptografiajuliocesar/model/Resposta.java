package dev.codenation.criptografiajuliocesar.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resposta {
	
	private Integer numero_casas;
	
	private String token;
	
	private String cifrado;
	
	private String decifrado;
	
	private String resumo_criptografico;
	
	

	public Integer getNumero_casas() {
		return numero_casas;
	}

	public void setNumero_casas(Integer numero_casas) {
		this.numero_casas = numero_casas;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCifrado() {
		return cifrado;
	}

	public void setCifrado(String cifrado) {
		this.cifrado = cifrado;
	}

	public String getDecifrado() {
		return decifrado;
	}

	public void setDecifrado(String decifrado) {
		this.decifrado = decifrado;
	}

	public String getResumo_criptografico() {
		return resumo_criptografico;
	}

	public void setResumo_criptografico(String resumo_criptografico) {
		this.resumo_criptografico = resumo_criptografico;
	}

	@Override
	public String toString() {
		return "Resposta [numero_casas=" + numero_casas + ", token=" + token + ", cifrado=" + cifrado + ", decifrado="
				+ decifrado + ", resumoCriptografico=" + resumo_criptografico + "]";
	}

	
	
}
