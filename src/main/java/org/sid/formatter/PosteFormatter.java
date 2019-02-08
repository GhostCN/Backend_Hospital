package org.sid.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.sid.dao.PosteRepository;
import org.sid.models.Poste;
import org.springframework.beans.factory.annotation.Autowired;
//@org.springframework.stereotype.Service
public class PosteFormatter implements org.springframework.format.Formatter<Poste>{
	@Autowired
	private PosteRepository posteRepository;
	@Override
	public String print(Poste object, Locale locale) {
		// TODO Auto-generated method stub
		return (object!=null ?object.getId().toString():"");
	}

	@Override
	public Poste parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		int id=Integer.valueOf(text);
		return this.posteRepository.getOne(id);
	}

}
