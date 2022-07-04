package com.gmail.olgabots.session.simple.example;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String encodeURL = response.encodeURL(request.getContextPath())+"/controller";
		request.setAttribute("encodeURL", encodeURL);
		System.out.println("encodeURL " + encodeURL);
		
		System.out.println(" 1--- " + request.getRequestURI());
		System.out.println(" 2--- " + request.getRequestURL());

		request.setAttribute("sessionId", session.getId());
		System.out.println("sessionId " + session.getId());

		saveCookiesToAttributes(request, response);

		saveParametersToSession(request);
		setSessionVariablesAsMapAttribute(request, response);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

	private void saveCookiesToAttributes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			Map<String, String> cookieValues = new HashMap<String, String>();
			for (Cookie cookie : cookies) {
				cookieValues.put(cookie.getName(), cookie.getValue());
			}

			request.setAttribute("cookies", cookieValues);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

	private void saveParametersToSession(HttpServletRequest request) {
		String varName = request.getParameter("var_name");
		String varValue = request.getParameter("var_value");

		if (varName != null && !varName.isEmpty()) {
			request.getSession().setAttribute(varName, varValue);
		}
	}

	private void setSessionVariablesAsMapAttribute(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Enumeration<String> attributeNames = request.getSession().getAttributeNames();
		Map<String, String> sessionAttributes = new HashMap<String, String>();
		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attribute = request.getSession().getAttribute(attributeName);
			if (attribute instanceof String) {
				sessionAttributes.put(attributeName, (String) attribute);
			}
		}

		request.setAttribute("session_attributes", sessionAttributes);
	}

}
