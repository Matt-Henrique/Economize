package br.com.economize.dao;

/**
* @author Mateus Henrique Tofanello
* 
*/

import org.junit.Test;

import br.com.economize.domain.Empresa;

public class EmpresaDAOTest {

	@Test
	public void salvarEmpresa() {

		EmpresaDAO empresaDAO = new EmpresaDAO();
		Empresa empresa = new Empresa();

		empresa.setRazaoSocial("WeSix Sistemas Ltda");
		empresa.setNomeFantasia("WeSix");
		empresa.setCnpj("75.285.488/0001-40");
		empresa.setIe("475.370.945.772");
		empresa.setFone1("(19) 3863-3863");
		empresa.setFone2("(19) 99999-9999");
		empresa.setFone3("(19) 99999-0000");
		empresa.setEmail("wesix@mail.com");

		empresa.getEndereco().setCep("13970-080");
		empresa.getEndereco().setEstado("SP");
		empresa.getEndereco().setCidade("Itapira");
		empresa.getEndereco().setBairro("Centro");
		empresa.getEndereco().setTipoLogradouro("Avenida");
		empresa.getEndereco().setLogradouro("dos Italianos");
		empresa.getEndereco().setNumero(100);
		empresa.getEndereco().setComplemento("Ap. 123");

		empresaDAO.merge(empresa);
	}
}