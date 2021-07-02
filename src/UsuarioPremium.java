import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class UsuarioPremium extends Usuario {

	Usuario[] usuariosQueBuscaram;
	
	
	public UsuarioPremium(String nome, String nomeDeUsuario, String senha) throws IOException {
		super(nome, nomeDeUsuario, senha);
		this.usuariosQueBuscaram = new Usuario[10];
		
		toStringArquivo();
		
	}
	
	public UsuarioPremium(String nome, String nomeDeUsuario, String senha, Usuario[] seguidores) throws IOException {
		super(nome, nomeDeUsuario, senha, seguidores);
		this.usuariosQueBuscaram = new Usuario[10];
		
		toStringArquivo();
		
	}
	
	public UsuarioPremium(String nome, String nomeDeUsuario, String senha, Usuario[] seguidores, Usuario[] usuariosQueBuscaram) throws IOException {
		super(nome, nomeDeUsuario, senha, seguidores);
		this.usuariosQueBuscaram = usuariosQueBuscaram;
		
		toStringArquivo();
		
	}
	
	public UsuarioPremium(String nome, String nomeDeUsuario, String senha, boolean x) throws IOException {
		super(nome, nomeDeUsuario, senha);
		this.usuariosQueBuscaram = new Usuario[10];
		
	}

	
	public void toStringArquivo() {
		FileWriter fw;
		try {
			fw = new FileWriter(dadosDosUsuarios 	, true);
			PrintWriter out = new PrintWriter(fw);
			
			out.println("UsuarioPremium, " + nome + ", " + nomeDeUsuario + ", " + getSenha() + ", "
					+ toStringSeguidores() + ", " + toStringBusca() + ", " + mensagens);
			//out.println("UsuarioPremium, nome=" + nome + ", nomeDeUsuario=" + nomeDeUsuario + ", senha=" + getSenha() + ", seguidores="
					//+ toStringSeguidores() + ", buscas=" + toStringBusca());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public String sair() {
		return "UsuarioPremium, " + nome + ", " + nomeDeUsuario + ", " + getSenha() + ", " 
				+ toStringSeguidores() + ", " + toStringBusca() + ", " + mensagens + "\n";
	}
	
	public String toStringBusca() {
		String s = "";
		if (usuariosQueBuscaram[0] == null)
			s = "0";
		else{
			for (int i = 0; i < usuariosQueBuscaram.length; i++) {
				if (usuariosQueBuscaram[i] == null)
					return s;
				else {
					if (i == usuariosQueBuscaram.length - 1 || usuariosQueBuscaram[i+1] == null)
						s += usuariosQueBuscaram[i].nome;
					else if (i < usuariosQueBuscaram.length - 1)
						s += usuariosQueBuscaram[i].nome + " ";
				} 
			}
		}
		return s;
	}
	
	public void NovaBusca(Usuario busca) {
		for (int i = 0; i < usuariosQueBuscaram.length; i++) {
			if (usuariosQueBuscaram[i] == null) {
				usuariosQueBuscaram[i] = busca;
				break;
			}
		}
		toStringArquivo();
	}
	
	public void NovaBusca(Usuario busca, boolean x) {
		for (int i = 0; i < usuariosQueBuscaram.length; i++) {
			if (usuariosQueBuscaram[i] == null) {
				usuariosQueBuscaram[i] = busca;
				break;
			}
		}

	}

	@Override
	public String toString() {
		return "UsuarioPremium [nome=" + nome + ", nomeDeUsuario=" + nomeDeUsuario + ", senha=" + getSenha() + ", seguidores=" + toStringSeguidores()
				+ ", usuariosQueBuscaram=" + toStringBusca() + ", mensagens=" + mensagens +"]";
	}
	
	
}
