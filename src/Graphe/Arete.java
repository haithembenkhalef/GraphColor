package Graphe;

public class Arete {
	
	private int poids;
	private int id;
	private JArete arete;
	
	private Sommet s1;
	private Sommet s2;
	
	public Arete(int id, int poids,Sommet s1, Sommet s2) {
		this.id = id;
		this.poids = poids;
		this.s1 = s1;
		this.s2 = s2;
		arete = new JArete(id, poids, s1.getSG(), s2.getSG());
	}

	public int getId() {
		return id;
	}

	public JArete getArete() {
		return arete;
	}

	public Sommet getS1() {
		return s1;
	}

	public Sommet getS2() {
		return s2;
	}

}
