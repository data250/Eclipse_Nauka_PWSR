package Temat2;

import java.io.*;
import java.net.*;
import java.util.*;

class CzatObsluga extends Thread {
	
	static Vector<CzatObsluga> czaty = new Vector<CzatObsluga>();
	private Socket socket;
	private BufferedReader wejscie;
	private PrintWriter wyjscie;
	private String nick;

	public CzatObsluga(Socket socket) {
		this.socket = socket;
	}

	private void wyslijDoWszystkich(String tekst) {
		for (CzatObsluga czat : czaty) {
			synchronized (czaty) {
				if (czat != this)
					czat.wyjscie.println("<" + nick + "> " + tekst);
			}
		}
	}

	private void info() {
		wyjscie.print("Witaj " + nick + ", aktualnie czatuj¹: ");
		for (CzatObsluga czat : czaty) {
			synchronized (czaty) {
				if (czat != this)
					wyjscie.print(czat.nick + " ");
			}
		}
		wyjscie.println();
	}

	public void run() {
		String linia;
		synchronized (czaty) {
			czaty.add(this);
		}
		try {
			wejscie = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			wyjscie = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()), true);
			wyjscie.println("Po³¹czony z serwerem. Komenda /end koñczy po³¹czenie.");
			wyjscie.print("Podaj swój nick: ");
			nick = wejscie.readLine();
			System.out.println("Do czatu do³¹czy³: " + nick);
			wyslijDoWszystkich("Pojawi³ siê na czacie");
			info();
			while (!(linia = wejscie.readLine()).equalsIgnoreCase("/end")) {
				wyslijDoWszystkich(linia);
			}
			wyslijDoWszystkich("Opuœci³ czat");
			System.out.println("Czat opuœci³: " + nick);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				wejscie.close();
				wyjscie.close();
				socket.close();
			} catch (IOException e) {
			} finally {
				synchronized (czaty) {
					czaty.removeElement(this);
				}
			}
		}
	}
}