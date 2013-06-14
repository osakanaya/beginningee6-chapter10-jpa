package org.beginningee6.book.jpa;

import org.beginningee6.book.jpa.util.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@Category(UnitTest.class)
public class BookTest {
	@Test
	public void testDefaultConstructor() throws Exception {
		// Set Up
		Book book = new Book();

		book.setDescription("book description");
		book.setIllustrations(true);
		book.setIsbn("book isbn");
		book.setNbOfPage(111);
		book.setPrice(22.2F);
		book.setTitle("book title");
		
		// Exercise and Verify
		assertThat(book.getDescription(), is("book description"));
		assertThat(book.getIllustrations(), is(true));
		assertThat(book.getIsbn(), is("book isbn"));
		assertThat(book.getNbOfPage(), is(111));
		assertThat(book.getPrice(), is(22.2F));
		assertThat(book.getTitle(), is("book title"));
		assertThat(book.getId(), is(nullValue()));
	}
	
	@Test
	public void testAugumentedConstructor() throws Exception {
		// Set up
		Book book = new Book("book title", 22.2F, "book description", "book isbn", 111, true);
		
		// Exercise and Verify
		assertThat(book.getDescription(), is("book description"));
		assertThat(book.getIllustrations(), is(true));
		assertThat(book.getIsbn(), is("book isbn"));
		assertThat(book.getNbOfPage(), is(111));
		assertThat(book.getPrice(), is(22.2F));
		assertThat(book.getTitle(), is("book title"));
		assertThat(book.getId(), is(nullValue()));
	}
	
	@Test
	public void testToString() throws Exception {
		// Set up
		Book book = new Book();
        book.setTitle("The Hitchhiker's Guide to the Galaxy");
        book.setPrice(12.5F);
        book.setDescription("Science fiction comedy book");
        book.setIsbn("1-84023-742-2");
        book.setNbOfPage(354);
        book.setIllustrations(false);

        // Exercise and Verify
        assertThat(book.toString(), is("Book [id=null, title=The Hitchhiker's Guide to the Galaxy, price=12.5, description=Science fiction comedy book, isbn=1-84023-742-2, nbOfPage=354, illustrations=false]"));
	}
}
