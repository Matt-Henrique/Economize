package br.com.economize.bean;

/**
* @author Mateus Henrique Tofanello
* 
*/

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.economize.dao.UsuarioDAO;
import br.com.economize.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioPerfilBean implements Serializable {

	HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	Usuario usuarioSessao = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

	private Usuario usuario;
	private List<Usuario> usuarios;

	private boolean success;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.buscaUsuarioLogado(usuarioSessao.getCodigo());

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuarios");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			usuarios = usuarioDAO.buscaUsuarioLogado(usuarioSessao.getCodigo());

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('pessoal').hide()");
			}

			Messages.addGlobalInfo("Usuario editado com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar o usuario");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar um usuario");
		}
	}

	public void mudarSenha() {
		try {
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
			usuario.setSenha(hash.toHex());

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			usuarios = usuarioDAO.buscaUsuarioLogado(usuarioSessao.getCodigo());

			if (success) {
				RequestContext.getCurrentInstance().execute("PF('senha').hide()");
			}

			Messages.addGlobalInfo("Senha alterada com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar alterar a senha");
			erro.printStackTrace();
		}
	}
}