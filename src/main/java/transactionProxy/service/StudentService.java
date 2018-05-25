package transactionProxy.service;

import java.sql.SQLException;

import transactionProxy.entity.Student;

public interface StudentService {
	
	Student query(int id) throws SQLException;

	void save(Student student) throws SQLException;

	void update(Student student) throws SQLException;
}
