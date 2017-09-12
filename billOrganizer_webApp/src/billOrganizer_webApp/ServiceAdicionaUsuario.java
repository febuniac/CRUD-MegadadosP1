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
import org.json.simple.JSONObject;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;



@WebServlet("/ServiceAdicionaUsuario")
public class ServiceAdicionaUsuario extends HttpServlet {
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
 		String url = "http://localhost:8080/billOrganizer/RecebeJsonUsuario";
 		HttpPost request1 = new HttpPost(url);
 		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
 		urlParameters.add(new BasicNameValuePair("json",json));
 		request1.setEntity(new UrlEncodedFormEntity(urlParameters));
 		HttpResponse resp = client.execute(request1);
 		
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

 		
  }

}

	/*
FileWriter writeFile = null;

try{
	writeFile = new FileWriter("usuario.json");
	writeFile.write(json);
	writeFile.close();
}catch(IOException e){
	e.printStackTrace();
}
response.getWriter().write(json);
*/
	
/*
out.println("<html>");
out.println("<body>");
out.println("Id: " + usuario.getId() );
out.println("Nome: " + usuario.getNome());
out.println("RG: " + usuario.getRG());
out.println("Emissor: " + usuario.getEmissor() );
out.println("cpf: " + usuario.getCpf() );
out.println("</body>");
out.println("</html>");



URL url;
HttpURLConnection connection = null;
ObjectOutputStream out1;
try{
url = new URL("http://localhost:8080/billOrganizer/RecebeJsonUsuario");
connection = (HttpURLConnection) url.openConnection();
//connection.connect();
connection.setRequestMethod("POST");
connection.setRequestProperty("Content-Type", "applicationjson");
connection.setUseCaches(false);
connection.setDoInput(true);
connection.setDoOutput(true);
OutputStream os = connection.getOutputStream();
OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
System.out.println(json.toString());
osw.write(json.toString());
osw.flush();
osw.close();
if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
	 System.out.println("Ok response");
} else{
	 System.out.println("Bad response");
}
}catch(Exception ex){
ex.printStackTrace();
}

URL url = new URL("http://localhost:8080/billOrganizer/RecebeJsonUsuario");
URLConnection connection = url.openConnection();
connection.setDoOutput(true);

OutputStreamWriter out1 = new OutputStreamWriter(connection.getOutputStream());

out1.write("usuario.json");
out1.close();
*/

         
         /*
     
     String url = "http://localhost:8080/billOrganizer/RecebeJsonUsuario";
     String charset = "UTF-8"; 
     String id = "7";
     String RG = "70";
     String emissor = "SSP";
     String cpf = "33";
     String query =  String.format("id=%s&RG=%s&emissor=%s&cpf=%s",
    		 URLEncoder.encode(id, charset),
    		 URLEncoder.encode(RG, charset),
    		 URLEncoder.encode(emissor, charset),
    		 URLEncoder.encode(cpf, charset));
     
     URLConnection connection = new URL(url + "?" + query).openConnection();
     connection.setRequestProperty("Accept-Charset", charset);
     InputStream response = connection.getInputStream();
     try  (OutputStream output = connection.getOutputStream()) {
    	 output.write(query.getBytes(charset));
     }
     
     InputStream response1 = connection.getInputStream();
     System.out.println(response1);
     } 
     
}
*/
/*

@WebServlet("/AdicionaUsuario")
public class ServiceAdicionaUsuario  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void service (HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
												IOException{
		JSONObject jsonObject = new JSONObject();
		try{
			jsonObject.set("nome", request.getParameter("nome"));
			jsonObject.set("cpf", request.getParameter("cpf"));
			
			// Montar request para o business layer
			// precisa da URL da business layer
			// Mandar esse request
			vaiRequest();
			
			pegaResponse();
			
			monta o response
			
		} catch (org.json.simple.parser.ParseException e1){
			e1.printStackTrace();
		}
	}
*/