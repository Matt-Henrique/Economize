package br.com.economize.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.economize.dao.CampanhaDAO;
import br.com.economize.domain.Campanha;
import br.com.economize.domain.Empresa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CampanhaBean implements Serializable {
	
	HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

	private Campanha campanha;
	private List<Campanha> campanhas;	
	
	private List<Empresa> empresas;
	
	public Campanha getCampanha() {
		return campanha;
	}
	
	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}
	
	public List<Campanha> getCampanhas() {
		return campanhas;
	}
	
	public void setCampanhas(List<Campanha> campanhas) {
		this.campanhas = campanhas;
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
			CampanhaDAO campanhaDAO = new CampanhaDAO();
			campanhas = campanhaDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as campanhas");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			campanha = new Campanha();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova campanha");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CampanhaDAO campanhaDAO = new CampanhaDAO();
			campanha.setVigencia(2);
			campanha.setTipo("2");
			campanhaDAO.merge(campanha);
			
			campanhas = campanhaDAO.listar();// atualiza a lista de empresas

			Messages.addGlobalInfo("Campanha salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a empresa");
			erro.printStackTrace();
		}
	}
	
	public void adcionarItem(ActionEvent evento) throws IOException {
		try {
			sessao.setAttribute("CAMPANHA_SESSAO", evento.getComponent().getAttributes().get("campanhaSelecionada"));

			Faces.redirect("./paginas/itemCampanha.xhtml");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao carregar a tela de itens da campanha");
			erro.printStackTrace();
		}
	}
}