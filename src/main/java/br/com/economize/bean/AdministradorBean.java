package br.com.economize.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;
import org.primefaces.component.wizard.Wizard;
import org.primefaces.context.RequestContext;

import br.com.economize.dao.AdministradorDAO;
import br.com.economize.domain.Administrador;
import br.com.economize.enumerate.Ativo;
import br.com.economize.enumerate.TipoUsuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AdministradorBean implements Serializable {

	private Administrador administrador;
	private List<Administrador> administradores;
	private List<Administrador> filteredAdms;

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

	public List<Administrador> getFilteredAdms() {
		return filteredAdms;
	}

	public void setFilteredAdms(List<Administrador> filteredAdms) {
		this.filteredAdms = filteredAdms;
	}

	@PostConstruct
	public void listar() {
		try {
			AdministradorDAO administradorDAO = new AdministradorDAO();
			administradores = administradorDAO.listar("nome");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os administradores");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			administrador = new Administrador();

			Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot().findComponent("formCadastro:w");
			wizard.setStep("tabAdm1");
			RequestContext.getCurrentInstance().update("formCadastro");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo administrador");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {

			SimpleHash hash = new SimpleHash("md5", administrador.getSenhaSemCriptografia());
			administrador.setSenha(hash.toHex());

			AdministradorDAO administradorDAO = new AdministradorDAO();
			administrador.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
			administrador.setAtivo(Ativo.SIM);
			administradorDAO.merge(administrador);

			administradores = administradorDAO.listar("nome");

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('dialogo').hide()");
			}

			Messages.addGlobalInfo("Administrador salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o administrador");
			erro.printStackTrace();
		}
	}

	public void salvarEdicao() {
		try {
			AdministradorDAO administradorDAO = new AdministradorDAO();
			administradorDAO.merge(administrador);

			administradores = administradorDAO.listar("nome");

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('edicao').hide()");
			}

			Messages.addGlobalInfo("Administrador editado com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar o administrador");
			erro.printStackTrace();
		}
	}

	public void salvarStatus() {
		try {
			AdministradorDAO administradorDAO = new AdministradorDAO();
			administradorDAO.merge(administrador);

			administradores = administradorDAO.listar("nome");

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
			administrador = (Administrador) evento.getComponent().getAttributes().get("administradorSelecionado");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um funcionário");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			administrador = (Administrador) evento.getComponent().getAttributes().get("administradorSelecionado");

			AdministradorDAO administradorDAO = new AdministradorDAO();
			administradorDAO.excluir(administrador);

			administradores = administradorDAO.listar("nome");

			Messages.addGlobalInfo("Administrador removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o funcionário");
			erro.printStackTrace();
		}
	}

	public void mudarSenha() {
		try {
			SimpleHash hash = new SimpleHash("md5", administrador.getSenhaSemCriptografia());
			administrador.setSenha(hash.toHex());

			AdministradorDAO administradorDAO = new AdministradorDAO();
			administradorDAO.merge(administrador);

			administradores = administradorDAO.listar("nome");

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('senha').hide()");
			}

			Messages.addGlobalInfo("Administrador salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o funcionário");
			erro.printStackTrace();
		}
	}
}