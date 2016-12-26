package br.com.aiec.sincronizarpasta.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import br.com.aiec.sincronizarpasta.regradenegocio.SincronizarPasta;
import br.com.aiec.sincronizarpasta.view.FormSicronizarPasta;
import br.com.aiec.sincronizarpasta.view.MontarArvore;
import br.com.aiec.sincronizarpasta.view.SelecionarArquivo;

/**
 * Classe responsável pelo controle da aplicalçao do modelo MVC
 * 
 */
public class ControllerSincronizarPasta {

	private FormSicronizarPasta form;
	private File origemPasta = null;
	private File destinoPasta = null;

	public ControllerSincronizarPasta(FormSicronizarPasta formulario) {
		form = formulario;

		// Evento de fechar a janela para sair
		form.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Evento do botão para mostrar arvores de diretórios na tela
		form.btnOrigem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				origemPasta = SelecionarArquivo.getFileChooser(); // Seleciona a pasta
				// Atualizar arvore de diretorio
				DefaultMutableTreeNode no = MontarArvore.getArvoreDirectore(origemPasta);
				form.arvoreOrigem.setModel(new DefaultTreeModel(no));
				form.arvoreOrigem.setEnabled(true); // abilita a edição da árvore 
			}
		});

		// Evento do botão para mostrar arvores de diretórios na tela
		form.btnDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destinoPasta = SelecionarArquivo.getFileChooser();
				DefaultMutableTreeNode no = MontarArvore.getArvoreDirectore(destinoPasta);
				form.arvoreDestino.setModel(new DefaultTreeModel(no));
				form.arvoreDestino.setEnabled(true);
			}
		});

		// Evento para sincronizar as pastas 
		// evente disparado pelo item do menú
		form.itemCompleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mostra mensagem se não tiver ainda escolhido uma pasta 
				if (!form.arvoreOrigem.isEnabled() || !form.arvoreDestino.isEnabled()) {
					JOptionPane.showMessageDialog(null, "Escolha as pastas para sincronizar!");
					return;
				}
				try {
					// Sincronizar pasta
					SincronizarPasta.completa(origemPasta, destinoPasta);

					// Atualizar árvore de diretórios
					DefaultMutableTreeNode origemNo = MontarArvore.getArvoreDirectore(origemPasta);
					form.arvoreOrigem.setModel(new DefaultTreeModel(origemNo));

					DefaultMutableTreeNode destinoNo = MontarArvore.getArvoreDirectore(destinoPasta);
					form.arvoreDestino.setModel(new DefaultTreeModel(destinoNo));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Evento para sincronizar as pastas 
		// evente disparado pelo item do menú
		form.itemDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!form.arvoreOrigem.isEnabled() || !form.arvoreDestino.isEnabled()) {
					JOptionPane.showMessageDialog(null, "Escolha as pastas para sincronizar!");
					return;
				}
				try {
					SincronizarPasta.destino(origemPasta, destinoPasta);

					DefaultMutableTreeNode origemNo = MontarArvore.getArvoreDirectore(origemPasta);
					form.arvoreOrigem.setModel(new DefaultTreeModel(origemNo));

					DefaultMutableTreeNode destinoNo = MontarArvore.getArvoreDirectore(destinoPasta);
					form.arvoreDestino.setModel(new DefaultTreeModel(destinoNo));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Evento para sincronizar as pastas 
		// evente disparado pelo item do menú
		form.itemOrigem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!form.arvoreOrigem.isEnabled() || !form.arvoreDestino.isEnabled()) {
					JOptionPane.showMessageDialog(null, "Escolha as pastas para sincronizar!");
					return;
				}
				try {
					SincronizarPasta.origem(origemPasta, destinoPasta);

					DefaultMutableTreeNode origemNo = MontarArvore.getArvoreDirectore(origemPasta);
					form.arvoreOrigem.setModel(new DefaultTreeModel(origemNo));

					DefaultMutableTreeNode destinoNo = MontarArvore.getArvoreDirectore(destinoPasta);
					form.arvoreDestino.setModel(new DefaultTreeModel(destinoNo));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
