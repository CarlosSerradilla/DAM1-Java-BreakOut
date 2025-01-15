package Principal;

import processing.core.PApplet;

public class Pala {
	private float posX;
	private float posY;
	private float velX;
	private float ancho;
	private float alto;
	private PApplet screen;
	
	public Pala(float posX, float posY, float alto, float ancho, PApplet screen) {
		this.posX = posX;
		this.posY = posY;
		this.velX = 0;
		this.ancho = ancho;
		this.alto = alto;
		this.screen = screen;
	}
	
	public float getPosX(){
		return posX;
	}
	public float getPosY(){
		return posY;
	}
	public float getAncho(){
		return ancho;
	}
	public float getVelX(){
		return velX / 3;
	}
	
	public void update()
	{
		velX = (screen.mouseX-posX)*0.1F;
		posX = posX + velX;
	}
	
	public void display () {
		screen.fill(0,255,0);
		screen.rect(posX, posY, ancho, alto); // Importante que este en este orden
	}
	
}
