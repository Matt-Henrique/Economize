package br.com.economize.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.economize.domain.Empresa;
import br.com.economize.util.HibernateUtil;

public class EmpresaDAO extends GenericDAO<Empresa> {

	@SuppressWarnings("unchecked")
	public List<Empresa> buscaEmpresaPorUsuario(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Query query = sessao.createQuery("from Empresa where usuario_codigo = :codigo ").setParameter("codigo",
					codigo);
			List<Empresa> resultado = query.list();

			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}