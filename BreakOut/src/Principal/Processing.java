package Principal;

import processing.core.PApplet;

public class Processing extends PApplet
{
	private Pista pista;
	private int estado;
	
	public void settings() // Este método tiene que llamarse "settings" obligatoriamente
	{
		size(800,600); //Esta línea se encarga de la configuración del tamaño de pantalla
	}
	
	public void setup() {
		background(0,0,0,0); //0 significa fondo negro y el blanco 255
		estado = 0;
	}
	
	
	// 
	public void presentacion()
	{
		fill(255,0,255);
		textSize(40);
		text ("Press click to Start", width/2, height/2+100);
		textSize(100);
		textAlign(CENTER);
		text ("BREAK OUT", width/2, height/2);
	}
	public void configurarJuego() // Este método se encarga de crear la pista
	{
		pista = new Pista(this);
	}
	public void juego()
	{
		pista.update();
		pista.display();
	}
	public void creditos()
	{
		fill(255,0,0);
		textAlign(CENTER);
		textSize(100);
		text ("GAME OVER", width/2, height/2);
		pista.display();
		
	}
	
	public void draw() {
		background(0,0,0,0);
		switch(estado)
		{
		case 0:
			presentacion();
			break;
		case 1:
			configurarJuego();
			estado=2;
			break;
		case 2:
			juego();
			if(pista.getFin())
				estado = 3;
			break;
		case 3:
			creditos();
			break;
		}
	}
	
	public void mouseClicked() // Este nombre tiene que ser este por narices
	{
		switch(estado)
		{
		case 0: estado = 1;break;
		case 1: break;
		case 2: break;
		case 3: estado =0;break;
		}
	}
	
}
