package org.beginningee6.book.jpa;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.beginningee6.book.jpa.util.IntegrationTest;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

/**
 * Bookエンティティの永続化に関するテスト。
 */
@Category(IntegrationTest.class)
@RunWith(Arquillian.class)
public class BookIntegrationTest {
	
	private static final Logger logger = Logger.getLogger(BookIntegrationTest.class.getName());
	
	@Deployment
	public static Archive<?> createDeployment() {
		JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
			.addClass(Book.class)
			.addClass(BookIntegrationTest.class)
			.addClass(IntegrationTest.class)
			.addAsManifestResource("persistence.xml")
			.addAsManifestResource("jbossas-ds.xml")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

		logger.info(archive.toString(true));

		return archive;
	}
	
	@PersistenceContext
	EntityManager em;
	
	@Inject
	UserTransaction userTransaction;
	
	@Before
	public void setUp() throws Exception {
		clearData();
	}
	
	private void clearData() throws Exception {
		userTransaction.begin();
		em.joinTransaction();

		logger.info("Dumping old records...");
		
		em.createQuery("DELETE FROM Book").executeUpdate();
		userTransaction.commit();
	}
	
	/**
	 * Bookエンティティをデータベースに永続化するテスト。
	 */
	@Test
	public void testCreateABook() throws Exception {
		
		///// 準備 /////
		
		Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5F);
        book.setDescription("Science fiction comedy book");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);

        ///// テスト /////
        
        userTransaction.begin();
        em.joinTransaction();
        
        em.persist(book);
        
        userTransaction.commit();
        
        ///// 検証 /////
        
        assertThat(book.getId(), is(notNullValue()));        
	}
	
	/**
	 * Bookエンティティがデータベースに永続化されている状況で、
	 * その名前付きクエリによりそのエンティティがデータベースから
	 * 取得できることを確認するテスト。
	 */
	@Test
	public void testFindOneBook() throws Exception {
		
		///// 準備 /////
		
		Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5F);
        book.setDescription("Science fiction comedy book");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);

        userTransaction.begin();
        em.joinTransaction();
        
        em.persist(book);
        
        userTransaction.commit();

        ///// テスト /////
        em.clear();
        
        List<Book> books = em.createNamedQuery("findAllBooks", Book.class).getResultList();
        
        ///// 検証 /////
        
        assertThat(books.size(), is(1));

        Book persisted = books.get(0);
        assertThat(persisted.getTitle(), 		is("The Hitchhiker's Guide to the Galaxy"));
        assertThat(persisted.getPrice(), 		is(12.5F));
        assertThat(persisted.getDescription(), 	is("Science fiction comedy book"));
        assertThat(persisted.getIsbn(), 		is("1-84023-742-2"));
        assertThat(persisted.getNbOfPage(), 	is(354));
        assertThat(persisted.getIllustrations(),is(false));
	}	
}
