import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;
import serializables.BalaEne;
import serializables.MiBala;

public class Logica implements Observer {
	private Conexion c;
	private Rocketch rocket;
	private ArrayList<Enemy> malos = new ArrayList<Enemy>();;
	private PApplet app;
	private ArrayList<BalaEne> balasEne = new ArrayList<BalaEne>();;
	private ArrayList<MiBala> myBalas = new ArrayList<MiBala>();;
	private ArrayList<Ladrillo> ladrillos = new ArrayList<Ladrillo>();
	private int pantallas;
	private int pantallasMuertas;
	private boolean puedeJugar=false;
	
	private PImage[] imgs = new PImage[35], balasEn = new PImage[2], balasMias = new PImage[2],
			rocketch = new PImage[5], enemigos = new PImage[4], ladrillosIzq = new PImage[4],
			ladrillosDer = new PImage[4], corazones = new PImage[5];
	
	/// SONIDOSS
	private Minim minim;
	private AudioSample boton;
	private AudioSample destruyo;
	private AudioSample disparo;
	private AudioSample disparoEne;
	private AudioPlayer fondo;
	private AudioSample golpeoEne;
	private AudioSample meGolpean;
	private String memuevo;
	
	public Logica(PApplet app) {
		this.app = app;
		c = Conexion.getInstance();
		c.addObserver(this);
		
		minim = new Minim(app);		 
		     boton = minim.loadSample("../data/boton.wav");
		  destruyo = minim.loadSample("../data/destruyo.wav");
		   disparo = minim.loadSample("../data/disparo.mp3");
	    disparoEne = minim.loadSample("../data/disparoene.wav");
		     fondo = minim.loadFile("../data/fondo.wav");
		  golpeoEne = minim.loadSample("../data/golpeoene.wav");
		 meGolpean = minim.loadSample("../data/megolpean.wav");
		  
		switch (c.getId()) {
		case 0:
			rocket = new Rocketch(app);
			fondo.loop();
			break;
		case 1:

			for (int i = 0; i < 2; i++) {
				malos.add(new Enemy(app, i));
			}
			for (int i = 0; i < 4; i++) {
				ladrillos.add(new Ladrillo(app, i, 70));
			}
			break;
		case 2:

			for (int i = 2; i < 4; i++) {
				malos.add(new Enemy(app, i));
			}
			for (int i = 0; i < 4; i++) {
				ladrillos.add(new Ladrillo(app, i, 350));
			}
			try {
				c.enviar("listo");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case 3:
			app.exit();
			break;
		}
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = app.loadImage("../data/sw-" + (i + 1) + ".png");
		}
		for (int i = 0; i < rocketch.length; i++) {
			rocketch[i] = imgs[30 + i];
		}
		for (int i = 0; i < enemigos.length; i++) {
			enemigos[i] = imgs[5 + i];
		}
		for (int i = 0; i < balasMias.length; i++) {
			balasMias[i] = imgs[11 + i];
		}
		for (int i = 0; i < balasEn.length; i++) {
			balasEn[i] = imgs[9 + i];
		}
		for (int i = 0; i < ladrillosIzq.length; i++) {
			ladrillosIzq[i] = imgs[13 + i];
		}
		for (int i = 0; i < ladrillosDer.length; i++) {
			ladrillosDer[i] = imgs[17 + i];
		}
		for (int i = 0; i < corazones.length; i++) {
			corazones[i] = imgs[25 + i];
		}
	}

