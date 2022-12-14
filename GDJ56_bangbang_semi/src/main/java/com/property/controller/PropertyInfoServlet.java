package com.property.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.property.model.vo.Files;
import com.property.model.vo.Property;
import com.property.service.PropertyService;

@WebServlet("/property/propertyInfo.bb")
public class PropertyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PropertyInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int propertyNo = Integer.parseInt(request.getParameter("propertyNo"));
//		System.out.println(propertyNo);
		
		List propertyInfo = PropertyService.getPropertyService().searchPropertyInfo(propertyNo);
//		propertyInfo.forEach(p->System.out.println(p));
		
		Property property = (Property) propertyInfo.get(0);
		List<Files> files = property.getFiles();
		List option = (List)propertyInfo.get(1);
//		files.forEach(f->System.out.println(f));
//		System.out.println(property);
//		System.out.println(option);
		
		
		request.setAttribute("property", property);
		request.setAttribute("files", files);
		request.setAttribute("option", option);
		request.getRequestDispatcher("/views/property/propertyInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
