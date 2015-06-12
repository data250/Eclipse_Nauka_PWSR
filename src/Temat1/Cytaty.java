package Temat1;

import java.util.Random;

public class Cytaty {
	
	private static int MAX = 3; // tak ma byæ 
	private static String[] tablica = {"Kto rano wstaje...", "Test test i test", "Kot"};
	public int max;
	
	
	public Cytaty() {
		super();
		this.max = Cytaty.MAX;
	}
	
	public Cytaty(int max) {
		super();
		this.max = max;
	}

	public int getMax() {
		return max;
	}

	public String losuj() {
		
		Random generator = new Random();
		
		int i = generator.nextInt(this.max);
		return tablica[i];
	}

	public void setMax(int max) {
		this.max = max;
	}

}
