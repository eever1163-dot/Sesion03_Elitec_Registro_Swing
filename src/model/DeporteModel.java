package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidad.Deporte;
import util.MySqlDBConexion;

public class DeporteModel {

	public List<Deporte> ListaTodos() {
		List<Deporte> lista = new ArrayList<Deporte>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			//1 Crear conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Crear sentencia SQL
			String sql = "SELECT * FROM deporte";
			pstm = conn.prepareStatement(sql);
			
			//3 Ejecutar sentencia SQL
			rs = pstm.executeQuery();
			while (rs.next()) {
				Deporte d = new Deporte();
				d.setIdDeporte(rs.getInt("idDeporte"));
				d.setNombre(rs.getString("nombre"));
				lista.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}
}