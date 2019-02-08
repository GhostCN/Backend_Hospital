package org.sid.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.sid.dao.RoleRepository;
import org.sid.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
@org.springframework.stereotype.Service
public class RoleFormatter implements org.springframework.format.Formatter<Role> {
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public String print(Role object, Locale locale) {
		
		return (object!=null ?object.getId().toString():"");
	}

	@Override
	public Role parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		Long id=Long.valueOf(text);
		return this.roleRepository.getOne(id);
	}

}
