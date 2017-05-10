package br.com.economize.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import br.com.economize.domain.Empresa;
import br.com.economize.domain.Usuario;
import br.com.economize.enumerate.Ativo;
import br.com.economize.enumerate.TipoUsuario;

public class UsuarioDAOTest {

	@Test
	public void salvarUsuario() {
		
		EmpresaDAO empresaDAO = new EmpresaDAO();
		Empresa empresa = empresaDAO.buscar(1L);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		
		SimpleHash hash = new SimpleHash("md5", "mateus");
		
		usuario.setEmpresa(empresa);
		usuario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
		usuario.setNome("Mateus Henrique");
		usuario.setCpf("111.111.111-11");
		usuario.setFone1("(19) 1111-1111");
		usuario.setFone2("(19) 11111-1111");
		usuario.setEmail("mateus09h@gmail.com");
		usuario.setAtivo(Ativo.SIM);
		usuario.setSenha(hash.toHex());
		
		usuarioDAO.merge(usuario);
	}
}