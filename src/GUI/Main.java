package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Graphe.Coloration;
import Graphe.Coloration.SommetCustom;
import Graphe.Graphe;
import Graphe.Sommet;
import JSommet.SommetGraphique;

import java.awt.Canvas;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.JToolBar;
import java.awt.Color;

public class Main extends JFrame {
    public static boolean ajouterSommet = false;
    public static boolean ajouterArete = false;
    
    private Graphe g;
    
	private JPanel contentPane;
	private JTextArea textArea;
	private PanGraphe p;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setJMenuBar(new JMenuBar());
		JMenu File = new JMenu("File");
		this.getJMenuBar().add(File);
		JMenuItem newGraphe = new JMenuItem("New graphe");
		File.add(newGraphe);
		newGraphe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("click new graphe");
				
			    g = new Graphe();
                p.setGraphe(g);
                p.removeAll();
                textArea.setText("");
                p.repaint();
				
			}

			
		});
		
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("Sommet");
		tglbtnNewToggleButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajouterArete = false;
				ajouterSommet = !ajouterSommet;
			}
		});
		panel_1.add(tglbtnNewToggleButton_1);
		
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Ar\u00EAte ");
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajouterSommet = false;
				ajouterArete = !ajouterArete;
			}
		});
		panel_1.add(tglbtnNewToggleButton);
		
		
		
		
		 g = new Graphe();
	    
	    JButton btnColorer = new JButton("Colorer");
	    ButtonGroup BG = new ButtonGroup();
	    
		btnColorer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Coloration clr = new Coloration(g);
				textArea.setText(textArea.getText()+"Matrice d'adjacense générer depuis le graphe : \n");
				textArea.setText(textArea.getText()+clr.afficherMat()+"\n");
				SommetCustom s[] = clr.Colorer();
				g.Colorer(s);
				textArea.setText(textArea.getText()+"Couleur Attribuée : \n");
				
				for(int i = 0;i < s.length;i++) {
					textArea.setText(textArea.getText()+s[i].toString()+"\n");
				}
				p.repaint();
			}
		});
		panel_1.add(btnColorer);
		 


		 p = new PanGraphe(g);
		 p.setBackground(Color.WHITE);
		
		
		
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		 textArea = new JTextArea();
		 textArea.setColumns(10);
		 panel.add(textArea);
		 
		 JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,p,panel);
		 contentPane.add(splitPane, BorderLayout.CENTER);
		 
		    BG.add(tglbtnNewToggleButton_1);
		    BG.add(tglbtnNewToggleButton);
		    BG.add(btnColorer);
		
		this.setVisible(true);
		
	}
}
