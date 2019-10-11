package dev.codenation.criptografiajuliocesar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resposta {

    @JsonProperty("numero_casas")
    private Integer numeroCasas;
    private String token;
    private String cifrado;
    private String decifrado;
    @JsonProperty("resumo_criptografico")
    private String resumoCriptografico;
}
