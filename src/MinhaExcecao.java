
public class MinhaExcecao extends Exception{
	
	/*	Representa uma exceção no seu programa. */

	private String mensagem;

	//Recebe no construtor uma mensagem, que será informada no momento da exceção. 
	public MinhaExcecao(String mensagem) {
		this.mensagem = mensagem;
	}


	//Sobrescreve o método toString(), retornando a mensagem presente no atributo
	
	@Override
	public String toString() {
		return "[ERRO] " + mensagem;
	}
}
