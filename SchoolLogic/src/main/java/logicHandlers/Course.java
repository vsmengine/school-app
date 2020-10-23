package logicHandlers;

import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbHandlers.DatabaseManager;

public class Course {

	private String courseName;
	private int courseId;
	private int teacherId;
	private String teacherName; 
	
	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacher_id) {
		this.teacherId = teacher_id;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Course() {
		this.courseId=0;
		setCourseName("nonname");
	}
	
	public Course(int courseId, String courseName) {
		this.courseId = courseId;
		setCourseName(courseName);
	}
	
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseId() {
		return courseId;
	}



	public static void main(String[] args) {
		//testSql();
		String sql = "SELECT c.course_id, c.name, c.required,"
				+ "t.teacher_id, t.name AS teacherName "
				+ "FROM course c "
				+ "LEFT OUTER JOIN teacher t ON t.teacher_id = c.teacher_id";
		System.out.println(sql);		
		
		
	}
	
	public static ArrayList<Course> getCourseList() {
		ArrayList<Course> list = new ArrayList<Course>();
		String sql = "SELECT c.course_id, c.name, c.required,"
				+ "t.teacher_id, t.name AS teacherName "
				+ "FROM course c "
				+ "LEFT OUTER JOIN teacher t ON t.teacher_id = c.teacher_id";
		
		// Connection con=null;
		try {
			ResultSet resultSet=DatabaseManager.getResultSetFromSql(sql);
			while (resultSet.next()) {
				int courseId = resultSet.getInt("course_id");
				String courseName = resultSet.getString("name");
				int required = resultSet.getInt("required");
				int teacherId = resultSet.getInt("teacher_id");
				String teacherName = resultSet.getString("teacherName");
				
				Course course = null;
				if (required==1) {
					//MandatoryCourse
					course = new MandatoryCourse(courseId, courseName);
				}
				else {
					//OptionalCourse
					course = new OptionalCourse(courseId, courseName);
				}
				course.setTeacherId(teacherId);
				course.setTeacherName(teacherName);
				list.add(course);
				
			}
		}
		catch (java.sql.SQLException ex) {
			System.out.println(ex.getMessage());
		}		
		
		return list;
	}
	
	public static Course getCourse(int courseId) {
		Course course=null;
		String sql = "SELECT name, required, teacher_id FROM course WHERE course_id=" + courseId;
		// Connection con=null;
		try {
			ResultSet resultSet=DatabaseManager.getResultSetFromSql(sql);
			if (resultSet.next()) {
				String courseName = resultSet.getString("name");
				int required = resultSet.getInt("required");
				int teacherId = resultSet.getInt("teacher_id");
				if (required==1) {
					//MandatoryCourse
					course = new MandatoryCourse(courseId, courseName);
				}
				else {
					//OptionalCourse
					course = new OptionalCourse(courseId, courseName);
				}
				course.setTeacherId(teacherId);
			}
		}
		catch (java.sql.SQLException ex) {
			System.out.println(ex.getMessage());
		}		
		
		return course;
	} //getCourse
	
	
	public static void save(int courseId, String courseName, int teacherId) throws Exception {
		String sql = "UPDATE course SET name='" + courseName + "' "
				    + ", teacher_id=" + teacherId
					+ " WHERE course_id=" + courseId;
		
		// Connection con=null;
		try {
			Statement statement=DatabaseManager.getStatement();
			int rowCount = statement.executeUpdate(sql);
			if (rowCount==0) {
				throw new Exception("Course not found for id=" + courseId);
			}
		}
		catch (java.sql.SQLException ex) {
			System.out.println(ex.getMessage());
		}		
	} //save

	public static int insert(String courseName) throws Exception {

		int newId = 1;
		// Connection con=null;
		try {
			//jauzzin maksimala id vertiba
			String sqlGetMax = "SELECT MAX(course_id) as max_id FROM course";
			ResultSet resultSet=DatabaseManager.getResultSetFromSql(sqlGetMax);
			
			if (resultSet.next()) {
				newId = resultSet.getInt("max_id") + 1;
			}
			
			String sqlInsert = "INSERT INTO course (course_id, name) VALUES (" 
			+ newId + ", " + "'" + courseName + "' )";			
			
			Statement statement=DatabaseManager.getStatement();
			statement.executeUpdate(sqlInsert);

		}
		catch (java.sql.SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return newId;

	} //save
	
	public static void delete(int courseId) throws Exception {
		String sql = "DELETE FROM course WHERE course_id=" + courseId;
		
		// Connection con=null;
		try {
			Statement statement=DatabaseManager.getStatement();
			int rowCount = statement.executeUpdate(sql);
			if (rowCount==0) {
				throw new Exception("Course not found for id=" + courseId);
			}
		}
		catch (java.sql.SQLException ex) {
			System.out.println(ex.getMessage());
		}		
	} //delete
	
	
	
	public String toString() {
		return "#" + this.getCourseId() + ". " + this.getCourseName() 
				+ " taught by " + this.getTeacherName() + " teacherId:" + this.getTeacherId();
	}
	
	public static void testSql() {
		String sql = "SELECT course_id, name, required FROM course";
		
		// Connection con=null;
		System.out.println("Kursu saraksts");
		try {

			ResultSet resultSet=DatabaseManager.getResultSetFromSql(sql);
			while (resultSet.next()) {
				int courseId = resultSet.getInt("course_id");
				String courseName = resultSet.getString("name");
				int required = resultSet.getInt("required");
				System.out.println(courseId + " " + courseName + " " + (required==1 ? "obligats":"izveles") );
			}
		}
		catch (java.sql.SQLException ex) {
			System.out.println(ex.getMessage());
		}		
	}

}
