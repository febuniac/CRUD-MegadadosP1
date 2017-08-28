
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

   

	@WebServlet("/open")
	public class Web extends HttpServlet {
	    protected void service (HttpServletRequest request,
	            HttpServletResponse response)
	            throws ServletException, IOException {
	        PrintWriter out = response.getWriter();
	        out.println("<html>");
	        out.println("<body>");
	        out.println("Opened!");
	        out.println("</body>");
	        out.println("</html>");
	     
;
	    
	    try{
	    	Process p = Runtime.getRuntime().exec("cd /opt/apache-tomcat-9.0.0.M11/bin/ && python servopython.py");
	    	
       	} catch (Exception ex) {
		   ex.printStackTrace();
		   }
        

	}
	}