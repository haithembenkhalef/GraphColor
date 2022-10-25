package Graphe;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

import JSommet.SommetGraphique;

public class Sommet {
	
	private int id;
	private Vector <Sommet> sommetAdjacent;
	private SommetGraphique SG;
	private Color c;
	
	
	public Color getC() {
		return c;
	}


	public void setC(Color c) {
		this.c = c;
		SG.setC(c);
	}


	public Sommet(int id, int x, int y) {
		this.id = id;
		SG = new SommetGraphique(id, x, y, null);
		sommetAdjacent = new Vector <Sommet>();
	}
	
	
	public SommetGraphique getSG() {
		return SG;
	}
	
	public void ajouterSommetAdjacant(Sommet s) {
		if(!sommetAdjacent.contains(s)) {
		sommetAdjacent.add(s);
		s.ajouterSommetAdjacant(this);
		}
	}


	public Vector<Sommet> getSommetAdjacent() {
		return sommetAdjacent;
	}


	public int getId() {
		return id;
	}

}
