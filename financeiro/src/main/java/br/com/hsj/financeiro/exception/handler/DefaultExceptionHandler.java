package br.com.hsj.financeiro.exception.handler;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.log4j.Logger;

/**
 * Classe criada para capturar as excessões checadas e não checadas
 * @author Hamilton dos Santos Junior
 * @date 18/10/2012
 *
 */
public class DefaultExceptionHandler extends ExceptionHandlerWrapper {

	private Logger logger = Logger.getLogger(DefaultExceptionHandler.class);
	private ExceptionHandler wrapped;

	public DefaultExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
			
			ExceptionQueuedEvent event = i.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();

			final FacesContext facesContext = FacesContext.getCurrentInstance();
		    final ExternalContext externalContext = facesContext.getExternalContext();
		    
//		    final Map<String, Object> requestMap = externalContext.getRequestMap();
            Throwable t = context.getException();

			// here you do what ever you want with exception
			try {
				// log error
				logger.error("Ocoreu um erro:", t);
				try {
					externalContext.dispatch("/erro.xhtml");
				} catch (final IOException e) {
					logger.error("Error view '/erro.xhtml' unknown!", e);
				}
				
				facesContext.responseComplete();
			} finally {
				// after exception is handeled, remove it from queue
				i.remove();
			}

            break;
		}
		// let the parent handle the rest
		getWrapped().handle();
	}
}
