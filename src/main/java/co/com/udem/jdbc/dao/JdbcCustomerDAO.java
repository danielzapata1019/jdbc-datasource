package co.com.udem.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import co.com.udem.model.Customer;

/**
 * Implementación de los servicios ofrecidos desde la base de datos
 * 
 * @author Milton
 */
public class JdbcCustomerDAO implements CustomerDAO {

	/** Datasource con la conexion a la base de datos. */
	private DataSource dataSource;

	/**
	 * Establece la conexion a la base de datos a partir del Datasource
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void insert(Customer customer) {

		// Sentencia de base de datos
		String sql = "INSERT INTO customer (custId, name, age) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// Se obtiene la conexion a la base de datos desde el datasource
			conn = dataSource.getConnection();

			// Se adicionan los parametros de ejecucion a la sentencia del datasource
			ps = conn.prepareStatement(sql);
			ps.setInt(1, customer.getCustId());
			ps.setString(2, customer.getName());
			ps.setInt(3, customer.getAge());

			// Ejecuta la sentencia en la base de datos
			ps.executeUpdate();

			// Cierra el recurso
			ps.close();
						
		} catch (SQLException e) {
			// Se capturan los errores que provienen de la base de datos
			throw new RuntimeException(e);

		} finally {
			// Se liberar la conexion
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Customer findByCustomerId(int custId) {

		// Sentencia de base de datos
		String sql = "SELECT * FROM customer WHERE custId = ?";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			// Se obtiene la conexion a la base de datos desde el datasource
			conn = dataSource.getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			Customer customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new Customer(rs.getInt("custId"),
						rs.getString("NAME"), rs.getInt("Age"));
			}
			rs.close();
			ps.close();
			return customer;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
