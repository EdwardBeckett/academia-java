package com.mycompany.mercaderia;

import com.mycompany.mercaderia.controller.ListaMercaderiaController;
import java.util.Locale;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		Locale.setDefault(new Locale("pt","BR"));
		new ListaMercaderiaController();
	}

}
