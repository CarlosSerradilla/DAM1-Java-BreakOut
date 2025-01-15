package Principal;
import processing.core.PApplet;

public class Bloque
{
	private float posX;
	private float posY;
	private float ancho;
	private float alto;
	private boolean existe;
	private PApplet screen;
	
	public Bloque(float posX, float posY, PApplet screen)
	{
		this.posX = posX;
		this.posY = posY;
		this.ancho = 60;
		this.alto = 20;
		this.existe = true;
		this.screen = screen;
	}
	
	public void setExiste(boolean existe){
		this.existe = existe;
	}
	public boolean getExiste(){
		return existe;
	}
	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public float getAncho() {
		return ancho;
	}

	public float getAlto() {
		return alto;
	}

	public void display()
	{
		if(this.existe)
		{
			screen.fill(255,255,0);
			screen.rect(posX, posY, ancho, alto);
		}
	}
}