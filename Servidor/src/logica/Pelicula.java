package logica;

public class Pelicula {

	//public static void main(String[] args) {
		
	private String titulo;
	private String descripcion;
	
	public Pelicula (String tit, String desc)  //Constructor Pelicula
	{
		this.titulo = tit;
		this.descripcion= desc;
	}
	
	public String getTitulo()
	{
		return titulo;
	}
	
	public void setTitulo( String titulo){
		this.titulo= titulo;
	}
	
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion=descripcion;
	}
	
	
	//}

}
