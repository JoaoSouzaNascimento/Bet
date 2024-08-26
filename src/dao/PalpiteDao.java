package dao;

import java.util.List;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Palpite;

public interface PalpiteDao {
	
	public Palpite createPalpite(Palpite palpite) throws InsercaoException;
	public Palpite updatePalpite(Palpite palpite) throws AtualizacaoException;
    public void deletePalpite(int apostaId, int partidaId) throws DelecaoException;
    public Palpite getPalpiteById(int apostaId, int partidaId) throws ConsultaException;
    public List<Palpite> getTodosPalpitesDeUmaAposta(int apostaId) throws ConsultaException;
	public void createListaDePalpites(List<Palpite> palpites) throws InsercaoException;
}
