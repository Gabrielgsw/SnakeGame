package entities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePainel extends JPanel implements ActionListener{

	
	// adicao da largura e altura da tela
	static final int SCREEN_WIDTH =600;
	static final int SCREEN_HEIGHT =600;
	// adicao do tamanho das unidades
	static final int  UNIT_SIZE=25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	// adicionando o atraso para o jogo
	static final int DELAY = 75;
	// adicionando coordenadas para o corpo da cobra
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	// definindo a quantidade de partes iniciais da cobra
	int bodyParts =6;
	// definindo macas comidas e posicionamento
	int applesEaten;
	int appleX;
	int appleY;
	// definindo as direcoes possiveis
	char direction = 'R'; // -> o primeiro movimento sera para a direita, Right
	boolean running = false;
	// instanciando a interface Random e Timer
	Timer timer;
	Random random;
	
	GamePainel(){
		random = new Random(); // Instanciando o random para o posicionamento aleatorio da maca
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT)); // adicionando o tamanho para o GamePainel
		this.setBackground(Color.black); // adicionando a cor preta ao background
		this.setFocusable(true); // definindo a capacidade de foco
		this.addKeyListener(new MyKeyAdapter()); // adicionando novo key adapter
		startGame(); // chamando metodo para iniciar o jogo
	}
	
	public void startGame() {
		// definicoes para o comeco do jogo
		newApple();
		running = true; 
		Timer timer = new Timer(DELAY,this);
		timer.start(); // iniciando o timer
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		// adicionando linhas verticais e horizontais a tela
		for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
			g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE, SCREEN_HEIGHT);
			g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);
		}
		// comandos para desenhar a maca
		g.setColor(Color.red);
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
		// fazendo a cobrinha
		for(int i=0;i<bodyParts;i++) {
			if(i == 0) {
				g.setColor(Color.green);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}
			else {
				g.setColor(new Color(45,180,0)); // definindo uma cor pelo padrao RGB
				g.fillRect(x[i], y[i], UNIT_SIZE,UNIT_SIZE);
			}
		}
	}
	
	public void newApple() {
		// Gerando uma maca aleatoria no eixo X e Y
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE);
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE);
	}
	
	public void move() {
		// loop para movimento da cobra
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		// definindo as direcoes
		switch(direction) {
		case 'U':
			y[0]=y[0]-UNIT_SIZE;
			break;
		case 'D':
			y[0]=y[0]+UNIT_SIZE;
			break;
		case 'L':
			x[0]=x[0]-UNIT_SIZE;
			break;
		case 'R':
			x[0]=x[0]+UNIT_SIZE;
			break;
		}
	}
	
	public void checkApple() {
		
	}
	
	public void checkCollisions() {
		// checando se a cabeca colide com o corpo
		for(int i=bodyParts;i>0;i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		// checando se a cabeca toca na borda horizontal esquerda
		if(x[0]<0) {
			running = false;
		}
		// checando se a cabeca toca a borda da direita
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		// checando se a cabeca toca a borda superior
		if(y[0] < 0) {
			running = false;
		}
		// checando se a cabeca toca a borda inferior
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		// condicao para o jogo parar
		if(!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// fazendo o jogo comecar (cobra se mover)
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
		
	}
	
	// Classe herdada e metodo para pressionar tecla
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			// fazendo o movimento da cobra e adicionando as teclas
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:{
				if(direction != 'U') {
					direction = 'D';
					break;
				}
			}
			}
		}
	}
}
