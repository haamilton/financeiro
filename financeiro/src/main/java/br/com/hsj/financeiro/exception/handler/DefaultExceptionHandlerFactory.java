package br.com.hsj.financeiro.exception.handler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * Factory criada para a captura de esception Handler
 * 
 * @author Hamilton dos Santos Junior
 * @date 18/10/2012
 *
 */
public class DefaultExceptionHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;

	// this injection handles jsf
	public DefaultExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	// create your own ExceptionHandler
	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler result = new DefaultExceptionHandler(
				parent.getExceptionHandler());
		return result;
	}
}