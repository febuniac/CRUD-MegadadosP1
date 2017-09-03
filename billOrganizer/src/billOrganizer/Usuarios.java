package billOrganizer;

public class Usuarios {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRG() {
		return RG;
	}
	public void setRG(String rG) {
		RG = rG;
	}
	public String getEmissor() {
		return emissor;
	}
	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	private String id;
	private String RG;
	private String emissor;
	private String cpf;
	private String nome;
}
