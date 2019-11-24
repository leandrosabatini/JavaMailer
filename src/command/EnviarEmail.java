package command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
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

import model.Cliente;
import model.Email;
import model.Funcionario;
import model.Usuario;
import service.ClienteService;
import service.EmailService;
import service.FuncionarioService;

public class EnviarEmail implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getSession().getAttribute("logado");

		String assunto = "";
		String clientes[] = {};
		String funcionarios[] = {};
		String conteudo = "";
		ArrayList<String> emails = new ArrayList<String>();
		
		try {
			assunto = request.getParameter("assunto");
		} catch (Exception e) {}

		try {
			System.out.println("tentando pegar clientes");
			clientes = request.getParameterValues("clientes");
		} catch (Exception e) {}
		System.out.println("tentado");
		try {
			funcionarios = request.getParameterValues("funcionarios");
		} catch (Exception e) {}
		
		try {
			conteudo = request.getParameter("conteudo");
		} catch (Exception e) {}

		ClienteService cs = new ClienteService();
		ArrayList<Cliente> clientesArray = new ArrayList<Cliente>();
		System.out.println("5");
		if (clientes.length > 0) {
			for (String clienteId : clientes) {
				Cliente cliente = cs.carregar(Integer.parseInt(clienteId));
				clientesArray.add(cliente);
				emails.add(cliente.getEmail());
			}
		}
		System.out.println("6");
		FuncionarioService fs = new FuncionarioService();
		ArrayList<Funcionario> funcionariosArray = new ArrayList<Funcionario>();
		
		if (funcionarios.length > 0) {
			for (String funcionarioId : funcionarios) {
				Funcionario funcionario = fs.carregar(Integer.parseInt(funcionarioId));
				funcionariosArray.add(funcionario);
				emails.add(funcionario.getEmail());
			}
		}
		
		EmailService es = new EmailService();
		Email email = new Email();
		email.setAssunto(assunto);
		email.setConteudo(conteudo);
		if (funcionariosArray.size() > 0) {
			email.setFuncionarios(funcionariosArray);
		}
		if (clientesArray.size() > 0) {
			email.setClientes(clientesArray);
		}
		email.setEmpresaId(user.getEmpresaId());
		
		
		
		
		System.out.println("7");
		
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
	 
//	    session.setDebug(true);
	 
	    try {
	    	Message message = new MimeMessage(session);
	    	message.setFrom(new InternetAddress("no-reply@mailer.com")); 
			
	    	for (String emailText : emails) {
    			message.addRecipient(Message.RecipientType.TO, InternetAddress.parse(emailText)[0]);
	    	}

	    	message.setSubject(assunto);
	    	message.setContent(conteudo, "text/html; charset=utf-8");
			
	    	Transport.send(message);
			 
	    } catch (MessagingException e) {
	    	throw new RuntimeException(e);
	    }
	    
	    email.setId(es.criar(email));

	    response.sendRedirect("/Mailer/controller.do?command=ListarEmails");
	}

}
