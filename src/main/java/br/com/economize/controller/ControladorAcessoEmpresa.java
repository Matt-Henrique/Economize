package br.com.economize.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.economize.domain.Empresa;

public class ControladorAcessoEmpresa {

	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	private boolean permissaoEmpresa;
	private boolean permissaoUsuario;

	public boolean isPermissaoEmpresa() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Empresa usuarioSessao = (Empresa) sessao.getAttribute("USUARIO_SESSAO");

		if (usuarioSessao != null) {
			//permissaoEmpresa = (usuarioSessao.getTipoEmpresa() == TipoEmpresa.GERENTE);
		} else {
			permissaoEmpresa = false;
		}
		return permissaoEmpresa;
	}

	public boolean isPermissaoUsuario() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Empresa usuarioSessao = (Empresa) sessao.getAttribute("USUARIO_SESSAO");

		if (usuarioSessao != null) {
			//permissaoEmpresa = (usuarioSessao.getTipoEmpresa() == TipoEmpresa.GERENTE);

			if (permissaoEmpresa) {
				permissaoUsuario = true;
			} else {
				//permissaoUsuario = (usuarioSessao.getTipoEmpresa() == TipoEmpresa.USUARIO);
			}
		} else {
			permissaoUsuario = false;
		}
		return permissaoUsuario;
	}
}