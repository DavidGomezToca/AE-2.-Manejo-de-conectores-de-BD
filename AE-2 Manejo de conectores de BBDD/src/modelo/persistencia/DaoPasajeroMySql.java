package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero {
	private Connection conexion;

	public boolean abrirConexion() {
		String url = "jdbc:mysql://localhost:3306/CochesAE2";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean cerrarConexion() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean alta(Pasajero p) {
		if (!abrirConexion()) {
			return false;
		}
		boolean alta = true;
		String query = "INSERT INTO pasajeros (NOMBRE,EDAD,PESO) VALUES(?,?,?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());

			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + p);
			alta = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return alta;
	}

	@Override
	public boolean baja(int id) {
		if (!abrirConexion()) {
			return false;
		}
		boolean borrado = true;
		String query = "delete from pasajeros where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			System.out.println("baja -> No se ha podido dar de baja el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado;
	}

	@Override
	public boolean modificar(Pasajero p) {
		if (!abrirConexion()) {
			return false;
		}
		boolean modificado = true;
		String query = "UPDATE pasajeros SET NOMBRE=?, EDAD=?, PESO=? WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());
			ps.setInt(4, p.getId());
			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				modificado = false;
		} catch (SQLException e) {
			System.out.println("modificar -> error al modificar el " + " pasajero " + p);
			modificado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return modificado;
	}

	@Override
	public Pasajero obtener(int id) {
		if (!abrirConexion()) {
			return null;
		}
		Pasajero pasajero = null;
		String query = "SELECT ID,NOMBRE,EDAD,PESO FROM pasajeros WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getInt(4));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el " + "pasajero con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return pasajero;
	}

	@Override
	public List<Pasajero> listar() {
		if (!abrirConexion()) {
			return null;
		}
		List<Pasajero> listaPasajeros = new ArrayList<>();
		String query = "SELECT ID,NOMBRE,EDAD,PESO,COCHE_ID FROM pasajeros";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getInt(4));
				pasajero.setCoche_id(rs.getInt(5));
				listaPasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los " + "pasajeros");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaPasajeros;
	}

	@Override
	public boolean añadirEnCoche(Pasajero p) {
		if (!abrirConexion()) {
			return false;
		}
		boolean añadido = true;
		String query = "UPDATE pasajeros SET COCHE_ID=? WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, p.getCoche_id());
			ps.setInt(2, p.getId());
			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				añadido = false;
		} catch (SQLException e) {
			System.out.println("modificar -> error al asociar el " + " pasajero " + p.getId());
			añadido = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return añadido;
	}

	@Override
	public boolean borrarDeCoche(int id) {
		if (!abrirConexion()) {
			return false;
		}
		boolean retirado = true;
		String query = "UPDATE pasajeros SET COCHE_ID=NULL WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			int numeroFilasAfectadas = ps.executeUpdate();
			if (numeroFilasAfectadas == 0)
				retirado = false;
		} catch (SQLException e) {
			System.out.println("modificar -> error al asociar el " + " pasajero " + id);
			retirado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return retirado;
	}

	@Override
	public List<Pasajero> listarPorCoche(int id) {
		if (!abrirConexion()) {
			return null;
		}
		List<Pasajero> listaPasajeros = new ArrayList<>();
		String query = "SELECT ID,NOMBRE,EDAD,PESO,COCHE_ID FROM pasajeros WHERE COCHE_ID = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getInt(4));
				pasajero.setCoche_id(rs.getInt(5));
				listaPasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los " + "pasajeros");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaPasajeros;
	}
}