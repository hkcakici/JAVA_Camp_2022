package courseProject.business;

import java.util.List;

import courseProject.core.logging.Logger;
import courseProject.dataAccess.Interfaces.ICourseDao;
import courseProject.entities.Course;

public class CourseManager {

	private ICourseDao iCourseDao;
	List<Logger> loggers;
	List<Course> courses;
	
	
	public CourseManager(ICourseDao iCourseDao,List<Logger> loggers,List<Course> courses) {
		this.iCourseDao = iCourseDao;
		this.loggers = loggers;	
		this.courses = courses;
	}
	
	public void add(Course course) throws Exception {
		
		for (Course c : courses) {
			if(course.getName().equals(c.getName()) || course.getPrice() < 0  ) {
				throw new Exception("Kurs adı veya kurs ücreti geçersiz! [" + course.getName() + "]");
			}
		}
				
		iCourseDao.add(course);
		courses.add(course);
		
		for (Logger logger : loggers) {
			logger.log(course.getName());
		}
	}
	
	public void list() {
		iCourseDao.list(courses);
	}
}
