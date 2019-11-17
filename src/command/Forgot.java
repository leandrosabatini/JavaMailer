package command;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Empresa;
import model.Usuario;
import service.EmpresaService;
import service.UsuarioService;

public class Forgot implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pEmail = request.getParameter("email");

		UsuarioService us = new UsuarioService();
		Usuario usuario = new Usuario();

		usuario = us.carregarByEmail(pEmail);
		
		if (usuario.getId() > 0) {
			Random random = new Random(System.currentTimeMillis());

			String senha = new BigInteger(130, random).toString(32).substring(0, 6);
			
			usuario.setSenha(senha);
			
			
			
			
			
			
			
			
			

			Properties props = new Properties();
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.socketFactory.port", "465");
		    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.port", "465");
		 
		    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		    	protected PasswordAuthentication getPasswordAuthentication() {
		    		return new PasswordAuthentication("mailersaojudas@gmail.com", "mailer123123");
		    	}
		    });
		 
//		    session.setDebug(true);
		 
		    try {
		    	Message message = new MimeMessage(session);
		    	message.setFrom(new InternetAddress("no-reply@mailer.com")); 
		    	message.addRecipient(Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail())[0]);
		    	
		    	message.setSubject("Recuperação de senha");
		    	message.setContent("Sua nova senha para acessar o sistema: " + senha, "text/html; charset=utf-8");
				
		    	Transport.send(message);
				 
		    } catch (MessagingException e) {
		    	throw new RuntimeException(e);
		    }

		    
		    
		    
		    us.atualizar(usuario);
		    
			//trocar senha e mandar por email para o usuario
			request.setAttribute("message", "Uma nova senha foi enviada para seu email!");
		} else {
			request.setAttribute("message", "Usuário não encontrado");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("forgot.jsp");

		dispatcher.forward(request, response);
	}

}
