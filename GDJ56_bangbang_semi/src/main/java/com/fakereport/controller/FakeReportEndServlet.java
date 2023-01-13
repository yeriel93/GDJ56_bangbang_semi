package com.fakereport.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fakereport.service.FakeReportService;
import com.google.gson.Gson;

/**
 * Servlet implementation class FakeReportEndServlet
 */
@WebServlet("/property/propertyInfo/fakeReportEnd.bb")
public class FakeReportEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FakeReportEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int propertyNo = Integer.parseInt(request.getParameter("propertyNo"));
		String content = request.getParameter("content");
		//System.out.println(content + " " + propertyNo + " " + userNo);
		
		int result = FakeReportService.getFakeReportService().insertFakeReport(userNo, propertyNo, content);
		
		response.setContentType("application/json;charset=utf-8");
		Gson g = new Gson();
		g.toJson(result, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
