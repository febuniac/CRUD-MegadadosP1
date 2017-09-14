package billOrganizer_webApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

@WebServlet("/ServiceListaContas")
public class ServiceListaContas extends HttpServlet {
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
String url = "http://localhost:8080/billOrganizer/DevolveJsonListaContas";
HttpPost request1 = new HttpPost(url);
HttpResponse resp = client.execute(request1);


//Post Response
Gson gson = new Gson();

if(resp.getStatusLine().getStatusCode() == 200) {
    InputStreamReader stream = new InputStreamReader(resp.getEntity().getContent());
    BufferedReader br = new BufferedReader(stream);
    String line;
    out.println("<html><body><table border='1'>");
    out.println("<tr><td>Id</td><td>Emissor</td>" + 
                "<td>Data</td><td>Valor</td><td>Atualizar</td><td>Remover</td></tr>");
    
    //usuario = (Usuarios) gson.fromJson(line, Usuarios.class);
	JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject;
	try {

		JSONArray array = (JSONArray) jsonParser.parse(br.readLine());
		for (int i = 0; i < array.size(); i++) {
		    JSONObject object = (JSONObject) array.get(i);
		    out.println("<td >" + object.get("id") + "</td>");
		    out.println("<td >" + object.get("emissor") + "</td>");
		    JSONObject objectV = (JSONObject) object.get("vencimento");
		    int year = Integer.valueOf(objectV.get("year").toString());
	        int month = Integer.valueOf(objectV.get("month").toString());
	        int dayOfMonth = Integer.valueOf(objectV.get("dayOfMonth").toString());
		    out.println("<td >" + dayOfMonth+"/"+month+"/"+year + "</td>");
		    out.println("<td >" + object.get("valor") + "</td></tr>");
		    out.println("<td></td></tr>");
		    out.println("<td></td></tr>");
		    //object.get("event_title");
		}
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	out.println("Voltar para o ");
	out.println("<a href=\"menu.html\">Menu Principal</a>");
	out.println(".");
	out.println("</table></body></html>");
    }
}
}