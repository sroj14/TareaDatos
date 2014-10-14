import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import javax.imageio.ImageIO;                    
import java.awt.image.BufferedImage;
import javax.swing.*;


/**Clase que permite enviar correos*/
public class EnviarCorreo{

	public Properties props;
	public String mensaje;
  /**Constructor de la clase*/
	public EnviarCorreo(String correo, String nombreCaja, String nombre, String tipoCliente, String fecha, String hora){
            props = new Properties();
            /**Mensaje que sera enviado*/
            mensaje = "Pagina Web: "+"www.bac.net/bacsanjose/esp/banco/index.html"+"<br>"+"Nombre del Cliente: "+nombre+"<br>"+"Tipo de Cliente: "+tipoCliente+"<br>"+"Fecha: "+fecha+"<br>"+"Hora: "+hora;
            mensaje = mensaje+"<br>"+"<br>"+"se le ha asignado la  "+nombreCaja+" Gracias por visitar el Bac San José, para nosostros es un placer atenderles";
            mensaje = mensaje + "<br>"+ "Para consultas puede llamar al : 5000 BacSJ";

            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("jdnavarro17@live.com", "jupiter070417");
                             }
                        });

            session.setDebug(true);

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("jdnavarro17@live.com")); 
                message.setRecipients(Message.RecipientType.TO, 
                                    InternetAddress.parse(correo)); 
               message.setSubject("Caja Asignada");
               message.setText("");
               MimeMultipart multipart = new MimeMultipart("related");
               BodyPart messageBodyPart = new MimeBodyPart();
               String htmlText = "<img src=\"cid:image\">"+"<H1>"+mensaje+"</H1>";
               messageBodyPart.setContent(htmlText, "text/html");
               multipart.addBodyPart(messageBodyPart);
               messageBodyPart = new MimeBodyPart();
               DataSource fds = new FileDataSource(new File ("").getAbsolutePath()+"/datos/imagenes/bac.gif");
               messageBodyPart.setDataHandler(new DataHandler(fds));
               messageBodyPart.setHeader("Content-ID", "<image>");
               multipart.addBodyPart(messageBodyPart);
               message.setContent(multipart);
               Transport.send(message);
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      }
}

















  
	
