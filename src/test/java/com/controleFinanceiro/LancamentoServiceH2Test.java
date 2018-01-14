package com.controleFinanceiro;

import static org.h2.engine.Constants.UTF8;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.BeforeClass;
import org.junit.Test;

import com.controleFinanceiro.model.Categoria;
import com.controleFinanceiro.model.Lancamento;
import com.controleFinanceiro.model.Pessoa;
import com.controleFinanceiro.model.TipoLancamento;

public class LancamentoServiceH2Test {
	
//	private static EntityManager entityManager = null;
//	
//	private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
//	private static final String USER = "sa";
//	private static final String PASSWORD = "";
//
//	private Connection connection;
//
//	@BeforeClass
//	public static void createSchema() throws Exception {
//		RunScript.execute(JDBC_URL, USER, PASSWORD, "schema.sql", UTF8, false);
//    	getEntityManager().getTransaction().begin();
//    	populaBanco();
//	}	
//	
//	private void cleanCategoryTable() throws SQLException {
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
//			statement.executeUpdate("DELETE FROM categoria");
//		} finally {
//			if (statement != null) {
//				try {
//					statement.close();
//				} catch (SQLException ignore) {
//				}
//			}
//		}
//	}
//	
//	private DataSource dataSource() {
//		JdbcDataSource dataSource = new JdbcDataSource();
//		dataSource.setURL(JDBC_URL);
//		dataSource.setUser(USER);
//		dataSource.setPassword(PASSWORD);
//		return dataSource;
//	}	
//	
//	public static EntityManager getEntityManager(){
//		
//		if (entityManager == null){
//			final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("teste-unit");
//			entityManager = entityManagerFactory.createEntityManager();
//		}
//		
//		return entityManager;
//		
//	}
//	
//	public static void populaBanco(){
//		Categoria c1 = new Categoria();
//		c1.setNome("Lazer");
//		getEntityManager().persist(c1);
//		
//		Pessoa p1 = new Pessoa();
//		p1.setAtivo(true);
//		p1.setNome("João");
//		getEntityManager().persist(p1);
//				
//		getEntityManager().persist(new Lancamento(1L, "Primeiro lançamento", LocalDate.now(), LocalDate.now(), new BigDecimal(0), "Observação", TipoLancamento.DESPESA, c1, p1));
//		
//	}	
	
	@Test
	public void teste(){
		
	}

}
