package Temat1;

import java.net.*;
import java.io.*;

public class Zad1Cytat {


	private static ServerSocket server;

	public static void main(String[] args) {
		try {
			server = new ServerSocket(2345);
			while (true) {
				System.out.println("Oczekuje na po³¹czenie...");
				Socket socket = server.accept();
				InetAddress addr = socket.getInetAddress();
				System.out.println("Po³¹czenie z adresu: " + addr.getHostName()
						+ " [" + addr.getHostAddress() + "]");
				// test
				//	OutputStream os = socket.getOutputStream();
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					out.println("Pod³¹czono do serwera");
			
					Cytaty cytat = new Cytaty(3);
					out.println(cytat.losuj());
					//System.out.println(addr.getCanonicalHostName());
					
				// test
				pause(10000);
				socket.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}

	private static void pause(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
}