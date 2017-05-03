package br.com.economize.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.economize.domain.Produto;
import br.com.economize.util.HibernateUtil;

public class ProdutoDAO extends GenericDAO<Produto> {

	// ProdutoEmpresaBean
	@SuppressWarnings("unchecked")
	public List<Produto> buscaProdutoPorEmpresa(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Query query = sessao.createQuery("from Produto where empresa_codigo = :codigo ").setParameter("codigo",
					codigo);
			List<Produto> resultado = query.list();

			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}