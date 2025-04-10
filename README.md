# BREAK OUT - Java
### Videojuego de Break Out recreado usando Java como proyecto de clase de DAM1V (Desarrollo de Aplicaciones Multiplataformas 1er grado Vespertino)
### El proyecto fue creado usando la versión 1.8 de Java en EclipseIDE

![Rompebloques](https://www.google.com/logos/fnbx/block_breaker/cta.webp)

## Main
```java
package Principal;

import processing.core.PApplet;

public class Main {

	public static void main(String[] args) 
	{
		PApplet.main("Principal.Processing");
	}

}
```
## Ball
```java
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
```
## Bloque
```java
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
```
## Marcador
```java
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
```
## Pala
```java
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
```
## Pista
```java
package Principal;

import processing.core.PApplet;

public class Pista {
	private Ball ball;
	private Pala pala;
	private Marcador marcador;
	private Bloque bloque;
	private boolean fin;
	private PApplet screen;
	
	// La coordenada X es la coordenada horizontal
	// La coordenada Y es la coordenada vertical
	
	public Pista(PApplet screen) {
		// Los diferentes tamaños de la pala:
			float modoFacil = 100;
			float modoNormal = 60;
			float modoDificil = 40;
			float modoPesadilla = 10;
		this.screen = screen;
		ball = new Ball(screen.width/2, 50, 20, screen); //primero es cordenada X, luego la cordenada Y & finalmente el radio
		pala = new Pala(screen.width/2-modoNormal/2, screen.height-10, 10, modoNormal,screen);
		marcador = new Marcador(screen.width-100,50,screen);
		bloque = new Bloque (100, 60, screen);
		this.fin = false;
	}
	
	public boolean getFin()
	{
		return this.fin;
	}
	
	public void update() {
		ball.update();
		pala.update();
		
		if(bloque.getExiste())
		{
			if(detectarColisionBolaBloque(bloque))
			{
				bloque.setExiste(false);
				marcador.incrementar();
			}
		}
		
		if(detectarColisionBolaPala()) // Este operador se encarga de detectar la bola
		{
			ball.reboteY();
			ball.setVelX(pala.getVelX());
			marcador.seriousPunch(); // Esta línea se encarga de detectar la cantidad de golpes
		}
		if(detectarColisionBolaLimites())
		{
			ball.reboteX();
		}
		if (ball.getPosY()>screen.height) // Este operador detecta cuando la bola se cae
		{
			fin = true;
		}
	}
	
	public void display() {
		ball.display();
		pala.display();
		marcador.display();
		bloque.display();
	}
	
	// Este método se encarga de detectar cuando la bola choca con la pala
	public boolean detectarColisionBolaPala()
	{	
		float px; // Coordenada X del punto de referencia
		float py; //Coordenada Y del punto de referencia
		float palaX = pala.getPosX();
		float palaAncho = pala.getAncho();
		float cx, cy;	// Coordenadas del centro de la bola
		float distancia;
		float radio;
		
		palaX = pala.getPosX();
		palaAncho = pala.getAncho();
		
		cx = ball.getPosX();
		cy = ball.getPosY();
		radio = ball.getRadio();
		
		px = cx; // La bola esta sobre la pala
		if (px < palaX)	px = palaX;	// Si esta a la izquierda corrijo
		
		if (px > palaX + palaAncho) px = palaX + palaAncho;	// Si esta a la derecha corrijo	
		
		py = pala.getPosY();
		
		distancia = (float) Math.sqrt(Math.pow(px-cx,2) + Math.pow(py-cy,2));
		if(distancia < radio)
			return true; // La pala choca contra la bola
		return false;	// La pala NO choca contra la bola
	}

	// Este método se encarga de detectar cuando la bola choca contra los bordes
	public boolean detectarColisionBolaLimites()
	{
		float x = ball.getPosX();
		float radio = ball.getRadio();
		
		if(x-radio<0 || x+radio>screen.width)
			return true;
		return false;
	}

	public boolean detectarColisionBolaBloque(Bloque b)
	{
		float px,py;
		
		float cx = ball.getPosX();
		float cy = ball.getPosY();
		float bloqueX = b.getPosX();
		float bloqueY = b.getPosY();
		float bloqueAncho = b.getAncho();
		float bloqueAlto = b.getAlto();
		
		px = cx;
		if(px < bloqueX)
			px = bloqueX;
		if(px > bloqueX+bloqueAncho)
			px = bloqueX+bloqueAncho;
		
		py = cy;
		if(py < bloqueY)
			py = bloqueY;
		if(py > bloqueY+bloqueAlto)
			py = bloqueY+bloqueAlto;
		
		float distancia = (float) Math.sqrt(Math.pow(px-cx,2)+Math.pow(py-cy,2));
		if(distancia<=ball.getRadio())
			return true;
		return false;
		
	}
}
```
## Processing
```java
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
```
