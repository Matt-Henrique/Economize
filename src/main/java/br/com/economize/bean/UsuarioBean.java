package br.com.economize.bean;

/**
* @author Mateus Henrique Tofanello
* 
*/

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.economize.dao.EmpresaDAO;
import br.com.economize.dao.UsuarioDAO;
import br.com.economize.domain.Empresa;
import br.com.economize.domain.Usuario;
import br.com.economize.enumerate.Ativo;
import br.com.economize.mail.MailJavaMessage;
import br.com.economize.mail.MailJavaMessageSender;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Usuario> filteredAdms;

	EmpresaDAO empresaDAO = new EmpresaDAO();
	private List<Empresa> empresas = empresaDAO.listar();

	private boolean success;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getFilteredAdms() {
		return filteredAdms;
	}

	public void setFilteredAdms(List<Usuario> filteredAdms) {
		this.filteredAdms = filteredAdms;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuarios");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			usuario = new Usuario();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo usuario");
			erro.printStackTrace();
		}
	}

	public void salvar(String senha) {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
					"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B",
					"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
					"W", "X", "Y", "Z" };
			senha = "";
			for (int x = 0; x < 10; x++) {
				int j = (int) (Math.random() * carct.length);
				senha += carct[j];
			}
			SimpleHash hash = new SimpleHash("md5", senha);

			usuario.setSenha(hash.toHex());
			usuario.setAtivo(Ativo.SIM);

			usuarioDAO.merge(usuario);

			enviarMensagem(senha, this.usuario);

			usuarios = usuarioDAO.listar("nome");

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('dialogo').hide()");
			}

			Messages.addGlobalInfo("Usuario salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o usuario");
			erro.printStackTrace();
		}
	}

	public void salvarEdicao() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			usuarios = usuarioDAO.listar("nome");

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('edicao').hide()");
			}

			Messages.addGlobalInfo("Usuario editado com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar o usuario");
			erro.printStackTrace();
		}
	}

	public void salvarStatus() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			usuarios = usuarioDAO.listar("nome");

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('status').hide()");
			}

			Messages.addGlobalInfo("Status editado com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar o status");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um funcionário");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);

			usuarios = usuarioDAO.listar("nome");

			Messages.addGlobalInfo("Usuario removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o funcionário");
			erro.printStackTrace();
		}
	}

	public void mudarSenha() {
		try {
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
			usuario.setSenha(hash.toHex());

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			usuarios = usuarioDAO.listar("nome");

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('senha').hide()");
			}

			Messages.addGlobalInfo("Usuario salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o funcionário");
			erro.printStackTrace();
		}
	}

	// Envia email para o profissional com a nova senha.
	public void enviarMensagem(String senha, Usuario usuario) {
		new Thread() {
			@Override
			public void run() {
				MailJavaMessage mj = new MailJavaMessage("Cadastro Efetuado com Sucesso");
				mj.setBodyMail(htmlMessage(senha, usuario));
				// Pega o email do profissional
				mj.map.put(usuario.getEmail(), "");
				// Lista com os destinátario
				mj.setToMailsUsers(mj.map);

				try {
					new MailJavaMessageSender().senderMail(mj);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	//Corpo do email.
	private static String htmlMessage(String senha, Usuario usuario) {
		return
		"<html>" +
		"<style>" +
		/* ------------------------------------- 
				GERAL
		------------------------------------- */
		"body {" +
			"-webkit-font-smoothing: antialiased;" +
			"-webkit-text-size-adjust: none;" +
			"width: 100% !important;" +
			"height: 100%;" +
		"}" +
			
		".collapse {" +
			"margin: 0;" +
			"padding: 0;" +
		"}" +
		/* ------------------------------------- 
				ELEMENTS 
		------------------------------------- */	
		".btn {" +
			"text-decoration: none;" +
			"color: #FFF;" +
			"background-color: #666;" +
			"border:none;" +
			"padding: 10px 16px;" +
			"font-size: 15px;" +
			"font-weight: bold;" +
			"margin-right: 10px;" +
			"text-align: center;" +
			"cursor: pointer;" +
			"display: inline-block;" +
		"}" +
		
		"p.callout {" +
			"padding: 15px;" +
			"background-color: #ECF8FF;" +
			"margin-bottom: 15px;" +
		"}" +

		".callout a {" +
			"font-weight: bold;" +
			"color: #2BA6CB;" +
		"}" +
		/* ------------------------------------- 
				HEADER 
		------------------------------------- */	
		"table.head-wrap {" +
			"width: 100%;" +
		"}" +

		".header.container table td.logo {" +
			"padding: 15px;" +
		"}" +

		".header.container table td.label {" +
			"padding: 15px;" +
			"padding-left: 0px;" +
		"}" +
		/* ------------------------------------- 
				TYPOGRAPHY 
		------------------------------------- */
		"h1, h2, h3, h4, h5, h6 {" +
			"font-family: 'HelveticaNeue-Light', 'Helvetica Neue Light'," +
				"'Helvetica Neue', Helvetica, Arial, 'Lucida Grande', sans-serif;" +
			"line-height: 1.1;" +
			"margin-bottom: 15px;" +
			"color: #000;" +
		"}" +

		"h1 {" +
			"font-weight: 200;" +
			"font-size: 44px;" +
		"}" +
			
		"h3 {" +
			"font-weight: 500;" +
			"font-size: 27px;" +
		"}" +

		"h6 {" +
			"font-weight: 900;" +
			"font-size: 14px;" +
			"text-transform: uppercase;" +
			"color: #444;" +
		"}" +
			
		".collapse {" +
			"margin: 0 !important;" +
		"}" +

		"p.lead {" +
			"font-size: 17px;" +
		"}" +
		/* -------------------------------------
				RESPONSIVENESS
		------------------------------------- */
		".content {" +
			"padding: 15px;" +
			"max-width: 600px;" +
			"margin: 0 auto;" +
			"display: block;" +
		"}" +
			
		".content table {" +
			"width: 100%;" +
		"}" +

		".column {" +
			"width: 300px;" +
			"float: left;" +
		"}" +

		".column tr td {" +
			"padding: 15px;" +
		"}" +

		".column-wrap {" +
			"padding: 0 !important;" +
			"margin: 0 auto;" +
			"max-width: 600px !important;" +
		"}" +

		".column table {" +
			"width: 100%;" +
		"}" +
		"</style>" +
		"<body>" +
			"<div class='content'>" +
				"<table class='head-wrap' bgcolor='#FFF'>" +
					"<tr>" +
						"<td></td>" +
						"<td class='header container'>" +
							"<div class='content'>" +
								"<table bgcolor='#FFF'>" +
									"<tr>" +
										"<td><img src='http://localhost:8080/Economize/resources/imagens/econologo.png' width='200' height='75' /></td>" +
										"<td align='right'><h6 class='collapse'>Divulgação " +
												"de Ofertas</h6></td>" +
									"</tr>" +
								"</table>" +
							"</div>" +
						"</td>" +
						"<td></td>" +
					"</tr>" +
				"</table>" +
				"<section class='informacao'>" +
					"<h3>Cadastro efetuado com sucesso</h3>" +
					"</br>" +
					"<p class='lead'>" +usuario.getNome().split(" ")[0]+ ", sua senha para login é: <b>" + senha + "</b></p>" +
					"</br>" +
					"</br>" +
					"<a href='http://localhost:8080/Economize'><button class='btn'>Entre no Sistema</button></a>" +
				"</section>"+
			"</div>"+
		"</body>";
	}
}