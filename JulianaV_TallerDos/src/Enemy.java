import java.util.ArrayList;

import ddf.minim.AudioSample;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import serializables.BalaEne;

public class Enemy {

	private PApplet app;
	private int i;
	private int x;
	private int y;
	private int vel;
	private ArrayList<BalaEne> balasEne= new ArrayList<BalaEne>();
	private PImage im;

	public Enemy(PApplet app, int i) {
		this.app = app;
		this.i = i;
		vel= (int)app.random(2,5);
		
		y = app.height / 2;
		switch (i) {
		case 0:
			x = 150;
			break;
		case 1:
			vel *= -1;
			x = 250;
			break;
		case 2:
			x = 250;
			break;
		case 3:
			vel *= -1 ;
			x = 150;
			break;
		}
	}

	public void pintar(PImage im, PImage[] balas) {
		this.im=im;
		app.imageMode(app.CENTER);
		app.image(im, x, y);
		
		for (int i = 0; i < balasEne.size(); i++) {
			app.image(balas[balasEne.get(i).getI()], balasEne.get(i).x(),balasEne.get(i).y());
			balasEne.get(i).mover();
		}
	}

	public void mover(AudioSample disparo) {
y=y+vel;

if(y<=0+(im.width/2) || y>=480-(im.width/2)){
	vel*= -1;
}

if (app.frameCount%(int)app.random(200,800)==0){
	switch (i) {
	case 0:
		balasEne.add(new BalaEne("der", x,y));
		disparo.trigger();
		break;
	case 1:
		balasEne.add(new BalaEne("der", x,y));
		disparo.trigger();
		break;
	case 2:
		balasEne.add(new BalaEne("izq", x,y));
		disparo.trigger();
		break;
	case 3:
		balasEne.add(new BalaEne("izq", x,y));
		disparo.trigger();
		break;
	}
}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ArrayList<BalaEne> getBalasEne() {
		return balasEne;
	}
}
