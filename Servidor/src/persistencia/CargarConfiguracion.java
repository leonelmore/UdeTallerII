package persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import excepciones.ConfiguracionException;

 /* * * * * * * ** * * * * * * * * * * * * * * * * * *  
 * Ejemplo de uso:
 *  String rutaYArchivoPersistencia = LoadSettings.getData.rutaYArchivoPersistencia;
 *  String passwordAdmin = LoadSettings.getData.passwordAdmin;
 * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class CargarConfiguracion {

	static CargarConfiguracion instancia;

	public static CargarConfiguracion getData() throws ConfiguracionException {
		if (instancia == null){
			instancia = new CargarConfiguracion();
		}
		return instancia;
	}
	
	//Para cada variable nueva, agregar la variable a cargar aqui
	public String rutaYArchivoPersistencia;
	public int puertoRMI;
	public String ipServidor;
	public String nombreObjRMI;
	
	private CargarConfiguracion() throws ConfiguracionException {
		Properties p = new Properties();
		try{	
			p.load(new FileInputStream("conf/config.properties"));
			
			//Para cada variable nueva, agregar una línea aqui
			rutaYArchivoPersistencia = p.getProperty("rutaYArchivoPersistencia");
			puertoRMI = Integer.parseInt(p.getProperty("puertoRMI"));
			ipServidor = p.getProperty("ipServidor");
			nombreObjRMI = p.getProperty("nombreObjRMI");

		}
		catch (FileNotFoundException e){
			throw new ConfiguracionException("No se encuentra el archivo de configuración.",e);
		}
		catch (NumberFormatException e){
			throw new ConfiguracionException("El formato de número no es correcto.",e);
		}
		catch (Exception e){
			throw new ConfiguracionException("Ha ocurrido una excepcion generica.",e);				
		}	
	}
	
	
}
