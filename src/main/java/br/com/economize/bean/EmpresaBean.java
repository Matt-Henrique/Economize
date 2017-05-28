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
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;
import org.primefaces.component.wizard.Wizard;
import org.primefaces.context.RequestContext;

import br.com.economize.dao.EmpresaDAO;
import br.com.economize.domain.Empresa;
import br.com.economize.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EmpresaBean implements Serializable {

	HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	Usuario usuario = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

	private Empresa empresa;
	private List<Empresa> empresas;
	private List<Empresa> filteredEmpresas;

	private boolean success;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<Empresa> getFilteredEmpresas() {
		return filteredEmpresas;
	}

	public void setFilteredEmpresas(List<Empresa> filteredEmpresas) {
		this.filteredEmpresas = filteredEmpresas;
	}

	@PostConstruct
	public void listar() {
		try {
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as empresas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			empresa = new Empresa();

			Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent("formCadastro:empresaWiz");
			wizard.setStep("tabEmp1");
			RequestContext.getCurrentInstance().update("formCadastro");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova empresa");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {

			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresaDAO.merge(empresa);

			empresas = empresaDAO.listar("nomeFantasia");

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('dialogo').hide()");
			}

			Messages.addGlobalInfo("Empresa salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar a empresa");
			erro.printStackTrace();
		}
	}

	public void salvarEdicao() {
		try {
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresaDAO.merge(empresa);

			empresas = empresaDAO.listar("nome");

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('edicao').hide()");
			}

			Messages.addGlobalInfo("Empresa editada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar a empresa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			empresa = (Empresa) evento.getComponent().getAttributes().get("empresaSelecionada");
			
			Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot()
					.findComponent("formCadastro:empresaWiz");
			wizard.setStep("tabEmp1");
			RequestContext.getCurrentInstance().update("formCadastro");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma empresa");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			empresa = (Empresa) evento.getComponent().getAttributes().get("empresaSelecionada");

			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresaDAO.excluir(empresa);

			empresas = empresaDAO.listar("nome");

			Messages.addGlobalInfo("Empresa removida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a empresa");
			erro.printStackTrace();
		}
	}
}