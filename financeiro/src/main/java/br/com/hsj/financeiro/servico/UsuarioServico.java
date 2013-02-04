/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
package br.com.hsj.financeiro.servico;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.hsj.financeiro.entidade.Usuario;

/**
 * @author Hamilton dos Santos Junior
 * @date 04/04/2012
 *
 */
public interface UsuarioServico extends BaseServico<Usuario> {

	public UserDetails loadUserByUsername(String _username) throws UsernameNotFoundException;
}
