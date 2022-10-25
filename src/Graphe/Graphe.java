package Graphe;

import java.util.Arrays;
import java.util.Vector;

import Graphe.Coloration.SommetCustom;
import JSommet.SommetGraphique;

public class Graphe {
	public Vector<Sommet> mesSommet;
	public Vector<Arete> mesArete;
	
	public Vector <Sommet> SommetSelect;
	public Sommet SommetSelect1 = null; 
	public Sommet SommetSelect2 = null; 

	public Graphe() {
		mesSommet = new Vector<Sommet>();
		mesArete = new  Vector<Arete>();
		SommetSelect = new Vector<Sommet>();
	}

	public boolean ajouter(Sommet s) {
		for (int i = 0; i < mesSommet.size(); i++) {
			Sommet tmep = mesSommet.elementAt(i);
			if (tmep.getId() == s.getId()) 
				return false;
		}
		mesSommet.add(s);
		return true;
	}
	
	public boolean ajouter(Arete a) {
		for (int i = 0; i < mesArete.size(); i++) {
			Arete temp = mesArete.elementAt(i);
			if ( ((temp.getS1().getId() == a.getS1().getId()) && (temp.getS2().getId()==a.getS2().getId())) || ((temp.getS1().getId() == a.getS2().getId()) && (temp.getS2().getId()==a.getS1().getId())))
				
				return false;
		}
		if(a.getS1().getId() != a.getS2().getId())
		   a.getS1().ajouterSommetAdjacant(a.getS2());
		System.out.println((a.getS1().getSommetAdjacent().firstElement().getId()));
		System.out.println((a.getS2().getSommetAdjacent().firstElement().getId()));
		mesArete.add(a);
		return true;
	}
	
	
	public Sommet SommetSelectionner(int x, int y) {
		for (int i = 0; i < mesSommet.size(); i++) {
			Sommet tmep = mesSommet.elementAt(i);
			if (tmep.getSG().isDeplacer())
				return tmep;
		}
		return null;
	}
	
	
	public void initSelection() {
		SommetSelect.removeAllElements();
		for (int i = 0; i < mesSommet.size(); i++) {
			if(SommetSelect.size()==2) break;
			Sommet tmep = mesSommet.elementAt(i);
			if(tmep.getSG().selectionner) SommetSelect.add(tmep);
		}
	}
	
	public void removeSelection(int id) {
		for (int i = 0; i < SommetSelect.size(); i++) {
			Sommet tmep = SommetSelect.elementAt(i);
			if(tmep.getId()==id) {
				SommetSelect.remove(i);
			    break;
			}
		}
	}

	public void clearSelection() {
		
		for (int i = 0; i < SommetSelect.size(); i++) {
			Sommet tmep = SommetSelect.elementAt(i);
			tmep.getSG().selectionner = false;
			tmep.getSG().setBorder(null);
		}
	}
	
	public void Colorer(SommetCustom[] s) {
		for(int i = 0;i < s.length;i++) {
			System.out.println("Graphe :"+s[i]);
			Sommet temp = mesSommet.elementAt(s[i].num-1);
			temp.setC(s[i].couleur);
		}
		
	}
	
}

    