package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	List<Integer> listaParziale = new ArrayList<Integer>(); 
	List<List<Integer>> listaCompleta = new ArrayList<List<Integer>>(); 
	

	public Model() {

	}

	// of course you can change the String output with what you think works best
	public String getUmiditaMedia(int mese) {
		return "TODO!";
	}
	
	// of course you can change the String output with what you think works best
	public String trovaSequenza(int mese) {
		return "TODO!";
	}
	
	public void risolvi() {
		this.listaCompleta.clear();
		this.listaParziale.clear();
		recursive(0);
		System.out.print(this.listaCompleta);
	}
	
	// genera tutte le permutazioni
	public void recursive(int dimensione) {
		if (dimensione==15) {
			if (this.visitaTutteCitta(listaParziale) && this.seiGiorniAlMax(listaParziale))
				this.listaCompleta.add(new ArrayList<Integer>(this.listaParziale));
			return;
		}
		for (int i=1; i<=3; i++) {
			// caso in cui aggiungo più copie di i
			if (this.listaParziale.size()==0 || this.listaParziale.get(dimensione-1)!=i) {
				// aggiungo 3 copie di i
				if (dimensione<=12) {
					this.listaParziale.add(i);
					this.listaParziale.add(i);
					this.listaParziale.add(i);
					recursive(this.listaParziale.size());
					this.listaParziale.remove(listaParziale.size()-1);
					this.listaParziale.remove(listaParziale.size()-1);
					this.listaParziale.remove(listaParziale.size()-1);
				}else {
					// conto se devo aggiungere 1 o 2 copie di i
					int diff = 15-dimensione; 
					if (diff==2) {
						this.listaParziale.add(i);
						this.listaParziale.add(i);
						recursive(this.listaParziale.size());
						this.listaParziale.remove(listaParziale.size()-1);
						this.listaParziale.remove(listaParziale.size()-1);
					}else if (diff==1) {
						this.listaParziale.add(i);
						recursive(this.listaParziale.size());
						this.listaParziale.remove(listaParziale.size()-1);
					}
				}
			//caso in cui metto solo i, perché l'int precedente è uguale a i
			}else {
				this.listaParziale.add(i);
				recursive(this.listaParziale.size());
				this.listaParziale.remove(listaParziale.size()-1);
			}
		}
	}
	
	// controlla che il tecnico visiti tutte le citta almeno 1 volta
	public boolean visitaTutteCitta (List<Integer> daControllare) {
		boolean uno = false;
		boolean due = false;
		boolean tre = false;
		for (Integer i: daControllare) {
			if (i==1)
				uno = true;
			if (i==2) 
				due = true;
			if (i==3)
				tre = true;
		}
		if (uno && due && tre) 
			return true;
		else 
			return false;
	}
	
	// controlla che il tecnico non si fermi più di 6 giorni per citta
	public boolean seiGiorniAlMax (List<Integer> daControllare) {
		int Contuno = 0;
		int Contdue = 0;
		int Conttre = 0;
		for (Integer i: daControllare) {
			if (i==1)
				Contuno ++;
			if (i==2) 
				Contdue ++;
			if (i==3)
				Conttre ++;
		}
		if (Contuno<=6 && Contdue<=6 && Conttre<=6)
			return true;
		else
			return false;
	}
	

}
