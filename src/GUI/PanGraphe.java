package GUI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import Graphe.Arete;
import Graphe.Graphe;
import Graphe.Sommet;

public class PanGraphe extends JPanel implements MouseListener {

	public Graphe graphe;

	public PanGraphe(Graphe g) {
		graphe = g;
		this.setLayout(null);

		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < graphe.mesSommet.size(); i++) {
			Sommet s = graphe.mesSommet.elementAt(i);
			s.getSG().drawCircle(g);
		}

		for (int i = 0; i < graphe.mesArete.size(); i++) {
			Arete a = graphe.mesArete.elementAt(i);
			//System.out.println("dessin arrete entre sommet " + a.getS1().getId() + a.getS2().getId());
			a.getArete().drawLine(g);

		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton() == MouseEvent.BUTTON1) {
			if (Main.ajouterSommet) {
				ajouterSommet(arg0.getX(), arg0.getY());
			}
			else if (Main.ajouterArete) {
				ajouterArete();
			}

		}
		
		else if(arg0.getButton() == MouseEvent.BUTTON3) {
			System.out.println("click droit");
			graphe.clearSelection();
		}

	}

	public void ajouterArete() {
		Arete a = new Arete(graphe.mesArete.size() + 1, 0, graphe.SommetSelect.elementAt(0),
				graphe.SommetSelect.elementAt(1));
		System.out.println(graphe.ajouter(a));
		repaint();
	}

	private void ajouterSommet(int x, int y) {
		Sommet s = new Sommet(graphe.mesSommet.size() + 1, x, y);
		if (graphe.ajouter(s))
			this.add(s.getSG());
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void setGraphe(Graphe graphe) {
		this.graphe = graphe;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
