package com.villegas.juliana.t2_juliv_controlandroid;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Observable;


public class Conexion {
	MulticastSocket socket;

	final int PORT = 5000;
	InetAddress ip;
	private int id;
	private static Conexion instance;

	private boolean identificar;
	private int destino;
	DatagramPacket paquete;
	public Conexion() {
		instance = null;


		identificar = false;
		try {
			socket = new MulticastSocket(PORT);
			ip = InetAddress.getByName("230.9.5.96");
			socket.joinGroup(ip);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static Conexion getInstance() {
		if (instance == null) {
			instance = new Conexion();

		}
		return instance;
	}


	public void enviar(Object obj) throws IOException {

		ByteArrayOutputStream bytesFlujito = new ByteArrayOutputStream();
		ObjectOutputStream cereal = new ObjectOutputStream(bytesFlujito);
		cereal.writeObject(obj);
		cereal.flush();
		cereal.close();
		bytesFlujito.close();
		byte[] bytes = bytesFlujito.toByteArray();
		 paquete = new DatagramPacket(bytes, bytes.length, ip, PORT);
		Thread hilo = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					socket.send(paquete);
					socket.send(paquete);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		hilo.start();

	}

}

