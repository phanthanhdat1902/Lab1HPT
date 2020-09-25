package com.hust.soict.phan_thanh_dat.client_server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Client {
	public static void main(String[] args) {
		System.out.println("client running");
		Socket socket =null;
		BufferedReader in=null;
		PrintWriter out =null;
		try {
			socket = new Socket("127.0.0.1", 8888);
			in = new BufferedReader(new 
					InputStreamReader(socket.getInputStream()));
			out = new
					PrintWriter(socket.getOutputStream(), true);
			new Recv(in).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String message = scanner.nextLine();
			if (message.compareTo("quit")==0) {
				break;	
			}
			out.println(message);
		}
		try {
			scanner.close();
			socket.close();
		}catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
	}
	private static class Recv extends Thread{
		private BufferedReader in;
		public Recv(BufferedReader in) {
			this.in=in;
		}
		@Override
		public void run() {
			while(true) {
				try {
					String input = in.readLine();
					System.out.println("Server: "+input);
				}catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}	
			}
		}
	}
}
