package JSommet;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.beans.*;
import java.util.*;
import javax.swing.*;

import GUI.Main;
import GUI.PanGraphe;

import java.awt.geom.*;

import Graphe.Sommet;

public class SommetGraphique extends JComponent implements MouseListener, MouseMotionListener {

	public int getX1() {
		return x;
	}

	public int getY1() {
		return y;
	}

	private int id;
	private boolean deplacer = false;
	public boolean selectionner = false;

	private int x, y;
	public int centreX, centreY;
	public int rayonCercle = 35, rayonCarre = 36;
	private Color c;

	public boolean isDeplacer() {
		return deplacer;
	}

	public SommetGraphique() {

	}

	public SommetGraphique(int id, int x, int y, Color c) {
		this.c = c;
		centreX = x;
		centreY = y;
		this.x = x - (rayonCarre / 2);
		this.y = y - (rayonCarre / 2);
		this.id = id;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		// this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBounds(this.x, this.y, rayonCarre, rayonCarre);
		repaint();
	}

	public void drawCircle(Graphics g) {
		//System.out.println("paint x,y:" + x + "," + y);
		this.setBounds(x, y, rayonCarre, rayonCarre);
		Ellipse2D noeud = new Ellipse2D.Double(x, y, rayonCercle, rayonCercle);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(noeud);
		FontMetrics fm = g.getFontMetrics();
		String text = String.valueOf(id);
		double textWidth = fm.getStringBounds(text, g).getWidth();
		if(c!=null) {
			g2d.setColor(c);
			g2d.fill(noeud);
		}
		g2d.setColor(Color.BLACK);
		g2d.drawString(text, (int) (noeud.getCenterX() - textWidth / 2),
				(int) (noeud.getCenterY() + fm.getMaxAscent() / 2));

	}

	public void setC(Color c) {
		this.c = c;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (Main.ajouterArete) {
			System.out.println("composant selectioner sommmet :" + id);
			selectionner = true;
			this.setBorder(BorderFactory.createLineBorder(Color.black));
			((PanGraphe) this.getParent()).graphe.initSelection();
			if (((PanGraphe) this.getParent()).graphe.SommetSelect.size() == 2) {
				((PanGraphe) this.getParent()).ajouterArete();
				((PanGraphe) this.getParent()).graphe.clearSelection();

			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//if ((!Main.ajouterSommet) && (!Main.ajouterArete))
			deplacer = true;

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		deplacer = false;

	}

	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(rayonCarre, rayonCarre);
	}

	public void SetLocation(int x, int y) {
		this.x = x - (rayonCarre / 2);
		this.y = y - (rayonCarre / 2);
		centreX = x;
		centreY = y;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (deplacer) {
			SetLocation(x + e.getX(), y + e.getY());
			this.getParent().repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
