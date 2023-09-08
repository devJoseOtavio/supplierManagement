package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dao;
import model.Supplier;

@WebServlet(urlPatterns = { "/Controller", "/main", "/register" })
public class SupplierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao = new Dao();
	Supplier supplier = new Supplier();

	public SupplierController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		if (action.equals("/main")) {
			suppliers(request, response);
		} else if (action.equals("/register")) {
			newSupplier(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void suppliers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("supplier.jsp");
	}

	protected void newSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("comment"));
		System.out.println(request.getParameter("cnpj"));

		supplier.setName(request.getParameter("name"));
		supplier.setEmail(request.getParameter("email"));
		supplier.setComment(request.getParameter("comment"));
		supplier.setCnpj(request.getParameter("cnpj"));
		dao.insertSupplier(supplier);
		response.sendRedirect("main");
	}
}
