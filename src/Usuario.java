import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public abstract class Usuario {
	//atributos
	public String nome;
	public String nomeDeUsuario;
	private String senha;
		
	public Usuario[] seguidores;
	public String mensagens;
	
	File dadosDosUsuarios = new File("Dados dos Usuarios");
	
	Scanner teclado = new Scanner(System.in);
	

	public Usuario(String nome, String nomeDeUsuario, String senha) throws IOException {
		super();
		this.nome = nome;
		this.nomeDeUsuario = nomeDeUsuario;
		this.senha = senha;
		verificaSenha();
		
		seguidores = new Usuario[10];
		dadosDosUsuarios.createNewFile();
		
		mensagens = null;
		
		//toStringArquivo();
	}
	
	public Usuario(String nome, String nomeDeUsuario, String senha, Usuario[] seguidores) throws IOException {
		super();
		this.nome = nome;
		this.nomeDeUsuario = nomeDeUsuario;
		this.senha = senha;
		
		
		this.seguidores = seguidores;
		dadosDosUsuarios.createNewFile();	
		
		mensagens = null;
		
		//toStringArquivo();
	}

	//escreve no arquivo
	public void toStringArquivo() {
		FileWriter fw;
		try {
			fw = new FileWriter(dadosDosUsuarios , true);
			PrintWriter out = new PrintWriter(fw);
			
			out.println("Usuario, " + nome + ", " + nomeDeUsuario + ", " + senha + ", "
					+ toStringSeguidores() + ", " + mensagens);
			//out.println("Usuario, nome=" + nome + ", nomeDeUsuario=" + nomeDeUsuario + ", senha=" + senha + ", seguidores="
					//+ toStringSeguidores());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public String toStringSeguidores() {
		String s = "";
		if (seguidores[0] == null)
			s = "0";
		else{
			for (int i = 0; i < seguidores.length; i++) {
				if (seguidores[i] == null)
					return s;
				else {
					if (i == seguidores.length - 1 || seguidores[i+1] == null)
						s += seguidores[i].nome;
					else if (i < seguidores.length - 1)
						s += seguidores[i].nome + " ";
				} 
			}
		}
		return s;
	}

	public void NovoSeguidor(Usuario seguidor) {
		for (int i = 0; i < seguidores.length; i++) {
			if (seguidores[i] == null) {
				seguidores[i] = seguidor;
				break;
			}
		}
		toStringArquivo();

	}

	public void NovoSeguidor(Usuario seguidor, boolean x) {
		for (int i = 0; i < seguidores.length; i++) {
			if (seguidores[i] == null) {
				seguidores[i] = seguidor;
				break;
			}
		}
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha() {
		System.out.print("Escolha uma nova senha:\n       👉 ");
		String novaSenha = teclado.nextLine();
		this.senha = novaSenha;
	}
	
	//verifica se a senha eh forte e metodo recursivo
	public void verificaSenha() {
		boolean senhaForte = true;
		//Verifica se contem letra maiuscula
		if (!this.senha.matches("(.*[A-Z].*)")){
			System.out.print("       😉 Ei! Cuidado! Senhas fortes geralmente contem pelo menos uma letra maiúscula. ");
            senhaForte = false;
			setSenha();
			verificaSenha();
            
        }
		//Verifica se contem letra minuscula
		else if (!this.senha.matches("(.*[a-z].*)")){
			System.out.print("       😉 Ei! Cuidado! Senhas fortes geralmente contem pelo menos uma letra minuscula. ");
            senhaForte = false;
			setSenha();
			verificaSenha();
        }
		//Verifica se contem caracteres especiais ou numeros
		else if (!this.senha.matches("(.*[0-9].*)") && (!this.senha.matches("(.*[@,#,$,%].*$)"))) {
			System.out.print("       😉 Ei! Cuidado! Senhas fortes geralmente contem pelo menos um numero ou caractere especial entre @#$%. ");
			senhaForte = false;
			setSenha();
			verificaSenha();
		}
		else if (senhaForte)
			;	
	}
	
	public String sair() {
		return "Usuario, " + nome + ", " + nomeDeUsuario + ", " + senha + ", "
					+ toStringSeguidores() + ", " + mensagens + "\n";
	}
			
	public void NovaMensagem(Usuario remetente, String mensagem) {
		mensagens = remetente.nome + " - " + mensagem;
	}
	
	public void verificaNovasMensagens(){
		String[] arrayDeMensagens;
		if(mensagens == null)
			System.out.println("\tVocê não possui novas mensagens");
		
		else if (mensagens != null){
			System.out.println("\tVocê possui novas mensagens\n");
			arrayDeMensagens = mensagens.split("-");
			System.out.println("\t" + arrayDeMensagens[0].trim() + " disse:");
			System.out.println("\t\t" + arrayDeMensagens[1].trim());
			
			mensagens = null;
		}
	}
	

	public abstract void NovaBusca(Usuario busca);
	public abstract void NovaBusca(Usuario busca, boolean x);
	
}
