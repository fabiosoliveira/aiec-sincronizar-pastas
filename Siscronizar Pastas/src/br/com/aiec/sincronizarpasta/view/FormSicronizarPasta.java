
package br.com.aiec.sincronizarpasta.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


/**
 * Classe responsável pela janela principal da aplicação
 */
@SuppressWarnings("serial")
public class FormSicronizarPasta extends JFrame {

	// Menús
	JMenuBar menuBar;
	JMenu menuSincronizar;
	public JMenuItem itemCompleta;
	public JMenuItem itemOrigem;
	public JMenuItem itemDestino;
	
	// Botões
	public JButton btnOrigem;
	public JButton btnDestino;
	
	// Árvore de diretorios
	public JTree arvoreOrigem;
	public JTree arvoreDestino;
	
	public FormSicronizarPasta() {
		super("Sincronizar Pasta");
		 
		// Layout principal
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		
		criandoMenu(); // Criar menus
		criandoBotões(); // Criar botões
		criandoAarvores(); // Criar árvores de diretorios
		
		pack();
	}
	
	private void criandoAarvores() {
		DefaultTreeModel model1 = new DefaultTreeModel(new DefaultMutableTreeNode("Pasta origem"));
		arvoreOrigem = new JTree();
		arvoreOrigem.setModel(model1);
		arvoreOrigem.setEnabled(false);
		
		DefaultTreeModel model2 = new DefaultTreeModel(new DefaultMutableTreeNode("Pasta destino"));
		arvoreDestino = new JTree();
		arvoreDestino.setModel(model2);
		arvoreDestino.setEnabled(false);
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(new JScrollPane(arvoreOrigem));
		panel.add(new JScrollPane(arvoreDestino));
		add(BorderLayout.CENTER, panel);
	}


	private void criandoBotões() {
		btnOrigem = new JButton("Escolha Pasta Origem");
		btnDestino = new JButton("Escolha Pasta Destino");
		
		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.add(btnOrigem);
		panel.add(btnDestino);
		add(BorderLayout.NORTH, panel);
		
	}


	private void criandoMenu(){
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuSincronizar = new JMenu("Sincronizar");
		menuBar.add(menuSincronizar);
		
		itemCompleta = new JMenuItem("Completa");
		menuSincronizar.add(itemCompleta);
		
		itemOrigem = new JMenuItem("Origem");
		menuSincronizar.add(itemOrigem);
		
		itemDestino = new JMenuItem("Destino");
		menuSincronizar.add(itemDestino);
	}
}
