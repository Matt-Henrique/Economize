package br.com.economize.util;

/**
* @author Mateus Henrique Tofanello
* 
*/

import org.hibernate.Session;
import org.junit.Test;

import br.com.economize.util.HibernateUtil;

public class HibernateUtilTest {
	@Test
	public void conectar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}