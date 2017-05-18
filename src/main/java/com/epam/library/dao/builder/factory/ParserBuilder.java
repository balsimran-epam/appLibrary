package com.epam.library.dao.builder.factory;

import java.util.HashMap;
import java.util.Map;

import com.epam.library.dao.builder.Parser;
import com.epam.library.dao.builder.impl.ElectronicBookParser;
import com.epam.library.dao.builder.impl.PaperBookParser;
import com.epam.library.dao.builder.impl.SelectedBookParser;


public class ParserBuilder {
	private final static ParserBuilder objectFactory = new ParserBuilder();
	
	
	private static final String PAPER_BOOK = "PB";
	private static final String ELECTRONIC_BOOK = "EB";
	private static final String SELECTED_BOOK = "selected";
	Map<String, Parser> parser = new HashMap<>();

	private ParserBuilder() {
		parser.put(PAPER_BOOK, new PaperBookParser());
		parser.put(ELECTRONIC_BOOK, new ElectronicBookParser());
		parser.put(SELECTED_BOOK, new SelectedBookParser());
		
	}

	public static ParserBuilder getInstance() {
		return objectFactory;
	}

	public Parser getBook(String objectType) {
		for (String key : parser.keySet()) {
			Parser parserObject = (Parser) parser.get(objectType);
			return parserObject;

		}
		return null;

	}
	

}

