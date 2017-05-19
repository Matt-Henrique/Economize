package br.com.economize.dao;

/**
* @author Mateus Henrique Tofanello
* 
*/

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.economize.domain.Campanha;
import br.com.economize.util.HibernateUtil;

public class CampanhaDAO extends GenericDAO<Campanha> {

	// CampanhaEmpresaBean
	@SuppressWarnings("unchecked")
	public List<Campanha> buscaCampanhaPorEmpresa(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Query query = sessao.createQuery("from Campanha where empresa_codigo = :codigo ").setParameter("codigo",
					codigo);
			List<Campanha> resultado = query.list();

			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}