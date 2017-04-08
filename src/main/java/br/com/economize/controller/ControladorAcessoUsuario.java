package br.com.economize.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.economize.domain.Usuario;
import br.com.economize.enumerate.TipoUsuario;

public class ControladorAcessoUsuario {

	private boolean permissaoAdministrador;
	private boolean permissaoGerente;
	private boolean permissaoUsuario;

	public boolean isPermissaoAdministrador() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuarioSessao = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

		if (usuarioSessao != null) {
			permissaoAdministrador = (usuarioSessao.getTipoUsuario() == TipoUsuario.ADMINISTRADOR);
		} else {
			permissaoAdministrador = false;
		}
		return permissaoAdministrador;
	}

	public boolean isPermissaoGerente() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuarioSessao = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

		if (usuarioSessao != null) {
			permissaoAdministrador = (usuarioSessao.getTipoUsuario() == TipoUsuario.ADMINISTRADOR);

			if (permissaoAdministrador) {
				permissaoGerente = true;
			} else {
				permissaoGerente = (usuarioSessao.getTipoUsuario() == TipoUsuario.GERENTE);
			}
		} else {
			permissaoGerente = false;
		}
		return permissaoGerente;
	}

	public boolean isPermissaoUsuario() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuarioSessao = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

		if (usuarioSessao != null) {
			permissaoUsuario = (usuarioSessao.getTipoUsuario() == TipoUsuario.USUARIO);
		} else {
			permissaoUsuario = false;
		}
		return permissaoUsuario;
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