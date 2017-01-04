package br.com.ajvideira.cursos.alura.springmvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(value=Exception.class)
	public ModelAndView dealException(Exception ex) {
		ModelAndView modelAndView = new ModelAndView("erro");
		modelAndView.addObject("mensagem", ex.getMessage());
		
		return modelAndView;
	}
	
}
