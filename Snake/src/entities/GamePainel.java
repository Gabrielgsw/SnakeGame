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
	}
	
	public void newApple() {
		
	}
	
	public void move() {
		
	}
	
	public void checkApple() {
		
	}
	
	public void checkCollisions() {
		
	}
	
	public void gameOver(Graphics g) {
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	// Classe herdada e metodo para pressionar tecla
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			
		}
	}
}
