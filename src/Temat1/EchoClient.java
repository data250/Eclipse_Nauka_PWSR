package Temat1;

import java.io.*;
import java.net.*;
import java.util.*;

public class EchoClient {
	static private Socket socket;
	static private final int PORT = 2345;
	static private final String SERVER = "localhost";
	static private final String PROMPT = "> ";

	public static void main(String[] args) {
		Scanner keyboard = null;
		Scanner in = null;
		PrintWriter out = null;
		try {
			System.out.println("��cze si� z serwerem na porcie " + PORT);
			socket = new Socket(SERVER, PORT);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new Scanner(socket.getInputStream());
			keyboard = new Scanner(System.in);
		} catch (UnknownHostException e) {
			return;
		} catch (IOException e) {
			return;
		}
		System.out.println(in.nextLine());
		boolean koniec = false;
		while (!koniec) {
			System.out.print(PROMPT);
			out.println(keyboard.nextLine());
			if (in.hasNextLine()) {
				System.out.println(in.nextLine());
			} else {
				koniec = true;
			}
		}
		System.out.println("Po��czenie zosta�o zako�czone.");
		try {
			socket.close();
		} catch (IOException e) {
		}
	}
}