package dev.codenation.criptografiajuliocesar.controller;

import dev.codenation.criptografiajuliocesar.model.Resposta;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

@Slf4j
@NoArgsConstructor
public class RespostaJsonWriter {

    private String converterRepostaParaJSON(Resposta resposta) {
        String respostaJson = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            respostaJson = mapper.writeValueAsString(resposta);
            log.info("Sucesso ao converter objeto: {}", respostaJson);
        } catch (Exception e) {
            log.error("Erro ao converter objeto Resposta para Json", e);
        }
        return respostaJson;
    }

    public void escreverNoArquivo(Resposta resposta) throws IOException {
        FileWriter escritorArquivo = new FileWriter("answer.json");
        escritorArquivo.write(converterRepostaParaJSON(resposta));
        escritorArquivo.close();

        converterRepostaParaJSON(resposta);
    }
}
