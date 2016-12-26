package br.com.aiec.sincronizarpasta.regradenegocio;

import java.io.File;
import java.io.IOException;

/**
 * Classe responsável pela sincronização das pastas
 */
public class SincronizarPasta {

	// Método usado para sincronizar a pasta destino
	// Tem como argumento a pasta origem e destino
	public static void destino(File pastaOrigem, File pastaDestino) throws IOException {
		String pathDestino = pastaDestino.getPath() + "/"; // caminho para pasta destino
		
		File[] filesPastaOrigem = pastaOrigem.listFiles(); // Lista de diretorios e arquivos
		File[] filesPastaDestino = pastaDestino.listFiles();
		
		for (int i = 0; i < filesPastaOrigem.length; i++) {
			boolean achei = false;
			for (int j = 0; j < filesPastaDestino.length; j++) {
				// verifica se é uma pasta e um arquivo
				if(!(filesPastaOrigem[i].isFile() ^ filesPastaDestino[j].isFile())) {
					// compara nomes e tamanhos do arquivo
					if(OperacoesComArquivos.compararNomeETamanho(filesPastaOrigem[i], filesPastaDestino[j])) {
						// verifica se é uma arquivo
						if(filesPastaOrigem[i].isFile()) {
							// Se for arquivo, verifica se possuém o mesmo conteúdo
							if(OperacoesComArquivos.compararConteudo(filesPastaOrigem[i], filesPastaDestino[j])){
								achei = true; // Se achar pastas ou arquivos identicos sinaliza
							}
						} else {
							achei = true;
						}
					}
				}
			}
			if (!achei){ // se não achou arquivos identicos entao executa o bloco de codigo
				File fileDestino = new File(pathDestino + filesPastaOrigem[i].getName()); // cria o arquivo destino
				if(filesPastaOrigem[i].isDirectory()) // se for um diretorio a origem, entao cria diretorio destino
					fileDestino.mkdir(); // cria diretorio
				OperacoesComArquivos.copy(filesPastaOrigem[i], fileDestino); // copia arquivo ou diretorio
			}
		}
	}

	// Método usado para sincronizar a pasta origem
	// Tem como argumento a pasta origem e destino
	public static void origem(File pastaOrigem, File pastaDestino) throws IOException {
		destino(pastaDestino, pastaOrigem);
	}

	// Método usado para sincronizar as duas pastas
	// Tem como argumento a pasta origem e destino
	public static void completa(File pastaOrigem, File pastaDestino) throws IOException {
		destino(pastaDestino, pastaOrigem);
		destino(pastaOrigem, pastaDestino);
	}
}
