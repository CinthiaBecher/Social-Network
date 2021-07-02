import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class RedeSocial {
	Usuario[] usuarios = new Usuario[20];
	int numUsuarios = 0;
	
	String[] seguidores = new String[20];
	int numSeguidores = 0;
	
	String[] buscas = new String[20];
	int numBusca = 0;
	
	String[] mensagens = new String[20];
	int numMensagens = 0;
	
	Scanner teclado = new Scanner(System.in);
	
	public RedeSocial() {
				
		leituraDoArquivo();
		addSeguidores();
		addBusca();
		addMensagens();
	}

	public void leituraDoArquivo() {
		FileReader fw;
		BufferedReader br;
				
		try {
			fw = new FileReader("Dados dos Usuarios");
			br = new BufferedReader(fw);
			
			//descobre numero de linhas
			int linhas = (int) br.lines().count();
			br.close();
			
			fw = new FileReader("Dados dos Usuarios");
			br = new BufferedReader(fw);
			
			for (int i = 0; i < linhas; i++) {
				String line = br.readLine();
				String dados[] = line.split(", ");
							
				if (dados[0].equals("UsuarioPremium")) {
					usuarios[numUsuarios++] = new UsuarioPremium(dados[1],dados[2], dados[3], false);
					seguidores[numSeguidores++] = dados[4];
					if((dados[5].equals("0")))
						buscas[numBusca++] = "0";
					
					else if(!(dados[5].equals("0")))
						buscas[numBusca++] = dados[5];
					
					if((dados[6].equals("null")))
						mensagens[numMensagens++] = "null";
					
					else if(!(dados[6].equals("null")))
						mensagens[numMensagens++] = dados[6];
					
				}
				else if (dados[0].equals("Usuario")) {
					usuarios[numUsuarios++] = new UsuarioNormal(dados[1],dados[2], dados[3], false);
					seguidores[numSeguidores++] = dados[4];
					buscas[numBusca++] = "0";	
					
					if((dados[5].equals("null")))
						mensagens[numMensagens++] = "null";
					
					else if(!(dados[5].equals("null")))
						mensagens[numMensagens++] = dados[5];
				}
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addSeguidores() {
		for (int i = 0; i < usuarios.length; i++) {
			//adiciona seguidores
			String segui = seguidores[i];
			if(segui==null)
				;
			else if (segui!=null) {
				if (!segui.equals("0")) {
					String[] arrayDeSeguidores = segui.split("\\s");
					for (String string : arrayDeSeguidores) {
						for (Usuario usuario : usuarios) {
							if (usuario !=null) {
								if((usuario.nome).equals(string))
									usuarios[i].NovoSeguidor(usuario,false);
								}
						}
					}
				}
			}
		}
	}
	
	public void addBusca() {
		
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] instanceof UsuarioPremium) {
				//adiciona seguidores
				String busca = buscas[i];
				
				if(busca==null)
					;
				else if (busca!=null) {
					if (!busca.equals("0")) {
						String[] arrayDeBuscas = busca.split("\\s");
						for (String string : arrayDeBuscas) {
							for (Usuario usuario : usuarios) {
								if (usuario !=null) {
									if((usuario.nome).equals(string))
										usuarios[i].NovaBusca(usuario, false);
									}
							}
						}
					}
				}
		}
			}
	}

	public void addMensagens() {
		for (int i = 0; i < usuarios.length; i++) {
			String mensagem = mensagens[i];
			String[] arrayDeMensagens;
			if (mensagem != null) {
				if (!(mensagem.equals("null"))) {
					arrayDeMensagens = mensagem.split("-");
					
					for (Usuario usuario : usuarios) {
						if (usuario != null) {
							if ((arrayDeMensagens[0].trim()).equals(usuario.nome)) {
								usuarios[i].NovaMensagem(usuario, arrayDeMensagens[1].trim());
							}
						}
					}
				}
			}
		
		}
	}
	
	//faz cadastro de novo usuario
	public Usuario CadastroNovoUsuario() throws IOException{
		System.out.print("    ✋ Primeiro de tudo, você gostaria de ser Usuario Premium?\n       👉 ");
		String premium = teclado.nextLine().trim();
		
		System.out.print("\n    😎 Legal, Agora digite seu nome?\n       👉 ");
		String nome = teclado.nextLine().trim();
		
		System.out.print("\n    😜 Show, " + nome + "! Agora digite um nome de usuario:\n       👉 ");
		String nomeDeUsuario = teclado.nextLine().trim();
		
		//metodo verifica se o nome de usuario nao eh existente
		while (!verificaUsuario(nomeDeUsuario)) {
			System.out.print("       ☹ Poxa, esse nome de usuario ja existe! Digite um novo:\n       👉 ");
			nomeDeUsuario = teclado.nextLine().trim();
		}
		
		System.out.print("\n    😎 Escolha uma senha:\n       👉 ");
		String senha = teclado.nextLine().trim();
		
		Usuario novoUsuario;
		if (premium.equals("sim")) {
			novoUsuario = new UsuarioPremium(nome, nomeDeUsuario, senha);
			return novoUsuario;
		}
		novoUsuario = new UsuarioNormal(nome, nomeDeUsuario, senha);
		return novoUsuario;
	}
	
	//faz login de usuario existente
	public Usuario login() throws IOException{
		System.out.print("    😎 Digite seu nome de Usuario:\n       👉 ");
		String nomeDeUsuario = teclado.nextLine();
		
		//metodo que verifica se o usuario existe
		if(verificaUsuario(nomeDeUsuario)) {
			System.out.print("     ☹ Opa! Nome de Usuario nao encontrado.\n        1 | Entrar\n        2 | Cadastre-se\n        👉 ");
			int opcao = Integer.parseInt(teclado.nextLine());
			if (opcao == 1)
				login();
			else if (opcao == 2)
				CadastroNovoUsuario();
		}
			
		System.out.print("     😎 Digite sua senha:\n       👉 ");
		String senha = teclado.nextLine();
		
		//verifica se as senhas sao iguais
		while(!verificaSenha(nomeDeUsuario, senha)) {
			System.out.print("       🤨 Vish, senha Incorreta! Tente novamente:\n       👉 ");
			senha = teclado.nextLine();
			verificaSenha(nomeDeUsuario, senha);
		}
		
		for (Usuario user : usuarios) {
			if(user.nomeDeUsuario.equals(nomeDeUsuario)) return user;
		}
		return null;
	}

	//verifica se o nome do usuario ja eh existente
	public boolean verificaUsuario(String nomeDeUsuario) {
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null)
				if (usuarios[i].nomeDeUsuario.equals(nomeDeUsuario)) return false;		
			}
		return true;
		}

	//verifica se a senha eh compativel
	public boolean verificaSenha(String nomeDeUsuario, String senha) {
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null) {
				if (usuarios[i].nomeDeUsuario.equals(nomeDeUsuario)  && usuarios[i].getSenha().equals(senha))
					return true;	
				}
		}
		return false;
	}

	public void printUsersOnFeed() {
		System.out.println("------------------------------------------\n\t😃 USUARIOS ATIVOS 😃\n");

		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null)
				System.out.println("\t\t" + usuarios[i].nome + "\t\t");
		}	
	}

	public Usuario BuscaUsuario(Usuario usuarioAtivo) {
		System.out.print("\tDigite o nome do usuário:\n       👉 ");
		String nomeDoUsuario = teclado.nextLine();
		
		bubbleSort(usuarios);
		int posicao = pesquisaBinaria(usuarios, nomeDoUsuario);
		if (posicao != -1)
			return usuarios[posicao];
		return null;
		
		
		
	}
	
	public void bubbleSort(Usuario[] usuarios2) { 
		int i = usuarios2.length-1;
		while (i > 0) {
			int lastFlipped = 0;
			for (int j = 0; j < i; j++) { 
				if (usuarios[j] == null || usuarios[j+1] == null)
					;
				else 
					if (usuarios[j].nome.compareTo(usuarios[j+1].nome) > 0) { // troca par de posição
					Usuario T = usuarios2[j]; 
					usuarios2[j] = usuarios2[j+1]; 
					usuarios2[j+1] = T; 
					lastFlipped = j; 
				}
			}
			i = lastFlipped;
		}
	}
	
	public int pesquisaBinaria(Usuario[] usuarios2, String nome) {
		Usuario[] usuarios3 = new Usuario[numUsuarios];
		for (int i = 0; i < usuarios.length; i++) {
			if (!(usuarios[i] == null))
				usuarios3[i] = usuarios[i];
		}
		
		int inf = 0;   int sup = usuarios3.length - 1;
		while (inf <= sup) {
			int med = (inf + sup) / 2; // divisão inteira
			if (nome.compareTo(usuarios3[med].nome) == 0 )
				return med;
			else if (nome.compareTo(usuarios3[med].nome) < 0) 	
				sup = med - 1; // procura na 1a. metade
			else
				inf = med + 1; // procura na 2a. metade
		}
		return -1;
}

	public void enviaMensagem(String mensagem, Usuario remetente, Usuario destinatario) {
		
	}
	
	public void sair(Usuario usuarioAtivo) {
		if (pesquisaBinaria(usuarios,  usuarioAtivo.nome) == -1)
			usuarios[numSeguidores] = usuarioAtivo;
		String dados = "";
		for (Usuario usuario : usuarios) {
			if (usuario != null)
				dados += usuario.sair();
		}
		
		FileWriter fw;
		try {
			fw = new FileWriter("Dados dos Usuarios" , false);
			PrintWriter out = new PrintWriter(fw);
			
			out.println(dados);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
} 