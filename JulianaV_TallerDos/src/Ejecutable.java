import processing.core.PApplet;

public class Ejecutable extends PApplet {
Logica log;
	
public void setup() {
	size(400,481);
log= new Logica(this);
}

	public void draw() {
		background(255);
		log.pantallas();
		
	}
	@Override
		public void keyPressed() {
			log.teclas();
		}
}
