package br.com.aiec.sincronizarpasta.view;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;
/**
 * Classe responsável por montar a árvore que aparecerá na janela
 */
public class MontarArvore {

	public static DefaultMutableTreeNode getArvoreDirectore(File file) {
		DefaultMutableTreeNode no = new DefaultMutableTreeNode(file.getName());

		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				no.add(getArvoreDirectore(f)); // recursão
			}
		}
		return no;
	}
}
