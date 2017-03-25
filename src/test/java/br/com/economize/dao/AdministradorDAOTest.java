package br.com.economize.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import br.com.economize.domain.Administrador;
import br.com.economize.enumerate.Ativo;
import br.com.economize.enumerate.Setor;
import br.com.economize.enumerate.TipoUsuario;


public class AdministradorDAOTest {

	@Test
	public void salvarAdministrador() {

		AdministradorDAO administradorDAO = new AdministradorDAO();
		Administrador administrador = new Administrador();

		SimpleHash hash = new SimpleHash("md5", "111");

		administrador.setNome("Mateus Administrador");
		administrador.setCpf_cnpj("111.111.111-11");
		administrador.setTelefone("(19) 9999-9999");
		administrador.setTelefone2("(19) 9999-9999");
		administrador.setCelular("(19) 99999-9999");
		administrador.setEmail("mateus@adm.com");
		administrador.setSenha(hash.toHex());
		administrador.setAtivo(Ativo.SIM);
		administrador.setSetor(Setor.ADMINISTRATIVO);
		administrador.setTipoUsuario(TipoUsuario.ADMINISTRADOR);

		administradorDAO.merge(administrador);
	}
}