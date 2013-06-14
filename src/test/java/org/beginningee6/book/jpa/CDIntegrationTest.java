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
 * CDエンティティの永続化に関するテスト。
 */
@Category(IntegrationTest.class)
@RunWith(Arquillian.class)
public class CDIntegrationTest {
	
	private static final Logger logger = Logger.getLogger(CDIntegrationTest.class.getName());
	
	@Deployment
	public static Archive<?> createDeployment() {
		JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
			.addClass(CD.class)
			.addClass(CDIntegrationTest.class)
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
		
		em.createQuery("DELETE FROM CD").executeUpdate();
		userTransaction.commit();
	}
	
	/**
	 * CDエンティティをデータベースに永続化するテスト。
	 */
	@Test
	public void testCreateACD() throws Exception {
		
		///// 準備 /////
        CD cd = new CD();
        cd.setTitle("Zoot Allures");
        cd.setPrice(12.5F);
        cd.setDescription("Released in October 1976, it is mostly a studio album");
        cd.setGender("male");
        cd.setMusicCompany("RCA Records");
        cd.setNumberOfCDs(2);
        cd.setTotalDuration(74.5F);
        cd.setCover("Cover Image".getBytes("UTF-8"));

        ///// テスト /////
        
        userTransaction.begin();
        em.joinTransaction();
        
        em.persist(cd);
        
        userTransaction.commit();
        
        ///// 検証 /////
        
        assertThat(cd.getId(), is(notNullValue()));        
	}
	
	/**
	 * CDエンティティがデータベースに永続化されている状況で、
	 * その名前付きクエリによりそのエンティティがデータベースから
	 * 取得できることを確認するテスト。
	 */
	@Test
	public void testFindOneCD() throws Exception {
		
		///// 準備 /////
        CD cd = new CD();
        cd.setTitle("Zoot Allures");
        cd.setPrice(12.5F);
        cd.setDescription("Released in October 1976, it is mostly a studio album");
        cd.setGender("male");
        cd.setMusicCompany("RCA Records");
        cd.setNumberOfCDs(2);
        cd.setTotalDuration(74.5F);
        cd.setCover("Cover Image".getBytes("UTF-8"));

        userTransaction.begin();
        em.joinTransaction();
        
        em.persist(cd);
        
        userTransaction.commit();

        ///// テスト /////
        em.clear();
        
        List<CD> cds = em.createNamedQuery("findAllCDs", CD.class).getResultList();
        
        ///// 検証 /////
        
        assertThat(cds.size(), is(1));
        
        CD persisted = cds.get(0);
        assertThat(persisted.getTitle(), 
        		is("Zoot Allures"));
        assertThat(persisted.getPrice(),
        		is(12.5F));
        assertThat(persisted.getDescription(),
        		is("Released in October 1976, it is mostly a studio album"));
        assertThat(persisted.getGender(),
        		is("male"));
        assertThat(persisted.getMusicCompany(),
        		is("RCA Records"));
        assertThat(persisted.getNumberOfCDs(),
        		is(2));
        assertThat(new String(persisted.getCover(), "UTF-8"),
        		is("Cover Image"));
        
	}
	
}
