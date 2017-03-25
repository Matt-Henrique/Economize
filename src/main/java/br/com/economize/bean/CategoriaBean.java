package br.com.economize.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.economize.dao.CategoriaDAO;
import br.com.economize.domain.Categoria;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CategoriaBean implements Serializable {

	private Categoria categoria;
	private List<Categoria> categorias;
	private List<Categoria> filteredCategorias;

	private boolean success;

	public Categoria getCategoria() {
		if (categoria == null) {
			categoria = new Categoria();
		}
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			if (categorias == null) {
				categorias = categoriaDAO.listar("categoria");
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as categorias");
			erro.printStackTrace();
		}
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Categoria> getFilteredCategorias() {
		return filteredCategorias;
	}
	
	public void setFilteredCategorias(List<Categoria> filteredCategorias) {
		this.filteredCategorias = filteredCategorias;
	}

	public void novo() {
		try {
			categoria = new Categoria();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar uma nova categoria");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.merge(categoria);

			categoria = new Categoria();
			categorias = categoriaDAO.listar("categoria");

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('dialogo').hide()");
			}

			Messages.addGlobalInfo("Categoria salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a categoria");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			categoria = (Categoria) evento.getComponent().getAttributes().get("categoriaSelecionada");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma categoria");
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			categoria = (Categoria) evento.getComponent().getAttributes().get("categoriaSelecionada");

			CategoriaDAO categoriaDAO = new CategoriaDAO();
			categoriaDAO.excluir(categoria);

			categorias = categoriaDAO.listar();

			Messages.addGlobalInfo("Categoria excluída com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a categoria");
			erro.printStackTrace();
		}
	}
}