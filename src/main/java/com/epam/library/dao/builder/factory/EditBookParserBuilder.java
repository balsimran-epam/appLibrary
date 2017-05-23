package com.epam.library.dao.builder.factory;

import java.util.HashMap;
import java.util.Map;

import com.epam.library.dao.builder.AddBookParser;
import com.epam.library.dao.builder.BookParser;
import com.epam.library.dao.builder.EditBookParser;
import com.epam.library.dao.builder.impl.AllBookParser;
import com.epam.library.dao.builder.impl.EditEbookParser;
import com.epam.library.dao.builder.impl.EditPbookParser;
import com.epam.library.dao.builder.impl.ElectronicBookParser;
import com.epam.library.dao.builder.impl.PaperBookParser;
import com.epam.library.dao.builder.impl.SelectedBookParser;

public class EditBookParserBuilder {
private final static EditBookParserBuilder objectFactory = new EditBookParserBuilder();
	
	
	private static final String PAPER_BOOK = "PB";
	private static final String ELECTRONIC_BOOK = "EB";
	private static final String SELECTED_BOOK = "selected";
	private static final String ALL_BOOKS = "ALL";
	Map<String, EditBookParser> parser = new HashMap<>();

	private EditBookParserBuilder() {
		parser.put(PAPER_BOOK, new EditPbookParser());
		parser.put(ELECTRONIC_BOOK, new EditEbookParser());
		
		
	}

	public static EditBookParserBuilder getInstance() {
		return objectFactory;
	}

	public EditBookParser getQuery(String objectType) {
		for (String key : parser.keySet()) {
			EditBookParser parserObject = (EditBookParser) parser.get(objectType);
			return parserObject;

		}
		return null;

	}
}
