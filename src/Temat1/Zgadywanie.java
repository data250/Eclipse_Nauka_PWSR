package Temat1;

import java.io.*;
import java.net.*;
import java.util.*;

public class Zgadywanie {

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
					
					Random generator = new Random();
					int liczba = generator.nextInt(21); //bo n-1
					
					boolean koniec = false;
					while (!koniec) {
						linia = in.nextLine();
						if (linia.trim().toLowerCase().equals("/end")) {
							koniec = true;
						} else {
							// tylko tyle w zasadzie roznic
					        boolean TylkoLiczby=true; 
					        int i=0; 
					        while(TylkoLiczby==true && i<linia.length()){ 
					            if(linia.charAt(i)<'0'|| linia.charAt(i)>'9'){ 
					                TylkoLiczby=false;  
					            } 
					            ++i; 
					        }
					        
					        //out.println("Serwer: moja liczba" + liczba);
					        if (TylkoLiczby==true) {
					        	int liczbaTest = Integer.parseInt(linia);
					        	 out.println("Serwer: podano " + liczbaTest);
					        	if (liczbaTest!=liczba){
					        		if (liczbaTest>liczba){
					        			out.println("Serwer: Za duzo!");
					        		} else {
					        			out.println("Serwer: Za malo!");
					        		}
					        	} else {
					        		out.println("Serwer: Wygra³eœ");
					        	}
					        } else {
					        	out.println("Serwer: Podaj liczbe!");
					        }

							// end
						}
					}
					socket.close();
					server.close(); //anty error w eclipse
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

