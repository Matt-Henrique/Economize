package br.com.economize.bean;

/**
* @author Mateus Henrique Tofanello
* 
*/

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

import br.com.economize.dao.EmpresaDAO;
import br.com.economize.dao.UsuarioDAO;
import br.com.economize.domain.Empresa;
import br.com.economize.domain.Usuario;
import br.com.economize.enumerate.Ativo;

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

			Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot().findComponent("formCadastro:w");
			wizard.setStep("tabAdm1");
			RequestContext.getCurrentInstance().update("formCadastro");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo usuario");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {

			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
			usuario.setSenha(hash.toHex());

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuario.setAtivo(Ativo.SIM);
			usuarioDAO.merge(usuario);

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
}