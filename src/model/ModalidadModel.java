package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entidad.Modalidad;
import util.MySqlDBConexion;

public class ModalidadModel {

	public int insertaModalidad(Modalidad obj) {
		int salida = -1;

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			//1 Crear conexion
			conn = MySqlDBConexion.getConexion();
			//2 Crear sentencia SQL
			String sql = "INSERT INTO modalidad (nombre, numHombres, numMujeres, edadMaxima, edadMinima, sede, idDeporte, estado, fechaRegistro, fechaActualizacion) VALUES (?,?,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setString(2, obj.getNumHombres());
			pstm.setString(3, obj.getNumMujeres());
			pstm.setString(4, obj.getEdadMaxima());
			pstm.setString(5, obj.getEdadMinima());
			pstm.setString(6, obj.getSede());
			pstm.setInt(7, obj.getDeporte().getIdDeporte());
			pstm.setInt(8, obj.getEstado());
			pstm.setTimestamp(9, java.sql.Timestamp.valueOf(obj.getFechaRegistro()));
			pstm.setTimestamp(10, java.sql.Timestamp.valueOf(obj.getFechaActualizacion()));

			//3 Ejecutar sentencia SQL
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;

	}
}