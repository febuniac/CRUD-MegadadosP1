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
	"(id, RG, emissor, cpf, nome) values(?, ?, ?, ?, ?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getId());
			stmt.setString(2,usuario.getRG());
			stmt.setString(3, usuario.getEmissor());
			stmt.setString(4, usuario.getCpf());
			stmt.setString(5, usuario.getNome());
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
				usuario.setId(rs.getString("id"));
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
	
	public void alteraUsuario(Usuarios usuario){
		String sql = "UPDATE Usuario SET " +
				"cpf=?, nome=?, RG=?, emissor=? WHERE id=?";
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
	
	public void removeUsuario(String id){
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("DELETE FROM Usuario WHERE id=?");
			stmt.setString(1, id);
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
