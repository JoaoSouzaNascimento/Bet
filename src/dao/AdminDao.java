package dao;

import model.Admin;
import java.util.List;
import java.util.UUID;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;

public interface AdminDao {
	
	public Admin getAdminByEmail(String email) throws ConsultaException;
	public List<Admin> getAllAdmins() throws ConsultaException;
	public boolean insertAdmin(Admin admin) throws InsercaoException;
	public boolean updateAdmin(Admin admin) throws AtualizacaoException;
	public void deleteAdmin(UUID adminId)throws DelecaoException;
	void addLog(UUID adminId, String action) throws InsercaoException;
	List<String> getLogsDeAdmin(UUID adminId) throws ConsultaException;
}
