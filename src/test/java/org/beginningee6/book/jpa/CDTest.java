package org.beginningee6.book.jpa;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.beginningee6.book.jpa.util.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(UnitTest.class)
public class CDTest {
	@Test
	public void testDefaultConstructor() throws Exception {
		// Set Up
        CD cd = new CD();

        cd.setTitle("cd title");
        cd.setPrice(22.2F);
        cd.setDescription("cd description");
        cd.setGender("gender");
        cd.setMusicCompany("music company");
        cd.setNumberOfCDs(1);
        cd.setTotalDuration(99.9F);
        cd.setCover("cd cover".getBytes("UTF-8"));
        
        // Exercise and Verify
        assertThat(cd.getTitle(), is("cd title"));
        assertThat(cd.getPrice(), is(22.2F));
        assertThat(cd.getDescription(), is("cd description"));
        assertThat(cd.getGender(), is("gender"));
        assertThat(cd.getMusicCompany(), is("music company"));
        assertThat(cd.getNumberOfCDs(), is(1));
        assertThat(cd.getTotalDuration(), is(99.9F));
        assertThat(new String(cd.getCover(), "UTF-8"), is("cd cover"));
        
        assertThat(cd.getId(), is(nullValue()));
	}
	
	@Test
	public void testAugmentedConstructor() throws Exception {
		// Set Up
        CD cd = new CD("cd title", 22.2F, "cd description", "cd cover".getBytes("UTF-8"), "music company", 1, 99.9F, "gender");

        // Exercise and Verify
        assertThat(cd.getTitle(), is("cd title"));
        assertThat(cd.getPrice(), is(22.2F));
        assertThat(cd.getDescription(), is("cd description"));
        assertThat(cd.getGender(), is("gender"));
        assertThat(cd.getMusicCompany(), is("music company"));
        assertThat(cd.getNumberOfCDs(), is(1));
        assertThat(cd.getTotalDuration(), is(99.9F));
        assertThat(new String(cd.getCover(), "UTF-8"), is("cd cover"));
        
        assertThat(cd.getId(), is(nullValue()));
	}

	@Test
	public void testToString() throws Exception {
		// Set up
        CD cd = new CD();
        cd.setTitle("Zoot Allures");
        cd.setPrice(12.5F);
        cd.setDescription("Released in October 1976, it is mostly a studio album");
        cd.setGender("male");
        cd.setMusicCompany("RCA Records");
        cd.setNumberOfCDs(2);
        cd.setTotalDuration(74.5F);
        cd.setCover("Cover Image".getBytes("UTF-8"));
		
		// Exercise and Verify
        assertThat(cd.toString(), is("CD [id=null, title=Zoot Allures, price=12.5, description=Released in October 1976, it is mostly a studio album, cover=[67, 111, 118, 101, 114, 32, 73, 109, 97, 103, 101], musicCompany=RCA Records, numberOfCDs=2, totalDuration=74.5, gender=male]"));
	}

}
