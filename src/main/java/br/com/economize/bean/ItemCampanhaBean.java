package br.com.economize.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;

import br.com.economize.dao.CampanhaDAO;
import br.com.economize.dao.ItensCampanhaDAO;
import br.com.economize.dao.ProdutoDAO;
import br.com.economize.domain.Campanha;
import br.com.economize.domain.ItensCampanha;
import br.com.economize.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemCampanhaBean implements Serializable {

	HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	Long campanha = (Long) sessao.getAttribute("CAMPANHA_SESSAO");

	private ItensCampanha itensCampanha;
	private List<ItensCampanha> itensCampanhas;

	CampanhaDAO campanhaDAO = new CampanhaDAO();
	private List<Campanha> campanhas;

	private List<Produto> produtos;

	public ItensCampanha getItensCampanha() {
		return itensCampanha;
	}

	public void setItensCampanha(ItensCampanha itensCampanha) {
		this.itensCampanha = itensCampanha;
	}

	public List<ItensCampanha> getItensCampanhas() {
		return itensCampanhas;
	}

	public void setItensCampanhas(List<ItensCampanha> itensCampanhas) {
		this.itensCampanhas = itensCampanhas;
	}

	public List<Campanha> getCampanhas() {
		return campanhas;
	}

	public void setCampanhas(List<Campanha> campanhas) {
		this.campanhas = campanhas;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@PostConstruct
	public void listar() {
		try {
			ItensCampanhaDAO itensCampanhaDAO = new ItensCampanhaDAO();
			itensCampanhas = itensCampanhaDAO.buscaItensPorCampanha(campanha);

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os itensCampanhas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			itensCampanha = new ItensCampanha();

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo itensCampanha");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ItensCampanhaDAO itensCampanhaDAO = new ItensCampanhaDAO();
			itensCampanha.getCampanha().setCodigo(campanha);
			itensCampanhaDAO.merge(itensCampanha);

			itensCampanha = new ItensCampanha();
			itensCampanhas = itensCampanhaDAO.buscaItensPorCampanha(campanha);

			Messages.addGlobalInfo("Item salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o itensCampanha");
			erro.printStackTrace();
		}
	}

	public void adicionar(ActionEvent evento) {
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

		int achou = -1;
		for (int posicao = 0; posicao < itensCampanhas.size(); posicao++) {
			if (itensCampanhas.get(posicao).getProdutos().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {

			ItensCampanha itensCampanha = new ItensCampanha();
			itensCampanha.setProdutos(produtos);

			itensCampanhas.add(itensCampanha);
		} else {
			itensCampanha = itensCampanhas.get(achou);
		}
	}
}