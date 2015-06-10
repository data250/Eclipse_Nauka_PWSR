package Temat1;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoServer {
	private static ServerSocket server;
	private static final int PORT = 2345;
	private static Scanner in;

	public static void main(String args[]) {
		String linia;
		try {
			server = new ServerSocket(PORT);
			System.out.println("Serwer uruchomiony...");
			while (true) {
				Socket socket = server.accept();
				in = new Scanner(socket.getInputStream());
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);
				out.println("Prosty serwer ECHO, komenda /end koñczy dzia³anie.");
				boolean koniec = false;
				while (!koniec) {
					linia = in.nextLine();
					if (linia.trim().toLowerCase().equals("/end")) {
						koniec = true;
					} else {
						out.println(linia);
					}
				}
				socket.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
				}
			}
		}
	}
}