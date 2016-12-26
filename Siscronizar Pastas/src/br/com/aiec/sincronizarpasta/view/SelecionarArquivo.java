package br.com.aiec.sincronizarpasta.view;

import java.io.File;

import javax.swing.JFileChooser;
/**
 * Classe responsável por selecionar um arquivo no modo grafico
 */
public class SelecionarArquivo {

	// Retorna o arquivo selecionado
	public static File getFileChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Seleciona apenas pastas
		int status = chooser.showOpenDialog(null); // abrirá na pasta padrão do usuário

		if (status == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile(); // retorna o arquivo
		}
		return null;
	}
}
