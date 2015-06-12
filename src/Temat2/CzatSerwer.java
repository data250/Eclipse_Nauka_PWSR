package Temat2;

import java.io.*;
import java.net.*;

public class CzatSerwer {
	private static ServerSocket server;
	private static final int PORT = 2345;

	public static void main(String[] args) {
		try {
			server = new ServerSocket(PORT);
			System.out.println("Czat Serwer uruchomiony na porcie: " + PORT);
			while (true) {
				Socket socket = server.accept();
				InetAddress addr = socket.getInetAddress();
				System.out.println("Po³¹czenie z adresu: " + addr.getHostName()
						+ " [" + addr.getHostAddress() + "]");
				new CzatObsluga(socket).start();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}