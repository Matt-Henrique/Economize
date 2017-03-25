package br.com.economize.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.economize.domain.ItemCampanha;
import br.com.economize.util.HibernateUtil;

public class ItemCampanhaDAO extends GenericDAO<ItemCampanha> {

	@SuppressWarnings("unchecked")
	public List<ItemCampanha> buscaItensPorCampanha(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Query query = sessao.createQuery("from ItemCampanha where campanha_codigo = :codigo))").setParameter("codigo",
				codigo);
		List<ItemCampanha> resultado = query.list();
		return resultado;
	}
}