package com.controleFinanceiro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.h2.engine.Constants.UTF8;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.controleFinanceiro.model.Categoria;
import com.controleFinanceiro.service.CategoriaService;

import ch.qos.logback.core.joran.action.ParamAction;

public class CategoriaRepositoryH2Test {
	
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
	

//	@Before
//	public void insertRows() throws Exception {
//		connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
//		cleanCategoryTable();
//		insert(new Categoria(1L, "Lazer"));
//		insert(new Categoria(2L, "Gastos"));
//	}
	
//	private void insert(Categoria categoria) throws SQLException {
//		PreparedStatement statement = null;
//		try {
//			statement = connection.prepareStatement("insert into categoria (nome) values (?)");
//			statement.setString(1, categoria.getNome());
//			statement.executeUpdate();
//		} finally {
//			if (statement != null) {
//				try {
//					statement.close();
//				} catch (SQLException ignore) {
//				}
//			}
//		}
//	}	
	
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
	
//	@Test
//	public void deveriaTerMaisDeUmaCategoria(){
//		Statement statement = null;
//		try {
//			statement = connection.createStatement();
//			ResultSet rs = statement.executeQuery("select * from categoria");
//			List<Categoria> categorias = new ArrayList<Categoria>();
//			while(rs.next()){
//				Categoria categoria = new Categoria();
//				categoria.setCodigo(rs.getLong("codigo"));
//				categoria.setNome(rs.getString("nome"));
//				categorias.add(categoria);
//			}
//			
//			assertTrue(categorias.size() > 1);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
//	public Page<Categoria> buscaCategoriaPorFiltro(Categoria categoria, Pageable pageable){
//
//		//ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("nome", GenericPropertyMatchers.contains());
//		//Example<Categoria> example = Example.of(categoria, matcher);
//		
//        int paginaAtual = pageable.getPageNumber();
//        int totalRegistrosPorPagina = pageable.getPageSize();
//        int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
//        
//        StringBuilder sb = new StringBuilder();
//        
//        sb.append(" select categoria from com.controleFinanceiro.model.Categoria categoria ");
//        if (null != categoria.getCodigo()){
//        	
//        }
//        
//        
//        //adicionaOrdenacao(sb, pageable);
//        Query query = this.em.createQuery(sb.toString());
//        query.setFirstResult(primeiroRegistro);
//        query.setMaxResults(totalRegistrosPorPagina); 
//		List<Categoria> lista = query.getResultList();
//		
//		return new PageImpl<>(lista, pageable, 5);
//		
//	}
	
	public static EntityManager getEntityManager(){
		
		if (entityManager == null){
			final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("teste-unit");
			entityManager = entityManagerFactory.createEntityManager();
		}
		
		return entityManager;
		
	}
	
	public static void populaBanco(){
		getEntityManager().createNativeQuery("INSERT INTO categoria VALUES('1', 'Lazer')").executeUpdate();
		getEntityManager().createNativeQuery("INSERT INTO categoria VALUES('2', 'Gastos')").executeUpdate();
		getEntityManager().createNativeQuery("INSERT INTO categoria VALUES('3', 'Carro')").executeUpdate();
		
	}	
	
	@Test
	public void deveriaTerMaisDeUmaCategoriaCadastrada(){
		
		List<Categoria> categorias = getEntityManager().createQuery(" select categoria from com.controleFinanceiro.model.Categoria categoria ").getResultList();
		
		assertTrue(categorias.size() > 1);
		
	}
	
	
	public List<Categoria> buscaCategoriaPorFiltro(Categoria categoria){

		Pageable pageable = new Pageable() {
			
			@Override
			public Pageable previousOrFirst() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Pageable next() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean hasPrevious() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Sort getSort() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getPageSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getPageNumber() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getOffset() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Pageable first() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		HashMap<String, Object> paramentros = new HashMap<>();
		boolean temWhere = false;
		
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
        StringBuilder sb = new StringBuilder();
        
        sb.append(" select categoria from com.controleFinanceiro.model.Categoria categoria ");
        if (null != categoria.getCodigo() || (null != categoria.getNome() && !categoria.getNome().isEmpty())){
        	sb.append(" where ");
        }
        
        if (null != categoria.getCodigo()){
        	sb.append(" categoria.codigo = :codigo");
        	paramentros.put("codigo", categoria.getCodigo());
        	temWhere = true;
        }
        
        if (null != categoria.getNome() && !categoria.getNome().isEmpty()){
        	if (temWhere == true){
        		sb.append(" and ");
        	}
        	sb.append(" categoria.nome like :nome");
        	paramentros.put("nome", "%" + categoria.getNome() + "%");
        }
        
        Query query = getEntityManager().createQuery(sb.toString());
        paramentros.forEach((chave,valor) -> query.setParameter(chave, valor));
        query.setFirstResult(primeiroRegistro);
        query.setMaxResults(totalRegistrosPorPagina); 
		return query.getResultList();
		
	}	
	
	@Test
	public void deveriaTerCategoriaComNome(){
		
		Categoria categoria = new Categoria();
		categoria.setNome("Lazer");
		
		List<Categoria> categorias = buscaCategoriaPorFiltro(categoria);
		
		assertTrue(categorias.size() > 0);
		assertTrue(categorias.get(0).getNome().equals("Lazer"));
		
	}
	
	@Test
	public void deveriaTerCategoriaComCodigo1(){
		
		Categoria categoria = new Categoria();
		categoria.setCodigo(1L);
		
		List<Categoria> categorias = buscaCategoriaPorFiltro(categoria);
		
		assertTrue(categorias.size() > 0);
		
	}	
	
	@Test
	public void deveriaTerCategoriaComCodigoENome(){
		
		Categoria categoria = new Categoria();
		categoria.setCodigo(1L);
		categoria.setNome("Lazer");
		
		List<Categoria> categorias = buscaCategoriaPorFiltro(categoria);
		
		assertTrue(categorias.size() > 0);
		assertTrue(categorias.get(0).getNome().equals("Lazer"));
		
	}	
	
	@Test
	public void deveriaTerCategoria(){
		
		Categoria categoria = new Categoria();
		
		List<Categoria> categorias = buscaCategoriaPorFiltro(categoria);
		
		assertTrue(categorias.size() > 0);
		
	}		
		
	

}