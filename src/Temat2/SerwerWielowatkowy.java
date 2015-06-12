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
				System.out.println("Po³aczenie numer: " + nrPolaczenia
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
			wyjscie.println("Serwer wita u¿ytkownika! Komenda /end koñczy po³¹czenie!");
			boolean done = false;
			while (!done) {
				String lancuch = wejscie.readLine();
				wyjscie.println("Po³¹czenie: " + nrPolaczenia + " Echo: "
						+ lancuch);
				if (lancuch.trim().toLowerCase().equals("/end"))
					done = true;
			}
			System.out.println("Po³¹czenie numer: " + nrPolaczenia
					+ " zosta³o zakoñczone");
			socket.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}