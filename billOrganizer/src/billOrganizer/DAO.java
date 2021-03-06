package billOrganizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.Calendar;

public class DAO {
	private Connection connection = null;
	
	public DAO() throws IOException{
	Properties config = new Properties();
	config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

	String url = config.getProperty("url");
	String username = config.getProperty("username");
	String password = config.getProperty("password");
	
	System.out.println(username);
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		connection = DriverManager.getConnection(url, username, password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}


	public void adicionaUsuario(Usuarios usuario){
		String sql = "INSERT INTO usuario" +
	"(RG, emissor, cpf, nome) values(?, ?, ?, ?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			//stmt.setString(1, usuario.getId());
			stmt.setString(1,usuario.getRG());
			stmt.setString(2, usuario.getEmissor());
			stmt.setString(3, usuario.getCpf());
			stmt.setString(4, usuario.getNome());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void adicionaConta(Contas conta){
		String sql = "INSERT INTO conta" +
	"(emissor, vencimento, valor, usuario_id, status) values(?, ?, ?, ?, ?)";
		PreparedStatement stmt;
		try{
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, conta.getEmissor());
			stmt.setDate(2, new Date(conta.getVencimento().getTimeInMillis()));
			stmt.setInt(3, conta.getValor());
			stmt.setInt(4, conta.getUsuario_id());
			stmt.setString(5, conta.getStatus());
			stmt.execute();
			stmt.close();
			System.out.println("DAO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Usuarios> getListaUsuarios(){
		List<Usuarios> usuarios = new ArrayList<Usuarios>();
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Usuario");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Usuarios usuario = new Usuarios();
				usuario.setId(rs.getString("usuario_id"));
				usuario.setRG(rs.getString("RG"));
				usuario.setEmissor(rs.getString("emissor"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarios;		
	}
	
	public List<Contas> getListaContas(){
		List<Contas> contas = new ArrayList<Contas>();
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Conta");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Contas conta = new Contas();
				conta.setId(rs.getInt("conta_id"));
				conta.setEmissor(rs.getString("emissor"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("vencimento"));
				conta.setVencimento(data);
				conta.setValor(Integer.valueOf(rs.getString("valor")));
				conta.setUsuario_id(rs.getInt("usuario_id"));
				conta.setStatus(rs.getString("status"));
				contas.add(conta);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contas;		
	}

	public List<ContasUsuario> getListaContasUsuario(int usuario_id){
		
		List<ContasUsuario> contasUsuario = new ArrayList<ContasUsuario>();
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT u.nome, c.conta_id, c.emissor, c.valor, c.vencimento, c.usuario_id, c.status FROM conta c, usuario u WHERE c.usuario_id = u.usuario_id AND u.usuario_id =?");
			stmt.setInt(1, usuario_id);
			//stmt.execute();
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				ContasUsuario contaUsuario = new ContasUsuario();
				contaUsuario.setConta_id(rs.getInt("conta_id"));
				contaUsuario.setEmissor(rs.getString("emissor"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("vencimento"));
				contaUsuario.setVencimento(data);
				contaUsuario.setValor(Integer.valueOf(rs.getString("valor")));
				contaUsuario.setNome(rs.getString("nome"));
				contaUsuario.setUsuario_id(Integer.valueOf(rs.getString("usuario_id")));
				contaUsuario.setStatus(rs.getString("status"));
				contasUsuario.add(contaUsuario);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contasUsuario;		
	}
	
	public void alteraUsuario(Usuarios usuario){
		String sql = "UPDATE Usuario SET " +
				"cpf=?, nome=?, RG=?, emissor=? WHERE usuario_id=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getRG());
			stmt.setString(4, usuario.getEmissor());
			stmt.setString(5, usuario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alteraConta(Contas conta){
		String sql = "UPDATE Conta SET "+
				"emissor=?, vencimento=?, valor=?, usuario_id=?, status=? WHERE conta_id=?";
		PreparedStatement stmt;
		try{
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, conta.getEmissor());
			stmt.setDate(2, new Date(conta.getVencimento().getTimeInMillis()));
			stmt.setInt(3, conta.getValor());
			stmt.setInt(4, conta.getUsuario_id());
			stmt.setString(5, conta.getStatus());
			stmt.setInt(6, conta.getId());
			stmt.execute();
			stmt.close();
			System.out.println("DAO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeUsuario(String id){
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM Usuario WHERE usuario_id=?");
			stmt.setString(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeConta(int id){
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM Conta WHERE conta_id=?");
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
