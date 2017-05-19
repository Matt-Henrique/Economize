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
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;

import br.com.economize.dao.ItensCampanhaDAO;
import br.com.economize.dao.ProdutoDAO;
import br.com.economize.domain.ItemCampanha;
import br.com.economize.domain.Produto;
import br.com.economize.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ItensCampanhaBean implements Serializable {

	HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	Usuario usuario = (Usuario) sessao.getAttribute("USUARIO_SESSAO");
	Long campanha = (Long) sessao.getAttribute("CAMPANHA_SESSAO");

	private ItemCampanha itemCampanha;
	private List<ItemCampanha> itensCampanha;

	ProdutoDAO produtoDAO = new ProdutoDAO();
	private List<Produto> produtos = produtoDAO.buscaProdutoPorEmpresa(usuario.getEmpresa().getCodigo());

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
			itensCampanha = itensCampanhaDAO.buscaItemPorCampanha(campanha);

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.buscaProdutoPorEmpresa(usuario.getEmpresa().getCodigo());

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			itemCampanha = new ItemCampanha();

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.buscaProdutoPorEmpresa(usuario.getEmpresa().getCodigo());

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo itensCampanha");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ItensCampanhaDAO itensCampanhaDAO = new ItensCampanhaDAO();
			itemCampanha.getCampanha().setCodigo(campanha);
			itensCampanhaDAO.merge(itemCampanha);

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.buscaProdutoPorEmpresa(usuario.getEmpresa().getCodigo());

			itensCampanha = itensCampanhaDAO.buscaItemPorCampanha(campanha);

			Messages.addGlobalInfo("Campanha salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a venda");
			erro.printStackTrace();
		}
	}
}