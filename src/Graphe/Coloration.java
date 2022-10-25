package Graphe;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Coloration {

	int[][] mat;
	Vector<Color> couleurUtilise = new Vector<Color>();

	public Coloration(Graphe g) {
		System.out.println("Coloration , nb sommet" + g.mesSommet.size());
		mat = generMatAdj(g);
		System.out.println(afficherMat());
	}

	private int[][] generMatAdj(Graphe g) {

		int[][] temp = new int[g.mesSommet.size()][g.mesSommet.size()];
		for (int i = 0; i < g.mesSommet.size(); i++) {
			Sommet s = g.mesSommet.elementAt(i);
			for (int j = 0; j < s.getSommetAdjacent().size(); j++) {

				Sommet sA = s.getSommetAdjacent().elementAt(j);

				temp[s.getId() - 1][sA.getId() - 1] = 1;
			}

		}
		return temp;
	}

	public SommetCustom[] Colorer() {
		// recuperer liste des sommets
		SommetCustom[] sommets = ListSommet();

		// tri des sommet par dans ordre croissant de numérotation.
		tri(sommets);
		int j;

		// balayage des sommet trié
		for (int i = 0; i < sommets.length; i++) {
			if (!sommets[i].colorer) {
				//attribuer une nouvelle couleur
				sommets[i].colorer = true;
				sommets[i].couleur = nouvelleCouleur();
				j = i + 1;
				System.out.println("fct " + ResteSommetNonColorer(sommets));
				// Comparaison de chaque sommet avec tout les autre
				// sommet non encore colorer
				while ((j < sommets.length) && (ResteSommetNonColorer(sommets))) {
					System.out.println("i,j" + i + "," + j);
					Vector<SommetCustom> v = MemeCouleur(sommets, sommets[i]);

					int k = 0;
					boolean nonAdjacent = true;

					// si il ya un autre sommet qui a la meme couleur que notre
					// sommet courrant on le compare lui aussi aux autre sommet
					while ((k < v.size()) && (nonAdjacent)) {
						if (Adjacent(v.elementAt(k), sommets[j]))
							nonAdjacent = false;
						k++;
					}
					// si on trouve un sommet non adjacent a nos sommet de meme
					// couleur on lui affecte lui aussi la meme couleur
					if (nonAdjacent) {
						System.out.println("i adjacent a j" + i + "," + j);
						sommets[j].colorer = true;
						sommets[j].couleur = sommets[i].couleur;
					}
					j++;

				}
				if (!ResteSommetNonColorer(sommets))
					break;
			}
		}

		return sommets;
	}

	private Vector<SommetCustom> MemeCouleur(SommetCustom[] degreSommet, SommetCustom s) {
		Vector<SommetCustom> v = new Vector<SommetCustom>();
		v.add(s);
		for (int i = 0; i < degreSommet.length; i++) {
			if (degreSommet[i].colorer) {
				if (degreSommet[i].couleur.getRGB() == s.couleur.getRGB()) {
					v.add(degreSommet[i]);
				}
			}
		}

		for (int i = 0; i < v.size(); i++) {
			System.out.println("degre sommets:" + v.elementAt(i));
		}

		return v;
	}

	private boolean Adjacent(SommetCustom a, SommetCustom b) {
		if (mat[a.num - 1][b.num - 1] == 1)
			return true;
		return false;
	}

	private boolean ResteSommetNonColorer(SommetCustom[] degresSommets) {
		for (int i = 0; i < degresSommets.length; i++) {
			if (!degresSommets[i].colorer)
				return true;
		}
		return false;
	}

	private void tri(SommetCustom[] sommets) {
		for (int i = 0; i < sommets.length - 1; i++) {
			for (int j = i + 1; j < sommets.length; j++) {
				if (sommets[i].num > sommets[j].num) {
					SommetCustom x = sommets[i];
					sommets[i] = sommets[j];
					sommets[j] = x;
				}
			}
		}

		for (int i = 0; i < sommets.length; i++) {
			System.out.println("trie:" + sommets[i]);
		}

	}

	private SommetCustom[] ListSommet() {
		SommetCustom[] t = new SommetCustom[mat.length];
		for (int i = 0; i < mat.length; i++) {
			SommetCustom s = new SommetCustom(i + 1);
			t[i] = s;
		}

		for (int i = 0; i < t.length; i++) {
			System.out.println("degre sommets:" + t[i]);
		}

		return t;
	}

	private Color nouvelleCouleur() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color c = new Color(r, g, b);
		while (ExistColor(c)) {
			r = rand.nextFloat();
			g = rand.nextFloat();
			b = rand.nextFloat();
			c = new Color(r, g, b);
		}
		return c;

	}

	private boolean ExistColor(Color c) {
		for (int i = 0; i < couleurUtilise.size(); i++) {
			if (couleurUtilise.elementAt(i).getRGB() == c.getRGB())
				return true;
		}
		return false;
	}

	public String afficherMat() {
		String s = "";
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				s = s + mat[i][j] + " ";

			}
			s = s + "\n";
		}
		return s;
	}

	/*
	 * private void triDegre(SommetCustom[] degresSommets) { for (int i = 0; i <
	 * degresSommets.length - 1; i++) { for (int j = i + 1; j <
	 * degresSommets.length; j++) { if (degresSommets[i].degre <
	 * degresSommets[j].degre) { SommetCustom x = degresSommets[i];
	 * degresSommets[i] = degresSommets[j]; degresSommets[j] = x; } } }
	 * 
	 * for (int i = 0; i < degresSommets.length; i++) {
	 * System.out.println("trie:" + degresSommets[i]); }
	 * 
	 * }
	 */

	/*
	 * private SommetCustom[] degresSommets() { SommetCustom[] t = new
	 * SommetCustom[mat.length]; int temp = 0; for (int i = 0; i < mat.length;
	 * i++) { for (int j = 0; j < mat.length; j++) { if (mat[i][j] == 1) temp =
	 * temp + 1; } SommetCustom s = new SommetCustom(i + 1, temp); t[i] = s;
	 * 
	 * temp = 0; }
	 * 
	 * for (int i = 0; i < t.length; i++) { System.out.println("degre sommets:"
	 * + t[i]); }
	 * 
	 * return t; }
	 */

	public class SommetCustom {
		int num;
		boolean colorer;
		Color couleur;

		public SommetCustom(int num) {
			this.num = num;
			colorer = false;
			couleur = null;
		}

		@Override
		public String toString() {
			return "SommetCustom [num=" + num + ", degre=" + ", colorer=" + colorer + ", couleur=" + couleur + "]";
		}

	}

}
