package br.com.economize.dao;

/*import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.economize.domain.Empresa;
import br.com.economize.domain.Usuario;
import br.com.economize.enumerate.Ativo;
import br.com.economize.enumerate.TipoUsuario;
import br.com.economize.util.HibernateUtil;

public class EmpresaDAO extends GenericDAO<Empresa> {

	public Empresa autenticar(String email, String senha, Ativo ativo, TipoUsuario tipo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.eq("email", email));
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			consulta.add(Restrictions.eq("ativo", ativo = Ativo.SIM));
			consulta.add(Restrictions.eq("tipoUsuario", tipo = TipoUsuario.EMPRESA));

			Empresa resultado = (Empresa) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	// EsqueciSenhaBean
	public Empresa verificarEmpresa(String cpf, String email, TipoUsuario tipo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Empresa.class);
			consulta.add(Restrictions.eq("cpf", cpf));
			consulta.add(Restrictions.eq("email", email));
			consulta.add(Restrictions.eq("tipoUsuario", tipo = TipoUsuario.EMPRESA));

			Empresa resultado = (Empresa) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	// EmpresaPerfilBean
	@SuppressWarnings("unchecked")
	public List<Empresa> buscaEmpresaLogada(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Query query = sessao.createQuery("from Empresa where codigo = :codigo ").setParameter("codigo",
					codigo);
			List<Empresa> resultado = query.list();

			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}*/