package br.com.aiec.sincronizarpasta.regradenegocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Classe utilizada para fazer operações com arquivos 
 * como copiar e comparar conteudo
 */
public class OperacoesComArquivos {

	
	//Copiar arquivo
	// Tem como parametro arquivo origem e o destino
	public static void copy(File origem, File destino) throws IOException {

		if(origem.isDirectory()) { // verifica se é diretorio
			for (File pasta : origem.listFiles()) { // se for diretorio obtem a lista do mesmo
				File file = new File(destino.getPath() + "/" + pasta.getName());
				if (pasta.isDirectory()) {
					file.mkdir();
				}
				copy(pasta, file); // recursão 
			}
		} else {

			// se for arquivo irá copiar bytes através de streams.
			FileInputStream in = new FileInputStream(origem);
			FileOutputStream out = new FileOutputStream(destino);

			byte[] buffer = new byte[1024]; // tamanho de blocos de memorias que serão lidos por vez
			int tamanho;
			
			// ler pedaços de 1MB por vez
			while ((tamanho = in.read(buffer)) > 0) {
				out.write(buffer, 0, tamanho);
			}

			out.close();
			in.close();
		}
	}
	
	// Compara de possuem mesmo nome e tamanho
	public static boolean compararNomeETamanho(File arquivo1, File arquivo2) {
		// compara nome dos arquivos
		if (!arquivo1.getName().equals(arquivo2.getName()))
			return false;
		
		// se for uma arquivo compara o tamanho
		if (arquivo1.isFile()) {
			if (arquivo1.length() != arquivo2.length())
				return false;
		}
		
		return true; // retorna verdadeiro se forem identicos
	}
	
	// compara se possuem os mesmos conteúdos, em caso de arquivo
	// compara byte por byte
	public static boolean compararConteudo(File arquivo1, File arquivo2) throws IOException {
		FileInputStream in1 = null;
		FileInputStream in2 = null;
		
		try {
			in1 = new FileInputStream(arquivo1);
			in2 = new FileInputStream(arquivo2);

			byte[] byte1 = new byte[(int) arquivo1.length()];
			byte[] byte2 = new byte[(int) arquivo2.length()];

			// Se o tamanho for diferente retorna falso
			if (in1.read(byte1) !=in2.read(byte2))
				return false;

			// compara byte a byte
			for (int i = 0; i < byte2.length; i++) {
				if (byte1[i] != byte2[i])
					return false;
			}
			
			return true; // retorna verdadeiro se tiverem conteúdos identicos
		} finally { // garante que o recurso do stream será liberado
			in1.close();
			in2.close();
		}
		
	}
}
