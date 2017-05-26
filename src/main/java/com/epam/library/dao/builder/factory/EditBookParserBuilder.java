package com.epam.library.dao.builder.factory;

import java.util.HashMap;
import java.util.Map;

import com.epam.library.dao.builder.EditBookParser;
import com.epam.library.dao.builder.impl.EditEbookParser;
import com.epam.library.dao.builder.impl.EditPbookParser;

public class EditBookParserBuilder {
	private final static EditBookParserBuilder objectFactory = new EditBookParserBuilder();

	private static final String PAPER_BOOK = "PB";
	private static final String ELECTRONIC_BOOK = "EB";
	Map<String, EditBookParser> parser = new HashMap<>();

	private EditBookParserBuilder() {
		parser.put(PAPER_BOOK, new EditPbookParser());
		parser.put(ELECTRONIC_BOOK, new EditEbookParser());

	}

	public static EditBookParserBuilder getInstance() {
		return objectFactory;
	}

	public EditBookParser getQuery(String objectType) {
		EditBookParser parserObject = (EditBookParser) parser.get(objectType);
		return parserObject;
	}
}
