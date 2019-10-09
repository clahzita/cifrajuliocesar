package dev.codenation.criptografiajuliocesar.controller;

import dev.codenation.criptografiajuliocesar.model.Resposta;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class RespostaJsonWriterTest {
    private RespostaJsonWriter respostaJsonWriter = new RespostaJsonWriter();
    private Resposta resposta = null;

    @Test
    public void converterRespostaParaJsonTest(){
        try{
            respostaJsonWriter.escreverNoArquivo(resposta);

        } catch (IOException ioe){
            ioe.printStackTrace();
        }

    }
}
