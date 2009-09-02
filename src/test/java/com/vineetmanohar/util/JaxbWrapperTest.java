package com.vineetmanohar.util;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import com.vineetmanohar.sitemap.jaxb.Urlset;

/**
 * @author Vineet Manohar
 */
public class JaxbWrapperTest {
	@Test
	public void testXmlToObject() throws JAXBException {
		JaxbWrapper<Urlset> sitemapJaxbWrapper = new JaxbWrapper<Urlset>(
				getClass().getResource("/sitemap.xsd"), Urlset.class);
		Urlset urlset = sitemapJaxbWrapper.xmlToObject(getClass()
				.getResourceAsStream("/sample-sitemap.xml"));
		Assert.assertNotNull(urlset);
	}
}