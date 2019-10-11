package dev.codenation.criptografiajuliocesar.service;

import dev.codenation.criptografiajuliocesar.model.Resposta;

import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class CriptografiaService {

    /**
     * Criptografa um texto usando a cifra de Cesar.
     *
     * @param decifrado Objeto com informações para encriptar
     * @return Reposta que consta informações sobre o texto encriptografado.
     * @throws CriptografiaCesarException
     */
    public Resposta encriptar(Resposta decifrado) throws CriptografiaCesarException {

        validate(decifrado.getDecifrado(), decifrado.getNumeroCasas());

        // Converter mensagem a encriptar para minúsculas
        String messagem = decifrado.getDecifrado().toLowerCase();
        decifrado.setDecifrado(messagem);

        // Delimitar o deslocamento dentro do intervalo de letrar minusculas 0..25
        decifrado.setNumeroCasas(decifrado.getNumeroCasas() % 26);

        // Converter a messagem para sha-1
        decifrado.setResumoCriptografico(DigestUtils.shaHex(messagem));

        // Encriptar a messagem
        StringBuilder cifrado = new StringBuilder();

        for (int i = 0; i < messagem.length(); i++) {
            int letraASCII = messagem.charAt(i);

            if (letraASCII < 97 || letraASCII > 122) {
                cifrado.append((char) letraASCII);
            } else {
                int letraDecifrada = letraASCII + decifrado.getNumeroCasas();

                if (letraDecifrada > 122) {
                    letraDecifrada -= 26;
                }
                cifrado.append((char) letraDecifrada);
            }
        }

        decifrado.setCifrado(cifrado.toString());

        return decifrado;
    }

    public Resposta decriptar(Resposta cifrado) throws CriptografiaCesarException {

        validate(cifrado.getCifrado(), cifrado.getNumeroCasas());

        // Converter mensagem a cifrada para minúsculas
        String msgCifradaStr = cifrado.getCifrado().toLowerCase();
        cifrado.setCifrado(msgCifradaStr);

        // Delimitar o deslocamento dentro do intervalo de letras minusculas 0..25
        int numeroCasas = cifrado.getNumeroCasas() % 26;

        // Encriptar a messagem
        StringBuilder msgDecifrada = new StringBuilder();

        for (int i = 0; i < msgCifradaStr.length(); i++) {
            int letraASCII = ((int) msgCifradaStr.charAt(i));

            if (letraASCII < 97 || letraASCII > 122) {
                msgDecifrada.append((char) letraASCII);
            } else {
                int letraCifrada = letraASCII - numeroCasas;

                if (letraCifrada < 97) {
                    letraCifrada += 26;
                }
                msgDecifrada.append((char) letraCifrada);
            }
        }

        cifrado.setDecifrado(msgDecifrada.toString());

        // Após decifrar a mensagem, convertê-la para sha-1
        cifrado.setResumoCriptografico(DigestUtils.shaHex(msgDecifrada.toString()));

        return cifrado;
    }

    private void validate(String mensagem, Integer numeroCasas) throws CriptografiaCesarException {
        StringBuilder msgExcecao = new StringBuilder();

        if (Objects.isNull(mensagem) || mensagem.isEmpty()) {
            msgExcecao.append("Mensagem inválida\n");
        }

        if (numeroCasas < 0) {
            msgExcecao.append("Número de casas do deslocamento deve ser maior que zero\n");
        }

        if (msgExcecao.length() > 0) {
            throw new CriptografiaCesarException(msgExcecao.toString());
        }
    }
}
