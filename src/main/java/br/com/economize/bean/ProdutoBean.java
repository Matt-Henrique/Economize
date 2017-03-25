package br.com.economize.bean;

/*import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.component.wizard.Wizard;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.economize.dao.CategoriaDAO;
import br.com.economize.dao.EmpresaDAO;
import br.com.economize.dao.ProdutoDAO;
import br.com.economize.domain.Categoria;
import br.com.economize.domain.Empresa;
import br.com.economize.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	String pathDefault = "C:/workspace-eco/Imagens/";

	private Produto produto;
	private Produto selectedProduto;

	private List<Produto> produtos;

	EmpresaDAO empresaDAO = new EmpresaDAO();
	private List<Empresa> empresas = empresaDAO.listar("nome");

	CategoriaDAO categoriaDAO = new CategoriaDAO();
	private List<Categoria> categorias = categoriaDAO.listar("categoria");

	private StreamedContent foto;

	private boolean success;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getSelectedProduto() {
		return selectedProduto;
	}

	public void setSelectedProduto(Produto selectedProduto) {
		this.selectedProduto = selectedProduto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public StreamedContent getFoto() {
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar("descricao");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			produto = new Produto();

			Wizard wizard = (Wizard) FacesContext.getCurrentInstance().getViewRoot().findComponent("formCadastro:w");
			wizard.setStep("tabProduto");
			RequestContext.getCurrentInstance().update("formCadastro");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo produto");
			erro.printStackTrace();
		}
	}

	public void salvar() throws IOException {
		try {

			success = false;
			while (success != true) {
				if (produto.getCaminho() == null) {
					Messages.addGlobalError("O campo Imagem é obrigatório");
					return;
				} else {
					success = true;
				}
			}

			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produtoRetorno = produtoDAO.merge(produto);

			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get(pathDefault + produtoRetorno.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);

			produtos = produtoDAO.listar("descricao");

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('dialogo').hide()");
			}

			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar um produto");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			produto.setCaminho(pathDefault + produto.getCodigo() + ".png");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar alterar o produto");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) throws IOException {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			Path arquivo = Paths.get(pathDefault + "/" + produto.getCodigo() + ".png");
			Files.deleteIfExists(arquivo);

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);

			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Produto excluído com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir o produto");
			erro.printStackTrace();
		}

	}

	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			produto.setCaminho(arquivoTemp.toString());

			Messages.addGlobalInfo("Upload realizado com sucesso");
		} catch (IOException erro) {
			Messages.addGlobalInfo("Ocorreu um erro ao tentar realizar o upload da imagem");
			erro.printStackTrace();
		}
	}

	public void download(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			InputStream stream = new FileInputStream(pathDefault + produto.getCodigo() + ".png");
			foto = new DefaultStreamedContent(stream, "image/png", produto.getCodigo() + ".png");
		} catch (FileNotFoundException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar efetuar o download da imagem");
			erro.printStackTrace();
		}
	}
}*/