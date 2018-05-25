package transactionProxy.service;

import java.sql.SQLException;

import transactionProxy.dao.StudentDao;
import transactionProxy.dao.StudentDaoImpl;
import transactionProxy.entity.Student;

public class StudentServiceImpl implements StudentService{

	StudentDao dao=new StudentDaoImpl();
	@Override
	public Student query(int id) throws SQLException {
		return dao.query(id);
	}

	@Override
	public void save(Student student) throws SQLException {
		dao.save(student);
		
	}

	@Override
	public void update(Student student) throws SQLException {
		dao.update(student);
		
	}

}
