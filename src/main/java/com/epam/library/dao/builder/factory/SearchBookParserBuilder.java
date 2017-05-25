package com.epam.library.dao.builder.factory;

import java.util.HashMap;
import java.util.Map;

import com.epam.library.dao.builder.SearchBookParser;
import com.epam.library.dao.builder.impl.EBookSearchparser;
import com.epam.library.dao.builder.impl.PBookSerachParser;

public class SearchBookParserBuilder {
	private final static SearchBookParserBuilder objectFactory = new SearchBookParserBuilder();
	private static final String PAPER_BOOK = "PB";
	private static final String ELECTRONIC_BOOK = "EB";
	private static final String SEARCH_BOOK = "SearchBook";
	Map<String, SearchBookParser> parser = new HashMap<>();

	private SearchBookParserBuilder() {
		parser.put(PAPER_BOOK, new PBookSerachParser() ); 
		parser.put(ELECTRONIC_BOOK, new EBookSearchparser());

		/* parser.put(SEARCH_BOOK, new SearchBookParserBuilder()); */

	}

	public static SearchBookParserBuilder getInstance() {
		return objectFactory;
	}

	public SearchBookParser getQuery(String objectType) {
		for (String key : parser.keySet()) {
			SearchBookParser parserObject = (SearchBookParser) parser.get(objectType);
			return parserObject;

		}
		return null;

	}
}
