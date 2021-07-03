import java.awt.*;

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {

	/**
		Construtor da classe Score.

		@param playerId uma string que identifica o player ao qual este placar está associado.
	*/
	private String id;
	private int score = 0;

	public Score(String playerId){
		this.id = playerId;
	}

	/**
		Método de desenho do placar.
	*/

	public void draw(){
		if(id == "Player 1") {
			//sendo player 1 da set color pra Green e alinha o score a esquerda
			GameLib.setColor(Color.GREEN);
			GameLib.drawText("Player 1:"+ String.valueOf(getScore()), 70, GameLib.ALIGN_LEFT);
		}
		if(id == "Player 2") {
			//sendo player 2 da set color pra blue e alinha o score a direita
			GameLib.setColor(Color.BLUE);
			GameLib.drawText("Player 2:"+ String.valueOf(getScore()), 70, GameLib.ALIGN_RIGHT);
		}
	}

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/

	public void inc(){
		score += 1; //aumenta o score em 1
	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.

		@return o valor inteiro referente ao total de pontos.
	*/

	public int getScore(){

		return score;
	}
}
