package com.controleFinanceiro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.h2.engine.Constants.UTF8;
import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.controleFinanceiro.model.Categoria;

public class CategoriaRepositoryH2Test {
	
	private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String USER = "sa";
	private static final String PASSWORD = "";

	private Connection connection;

	@BeforeClass
	public static void createSchema() throws Exception {
		RunScript.execute(JDBC_URL, USER, PASSWORD, "schema.sql", UTF8, false);
	}

	@Before
	public void insertRows() throws Exception {
		connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
		cleanCategoryTable();
		insert(new Categoria(1L, "Lazer"));
		insert(new Categoria(2L, "Gastos"));
	}
	
	private void insert(Categoria categoria) throws SQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("insert into categoria (nome) values (?)");
			statement.setString(1, categoria.getNome());
			statement.executeUpdate();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}	
	
	private void cleanCategoryTable() throws SQLException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM categoria");
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
	
	@Test
	public void deveriaTerMaisDeUmaCategoria(){
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from categoria");
			List<Categoria> categorias = new ArrayList<Categoria>();
			while(rs.next()){
				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getLong("codigo"));
				categoria.setNome(rs.getString("nome"));
				categorias.add(categoria);
			}
			
			assertTrue(categorias.size() > 1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	

}