	public void pantallas() {
		app.imageMode(app.CORNER);
		app.image(imgs[0], 0, 0);
		app.imageMode(app.CENTER);

		switch (pantallas) {
		/// INICIO
		case 0:
			app.imageMode(app.CORNER);
			if (c.getId() == 0) {
				app.image(imgs[1], 0, 0);
			}
			if (c.getId() == 1) {
				app.image(imgs[21], 0, 0);
			}
			if (c.getId() == 2) {
				app.image(imgs[22], 0, 0);
			}
			app.imageMode(app.CENTER);
			break;
		/// INSTRUCCIONES
		case 1:
			app.imageMode(app.CORNER);
			if (c.getId() == 0) {
				app.image(imgs[2], 0, 0);
			}
			if (c.getId() == 1) {
				app.image(imgs[23], 0, 0);
			}
			if (c.getId() == 2) {
				app.image(imgs[24], 0, 0);
			}
			app.imageMode(app.CENTER);
			break;
		/// JUEGO
		case 2:
			pintarJuego();
			condiciones();
			break;
		/// GANASTE
		case 3:
			app.imageMode(app.CORNER);
			if (c.getId() == 0) {
				app.image(imgs[4], 0, 0);
			}
			if (c.getId() == 1) {
				app.image(imgs[21], 0, 0);
			}
			if (c.getId() == 2) {
				app.image(imgs[22], 0, 0);
			}
			app.imageMode(app.CENTER);
			break;
		/// PERDISTE
		case 4:
			app.imageMode(app.CORNER);
			if (c.getId() == 0) {
				app.image(imgs[3], 0, 0);
			}
			if (c.getId() == 1) {
				app.image(imgs[21], 0, 0);
			}
			if (c.getId() == 2) {
				app.image(imgs[22], 0, 0);
			}
			app.imageMode(app.CENTER);
			break;
		}
	}

	public void pintarJuego() {

		switch (c.getId()) {
		case 0:
			rocket.pintar(rocketch, corazones, balasMias);
			for (int i = 0; i < balasEne.size(); i++) {
				app.image(balasEn[balasEne.get(i).getI()], balasEne.get(i).x(), balasEne.get(i).y());
				balasEne.get(i).mover();
			}
			if(memuevo.equals("arri")||memuevo.equals("aba")){
				rocket.mover(memuevo, disparo);
			}
			break;
		case 1:

			for (int i = 0; i < malos.size(); i++) {
				malos.get(i).pintar(enemigos[i], balasEn);
				malos.get(i).mover(disparoEne);

			}
			for (int i = 0; i < ladrillos.size(); i++) {
				ladrillos.get(i).pintar(ladrillosIzq);
			}
			for (int i = 0; i < myBalas.size(); i++) {
				app.image(balasMias[myBalas.get(i).getI()], myBalas.get(i).x(), myBalas.get(i).y());
				myBalas.get(i).mover();
			}
			break;
		case 2:
			for (int i = 0; i < malos.size(); i++) {
				malos.get(i).pintar(enemigos[i + 2], balasEn);
				malos.get(i).mover(disparoEne);
			}
			for (int i = 0; i < ladrillos.size(); i++) {
				ladrillos.get(i).pintar(ladrillosDer);
			}
			for (int i = 0; i < myBalas.size(); i++) {
				app.image(balasMias[myBalas.get(i).getI()], myBalas.get(i).x(), myBalas.get(i).y());
				myBalas.get(i).mover();
			}
			break;
		}
	}

