package com.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.model.service.AdminService;
import com.google.gson.Gson;

/**
 * Servlet implementation class AdminBrokerAdmissionServlet
 */
@WebServlet("/admin/brokerAdmission.bb")
public class AdminBrokerAdmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBrokerAdmissionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int brokerNo = Integer.parseInt(request.getParameter("brokerNo"));
		String setAdmission = request.getParameter("setAdmission");
		//System.out.println(brokerNo + " / " + setAdmission);
		
		int result = AdminService.getAdminService().updateBrokerAdmissionToY(brokerNo, setAdmission);
		int result2 = 0;
		if(result>0 && setAdmission.equals("N")) {
			result2 = AdminService.getAdminService().updatePropertyHidingByBrokerNo(brokerNo, "Y");
		}
		String msg = "";
		if(result>0 && setAdmission.equals("Y")) {
			msg = "승인이 완료되었습니다.";
		} else if(result>0 && setAdmission.equals("N")) {
			msg = "승인취소가 완료되었습니다.";
		}
		else {
			msg = "실패했습니다.";
		}
		response.setContentType("application/json;charset=utf-8");
		Gson g = new Gson();
		g.toJson(msg, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
