package org.sid.formatter;

import java.text.ParseException;
import java.util.Locale;
import org.sid.dao.ServiceRepository;
import org.sid.models.Service;
import org.springframework.beans.factory.annotation.Autowired;
@org.springframework.stereotype.Service
public class ServiceFormatter implements org.springframework.format.Formatter<Service>{
	@Autowired
	private ServiceRepository serviceRepository;
	@Override
	public String print(Service object, Locale locale) {
		// TODO Auto-generated method stub
		return (object!=null ?object.getId().toString():"");
	}

	@Override
	public Service parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		Long id=Long.valueOf(text);
		return this.serviceRepository.getOne(id);
	}

}
