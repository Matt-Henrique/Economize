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
		Categoria categoria1 = new Categoria();
		categoria1.setCategoria("Alimentos e Bebidas");
		categoriaDAO.merge(categoria1);

		new CategoriaDAO();
		Categoria categoria2 = new Categoria();
		categoria2.setCategoria("Brinquedos e Games");
		categoriaDAO.merge(categoria2);

		new CategoriaDAO();
		Categoria categoria3 = new Categoria();
		categoria3.setCategoria("Cama Mesa e Banho");
		categoriaDAO.merge(categoria3);

		new CategoriaDAO();
		Categoria categoria4 = new Categoria();
		categoria4.setCategoria("Carros Motos e Autopeças");
		categoriaDAO.merge(categoria4);

		new CategoriaDAO();
		Categoria categoria5 = new Categoria();
		categoria5.setCategoria("Celulares e Telefones");
		categoriaDAO.merge(categoria5);

		new CategoriaDAO();
		Categoria categoria6 = new Categoria();
		categoria6.setCategoria("Construção e Reforma");
		categoriaDAO.merge(categoria6);

		new CategoriaDAO();
		Categoria categoria7 = new Categoria();
		categoria7.setCategoria("Eletrodomésticos");
		categoriaDAO.merge(categoria7);

		new CategoriaDAO();
		Categoria categoria8 = new Categoria();
		categoria8.setCategoria("Eletrônicos");
		categoriaDAO.merge(categoria8);

		new CategoriaDAO();
		Categoria categoria9 = new Categoria();
		categoria9.setCategoria("Embalagens");
		categoriaDAO.merge(categoria9);

		new CategoriaDAO();
		Categoria categoria10 = new Categoria();
		categoria10.setCategoria("Escritórios");
		categoriaDAO.merge(categoria10);

		new CategoriaDAO();
		Categoria categoria11 = new Categoria();
		categoria11.setCategoria("Esporte e Lazer");
		categoriaDAO.merge(categoria11);

		new CategoriaDAO();
		Categoria categoria12 = new Categoria();
		categoria12.setCategoria("Ferramentas e Máquinas");
		categoriaDAO.merge(categoria12);

		new CategoriaDAO();
		Categoria categoria13 = new Categoria();
		categoria13.setCategoria("Foto Câmera e Filmadora");
		categoriaDAO.merge(categoria13);

		new CategoriaDAO();
		Categoria categoria14 = new Categoria();
		categoria14.setCategoria("Informática");
		categoriaDAO.merge(categoria14);

		new CategoriaDAO();
		Categoria categoria15 = new Categoria();
		categoria15.setCategoria("Joias e Relógios");
		categoriaDAO.merge(categoria15);

		new CategoriaDAO();
		Categoria categoria16 = new Categoria();
		categoria16.setCategoria("Materiais de Limpeza");
		categoriaDAO.merge(categoria16);

		new CategoriaDAO();
		Categoria categoria17 = new Categoria();
		categoria17.setCategoria("Moda e Acessórios");
		categoriaDAO.merge(categoria17);

		new CategoriaDAO();
		Categoria categoria18 = new Categoria();
		categoria18.setCategoria("Móveis e Decoração");
		categoriaDAO.merge(categoria18);

		new CategoriaDAO();
		Categoria categoria19 = new Categoria();
		categoria19.setCategoria("Papelarias e Livrarias");
		categoriaDAO.merge(categoria19);

		new CategoriaDAO();
		Categoria categoria20 = new Categoria();
		categoria20.setCategoria("Perfumes e Cosméticos");
		categoriaDAO.merge(categoria20);

		new CategoriaDAO();
		Categoria categoria21 = new Categoria();
		categoria21.setCategoria("Pet Shop");
		categoriaDAO.merge(categoria21);

		new CategoriaDAO();
		Categoria categoria22 = new Categoria();
		categoria22.setCategoria("Saúde e Odontologia");
		categoriaDAO.merge(categoria22);

		new CategoriaDAO();
		Categoria categoria23 = new Categoria();
		categoria23.setCategoria("Utensílios Domésticos");
		categoriaDAO.merge(categoria23);
	}
}