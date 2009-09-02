package com.vineetmanohar.util;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

/**
 * Simple JAXB Wrapper
 * 
 * @author Vineet Manohar
 */
public class JaxbWrapper<T> {
	private JAXBContext jaxbContext = null;
	private SchemaFactory schemaFactory = null;

	private Schema schema;

	public JaxbWrapper(URL schemaUrl, Class<?> clazz) {
		try {
			init(schemaUrl, clazz);
		} catch (JAXBException e) {
			throw new RuntimeException("Could not intialize class", e);
		} catch (SAXException e) {
			throw new RuntimeException("Could not intialize class", e);
		}
	}

	private Marshaller createMarshaller(Schema schema) throws JAXBException,
			PropertyException {
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setSchema(schema);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(
				true));
		return marshaller;
	}

	private Unmarshaller createUnmarshaller(Schema schema) throws JAXBException {
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		unmarshaller.setSchema(schema);
		return unmarshaller;
	}

	private Marshaller createMarshaller() throws JAXBException {
		return createMarshaller(schema);
	}

	private Unmarshaller createUnmarshaller() throws JAXBException {
		return createUnmarshaller(schema);
	}

	public String objectToXml(T t) throws JAXBException {
		StringWriter writer = new StringWriter();
		createMarshaller().marshal(t, writer);
		return writer.toString();
	}

	private void init(URL schemaUrl, Class<?> clazz) throws JAXBException,
			SAXException {
		jaxbContext = JAXBContext.newInstance(clazz);
		schemaFactory = SchemaFactory
				.newInstance("http://www.w3.org/2001/XMLSchema");

		schema = schemaFactory.newSchema(schemaUrl);
	}

	public void validate(T t) throws JAXBException {
		createMarshaller().marshal(t, new StringWriter());
	}

	public T xmlToObject(String xml) throws JAXBException {
		StringReader stringReader = new StringReader(xml);
		return (T) createUnmarshaller().unmarshal(stringReader);
	}

	public T xmlToObject(InputStream is) throws JAXBException {
		return (T) createUnmarshaller().unmarshal(is);
	}
}