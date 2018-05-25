package transactionProxy.dao;

import java.sql.SQLException;

import transactionProxy.entity.Student;

public interface StudentDao {
	Student query(int id) throws SQLException;
	
	void save(Student student) throws SQLException;
	
	void update(Student student) throws SQLException;
}