	public void condiciones() {
		/// SI LAS BALAS SE SALEN, LAS ENVIO AL ID 0
		if (c.getId() == 1 || c.getId() == 2) {
			for (int i = 0; i < malos.size(); i++) {
				for (int j = 0; j < malos.get(i).getBalasEne().size(); j++) {
					if (malos.get(i).getBalasEne().get(j).x() <= -10 || malos.get(i).getBalasEne().get(j).x() >= 410) {
						try {
							c.enviar(malos.get(i).getBalasEne().get(j));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						malos.get(i).getBalasEne().remove(j);
						break;
					}
				}
			}
			///// desturyo ladrillos
			for (int i = 0; i < ladrillos.size(); i++) {
				for (int j = 0; j < myBalas.size(); j++) {
					if (app.dist(ladrillos.get(i).getX(), ladrillos.get(i).getY(), myBalas.get(j).x(),
							myBalas.get(j).y()) < 60) {
						destruyo.trigger();
						ladrillos.remove(i);
						myBalas.remove(j);
						break;
					}
				}
			}
			for (int i = 0; i < malos.size(); i++) {
				for (int j = 0; j < myBalas.size(); j++) {
					if (app.dist(malos.get(i).getX(), malos.get(i).getY(), myBalas.get(j).x(),
							myBalas.get(j).y()) < 60) {
golpeoEne.trigger();
						myBalas.remove(j);
						break;
					}
				}
			}
		}
		/// ENVIO MIS BALAS A LOS ENEMIGOS
		if (c.getId() == 0) {
			for (int i = 0; i < rocket.getBalas().size(); i++) {
				try {
					if (rocket.getBalas().get(i).x() <= -10) {

						c.enviar(rocket.getBalas().get(i));
						rocket.getBalas().remove(i);
						break;
					} else if (rocket.getBalas().get(i).x() >= 410) {

						c.enviar(rocket.getBalas().get(i));
						rocket.getBalas().remove(i);
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			/// PIERDO VIDAAAS
			for (int i = 0; i < balasEne.size(); i++) {
				if (app.dist(balasEne.get(i).x(), balasEne.get(i).y(), rocket.getX(), rocket.getY()) < 50) {
					meGolpean.trigger();
					rocket.perderVidas();
					balasEne.remove(i);
					break;
				}
			}
		}
		
		//// PIERDO O GANO.
		if(pantallasMuertas>=2){
			pantallas=3;
		}
		try{
			if(c.getId()==0){
		if (rocket.getVidas()==0){
			c.enviar("perdiste");
		}
		}
		
			if(c.getId()==1 && ladrillos.size()<1){
				c.enviar(true);
			}
			if(c.getId()==2 && ladrillos.size()<1){
				c.enviar(true);
			}
		
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void teclas() {
		try {
			if (app.keyCode == app.RIGHT) {

				c.enviar("der");
			}
			if (app.keyCode == app.LEFT) {
				c.enviar("izq");
			}
			if (app.keyCode == app.DOWN) {
				c.enviar("aba");
			}
			if (app.keyCode == app.UP) {
				c.enviar("arri");
			}
			if (app.keyCode == ' ') {
				c.enviar("siguiente");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void reiniciar() {
		
		ladrillos.clear();
		pantallasMuertas=0;
		switch(c.getId()){
		case 0:
		rocket.setVidas(4);
			break;
	case 1:	
		for (int i = 0; i < 4; i++) {
			ladrillos.add(new Ladrillo(app, i, 70));
		}
		break;
	case 2:
		for (int i = 0; i < 4; i++) {
			ladrillos.add(new Ladrillo(app, i, 350));
		}
		}
		pantallas=2;
	}
	
	public void update(Observable arg0, Object arg1) {

		/// RECIBO BALAS ENEMIGAS EN EL ID 0
		if (c.getId() == 0) {
			if (arg1 instanceof BalaEne) {
				if (((BalaEne) arg1).getDireccion().equals("der")) {
					((BalaEne) arg1).setX(-5);
				}
				if (((BalaEne) arg1).getDireccion().equals("izq")) {
					((BalaEne) arg1).setX(405);
				}
				balasEne.add((BalaEne) arg1);
			}
		}
		/// COMANDOS DEL CONTROL ANDROID

		if (arg1 instanceof String) {
			memuevo=(String)arg1;
			if (c.getId() == 0) {
				if(arg1.equals("der")||arg1.equals("izq")){
				rocket.mover((String) arg1,disparo);
				}
			}
			if(puedeJugar){
			if (arg1.equals("siguiente")) {
				pantallas++;
				boton.trigger();
			}
			}
			/// PERDISTE O GANASTE
			if (arg1.equals("ganaste")) {
				pantallas=3;
			}
			if (arg1.equals("perdiste")) {
				pantallas=4;
			}
			/// REINICIAR
			if (arg1.equals("reiniciar")) {
				reiniciar();
				boton.trigger();
			}
			// LAS 3 PANTALLAS ESTAN CONECTADAS 
			if (arg1.equals("listo")) {
				puedeJugar=true;
			}
		}
		/// RECIBO BALAS DE LA PANTALLA DEL JUGADOR A LAS ENEMIGAS
		if (c.getId() == 1 || c.getId() == 2) {
			if (arg1 instanceof MiBala) {
				if (((MiBala) arg1).getDireccion().equals("der")) {
					if (c.getId() == 2) {
						((MiBala) arg1).setX(-5);
						myBalas.add((MiBala) arg1);
					}
				}

				if (((MiBala) arg1).getDireccion().equals("izq")) {
					if (c.getId() == 1) {
						((MiBala) arg1).setX(405);
						myBalas.add((MiBala) arg1);
					}
				}
			}
		}
/// VALIDAR GANAR
		if(arg1 instanceof Boolean){
			if(arg1.equals(true)){
				pantallasMuertas++;
			}
		}
	}

	

}
