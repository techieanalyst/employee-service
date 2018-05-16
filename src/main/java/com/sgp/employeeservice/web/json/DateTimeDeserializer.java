package com.sgp.employeeservice.web.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateTimeDeserializer extends JsonDeserializer<Date> {

	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String date = p.getText();
		formatter.setLenient(false);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			throw new IOException("Unable to parse the date");
		}
	}

}
