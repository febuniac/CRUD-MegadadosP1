package billOrganizer_webApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
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
import com.google.gson.reflect.TypeToken;

@WebServlet("/ServiceListaUsuarios")
public class ServiceListaUsuarios extends HttpServlet {
    protected void service(HttpServletRequest request,
            HttpServletResponse response)
throws IOException, ServletException {
PrintWriter out = response.getWriter();
Usuarios usuario = new Usuarios();
/*
usuario.setId(request.getParameter("id"));
usuario.setNome(request.getParameter("nome"));
usuario.setRG(request.getParameter("RG"));
usuario.setEmissor(request.getParameter("emissor"));
usuario.setCpf(request.getParameter("cpf"));

String json = new Gson().toJson(usuario);
System.out.println(json);
*/
//POST Request
HttpClient client = HttpClients.createDefault();
String url = "http://localhost:8080/billOrganizer/DevolveJsonListaUsuarios";
HttpPost request1 = new HttpPost(url);
HttpResponse resp = client.execute(request1);


//Post Response
Type collectionType = new TypeToken<Collection<channelSearchEnum>>(){}.getType();
Collection<channelSearchEnum> enums = gson.fromJson(yourJson, collectionType);
Gson gson = new Gson();
if(resp.getStatusLine().getStatusCode() == 200) {
    InputStreamReader stream = new InputStreamReader(resp.getEntity().getContent());
    BufferedReader br = new BufferedReader(stream);
    String line;
    out.println("<html><body><table border='1'>");
    out.println("<tr><td>Nome</td>" + 
                "<td>Emissor</td><td>CPF</td><td>RG</td></tr>");
    while((line = br.readLine()) != null) {
        usuario = (Usuarios) gson.fromJson(line, Usuarios.class);
        out.println("<td >" + usuario.getNome() + "</td>");
        out.println("<td >" + usuario.getEmissor() + "</td>");
        out.println("<td >" + usuario.getCpf() + "</td></tr>");
        out.println("<td>" + usuario.getRG() + "</td></tr>");
        }
    }

/*
out.println("</table></body></html>");
out.println("<html>");
out.println("<body>");
out.println(usuario.getNome() + " adicionado com sucesso.");
out.println("Voltar para o ");
out.println("<a href=\"menu.html\">Menu Principal</a>");
out.println(" ou ");
out.println("<a href=\"CriaUsuario.html\"> Adicionar novo Usuario</a>");
out.println("</a>");
out.println("</body>");
out.println("</html>");

*/

}

}

