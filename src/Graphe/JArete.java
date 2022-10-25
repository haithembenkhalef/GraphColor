package Graphe;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import JSommet.SommetGraphique;

public class JArete {
	
	private int id;
	private int poid;
	private SommetGraphique s1;
	private SommetGraphique s2;
	
	public JArete(int id, int poid, SommetGraphique s1, SommetGraphique s2){
		this.id = id;
		this.poid = poid;
		this.s1 = s1;
		this.s2 = s2;
	}
	
	
	public void drawLine(Graphics g) {
		int x1 = (int) s1.centreX;
	    int y1 = (int) s1.centreY;
	    int x2 = (int) s2.centreX;
	    int y2 = (int) s2.centreY;
	    g.setColor(Color.BLACK);
		g.drawLine(x1, y1, x2, y2);
	}
	
	

}
