package Entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leitor {

    public static List<Propriedades> lerPropriedades(String caminhoArquivo) {
        List<Propriedades> propriedades = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            int contador = 0;
            while ((linha = br.readLine()) != null && contador < 20) {
                linha = linha.trim();
                if (linha.isEmpty()) continue; // pula linhas vazias

                String[] partes = linha.split("\\s+");
                if (partes.length != 2) {
                    System.out.println("Linha com formato inválido: " + linha);
                    continue;
                }

                int custo = Integer.parseInt(partes[0]);
                int aluguel = Integer.parseInt(partes[1]);
                propriedades.add(new Propriedades(custo, aluguel));
                contador++;
            }

            if (contador < 20) {
                System.out.println("Arquivo não contém 20 propriedades.");
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Formato numérico inválido no arquivo: " + e.getMessage());
        }

        return propriedades;
    }
}

