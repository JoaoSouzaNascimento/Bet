package service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

import dao.ApostaDao;
import dao.ApostaDaoPostgreSQL;
import dao.PalpiteDao;
import dao.PalpiteDaoPostgreSQL;
import dao.UsuarioDaoPostgreSQL;
import model.Aposta;
import model.CargoUsuario;
import model.Usuario;

public class Main {
    public static void main(String[] args) {
        UsuarioDaoPostgreSQL usuarioDao = new UsuarioDaoPostgreSQL();
        
        Usuario usuario = new Usuario(null, "exemplo_usuario", "Apelido", "senha123", "aa", 100.0, false, CargoUsuario.USUARIO);
    
		try {
			usuarioDao.createUsuario(usuario);
			System.out.println("Usuário criado com sucesso! ID: " + usuario.getId());
		} catch (Exception e) {
			System.err.println("Erro ao criar usuário: " + e.getMessage());
		}
    }
}
