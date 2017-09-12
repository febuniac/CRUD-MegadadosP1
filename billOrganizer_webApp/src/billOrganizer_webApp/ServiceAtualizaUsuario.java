package billOrganizer_webApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

@WebServlet("/ServiceAtualizaUsuario")
public class ServiceAtualizaUsuario extends HttpServlet {
    protected void service(HttpServletRequest request,
            HttpServletResponse response)
throws IOException, ServletException {
PrintWriter out = response.getWriter();
Usuarios usuario = new Usuarios();
usuario.setId(request.getParameter("id"));
usuario.setNome(request.getParameter("nome"));
usuario.setRG(request.getParameter("RG"));
usuario.setEmissor(request.getParameter("emissor"));
usuario.setCpf(request.getParameter("cpf"));

String json = new Gson().toJson(usuario);
System.out.println(json);

//POST
HttpClient client = HttpClients.createDefault();
String url = "http://localhost:8080/billOrganizer/RecebeJsonAtualizaUsuario";
HttpPost request1 = new HttpPost(url);
List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
urlParameters.add(new BasicNameValuePair("json",json));
request1.setEntity(new UrlEncodedFormEntity(urlParameters));
HttpResponse resp = client.execute(request1);

out.println("<html>");
out.println("<body>");
out.println("Id " + usuario.getId() + " atualizado com sucesso.");
out.println("Voltar para o ");
out.println("<a href=\"menu.html\">Menu Principal</a>");
out.println(" ou ");
out.println("<a href=\"AtualizaUsuario.html\"> Atualizar outro Usuario</a>");
out.println("</a>");
out.println(".");
out.println("</body>");
out.println("</html>");


}

}
