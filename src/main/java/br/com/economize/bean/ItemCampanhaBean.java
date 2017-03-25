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
import br.com.economize.dao.ItemCampanhaDAO;
import br.com.economize.dao.ProdutoDAO;
import br.com.economize.domain.Campanha;
import br.com.economize.domain.ItemCampanha;
import br.com.economize.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItemCampanhaBean implements Serializable {
	
	HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	Long campanha = (Long) sessao.getAttribute("CAMPANHA_SESSAO");
	
	private ItemCampanha itemCampanha;
	private List<ItemCampanha> itensCampanha;
	
	CampanhaDAO campanhaDAO = new CampanhaDAO();
	private List<Campanha> campanhas;
	
	private List<Produto> produtos;
	
	public ItemCampanha getItemCampanha() {
		return itemCampanha;
	}
	
	public void setItemCampanha(ItemCampanha itemCampanha) {
		this.itemCampanha = itemCampanha;
	}
	
	public List<ItemCampanha> getItensCampanha() {
		return itensCampanha;
	}
	
	public void setItensCampanha(List<ItemCampanha> itensCampanha) {
		this.itensCampanha = itensCampanha;
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
			ItemCampanhaDAO itemCampanhaDAO = new ItemCampanhaDAO();
			itensCampanha = itemCampanhaDAO.buscaItensPorCampanha(campanha);

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os itemCampanhas");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		try {
			itemCampanha = new ItemCampanha();
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo itemCampanha");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			ItemCampanhaDAO itemCampanhaDAO = new ItemCampanhaDAO();
			itemCampanha.getCampanha().setCodigo(campanha);
			itemCampanhaDAO.merge(itemCampanha);

			itemCampanha = new ItemCampanha();
			itensCampanha = itemCampanhaDAO.buscaItensPorCampanha(campanha);

			Messages.addGlobalInfo("Item salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o itemCampanha");
			erro.printStackTrace();
		}
	}
	
	public void adicionar(ActionEvent evento){
		Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		
		int achou = -1;
		for (int posicao = 0; posicao < itensCampanha.size(); posicao++) {
			if (itensCampanha.get(posicao).getProduto().equals(produto)) {
				achou = posicao;
			}
		}

		if (achou < 0) {		
		
		ItemCampanha itemCampanha = new ItemCampanha();
		itemCampanha.setProduto(produto);
		itemCampanha.setPrecoOferta(itemCampanha.getPrecoOferta());
		
		itensCampanha.add(itemCampanha);
		}
		else{
			@SuppressWarnings("unused")
			ItemCampanha itemCampanha = itensCampanha.get(achou);
		}
	}
}