package Principal;

import processing.core.PApplet;

public class Marcador
{
	private int puntos;
	private int golpes;
	private float posX;
	private float posY;
	private PApplet screen;
	
	public Marcador (float posX, float posY, PApplet screen)
	{
		this.posX = posX;
		this.posY = posY;
		this.puntos = 0;
		this.screen = screen;
	}
	
	public void incrementar()
	{
		this.puntos = puntos + 100;
	}
	
	public void seriousPunch()
	{
		golpes++;
	}
	
	public void display()
	{
		screen.stroke(255,0,0);
		screen.fill(255,0,0);
		screen.textSize(24);
		screen.textAlign(screen.CENTER);
		screen.text("Points: "+this.puntos, posX, posY);
		screen.text("Hits: "+this.golpes, posX, posY+40);
	}
	
	/*public void puntajeFinal()
	{
		screen.stroke(255,0,0);
		screen.fill(255,0,0);
		screen.textSize(24);
		screen.textAlign(screen.CENTER);
		screen.text("Points: "+this.puntos, posX, posY);
		screen.text("Hits: "+this.golpes, posX, posY+40);
	}*/
}