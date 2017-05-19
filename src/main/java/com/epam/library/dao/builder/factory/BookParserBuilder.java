package com.epam.library.dao.builder.factory;

import java.util.HashMap;
import java.util.Map;

import com.epam.library.dao.builder.BookParser;
import com.epam.library.dao.builder.impl.AllBookParser;
import com.epam.library.dao.builder.impl.ElectronicBookParser;
import com.epam.library.dao.builder.impl.PaperBookParser;
import com.epam.library.dao.builder.impl.SelectedBookParser;

public class BookParserBuilder {
private final static BookParserBuilder objectFactory = new BookParserBuilder();
	
	
	private static final String PAPER_BOOK = "PB";
	private static final String ELECTRONIC_BOOK = "EB";
	private static final String SELECTED_BOOK = "selected";
	private static final String ALL_BOOKS = "ALL";
	Map<String, BookParser> parser = new HashMap<>();

	private BookParserBuilder() {
		parser.put(PAPER_BOOK, new PaperBookParser());
		parser.put(ELECTRONIC_BOOK, new ElectronicBookParser());
		parser.put(SELECTED_BOOK, new SelectedBookParser());
		parser.put(ALL_BOOKS, new AllBookParser());
		
	}

	public static BookParserBuilder getInstance() {
		return objectFactory;
	}

	public BookParser getQuery(String objectType) {
		for (String key : parser.keySet()) {
			BookParser parserObject = (BookParser) parser.get(objectType);
			return parserObject;

		}
		return null;

	}
	
}
