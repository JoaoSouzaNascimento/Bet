package service;

import java.util.List;
import java.util.UUID;

import dao.AdminDaoPostgreSQL;
import dao.UsuarioDaoPostgreSQL;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Admin;
import model.Usuario;

public class AdminService {
    private final AdminDaoPostgreSQL adminDao;
    private final UsuarioDaoPostgreSQL usuarioDao;

    public AdminService() {
        this.adminDao = new AdminDaoPostgreSQL();
        this.usuarioDao = new UsuarioDaoPostgreSQL();
    }

    // Cria um novo administrador e persiste no banco de dados
    public void criarAdmin(Admin admin) {
        try {
            usuarioDao.createUsuario(admin); // Cria o usuário como base
            adminDao.insertAdmin(admin); // Cria as informações específicas do admin
        } catch (InsercaoException e) {
            e.printStackTrace();
        }
    }

 // Recupera um administrador específico pelo email
    public Admin getAdminByEmail(String email) {
        try {
            return adminDao.getAdminByEmail(email);
        } catch (ConsultaException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Recupera todos os administradores
    public List<Admin> getTodosAdmins() {
        try {
            return adminDao.getAllAdmins();
        } catch (ConsultaException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Atualiza as informações de um administrador
    public void atualizarAdmin(Admin admin) {
        try {
            usuarioDao.updateUsuario(admin); // Atualiza as informações gerais do usuário
            adminDao.updateAdmin(admin); // Atualiza as informações específicas do admin
        } catch (AtualizacaoException e) {
            e.printStackTrace();
        }
    }

    // Exclui um administrador específico
    public void excluirAdmin(UUID adminId) {
        try {
            adminDao.deleteAdmin(adminId); // Remove o administrador do banco de dados
        } catch (DelecaoException e) {
            e.printStackTrace();
        }
    }

    // Adiciona uma ação ou log específico para o administrador
    public void adicionarLog(Admin admin, String acao) {
        try {
            adminDao.addLog(admin.getAdminId(), acao);
        } catch (InsercaoException e) {
            e.printStackTrace();
        }
    }

    // Recupera todos os logs de um administrador
    public List<String> getLogsDeAdmin(UUID adminId) {
        try {
            return adminDao.getLogsDeAdmin(adminId);
        } catch (ConsultaException e) {
            e.printStackTrace();
            return null;
        }
    }
}

