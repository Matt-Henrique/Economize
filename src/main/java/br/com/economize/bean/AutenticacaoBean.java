package br.com.economize.bean;

/*import java.io.IOException;
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

import br.com.economize.controller.ControladorAcesso;
import br.com.economize.controller.ControladorAcessoEmpresa;
import br.com.economize.dao.AdministradorDAO;
import br.com.economize.dao.EmpresaDAO;
import br.com.economize.domain.Administrador;
import br.com.economize.domain.Empresa;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

	public static final String USUARIO_SESSAO = "usuario";
	HttpSession sessao;

	private Empresa empresa;
	private Empresa empresaLogada;

	private Administrador adm;
	private Administrador admLogado;

	private ControladorAcesso controladorAcesso;
	private ControladorAcessoEmpresa controladorFuncionario;

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

	public Administrador getAdm() {
		return adm;
	}

	public void setAdm(Administrador adm) {
		this.adm = adm;
	}

	public Administrador getAdmLogado() {
		return admLogado;
	}

	public void setAdmLogado(Administrador admLogado) {
		this.admLogado = admLogado;
	}

	public ControladorAcesso getControladorAcesso() {
		return controladorAcesso;
	}

	public ControladorAcessoEmpresa getControladorFuncionario() {
		return controladorFuncionario;
	}

	@PostConstruct
	public void iniciar() {
		empresa = new Empresa();
		adm = new Administrador();

		controladorAcesso = new ControladorAcesso();
		controladorFuncionario = new ControladorAcessoEmpresa();
	}

	public void autenticar() {
		try {
			EmpresaDAO empresaDAO = new EmpresaDAO();
			Empresa empresaLogada = empresaDAO.autenticar(empresa.getEmail(), empresa.getSenha(), empresa.getAtivo(),
					empresa.getTipoUsuario());

			if (empresaLogada == null) {
				Messages.addGlobalError("Usuário Inválido ou Incorreto");
				return;
			}
			sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			sessao.setAttribute("USUARIO_SESSAO", empresaLogada);

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

	public void autenticarAdm() {
		try {
			AdministradorDAO administradorDAO = new AdministradorDAO();
			Administrador admLogado = administradorDAO.autenticar(adm.getEmail(), adm.getSenha(), adm.getAtivo(),
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
}*/