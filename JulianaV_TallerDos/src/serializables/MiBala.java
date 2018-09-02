package serializables;

import java.io.Serializable;

public class MiBala implements Serializable {

	private String direccion;
	private int x;
	private int y;
	private int i;

	public MiBala(String direccion, int x, int y) {
		this.direccion = direccion;
		this.x = x;
		this.y = y;
		
	}

	public void mover() {
		switch (direccion) {
		case "der":
			x = x + 3;
			i=1;
			break;
		case "izq":
			x = x - 3;
			i=0;
			break;
		}
	}

	public String getDireccion() {
		return direccion;
	}

	public int getI() {
		return i;
	}

	public int x() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int y() {
		return y;
	}

}
