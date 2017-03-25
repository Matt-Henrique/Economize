package br.com.economize.bean;

import java.io.Serializable;
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

import br.com.economize.dao.EmpresaDAO;
import br.com.economize.domain.Empresa;
import br.com.economize.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EmpresaPerfilBean implements Serializable {

	HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	Usuario usuario = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

	private Empresa empresa;
	private List<Empresa> empresas;

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

	@PostConstruct
	public void listar() {
		try {
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresas = empresaDAO.buscaEmpresaLogada(usuario.getCodigo());

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as empresas");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresaDAO.merge(empresa);

			empresas = empresaDAO.buscaEmpresaLogada(usuario.getCodigo());

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('pessoal').hide()");
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

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar uma empresa");
		}
	}

	public void mudarSenha() {
		try {
			SimpleHash hash = new SimpleHash("md5", empresa.getSenhaSemCriptografia());
			empresa.setSenha(hash.toHex());

			EmpresaDAO empresaDAO = new EmpresaDAO();
			empresaDAO.merge(empresa);

			empresas = empresaDAO.buscaEmpresaLogada(usuario.getCodigo());

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('senha').hide()");
			}

			Messages.addGlobalInfo("Senha alterada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar alterar a senha");
			erro.printStackTrace();
		}
	}
}