package br.com.ajvideira.cursos.alura.springmvc.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ajvideira.cursos.alura.springmvc.model.CarrinhoCompras;
import br.com.ajvideira.cursos.alura.springmvc.model.DadosPagamento;
import br.com.ajvideira.cursos.alura.springmvc.model.Usuario;

@Controller
@RequestMapping("/pagamento")
@RequestScope
public class PagamentoController {

	@Autowired
	CarrinhoCompras carrinhoCompras;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	MailSender mailSender;
	
	@RequestMapping(value = "/finalizar", method = RequestMethod.GET)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes redirectAttributes) throws IOException {
		
		return () -> {
		
			String uri = "http://book-payment.herokuapp.com/payment";
			
			//URL obj = new URL(uri);
			//HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			//con.setRequestMethod("GET");
			//int responseCode = con.getResponseCode();
	
			String tipoMensagem = "";
			String mensagem = "";
			
			try {
				ResponseEntity<String> response = restTemplate.postForEntity(uri, new DadosPagamento(carrinhoCompras.getTotal()), String.class);
				
				if (response.getStatusCodeValue() >= 200 && response.getStatusCodeValue() <= 299) {
					tipoMensagem = "success";
					mensagem = response.getBody();
					
					sendMail(usuario);
				} else {
					tipoMensagem = "danger";
					mensagem = response.getBody();
				}
				
			} catch (HttpClientErrorException e) {
				System.out.println("StatusCode: " + e.getRawStatusCode());
				tipoMensagem = "danger";
				mensagem = e.getResponseBodyAsString();
			}
			
			System.out.println("Total da compra: " + carrinhoCompras.getTotal());
			redirectAttributes.addFlashAttribute("mensagem", mensagem);
			redirectAttributes.addFlashAttribute("tipoMensagem", tipoMensagem);
			
			return new ModelAndView("redirect:/");
		};
	}
	
	public void sendMail(Usuario usuario) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra finalizada com sucesso!!");
		email.setTo("jonathan.videira@procempa.com.br");
		email.setText("Compra efetuada com sucesso no valor de " + carrinhoCompras.getTotal());
		email.setFrom("compras@ajvideira.com");
		
		mailSender.send(email);
	}
	
}
