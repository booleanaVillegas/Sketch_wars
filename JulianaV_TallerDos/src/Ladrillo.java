import processing.core.PApplet;
import processing.core.PImage;

public class Ladrillo {

	private PApplet app;
	private int i;
	private float x;
	private float y;

	public Ladrillo(PApplet app, int i, int x) {
		this.app = app;
		this.x=x;
		this.i=i;
		switch (i){
		case 0:
			y=60;
			break;
		case 1:
			y=180;
			break;
		case 2:
			y=290;
			break;
		case 3:
			y=400;
			break;
		}
	}
	public void pintar(PImage[] img){
		app.imageMode(app.CENTER);
		app.image(img[i], x, y);
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
}
