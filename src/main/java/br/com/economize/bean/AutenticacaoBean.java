package br.com.economize.bean;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.economize.controller.ControladorAcessoUsuario;
import br.com.economize.dao.UsuarioDAO;
import br.com.economize.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

	public static final String USUARIO_SESSAO = "usuario";
	HttpSession sessao;

	private Usuario usuario;
	private Usuario usuarioLogado;

	private ControladorAcessoUsuario controladorAcesso;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public ControladorAcessoUsuario getControladorAcesso() {
		return controladorAcesso;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		controladorAcesso = new ControladorAcessoUsuario();
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuarioLogado = usuarioDAO.autenticar(usuario.getEmail(), usuario.getSenha(), usuario.getAtivo());

			if (usuarioLogado == null) {
				Messages.addGlobalError("Usuário Inválido ou Incorreto");
				return;
			}
			sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			sessao.setAttribute("USUARIO_SESSAO", usuarioLogado);

			controladorAcesso.configurarAcesso();

			String ultimoAcesso = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"))
					.format(new Date(sessao.getCreationTime()));

			System.out.println("Sessão iniciada: " + sessao.getId() + ". Acesso em: " + ultimoAcesso);

			Faces.redirect("./paginas/inicio.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}

	public void logout() throws IOException {
		String ultimoAcesso = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"))
				.format(new Date(sessao.getLastAccessedTime()));
		System.out.println("Sessão " + sessao.getId() + " expirada. Último Acesso em: " + ultimoAcesso);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		ec.redirect(ec.getRequestContextPath() + "/paginas/autenticacao.xhtml?faces-redirect=true");
	}
}