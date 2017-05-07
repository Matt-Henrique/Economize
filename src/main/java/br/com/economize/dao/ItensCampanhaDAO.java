package br.com.economize.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.economize.domain.ItemCampanha;
import br.com.economize.util.HibernateUtil;

public class ItensCampanhaDAO extends GenericDAO<ItemCampanha> {

	// ProdutoEmpresaBean
	@SuppressWarnings("unchecked")
	public List<ItemCampanha> buscaItemPorCampanha(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Query query = sessao.createQuery("from ItemCampanha where campanha_codigo = :codigo ").setParameter("codigo",
					codigo);
			List<ItemCampanha> resultado = query.list();

			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}