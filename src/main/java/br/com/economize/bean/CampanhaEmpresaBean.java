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
import br.com.economize.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CampanhaEmpresaBean implements Serializable {
	
	HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	Usuario usuario = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

	private Campanha campanha;
	private Campanha selectedCampanha;

	CampanhaDAO campanhaDAO = new CampanhaDAO();
	private List<Campanha> campanhas = campanhaDAO.buscaCampanhaPorEmpresa(usuario.getEmpresa().getCodigo());
		
	public Campanha getCampanha() {
		return campanha;
	}
	
	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}
	
	public Campanha getSelectedCampanha() {
		return selectedCampanha;
	}
	
	public void setSelectedCampanha(Campanha selectedCampanha) {
		this.selectedCampanha = selectedCampanha;
	}
	
	public List<Campanha> getCampanhas() {
		return campanhas;
	}
	
	public void setCampanhas(List<Campanha> campanhas) {
		this.campanhas = campanhas;
	}
	
	@PostConstruct
	public void listar() {
		try {
			CampanhaDAO campanhaDAO = new CampanhaDAO();
			campanhas = campanhaDAO.buscaCampanhaPorEmpresa(usuario.getEmpresa().getCodigo());

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
			campanhaDAO.merge(campanha);
			
			campanhas = campanhaDAO.buscaCampanhaPorEmpresa(usuario.getEmpresa().getCodigo());

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