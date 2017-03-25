package br.com.economize.bean;

/*import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.economize.dao.AdministradorDAO;
import br.com.economize.domain.Administrador;
import br.com.economize.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AdministradorPerfilBean implements Serializable {

	HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	Usuario usuario = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

	private Administrador administrador;
	private List<Administrador> administradores;

	private boolean success;

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	@PostConstruct
	public void listar() {
		try {
			AdministradorDAO administradorDAO = new AdministradorDAO();
			administradores = administradorDAO.buscaAdministradorLogado(usuario.getCodigo());

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os administradores");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			AdministradorDAO administradorDAO = new AdministradorDAO();
			administradorDAO.merge(administrador);

			administradores = administradorDAO.buscaAdministradorLogado(usuario.getCodigo());

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('pessoal').hide()");
			}

			Messages.addGlobalInfo("Administrador editado com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar o administrador");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			administrador = (Administrador) evento.getComponent().getAttributes().get("administradorSelecionado");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar um administrador");
		}
	}

	public void mudarSenha() {
		try {
			SimpleHash hash = new SimpleHash("md5", administrador.getSenhaSemCriptografia());
			administrador.setSenha(hash.toHex());

			AdministradorDAO administradorDAO = new AdministradorDAO();
			administradorDAO.merge(administrador);

			administradores = administradorDAO.buscaAdministradorLogado(usuario.getCodigo());

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('senha').hide()");
			}

			Messages.addGlobalInfo("Senha alterada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar alterar a senha");
			erro.printStackTrace();
		}
	}
}*/