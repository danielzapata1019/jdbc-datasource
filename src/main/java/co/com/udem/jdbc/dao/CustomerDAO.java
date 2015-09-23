package co.com.udem.jdbc.dao;

import co.com.udem.model.Customer;

/**
 * Definicion de los servicios ofrecidos desde la base de datos 
 * @author Milton
 */
public interface CustomerDAO {
	
	/**
	 * Inserta un registro nuevo en la base de datos 
	 * @param customer
	 */
	public void insert(Customer customer);
	
	/**
	 * Consulta la información de la base de datos asociada al id del cliente 
	 * @param custId Id del cliente
	 * @return Entidad cliente
	 */
	public Customer findByCustomerId(int custId);
}




