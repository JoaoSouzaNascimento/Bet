package config;

import service.TransactionService;
import service.UsuarioService;

public class AppContext {

    private static UsuarioService usuarioService;
    private static TransactionService transactionService;
    
    
    public static void setUsuarioService(UsuarioService service) {
        usuarioService = service;
    }
 

    
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
