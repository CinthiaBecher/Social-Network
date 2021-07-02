
public class MinhaExcecao extends Exception{
	
	/*	Representa uma exce��o no seu programa. */

	private String mensagem;

	//Recebe no construtor uma mensagem, que ser� informada no momento da exce��o. 
	public MinhaExcecao(String mensagem) {
		this.mensagem = mensagem;
	}


	//Sobrescreve o m�todo toString(), retornando a mensagem presente no atributo
	
	@Override
	public String toString() {
		return "[ERRO] " + mensagem;
	}
}
