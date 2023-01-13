package com.centercoordinate.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centercoordinate.model.vo.CenterCoordinate;
import com.centercoordinate.service.CenterCoordinateService;

/**
 * Servlet implementation class SubmitAddressServlet
 */
@WebServlet("/searchAddress.bb")
public class SearchAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAddressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//나중에 필터 추가 후 지워야 함
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String gu;
		try {
			gu = request.getParameter("gu");
		} catch (NullPointerException e) {
			gu = "관악구";
		}
		String dong;
		try {
			dong = request.getParameter("dong");
		} catch (NullPointerException e) {
			dong = "남현동";
		}
		
		CenterCoordinate cc = CenterCoordinateService.getCenterCoordinateService().searchCenterCoordinate(gu, dong);
		request.setAttribute("cc", cc);
		request.getRequestDispatcher("/views/map/mappage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
