package br.com.hsj.financeiro.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class StringUtils {

	public static String encriptar(String senha) {  
        PasswordEncoder encoder = new Md5PasswordEncoder();  
        senha = encoder.encodePassword(senha, null);  
        return senha;  
    }  
	
	
	public static void main(String[] args) {
		System.out.println(StringUtils.encriptar("admin"));
	}
}
