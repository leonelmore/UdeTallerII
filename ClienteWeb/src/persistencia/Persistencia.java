package persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import excepciones.CargarDatosException;
import excepciones.GuardarDatosException;
import logica.Fachada;

public class Persistencia {
	static public void guardarDatos(String nombreYRutaArchivo, Fachada unaFachada) throws GuardarDatosException {
		FileOutputStream f = null;
		ObjectOutputStream o = null;
		try {
			f = new FileOutputStream(nombreYRutaArchivo);
			o = new ObjectOutputStream(f);
			o.writeObject(unaFachada);
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			throw new GuardarDatosException("No se encuentra el archivo " + nombreYRutaArchivo, e);
		} catch (IOException e) {
			try {
				//intenta cerrar el flujo y el archivo.
				o.close();
				f.close();
			}
			catch (Exception e1){
				//No hacer nada
			}
			throw new GuardarDatosException("Error accediendo al archivo " + nombreYRutaArchivo, e);
		}
		
	}
	
	static public Fachada recuperarDatos(String nombreYRutaArchivo) throws CargarDatosException {
		FileInputStream f = null;
		ObjectInputStream o = null;
		Fachada unaFachada = null;
		try {
			f = new FileInputStream(nombreYRutaArchivo);
			o = new ObjectInputStream(f);
			unaFachada = (Fachada) o.readObject();
			o.close();
			f.close();
		} catch (FileNotFoundException e) {
			throw new CargarDatosException("No se encuentra el archivo " + nombreYRutaArchivo,e);
		} catch (IOException e) {
			try {
				//intenta cerrar el flujo y el archivo.
				o.close();
				f.close();
			}
			catch (Exception e1){
				//No hacer nada
			}
			throw new CargarDatosException("Error accediendo al archivo " + nombreYRutaArchivo,e);
		} catch (ClassNotFoundException e) {
			try {
				//intenta cerrar el flujo y el archivo.
				o.close();
				f.close();
			}
			catch (Exception e1){
				//No hacer nada
			}
			throw new CargarDatosException("Error indeterminado.",e);
		}
		return unaFachada;
	}
}
