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
import br.com.economize.domain.Empresa;
import br.com.economize.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

	public static final String USUARIO_SESSAO = "usuario";
	HttpSession sessao;

	private Empresa empresa;
	private Empresa empresaLogada;

	private Usuario adm;
	private Usuario admLogado;

	private ControladorAcessoUsuario controladorAcesso;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Empresa getEmpresaLogada() {
		return empresaLogada;
	}

	public void setEmpresaLogada(Empresa empresaLogada) {
		this.empresaLogada = empresaLogada;
	}

	public Usuario getAdm() {
		return adm;
	}

	public void setAdm(Usuario adm) {
		this.adm = adm;
	}

	public Usuario getAdmLogado() {
		return admLogado;
	}

	public void setAdmLogado(Usuario admLogado) {
		this.admLogado = admLogado;
	}

	public ControladorAcessoUsuario getControladorAcesso() {
		return controladorAcesso;
	}

	@PostConstruct
	public void iniciar() {
		empresa = new Empresa();
		adm = new Usuario();

		controladorAcesso = new ControladorAcessoUsuario();
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario admLogado = usuarioDAO.autenticar(adm.getEmail(), adm.getSenha(), adm.getAtivo(),
					adm.getTipoUsuario());

			if (admLogado == null) {
				Messages.addGlobalError("Usuário Inválido ou Incorreto");
				return;
			}
			sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			sessao.setAttribute("USUARIO_SESSAO", admLogado);

			controladorAcesso.configurarAcesso();

			System.out.println("Sessão criada " + sessao.getId().toString());
			String ultimoAcesso = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"))
					.format(new Date(sessao.getCreationTime()));

			System.out.println("Sessão iniciada " + sessao.getId() + ". Ultimo Acesso = " + ultimoAcesso);

			Faces.redirect("./paginas/inicio.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}

	public void logout() throws IOException {
		String ultimoAcesso = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"))
				.format(new Date(sessao.getLastAccessedTime()));
		System.out.println("Sessão expirada " + sessao.getId() + ". Ultimo Acesso: " + ultimoAcesso);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();
		ec.redirect(ec.getRequestContextPath() + "/paginas/autenticacao.xhtml?faces-redirect=true");
	}
}