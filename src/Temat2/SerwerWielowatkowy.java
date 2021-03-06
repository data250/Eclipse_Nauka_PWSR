package Temat2;

import java.io.*;
import java.net.*;

public class SerwerWielowatkowy {
	private static ServerSocket server;
	private static final int PORT = 2345;

	public static void main(String[] args) {
		int nrPolaczenia = 1;
		try {
			server = new ServerSocket(PORT);
			System.out.println("Serwer uruchomiony na porcie: " + PORT);
			while (true) {
				Socket socket = server.accept();
				InetAddress addr = socket.getInetAddress();
				System.out.println("Połaczenie numer: " + nrPolaczenia
						+ " z adresu: " + addr.getHostName() + " ["
						+ addr.getHostAddress() + "]");
				new Obsluga(socket, nrPolaczenia).start();
				nrPolaczenia++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class Obsluga extends Thread {
	private Socket socket;
	private int nrPolaczenia;

	public Obsluga(Socket socket, int nrPolaczenia) {
		this.socket = socket;
		this.nrPolaczenia = nrPolaczenia;
	}

	public void run() {
		try {
			BufferedReader wejscie = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter wyjscie = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()), true);
			wyjscie.println("Serwer wita użytkownika! Komenda /end kończy połączenie!");
			boolean done = false;
			while (!done) {
				String lancuch = wejscie.readLine();
				wyjscie.println("Połączenie: " + nrPolaczenia + " Echo: "
						+ lancuch);
				if (lancuch.trim().toLowerCase().equals("/end"))
					done = true;
			}
			System.out.println("Połączenie numer: " + nrPolaczenia
					+ " zostało zakończone");
			socket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}