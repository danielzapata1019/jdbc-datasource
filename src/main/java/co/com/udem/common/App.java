package co.com.udem.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.com.udem.jdbc.dao.CustomerDAO;
import co.com.udem.model.Customer;

public class App 
{
    public static void main( String[] args )
    {
    	// Se obtiene el contexto de la aplicacion 
    	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	 
    	// Se obtiene el servicio que implementa los servicios de la base de datos
        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
        
        // Se realiza el insert
        Customer customer = new Customer(1, "Pepe", 30);
        customerDAO.insert(customer);
    	
        // Se hace la consulta
        Customer customer1 = customerDAO.findByCustomerId(1);
        System.out.println(customer1);
    }
}
