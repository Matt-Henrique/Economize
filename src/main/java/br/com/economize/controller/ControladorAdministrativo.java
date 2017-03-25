package br.com.economize.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.economize.domain.Administrador;
import br.com.economize.enumerate.Setor;

public class ControladorAdministrativo {

	private Administrador administrador;

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	private boolean permissaoAdministrador;
	private boolean permissaoCadastro;

	public boolean isPermissaoAdministrador() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Administrador usuarioSessao = (Administrador) sessao.getAttribute("USUARIO_SESSAO");

		if (usuarioSessao != null) {
			permissaoAdministrador = (usuarioSessao.getSetor() == Setor.ADMINISTRATIVO);
		} else {
			permissaoAdministrador = false;
		}
		return permissaoAdministrador;
	}

	public boolean isPermissaoCadastro() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Administrador usuarioSessao = (Administrador) sessao.getAttribute("USUARIO_SESSAO");

		if (usuarioSessao != null) {
			permissaoAdministrador = (usuarioSessao.getSetor() == Setor.ADMINISTRATIVO);

			if (permissaoAdministrador) {
				permissaoCadastro = true;
			} else {
				permissaoCadastro = (usuarioSessao.getSetor() == Setor.CADASTRO);
			}
		} else {
			permissaoCadastro = false;
		}
		return permissaoCadastro;
	}
}