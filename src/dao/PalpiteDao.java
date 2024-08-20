package dao;

import java.util.List;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Palpite;

public interface PalpiteDao {
	
	public Palpite createPalpite(int aposta, Palpite palpite) throws InsercaoException;
    public Palpite updatePalpite(int aposta, Palpite palpite) throws AtualizacaoException;
    public void deleteAposta(int id) throws DelecaoException;
    public Palpite getPalpiteById(int partidaId) throws ConsultaException;
    public List<Palpite> getAllApostas() throws ConsultaException;
}
