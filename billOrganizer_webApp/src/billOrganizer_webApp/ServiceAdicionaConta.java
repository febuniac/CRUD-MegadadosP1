package billOrganizer_webApp;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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



@WebServlet("/ServiceAdicionaConta")
public class ServiceAdicionaConta extends HttpServlet {
     protected void service(HttpServletRequest request,
                         HttpServletResponse response)
     throws IOException, ServletException {
         PrintWriter out = response.getWriter();
         Contas conta = new Contas();
         //conta.setId(request.getParameter("id"));
         conta.setEmissor(request.getParameter("emissor"));
         String vencimento = request.getParameter("vencimento");
         
         Date data;
     
    	 try {
			data = new SimpleDateFormat("yyyy-MM-dd").parse(vencimento);
			System.out.println(data);
			Calendar dataVencimento = Calendar.getInstance();
	    	dataVencimento.setTime(data);
	    	conta.setVencimento(dataVencimento);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
   
         conta.setValor(Integer.valueOf(request.getParameter("valor")));
         
        String json = new Gson().toJson(conta);
 		System.out.println(json);
 		
 		//POST
 		HttpClient client = HttpClients.createDefault();
 		String url = "http://localhost:8080/billOrganizer/RecebeJsonConta";
 		HttpPost request1 = new HttpPost(url);
 		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
 		urlParameters.add(new BasicNameValuePair("json",json));
 		request1.setEntity(new UrlEncodedFormEntity(urlParameters));
 		HttpResponse resp = client.execute(request1);
 		
 		out.println("<html>");
 		out.println("<body>");
 		out.println(conta.getEmissor() + " adicionado com sucesso.");
 		out.println("Voltar para o ");
 		out.println("<a href=\"menu.html\">Menu Principal</a>");
 		out.println(" ou ");
 		out.println("<a href=\"CriaConta.html\"> Adicionar nova Conta</a>");
 		out.println("</a>");
 		out.println("</body>");
 		out.println("</html>");

 		
  }

}
