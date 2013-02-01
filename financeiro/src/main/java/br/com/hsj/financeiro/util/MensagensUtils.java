/**
 * @author Hamilton dos Santos Junior
 * @date 14/12/2011
 *
 */
package br.com.hsj.financeiro.util;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Classe utilitária que centraliza a recuperação das mensagens do arquivo de mensagens
 * @author Hamilton dos Santos Junior
 * @date 14/12/2011
 *
 */
public class MensagensUtils {

	private static MensagensUtils mensagem = null;
	
	private MessageSource arquivoMensagens = null;
	
	private MensagensUtils() {
		ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(FacesUtils.getServletContext());
		
		arquivoMensagens = (MessageSource) appContext.getBean("arquivoMensagens");
	}
	
	public static MensagensUtils getInstance() {  
        if (mensagem == null) {  
        	mensagem = new MensagensUtils();  
        }  
        return mensagem;  
    }  
	
	public String getMensagem(String _chave, Object[] _parametros) {
		return arquivoMensagens.getMessage(_chave, _parametros, new Locale ("pt", "BR"));
	}
}
