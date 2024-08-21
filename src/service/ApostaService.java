package service;

import java.math.BigDecimal;
import java.util.List;
import dao.ApostaDaoPostgreSQL;
import dao.PalpiteDaoPostgreSQL;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Aposta;
import model.Palpite;
import model.Usuario;

public class ApostaService {
    private final ApostaDaoPostgreSQL apostaDao;
    private final PalpiteDaoPostgreSQL palpiteDao;

    public ApostaService() {
        this.apostaDao = new ApostaDaoPostgreSQL();
        this.palpiteDao = new PalpiteDaoPostgreSQL();
    }

    // Criar nova aposta e persistir no banco de dados
    public void apostar(Usuario usuario, BigDecimal valorApostado, List<Palpite> palpites) {
        Aposta aposta = new Aposta(valorApostado, false);
        try {
            apostaDao.createAposta(usuario.getId(), aposta);
            palpiteDao.createListaDePalpites(aposta.getId(), palpites);
            aposta.setPalpites(palpites);
        } catch (InsercaoException e) {
            e.printStackTrace();
        }
    }

    // Recupera uma aposta específica pelo ID
    public Aposta getApostaById(int apostaId) {
        try {
            return apostaDao.getApostaById(apostaId);
        } catch (ConsultaException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Recupera todas as apostas de um usuário
    public List<Aposta> getTodasApostas(Usuario usuario) {
        try {
            return apostaDao.getTodasApostasPorUsuarioId(usuario.getId());
        } catch (ConsultaException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Atualiza o valor apostado
    public void atualizarValorApostado(Aposta aposta, BigDecimal novoValorApostado) {
        aposta.setAmount(novoValorApostado);
        try {
            apostaDao.updateAposta(aposta);
        } catch (AtualizacaoException e) {
            e.printStackTrace();
        }
    }

    // Exclui uma aposta específica
    public void excluirAposta(Aposta aposta) {
        try {
            apostaDao.deleteAposta(aposta.getId());
        } catch (DelecaoException e) {
            e.printStackTrace();
        }
    }

    // Adiciona um novo palpite à aposta e persiste no banco de dados
    public void addPalpite(Aposta aposta, Palpite palpite) {
        try {
            palpiteDao.createPalpite(aposta.getId(), palpite);
            aposta.addPalpite(palpite);
        } catch (InsercaoException e) {
            e.printStackTrace();
        }
    }

    // Exclui um palpite específico da aposta
    public void excluirPalpite(Aposta aposta, int palpiteId) {
        try {
            palpiteDao.deletePalpite(palpiteId);
            aposta.removePalpiteById(palpiteId);
        } catch (DelecaoException e) {
            e.printStackTrace();
        }
    }
}