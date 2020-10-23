package logicHandlers;

public class MandatoryCourse extends Course {

	public MandatoryCourse(int courseId, String courseName) {
		super(courseId, courseName);
	}
	
	public String toString() {
		return "Mandatory course " + super.toString();
	}

}
