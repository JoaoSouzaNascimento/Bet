package service;

import java.time.LocalDate;
import java.time.ZoneId;

import dao.ApostaDao;
import dao.ApostaDaoPostgreSQL;
import dao.PalpiteDao;
import dao.PalpiteDaoPostgreSQL;
import model.Aposta;

public class Main {
    public static void main(String[] args) {
        // Inicialize as dependências necessárias
        ApostaDao apostaDao = new ApostaDaoPostgreSQL(); // Substitua pela implementação real
        PalpiteDao palpiteDao = new PalpiteDaoPostgreSQL(); // Substitua pela implementação real
        FootballApiService footballApiService = new FootballApiService("c3228facec7d0ee8fe14fc3b6d71742d", "v3.football.api-sports.io"); // Substitua pelos valores reais
        PartidaService partidaService = new PartidaService(); // Substitua pela implementação real

        // Inicialize o ApostaService
        ApostaService apostaService = new ApostaService(apostaDao, palpiteDao, footballApiService, partidaService);

        // Crie uma aposta para teste
        Aposta aposta = new Aposta(1, 0, null, false, LocalDate.of(2024, 8, 25)); // Substitua pela implementação real
        // Configure a aposta conforme necessário

        // Defina os valores de liga e fuso horário
        String league = "71"; // Substitua pelo valor real
        String timezone = "America/Bahia"; // Substitua pelo valor real

        // Chame o método validarApostaGanha
        try {
            apostaService.validarApostaGanha(aposta, league, timezone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
