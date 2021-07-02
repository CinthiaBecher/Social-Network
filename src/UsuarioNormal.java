import java.io.IOException;
import java.util.Arrays;

public class UsuarioNormal extends Usuario{

	public UsuarioNormal(String nome, String nomeDeUsuario, String senha) throws IOException {
		super(nome, nomeDeUsuario, senha);
		
		toStringArquivo();
	}
	
	public UsuarioNormal(String nome, String nomeDeUsuario, String senha, Usuario[] seguidores) throws IOException {
		super(nome, nomeDeUsuario, senha, seguidores);
		
		toStringArquivo();
	}
	
	public UsuarioNormal(String nome, String nomeDeUsuario, String senha, boolean x) throws IOException {
		super(nome, nomeDeUsuario, senha);
		
		
	}

	@Override
	public void toStringArquivo() {
		super.toStringArquivo();
	}
	
	@Override
	public void NovaBusca(Usuario busca) {
		;
	}
	
	public String sair() {
		return super.sair();
	}
	
	public void NovaBusca(Usuario busca, boolean x) {
		;
	}

	@Override
	public String toString() {
		return "UsuarioNormal [nome=" + nome + ", nomeDeUsuario=" + nomeDeUsuario + ", senha="+ getSenha() + ", seguidores="
				+ toStringSeguidores() + ", mensagens=" + mensagens +"]";
	}


}
