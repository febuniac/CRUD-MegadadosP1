
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection connection = null;
	public DAO() {
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			connection = DriverManager.getConnection(
			"jdbc:mysql://localhost/dados", "root", "123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void adiciona(Pessoas pessoa) {
		String sql = "INSERT INTO Pessoas" +
		"(nome,emissor,cpf,rg) values(?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			stmt.setString(1,pessoa.getNome());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.setString(2,pessoa.getEmissor());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setInt(3,pessoa.getCpf());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			stmt.setInt(4,pessoa.getRg());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Pessoas> getLista() {

	List<Pessoas> pessoas = new ArrayList<Pessoas>();

		PreparedStatement stmt = null;
		try {
			stmt = connection.
			    prepareStatement("SELECT * FROM Pessoas");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (rs.next()) {
				Pessoas pessoa = new Pessoas();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEmissor(rs.getString("emissor"));
				pessoa.setCpf(rs.getInt("cpf"));
				pessoa.setRg(rs.getInt("rg"));
				pessoas.add(pessoa);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pessoas;
	}
	public void altera(Pessoas pessoa) {
		/*Passaporte*/
		String sql = "UPDATE Pessoas SET " +
	          "nome=?, emissor=?, cpf=?, rg=?, WHERE id=?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(1, pessoa.getNome());
		} catch (SQLException e5) {
			// TODO Auto-generated catch block
			e5.printStackTrace();
		}
		try {
			stmt.setString(2,pessoa.getEmissor());
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		try {
			stmt.setInt(3,pessoa.getCpf());
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		try {
			stmt.setInt(4,pessoa.getRg());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			stmt.setInt(5, pessoa.getId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void remove(Integer id) {
		PreparedStatement stmt = null;
		try {
			stmt = connection
			   .prepareStatement("DELETE FROM Pessoas WHERE id=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setLong(1, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void removebyname(String nome) {
		PreparedStatement stmt = null;
		try {
			stmt = connection
			   .prepareStatement("DELETE FROM Pessoas WHERE nome=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.setString(1, nome);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closebyname() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}