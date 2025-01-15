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
