package modelo.entidad;

public class Coche {
	private int id;
	private String marca;
	private String modelo;
	private String matricula;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Coche [id = " + id + "; marca = " + marca + "; modelo = " + modelo + "; matricula = " + matricula + "]";
	}
}