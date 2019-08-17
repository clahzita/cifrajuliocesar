package dev.codenation.criptografiajuliocesar.controller;

import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import dev.codenation.criptografiajuliocesar.model.Resposta;

public class RespostaJsonWriter {
	
	
	private String converterRepostaParaJSON(Resposta resposta)
	{
	    String respostaJson = "";
	    try
	    {
	        ObjectMapper mapper = new ObjectMapper();
	        respostaJson = mapper.writeValueAsString(resposta);
	    }
	    catch(Exception ex)
	    {
	        System.out.println("Erro ao converter objeto Resposta para Json");
	        ex.printStackTrace();
	    }
	    
	    System.out.println(respostaJson);
	    
	    return respostaJson;
	}
	
	public void escreverNoArquivo(Resposta resposta) {
		 	FileWriter escritorArquivo = null;
	        	         
	        try{
	        	escritorArquivo = new FileWriter("answer.json");
	            escritorArquivo.write(converterRepostaParaJSON(resposta));
	        	escritorArquivo.close();

	        }
	        catch(IOException e){
	            e.printStackTrace();
			}
		converterRepostaParaJSON(resposta);
	}
	
	

}
