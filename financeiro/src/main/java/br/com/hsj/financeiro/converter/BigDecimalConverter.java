package br.com.hsj.financeiro.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * 
 * @date 11/11/2011
 * @author Hamilton dos Santos Junior
 * 
 */
@FacesConverter(value = "br.com.hsj.converter.BigDecimalConverter")
public class BigDecimalConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext _context, UIComponent _component, String _value) {
		if (_context == null || _component == null) {
			throw new NullPointerException();
		}

		if (_value == null) {
			return null;
		}
		
		_value = _value.trim();
		if (_value.length() < 1) {
			return null;
		}
		
		_value = _value.replace(".", "");
		_value = _value.replace(",", ".");
		
		try {
			return new BigDecimal(_value);
		} catch (NumberFormatException nfe) {
			// TODO alterar mensagem
			throw new ConverterException("Teste Converter", nfe);
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}

	@Override
	public String getAsString(FacesContext _context, UIComponent _component, Object _value) {
		if ((_context == null) || (_component == null)) {
			throw new NullPointerException();
		}

		if (_value == null) {
			return "";
		}

		if (_value instanceof String) {
			return ((String) _value);
		}
		try {
			return _value.toString();
		} catch (Exception e) {
			// TODO alterar mensagem
			throw new ConverterException("Teste Converter", e);
		}
	}

}
