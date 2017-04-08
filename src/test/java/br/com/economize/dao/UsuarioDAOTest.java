package br.com.economize.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import br.com.economize.domain.Usuario;
import br.com.economize.enumerate.Ativo;
import br.com.economize.enumerate.TipoUsuario;

public class UsuarioDAOTest {

	@Test
	public void salvarUsuario() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		usuario.setNome("Mateus Henrique");
		usuario.setCpf("111.111.111-11");
		usuario.setFone1("(19) 1111-1111");
		usuario.setFone2("(19) 22222-2222");
		usuario.setEmail("mateus@adm.com");
		usuario.setAtivo(Ativo.SIM);
		SimpleHash hash = new SimpleHash("md5", "111");
		usuario.setSenha(hash.toHex());
		usuarioDAO.merge(usuario);
	}
}