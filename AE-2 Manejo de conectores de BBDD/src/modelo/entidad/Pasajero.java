package modelo.entidad;

public class Pasajero {
	private int id;
	private String nombre;
	private int edad;
	private double peso;
	private int coche_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getCoche_id() {
		return coche_id;
	}

	public void setCoche_id(int coche_id) {
		this.coche_id = coche_id;
	}

	@Override
	public String toString() {
		return "Pasajero [id = " + id + "; nombre = " + nombre + "; edad = " + edad + "; peso = " + peso + "; coche_id = " + coche_id + "]";
	}
}