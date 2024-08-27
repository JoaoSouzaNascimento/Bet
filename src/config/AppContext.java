package config;

import service.TransactionService;
import service.UsuarioService;

public class AppContext {

    private static UsuarioService usuarioService;
    private static TransactionService transactionService;
    
    
    public static void setUsuarioService(UsuarioService service) {
        usuarioService = service;
    }
    public static final String API_KEY = "c56b9a36c9f8d7ccacc12acca78c5c1f";
    public static final String API_HOST = "v3.football.api-sports.io";

    
    public static TransactionService getTransactionService() {
		return transactionService;
	}



	public static void setTransactionService(TransactionService transactionService) {
		AppContext.transactionService = transactionService;
	}



	public static UsuarioService getUsuarioService() {
        return usuarioService;
    }
}
