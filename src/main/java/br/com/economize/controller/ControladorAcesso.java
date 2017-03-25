package br.com.economize.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.economize.domain.Usuario;

public class ControladorAcesso {

	private boolean permissaoAdministrador;
	private boolean permissaoEmpresa;

	public boolean isPermissaoAdministrador() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuarioSessao = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

		if (usuarioSessao != null) {
			//permissaoAdministrador = (usuarioSessao.getTipoUsuario() == TipoUsuario.ADMINISTRADOR);
		} else {
			permissaoAdministrador = false;
		}
		return permissaoAdministrador;
	}

	public boolean isPermissaoEmpresa() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuarioSessao = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

		if (usuarioSessao != null) {
			//permissaoEmpresa = (usuarioSessao.getTipoUsuario() == TipoUsuario.EMPRESA);
		} else {
			permissaoEmpresa = false;
		}
		return permissaoEmpresa;
	}

	// Configura o acesso do usuário logado às funcionalidades da aplicação.
	public void configurarAcesso() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuarioSessao = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

		if (usuarioSessao != null) {

			Logger.getLogger("ControladorAcesso").log(Level.INFO, "Usuário da sessão tem tipo {0}",
					usuarioSessao.getTipoUsuario());
		}
	}
}