package controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Email;
import service.EmailService;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;


/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/emails/Email.do")
public class EmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String assunto = request.getParameter("assunto");
		String conteudo = request.getParameter("conteudo");
//		String pEmpresa = request.getParameter("empresa");

		EmailService es = new EmailService();
		Email email = new Email();
		email.setAssunto(assunto);
		email.setConteudo(conteudo);
		email.setEmpresaId(1);
		email.setId(es.criar(email));
		
		
		
		Properties props = new Properties();
	    /** Parâmetros de conexão com servidor Gmail */
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", 
	    "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");
	 
	    Session session = Session.getDefaultInstance(props,
	      new javax.mail.Authenticator() {
	           protected PasswordAuthentication getPasswordAuthentication() 
	           {
	                 return new PasswordAuthentication("mailersaojudas@gmail.com", "mailer123123");
	           }
	      });
	 
	    /** Ativa Debug para sessão */
//	    session.setDebug(true);
	 
	    try {
	 
	      Message message = new MimeMessage(session);
	      message.setFrom(new InternetAddress("no-reply@mailer.com")); 
	      //Remetente
	 
	      Address[] toUser = InternetAddress.parse("leandro.sabatini31@gmail.com");  
	 
	      message.setRecipients(Message.RecipientType.TO, toUser);
	      message.setSubject(assunto);//Assunto
	      message.setText(conteudo);
	      /**Método para enviar a mensagem criada*/
	      Transport.send(message);
	 	 
	     } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
		
		
		
		
//		request.setAttribute("usuario", usuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		
//		switch(acao){
//		case "Incluir":
//			cs.criar(usuario);
//			usuario = cs.carregar(usuario.getId());
//			
//			//manda parametro para o JSP via request
//			request.setAttribute("usuario", usuario);
//			dispatcher = request.getRequestDispatcher("Usuario.jsp");
//			break;
//		case "Listar":
//			ArrayList<Cliente> clientes = cs.listarTodos();
//			request.setAttribute("clientes", clientes);
//			dispatcher = request.getRequestDispatcher("ListaDeClientes.jsp");
//		}
		
		
		dispatcher.forward(request, response);
		
	}

}
