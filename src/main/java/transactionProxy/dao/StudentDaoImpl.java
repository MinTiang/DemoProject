package transactionProxy.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import transactionProxy.connecManager.ConnectionManager;
import transactionProxy.entity.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public Student query(int id) throws SQLException {
		String sql = "select id,name,sex from student where id=?";
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet res = null;
		Student s=null;
		try {
			pst.setInt(1, id);
			res = pst.executeQuery();
			if(res.next()) {
				s=resultToEntity(Student.class,res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void save(Student student) throws SQLException {
		String sql = "insert into student(name,sex)values(?,?)";
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		try {
			pst.setString(1, student.getName());
			pst.setString(2, student.getSex());
			pst.executeUpdate();
		} finally {
			if (pst != null) {
				pst.close();
			}
		}

	}

	@Override
	public void update(Student student) throws SQLException {
		String sql = "update student set name=?,sex=? where id=?";
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		try {
			pst.setString(1, student.getName());
			pst.setString(2, student.getSex());
			pst.setInt(3, student.getId());
			pst.executeUpdate();
		} finally {
			if (pst != null) {
				pst.close();
			}
		}

	}

	/**
	 * 从jdbc返回的ResultSet对象转化为目标对象
	 * @param c 目标对象的class
	 * @param res ResultSet对象
	 * @return 包含内容的c对象
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public <T> T resultToEntity(Class<T> c, ResultSet res) throws InstantiationException, IllegalAccessException, SQLException, IllegalArgumentException, InvocationTargetException {

		Method[] ms = c.getMethods();
		T t = c.newInstance();
		for (Method m : ms) {
			if (m.getName().startsWith("set")) {
				Class<?>[] paraType = m.getParameterTypes();
				String name = m.getName().substring(3, m.getName().length());
				Object value=null;
				switch (paraType[0].getSimpleName()) {
				case "String":
					value=res.getString(name);
					break;
				case "int":
					value=res.getInt(name);
					break;
				case "Date":
					value=res.getDate(name);
					break;
				case "boolean":
					value=res.getBlob(name);
					break;
				default:
					break;
				}
				m.invoke(t, value);
			}
		}
		return t;
	}

}
