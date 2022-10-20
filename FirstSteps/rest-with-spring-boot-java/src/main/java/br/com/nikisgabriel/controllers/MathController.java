package br.com.nikisgabriel.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.nikisgabriel.converters.NumberConvert;
import br.com.nikisgabriel.exceptions.UnsupportMathOperationException;
import br.com.nikisgabriel.math.SimpleMath;

@RestController
public class MathController {
	
	private SimpleMath math = new SimpleMath();
	
	//para receber path params os declaramos entre chaves no @RequestMapping
	//e o path deve ser atribuído ao "value"
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne") String num1,
			@PathVariable(value = "numberTwo") String num2) {
		
		if(!NumberConvert.isNumeric(num1) || !NumberConvert.isNumeric(num2)) {
			throw new UnsupportMathOperationException("Por favor insira valores numericos");
		}
		
		return math.sum(NumberConvert.convertToDouble(num1), NumberConvert.convertToDouble(num2)); 
	}
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET)
	public Double sub(
			@PathVariable(value = "numberOne") String num1,
			@PathVariable(value = "numberTwo") String num2) {
		
		if(!NumberConvert.isNumeric(num1) || !NumberConvert.isNumeric(num2)) {
			throw new UnsupportMathOperationException("Por favor insira valores numericos");
		}
		
		return math.sub(NumberConvert.convertToDouble(num1), NumberConvert.convertToDouble(num2)); 
	}
	
	@RequestMapping(value = "/mul/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET)
	public Double mul(
			@PathVariable(value = "numberOne") String num1,
			@PathVariable(value = "numberTwo") String num2) {
		
		if(!NumberConvert.isNumeric(num1) || !NumberConvert.isNumeric(num2)) {
			throw new UnsupportMathOperationException("Por favor insira valores numericos");
		}
		
		return math.mul(NumberConvert.convertToDouble(num1), NumberConvert.convertToDouble(num2)); 
	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}", 
			method=RequestMethod.GET)
	public Double div(
			@PathVariable(value = "numberOne") String num1,
			@PathVariable(value = "numberTwo") String num2) {
		
		if(!NumberConvert.isNumeric(num1) || !NumberConvert.isNumeric(num2)) {
			throw new UnsupportMathOperationException("Por favor insira valores numericos");
		}
		if(NumberConvert.convertToDouble(num2) == 0) {
			throw new UnsupportMathOperationException("Não é possivel relizar divisões por 0");
		}
		
		return math.div(NumberConvert.convertToDouble(num1), NumberConvert.convertToDouble(num2)); 
	}
	
	
}
