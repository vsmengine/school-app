package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dbHandlers.DatabaseManager;
import logicHandlers.Course;

/**
 * Servlet implementation class CourseListSvlt
 */
@WebServlet(description = "Gets Course List in Json format", urlPatterns = { "/CourseListSvlt" })
public class CourseListSvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseListSvlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
        try{
            //Gson gson = BasicWebServices.gson_builder.create();
            //MyObject myObject = new MyObject();
        	Gson gson = new Gson();
        	HashMap<String, ArrayList<Course>> map = new HashMap<String, ArrayList<Course>>();

        	String appConfigFile= (String) (new InitialContext()).lookup("java:comp/env/appConfigFile");
	        DatabaseManager.updateDbConnectionInfo(appConfigFile);
        	ArrayList<Course> courseList = logicHandlers.Course.getCourseList();
        	map.put("CourseList",courseList);
        	String jsonString = gson.toJson(map);
        	
        	response.setContentType("application/json");
        	// Get the printwriter object from response to write the required json object to the output stream      
        	PrintWriter out = response.getWriter();
        	// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
        	out.print(gson.toJson(jsonString));
        	out.flush();
        	
        }
        catch(Exception e){
            response.getWriter().write("ERROR");
        }		
	}

}