import java.awt.*;

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/
	private double dirx ; // variaveis novas criadas para determinar a direção da bola;
	private double diry ;
	private double cx;
	private double cy;
	private double width;
	private double height;
	private double speed;
	private Color color;

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.speed = speed;
		this.diry = speed; // tenta o valor speed basta variar entre positivo e negativo para bola mudar de direção
		this.dirx = speed;
	}


	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){

		GameLib.setColor(color);
		GameLib.fillRect(cx, cy, width, height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
		//decidi não multiplicar o speed pelo delta pois ficava muito rapido o jogo
		this.cy += diry*delta; // bola recebe a direção
		this.cx += dirx*delta;
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
		if(playerId =="Player 1")
			//sendo o player a esquerda a bola tem que ter direção positiva no eixo x
			// para que ela vá para direita
			this.dirx = Math.abs(this.speed);
		if(playerId == "Player 2")
			//sendo o player adireita a bola tem que ter direção negativa no eixo x
			// para que ela vá para esquerda
			this.dirx = -Math.abs(this.speed);
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
		if(wallId == "Top") {
			//sendo topo a direção do eixo y tem que ser positiva para bola descer
			this.diry = Math.abs(this.speed);
		}
		if(wallId.equals("Bottom")) {
			//sendo a parte de baixo a direção do eixo y deve ser negativa para bola subir
			this.diry = -Math.abs(this.speed);
		}
		if(wallId == "Right") {
			//sendo a parede direita a bola tem que ter direção negativa no eixo x
			// para que ela vá para esquerda
			this.dirx = -Math.abs(this.speed);
		}
		if (wallId == "Left") {
			//sendo a parede esquerda a bola tem que ter direção positiva no eixo x
			//para que ela va para direita
			this.dirx = Math.abs(this.speed);
		}
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall){
		if(wall.getId() == "Top"){ // verifica se a parede é top
			//E então verifica se encostou nela
			if(getCy() - height/2 <= wall.getCy() + wall.getHeight()/2){
				return true;
			}
		}else if(wall.getId() == "Bottom"){// verifica se a parede é bottom
			//E então verifica se encostou nela
			if(getCy() + height/2 >= wall.getCy() - wall.getHeight()/2){
				return true;
			}
		}else if(wall.getId() == "Right"){// verifica se a parede é right
			//E então verifica se encostou nela
			if(getCx() + width/2 >= wall.getCx() - wall.getWidth()/2){
				return true;
			}
		}else if(wall.getId() == "Left"){// verifica se a parede é left
			//E então verifica se encostou nela
			if(getCx() - width/2 <= wall.getCx() + wall.getWidth()/2){
				return true;
			}
		}
		return false;
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		if(player.getId() == "Player 1"){
			//verifica se a bola esta dentro da coordenada y que envolvo a "Altura do player"
			if(getCy()  <= player.getCy() + player.getHeight()/2 && getCy()  >= player.getCy() - player.getHeight()/2) {
				//e então verifica se a bola esta na mesma cornada x
				if (getCx() - width / 2 <= player.getCx() + player.getWidth() / 2) {
					return true;
				}
			}
		}else if(player.getId() == "Player 2"){
			//verifica se a bola esta dentro da coordenada y que envolvo a "Altura do player"
			if(getCy()  <= player.getCy() + player.getHeight()/2 && getCy()  >= player.getCy() - player.getHeight()/2) {
				//e então verifica se a bola esta na mesma cornada x
				if (getCx() + width / 2 >= player.getCx() - player.getWidth() / 2) {
					return true;
				}
			}
		}
		return false;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){

		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){

		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){

		return this.speed;
	}

}
