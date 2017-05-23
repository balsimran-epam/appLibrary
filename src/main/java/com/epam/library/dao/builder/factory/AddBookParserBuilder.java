package com.epam.library.dao.builder.factory;

import java.util.HashMap;
import java.util.Map;

import com.epam.library.dao.builder.AddBookParser;
import com.epam.library.dao.builder.impl.AddEBookParser;
import com.epam.library.dao.builder.impl.AddPBookParser;

public class AddBookParserBuilder {
	private final static AddBookParserBuilder objectFactory = new AddBookParserBuilder();
	private static final String PAPER_BOOK = "PB";
	private static final String ELECTRONIC_BOOK = "EB";
	private static final String SELECTED_BOOK = "selected";
	private static final String ALL_BOOKS = "ALL";
	Map<String, AddBookParser> parser = new HashMap<>();

	private AddBookParserBuilder() {
		parser.put(PAPER_BOOK, new AddPBookParser());
		parser.put(ELECTRONIC_BOOK, new AddEBookParser());
		
		
	}

	public static AddBookParserBuilder getInstance() {
		return objectFactory;
	}

	public AddBookParser getQuery(String objectType) {
		for (String key : parser.keySet()) {
			AddBookParser parserObject = (AddBookParser) parser.get(objectType);
			return parserObject;

		}
		return null;

	}
}
