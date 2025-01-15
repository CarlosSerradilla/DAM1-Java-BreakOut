package Principal;

import processing.core.PApplet;

public class Ball {
	private float posX; //posicion
	private float posY;
	private float velX; //velocidad
	private float velY;
	private float aceX; //aceleracion
	private float aceY;
	private int radio;	//tamaño de la pelota
	private int diametro; 
	private PApplet screen;
	
	public Ball(float posX, float posY, int radio, PApplet screen) {
		this.posX = posX;
		this.posY = posY;
		this.radio = radio;
		this.diametro = radio*2;
		this.velX = 0;
		this.velY = 0;
		this.aceX = 0;
		this.aceY = 0.1f;
		this.screen = screen;
	}
	
	public float getPosX(){
		return posX;
	}
	public float getPosY(){
		return posY;
	}
	public float getRadio(){
		return radio;
	}
	public void setVelX(float velX){
		this.velX = velX;
	}
	
	public void reboteX(){
		velX = velX * (-1);
		// También se podría poner "velX = -velX"
	}
	public void reboteY(){
		velY = velY * (-1);
	}
	
	public void update()
	{
		velX = velX + aceX;
		velY = velY + aceY;
		posX = posX + velX;
		posY = posY + velY;
		// Se tiene que realizar en este orden
	}
	
	public void display() // Este método solo se encarga de dibujar la bola
	{
		screen.fill(255);
		screen.ellipse(posX, posY, diametro/2, diametro/2);
	}
}
