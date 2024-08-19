package dao;


import model.Aposta;
import java.util.List;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;

public interface ApostaDao {
	
    public Aposta createAposta(Aposta aposta) throws InsercaoException;
    public Aposta updateAposta(Aposta aposta) throws AtualizacaoException;
    public void deleteAposta(int id) throws DelecaoException;
    public Aposta getApostaById(int id) throws ConsultaException;
    public List<Aposta> getAllApostas() throws ConsultaException;
}

