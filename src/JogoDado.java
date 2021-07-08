import java.io.Serializable;

public class JogoDado implements Serializable {

	private static final long serialVersionUID = 7483245805827466964L;
	private int dado1;
	private int dado2;
	private int soma;
	private int tentativas = 3;
	
	public void inicializarJogo() {
		dado1 = lancarDado(1, 6);
		dado2 = lancarDado(1, 6);
		soma = dado1 + dado2;
		System.out.println(soma); //para testes
	}
	
	//versão 1
	public boolean jogar(int chute) {
		boolean retorno = false;
		if(tentativas > 0) {
			tentativas--;
			if(chute == soma) {
				retorno = true;
			}
		}
		return retorno;
	}
	
	//versão 2
	public boolean jogar2(int chute) {
		if(tentativas > 0) {
			tentativas--;
			return chute == soma;
		}
		return false;
	}
	
	public int obterTentativas() {
		return tentativas;
	}
	
	public boolean existemTentativas() {
		return tentativas > 0;
	}

	private int lancarDado(int min, int max) {
		return (int)(Math.random()*(max+1-min)) + min;
	}
}
