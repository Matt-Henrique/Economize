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
		SimpleHash hash = new SimpleHash("md5", "mateus09h");
		usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		usuario.setNome("Mateus Henrique");
		usuario.setCpf("466.174.588-42");
		usuario.setFone1("(19) 3863-8362");
		usuario.setFone2("(19) 99488-0457");
		usuario.setEmail("mateus09h@gmail.com");
		usuario.setAtivo(Ativo.SIM);
		usuario.setSenha(hash.toHex());
		usuarioDAO.merge(usuario);
	}
}