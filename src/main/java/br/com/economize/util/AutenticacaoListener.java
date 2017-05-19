package br.com.economize.util;

/**
* @author Mateus Henrique Tofanello
* 
*/

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;

import br.com.economize.bean.AutenticacaoBean;
import br.com.economize.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {

		String paginaAtual = Faces.getViewId();

		FacesContext context = event.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();

		System.out.println(nomePagina);

		if ("/paginas/esqueci-senha.xhtml".equals(nomePagina)) {
			return;
		}

		boolean ehPaginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml");

		if (!ehPaginaDeAutenticacao) {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			if (autenticacaoBean == null) {
				Faces.navigate("/paginas/autenticacao.xhtml");
				return;
			}
			System.out.println("Verificando se o usuário está na sessão");
			HttpSession sessao = (HttpSession) context.getExternalContext().getSession(false);
			Usuario usuario = (Usuario) sessao.getAttribute("USUARIO_SESSAO");

			if (usuario != null)
				System.out.println(usuario.getNome());
			else
				System.out.println("Usuario não está logado");

			if (usuario == null) {
				Faces.navigate("/paginas/autenticacao.xhtml");
				return;
			}
		}

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext fc = event.getFacesContext();
		ExternalContext ec = fc.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) ec.getResponse();
		response.setHeader("Expires", "-1");
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidade, proxy-revalidade, private, post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}