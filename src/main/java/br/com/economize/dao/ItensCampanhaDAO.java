package br.com.economize.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.economize.domain.ItensCampanha;
import br.com.economize.util.HibernateUtil;

public class ItensCampanhaDAO extends GenericDAO<ItensCampanha> {

	@SuppressWarnings("unchecked")
	public List<ItensCampanha> buscaItensPorCampanha(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Query query = sessao.createQuery("from ItensCampanha where campanha_codigo = :codigo))").setParameter("codigo",
				codigo);
		List<ItensCampanha> resultado = query.list();
		return resultado;
	}
}