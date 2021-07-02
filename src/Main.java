import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.Scanner;

public class Main {
	
	static Scanner teclado = new Scanner(System.in);
	static Usuario usuarioAtivo = null;
	
	public static void main(String[] args) {
		
		RedeSocial r = new RedeSocial();
		
		inicializando(r);
		menuDeAtividades(r);
		
	}
	
	public static void inicializando(RedeSocial r) {
		try {
			System.out.println("------------------------------------------\n\t"
					+ "😃 LEGAL TE VER AQUI! 😃\n");
			System.out.print("\t    1 | Cadastre-se\n\t    2 | Entre\n\n\t👉 ");
			int resposta = Integer.parseInt(teclado.nextLine());
			System.out.println();
			if (resposta==1) {
				System.out.println("\tSEJA BEM-VINDO(A)!\n");
				usuarioAtivo = r.CadastroNovoUsuario();
				
				r.printUsersOnFeed();
			}
			else if(resposta == 2) {
				usuarioAtivo = r.login();
				r.printUsersOnFeed();
			}
			else{ 
				throw new MinhaExcecao("Número inválido!");
			}
		}catch (MinhaExcecao e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
		
	public static void menuDeAtividades(RedeSocial r) {
		try {
	    	Thread.sleep(2000);
	    } catch (Exception e) {}
		System.out.println("------------------------------------------\n   😃 O QUE VOCÊ GOSTARIA DE FAZER? 😃\n");
		try {
			if(usuarioAtivo instanceof UsuarioNormal)
				menuDeAtividadesNormal(r);
			else 
				menuDeAtividadesPremium(r);
		}catch (MinhaExcecao e) {
			e.toString();
			menuDeAtividades(r);
		}
	}
	
	public static void menuDeAtividadesNormal(RedeSocial r) throws MinhaExcecao {
		System.out.print("\t    1 | Buscar usuario\n\t    "
				+ "2 | Listar meus seguidores\n\t    "
				+ "3 | Enviar uma mensagem\n\t    "
				+ "4 | Verificar novas mensagens\n\t    "
				+ "5 | Seguir novo usuario\n\t    "
				+ "6 | Sair\n\n\t👉 ");
		
			String resposta = teclado.nextLine();
		
	
			Usuario buscado = null;
			if (resposta.equals("1")) {
				opcao1(buscado, r);
			}
			else if (resposta.equals("2")) {
				System.out.println("\tSeus Seguidores:\n\t" + usuarioAtivo.toStringSeguidores());
				menuDeAtividades(r);
			}
			else if (resposta.equals("3")) {
				opcao3(r);
			}
			else if (resposta.equals("4")) {
				usuarioAtivo.verificaNovasMensagens();
				
				menuDeAtividades(r);
			}
			else if (resposta.equals("5")) {
				Usuario userASerSeguido = r.BuscaUsuario(usuarioAtivo);
				userASerSeguido.NovoSeguidor(usuarioAtivo, false);
				
				System.out.println("\tVocê está seguindo " + userASerSeguido.nome);
				menuDeAtividades(r);
			}
			else if (resposta.equals("6")) r.sair(usuarioAtivo);
			
			else throw new MinhaExcecao("Número inválido!");
	}
	
	public static void menuDeAtividadesPremium(RedeSocial r) throws MinhaExcecao {
		System.out.print("\t    1 | Buscar usuario\n\t    "
				+ "2 | Listar meus seguidores\n\t    "
				+ "3 | Enviar uma mensagem\n\t    "
				+ "4 | Verificar novas mensagens\n\t    "
				+ "5 | Seguir novo usuario\n\t    "
				+ "6 | Verificar novas buscas\n\t    "
				+ "7 | Sair\n\n\t👉 ");
		
			String resposta = teclado.nextLine();
		
	
			Usuario buscado = null;
			if (resposta.equals("1")) {
				opcao1(buscado, r);
			}
			else if (resposta.equals("2")) {
				System.out.println("\tSeus Seguidores:\n\t" + usuarioAtivo.toStringSeguidores());
				menuDeAtividades(r);
			}
			else if (resposta.equals("3")) {
				opcao3(r);
			}
			else if (resposta.equals("4")) {
				usuarioAtivo.verificaNovasMensagens();
			
				menuDeAtividades(r);
			}
			else if (resposta.equals("5")) {
				Usuario userASerSeguido = r.BuscaUsuario(usuarioAtivo);
				userASerSeguido.NovoSeguidor(usuarioAtivo, false);
				
				System.out.println("\tVocê está seguindo " + userASerSeguido.nome);
				menuDeAtividades(r);
			}
			else if (resposta.equals("6")) {
				System.out.println("\tQuem pesquisou seu perfil:\n\t" + ((UsuarioPremium) usuarioAtivo).toStringBusca());
			
				menuDeAtividades(r);
			}
			else if (resposta.equals("7")) r.sair(usuarioAtivo);
			
			else throw new MinhaExcecao("Número inválido!");
	}
	
	public static void opcao1(Usuario buscado, RedeSocial r) {
		buscado = r.BuscaUsuario(usuarioAtivo);
		if (buscado == null) {
			System.out.println("\tUsuário não encontrado");
			menuDeAtividades(r);
		}
		else {
			if (buscado instanceof UsuarioPremium)
				buscado.NovaBusca(usuarioAtivo,false);
			System.out.print("\t    1 | Seguir\n\t    "
				+ "2 | Enviar mensagem\n\t    "
				+ "3 | Voltar\n\n\t👉 ");
			String resposta2 = teclado.nextLine();
			if(resposta2.equals("1")) {
				//System.out.println(usuarioAtivo);
				buscado.NovoSeguidor(usuarioAtivo,false);
				//System.out.println(buscado);
			
				System.out.println("\tVocê está seguindo " + buscado.nome);
				menuDeAtividades(r);
			}
			else if(resposta2.equals("2")) {
				System.out.print("\tDigite sua mensagem:\n\t👉 ");
				String mensagem = teclado.nextLine();
				buscado.NovaMensagem(usuarioAtivo, mensagem);
				
				System.out.println("\tMensagem Enviada");
				menuDeAtividades(r);					
				}
			else
				menuDeAtividades(r);
			}
	}

	public static void opcao3(RedeSocial r) {
		Usuario userDestinatario = r.BuscaUsuario(usuarioAtivo);
		if (userDestinatario == null) {
			System.out.println("\tUsuário não encontrado");
			menuDeAtividades(r);
		}
		else if (userDestinatario != null) {
			System.out.print("\tDigite sua mensagem:\n\t👉 ");
			String mensagem = teclado.nextLine();
			userDestinatario.NovaMensagem(usuarioAtivo, mensagem);
	
			System.out.println("\tMensagem Enviada");
			menuDeAtividades(r);
		}
	}
}