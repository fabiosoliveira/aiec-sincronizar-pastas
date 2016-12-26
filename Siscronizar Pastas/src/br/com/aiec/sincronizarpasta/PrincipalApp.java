package br.com.aiec.sincronizarpasta;
import br.com.aiec.sincronizarpasta.controller.ControllerSincronizarPasta;
import br.com.aiec.sincronizarpasta.view.FormSicronizarPasta;

public class PrincipalApp {

	public static void main(String[] args) {

		/**
		 * Classe responśavel pela 
		 * Inicialização da aplicação
		 */
		FormSicronizarPasta form =  new FormSicronizarPasta(); // tela da aplicação
		form.setSize(800, 500);
		form.setVisible(true);
		
		new ControllerSincronizarPasta(form); // classe controller do MVC
	}

}
