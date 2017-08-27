package billOrganizer;

import java.util.Calendar;

public class Usuarios {
	public Integer getRG() {
		return RG;
	}
	public void setRG(Integer rG) {
		RG = rG;
	}
	public String getEmissor() {
		return emissor;
	}
	public void setEmissor(String emissor) {
		this.emissor = emissor;
	}
	public Integer getCpf() {
		return cpf;
	}
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	private Integer RG;
	private String emissor;
	private Integer cpf;
	private String nome;
}
