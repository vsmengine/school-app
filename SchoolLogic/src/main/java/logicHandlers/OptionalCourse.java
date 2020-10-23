package logicHandlers;

public class OptionalCourse extends Course {

	public OptionalCourse(int courseId, String courseName) {
		super(courseId, courseName);
	}
	
	public String toString() {
		return "Optional course " + super.toString();
	}
	
}
