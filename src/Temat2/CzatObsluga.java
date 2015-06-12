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
		wyjscie.print("Witaj " + nick + ", aktualnie czatuj�: ");
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
			wyjscie.println("Po��czony z serwerem. Komenda /end ko�czy po��czenie.");
			wyjscie.print("Podaj sw�j nick: ");
			nick = wejscie.readLine();
			System.out.println("Do czatu do��czy�: " + nick);
			wyslijDoWszystkich("Pojawi� si� na czacie");
			info();
			while (!(linia = wejscie.readLine()).equalsIgnoreCase("/end")) {
				wyslijDoWszystkich(linia);
			}
			wyslijDoWszystkich("Opu�ci� czat");
			System.out.println("Czat opu�ci�: " + nick);
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