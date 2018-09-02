import java.util.ArrayList;

import ddf.minim.AudioSample;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import serializables.MiBala;

public class Rocketch {

	private PApplet app;
	private int x;
	private int y;
	private ArrayList<MiBala> balas=new ArrayList<MiBala>();
	private int vidas;

	public Rocketch(PApplet app) {
		this.app = app;
		x = (int) app.width / 2;
		y = (int) app.height / 2;
		vidas = 4;
	}

	public void pintar(PImage im[], PImage[] vid, PImage[] misbalas) {
		app.imageMode(app.CENTER);
		app.image(im[vidas], x, y);
		app.image(vid[vidas], app.width / 2, 50);
		
		for (int i = 0; i < balas.size(); i++) {
			app.image(misbalas[balas.get(i).getI()], balas.get(i).x(),balas.get(i).y());
			balas.get(i).mover();
		}
		
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void mover(String dir, AudioSample disparo) {
		if(y<=20){
			y=21;
		}
		
		if(y>=455){
			y=454;
		}
		if (y>20 && y<455 ){
		switch (dir) {
		case "aba":
			y = y + 5;
			break;
		case "arri":
			y = y - 5;
			break;
		case "der":
			balas.add(new MiBala("der", x+100, y));
			disparo.trigger();
			break;
		case "izq":
			balas.add(new MiBala("izq", x-100, y));
			disparo.trigger();
			break;
		}
		}
	}
public void perderVidas(){
	if(vidas>0){
	vidas--;
	}
}
	public int getVidas() {
	return vidas;
}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public ArrayList<MiBala> getBalas() {
		return balas;
	}

}
