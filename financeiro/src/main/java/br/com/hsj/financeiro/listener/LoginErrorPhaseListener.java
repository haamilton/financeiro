/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
package br.com.hsj.financeiro.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

import br.com.hsj.financeiro.util.FacesUtils;

/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 * 
 */
public class LoginErrorPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -749091903016338203L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void beforePhase(PhaseEvent arg0) {
		Exception dadosIncorretosException = (Exception) FacesUtils.getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);
		if (dadosIncorretosException instanceof BadCredentialsException) {
			FacesUtils.getSessionMap().put(
					WebAttributes.AUTHENTICATION_EXCEPTION, null);
			FacesUtils.exibirMensagemErro("Dados incorretos!");
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
}