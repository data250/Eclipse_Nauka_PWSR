package Temat1;

import java.net.*;
import java.io.*;

public class SkanerPortow {
	public static void main(String[] args) {
		Socket socket;
		String host = "uek.krakow.pl";
		for (int i = 0; i < 1024; i++) {
			System.out.println("Testuje port " + i);
			try {
				socket = new Socket(host, i);

				System.out.println("Znalaz³em serwer na porcie " + i + " komputera: " + host);
				socket.close();
			} catch (UnknownHostException e) {
			//	System.out.println(e);
				System.err.println(e);
				break;
			} catch (IOException e) {
			//	System.out.println(e);
			}
		}
	}
}