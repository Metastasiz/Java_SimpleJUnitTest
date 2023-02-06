package eu.glowacki.utp.assignment10.repositories;

import java.sql.Connection;
import java.sql.SQLException;

import eu.glowacki.utp.assignment10.dtos.DTOBase;

public interface IRepository<TDTO extends DTOBase> {

	Connection getConnection();

	void add(TDTO dto);

	void update(TDTO dto);
	
	void addOrUpdate(TDTO dto);

	void delete(TDTO dto);

	TDTO findById(int id);

	void beginTransaction();

	void commitTransaction();

	void closeConnection();

	void rollbackTransaction();
	
	int getCount();
	
	boolean exists(TDTO dto);
}