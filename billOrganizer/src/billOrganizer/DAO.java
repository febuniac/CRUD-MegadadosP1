package billOrganizer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection connection = null;
	public DAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/billOrganizer", "root", "123456");
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
			stmt.setInt(1,usuario.getRG());
			stmt.setString(2, usuario.getEmissor());
			stmt.setInt(3, usuario.getCpf());
			stmt.setString(4, usuario.getNome());
			stmt.execute();
			stmt.close();
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
				usuario.setRG(rs.getInt("RG"));
				usuario.setEmissor(rs.getString("emissor"));
				usuario.setCpf(rs.getInt("cpf"));
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
	
	public void alteraUsuario(Usuarios usuario){
		String sql = "UPDATE Usuario SET" +
				"cpf=?, nome=?, where RG=?, emissor=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, usuario.getCpf());
			stmt.setString(2, usuario.getNome());
			stmt.setInt(3, usuario.getRG());
			stmt.setString(4, usuario.getEmissor());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeUsuario(Integer RG, String emissor){
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM Usuario WHERE RG=?, emissor=?");
			stmt.setInt(1, RG);
			stmt.setString(2, emissor);
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
