package br.com.economize.dao;

import org.junit.Test;

import br.com.economize.domain.Categoria;

public class CategoriaDAOTest {

	@Test
	public void salvarCategoria() {

		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = new Categoria();
		categoria.setCategoria("Agronegócios");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Alimentos e Bebidas");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Brinquedos e Games");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Cama Mesa e Banho");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Carros Motos e Autopeças");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Celulares e Telefones");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Construção e Reforma");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Eletrodomésticos");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Eletrônicos");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Embalagens");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Escritórios");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Esporte e Lazer");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Ferramentas e Máquinas");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Foto Câmera e Filmadora");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Informática");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Joias e Relógios");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Materiais de Limpeza");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Moda e Acessórios");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Móveis e Decoração");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Papelarias e Livrarias");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Perfumes e Cosméticos");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Pet Shop");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Saúde e Odontologia");
		categoriaDAO.merge(categoria);

		new CategoriaDAO();
		new Categoria();
		categoria.setCategoria("Utensílios Domésticos");
		categoriaDAO.merge(categoria);
	}
}