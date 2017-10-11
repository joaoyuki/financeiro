/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controleFinanceiro;

import static com.controleFinanceiro.EnderecoServiceH2Test.getEntityManager;
import com.controleFinanceiro.model.Endereco;
import com.controleFinanceiro.model.Pessoa;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import static org.h2.engine.Constants.UTF8;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Administrador
 */
public class PessoaServiceH2Test {
    
        	private static EntityManager entityManager = null;
	
	private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String USER = "sa";
	private static final String PASSWORD = "";

	private Connection connection;

	@BeforeClass
	public static void createSchema() throws Exception {
		RunScript.execute(JDBC_URL, USER, PASSWORD, "schema.sql", UTF8, false);
    	getEntityManager().getTransaction().begin();
    	populaBanco();
	}
        
	private void cleanCategoryTable() throws SQLException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM pessoa");
                        
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
	
	private DataSource dataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(JDBC_URL);
		dataSource.setUser(USER);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}	

	public static EntityManager getEntityManager(){
		
		if (entityManager == null){
			final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("teste-unit");
			entityManager = entityManagerFactory.createEntityManager();
		}
		
		return entityManager;
		
	}
        
	public static void populaBanco(){

            getEntityManager().createNativeQuery("INSERT INTO endereco VALUES(1, 1, 'RUa teste', '123', 'Fundo', 'Km 18', '123456789', 'Osasco', 'SP')").executeUpdate();
            Endereco endereco = getEntityManager().find(Endereco.class, 1L);
            
            getEntityManager().persist(new Pessoa("João", true, endereco));
            getEntityManager().persist(new Pessoa("Felipe", true, endereco));
            getEntityManager().persist(new Pessoa("Rodrigues", false, endereco));
	}  
        
        @Test
        public void testa(){
            
        }
    
}
