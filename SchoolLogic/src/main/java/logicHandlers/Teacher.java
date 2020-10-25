package logicHandlers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dbHandlers.DatabaseManager;

public class Teacher extends Person  {
	private int teacherId;
	private String teacherLevel;
	
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherLevel() {
		return teacherLevel;
	}
	public void setTeacherLevel(String teacherLevel) {
		this.teacherLevel = teacherLevel;
	}
	
	public static ArrayList<Teacher> getTeacherList() {
		ArrayList<Teacher> list = new ArrayList<Teacher>();
		String sql = "SELECT teacher_id, name, level_code FROM teacher";
		
		// Connection con=null;
		try {
			ResultSet resultSet=DatabaseManager.getResultSetFromSql(sql);
			while (resultSet.next()) {
				int teacherId = resultSet.getInt("teacher_id");
				String teacherName = resultSet.getString("name");
				String teacherLevel = resultSet.getString("level_code");
				Teacher teacher = new Teacher();
				teacher.setPersonName(teacherName);
				teacher.setTeacherId(teacherId);
				teacher.setTeacherLevel(teacherLevel);
				list.add(teacher);
			}
		}
		catch (java.sql.SQLException ex) {
			System.out.println(ex.getMessage());
		}		
		
		return list;
	}
	
	public static Teacher getTeacher(int teacherId) {
		Teacher teacher=null;
		String sql = "SELECT name, level_code FROM teacher WHERE teacher_id=" + teacherId;
		// Connection con=null;
		try {
			ResultSet resultSet=DatabaseManager.getResultSetFromSql(sql);
			if (resultSet.next()) {
				String teacherName = resultSet.getString("name");
				String teacherLevel = resultSet.getString("level_code");
				teacher = new Teacher();
				teacher.setTeacherId(teacherId);
				teacher.setPersonName(teacherName);
				teacher.setTeacherLevel(teacherLevel);
			}
		}
		catch (java.sql.SQLException ex) {
			System.out.println(ex.getMessage());
		}		
		
		return teacher;
	} //getTeacher
	
	public static void save(int teacherId, String teacherName) throws Exception {
		String sql = "UPDATE teacher SET name='" + teacherName + "'"
					+ " WHERE teacher_id=" + teacherId;
		
		// Connection con=null;
		try {
			Statement statement=DatabaseManager.getStatement();
			int rowCount = statement.executeUpdate(sql);
			if (rowCount==0) {
				throw new Exception("Teacher not found for id=" + teacherId);
			}
		}
		catch (java.sql.SQLException ex) {
			System.out.println(ex.getMessage());
		}		
	} //save
	
	public static int insert(String teacherName) throws Exception {
		int newId = 1;
		// Connection con=null;
		try {
			//jauzzin maksimala id vertiba
			String sqlGetMax = "SELECT MAX(teacher_id) as max_id FROM teacher";
			ResultSet resultSet=DatabaseManager.getResultSetFromSql(sqlGetMax);
			
			if (resultSet.next()) {
				newId = resultSet.getInt("max_id") + 1;
			}
			
			String sqlInsert = "INSERT INTO teacher (teacher_id, name) VALUES (" 
			+ newId + ", " + "'" + teacherName + "')";			
			
			Statement statement=DatabaseManager.getStatement();
			statement.executeUpdate(sqlInsert);

		}
		catch (java.sql.SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return newId;
	} //save
	
	public static void delete(int teacherId) throws Exception {
		String sql = "DELETE FROM teacher WHERE teacher_id=" + teacherId;
		
		// Connection con=null;
		try {
			Statement statement=DatabaseManager.getStatement();
			int rowCount = statement.executeUpdate(sql);
			if (rowCount==0) {
				throw new Exception("Course not found for id=" + teacherId);
			}
		}
		catch (java.sql.SQLException ex) {
			System.out.println(ex.getMessage());
		}		
	} //delete
	
	@Override
	public String toString() {
		return this.getTeacherId() + ". " + this.getPersonName();
	}
	
}

