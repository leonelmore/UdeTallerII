package logica.VOs;

import java.io.Serializable;

public class VOLogin implements Serializable{
	private String usuario;
	private String codigo;
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	

}
