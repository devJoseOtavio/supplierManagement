package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Dao;
import model.Supplier;

@WebServlet(urlPatterns = { "/Controller", "/main", "/register", "/edit", "/update", "/delete" })
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
			supplierList(request, response);
		} else if (action.equals("/register")) {
			newSupplier(request, response);
		} else if (action.equals("/edit")) {
			editSupplier(request, response);
		} else if (action.equals("/update")) {
			updateSupplier(request, response);
		} else if (action.equals("/delete")) {
			deleteSupplier(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void newSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		supplier.setName(request.getParameter("name"));
		supplier.setEmail(request.getParameter("email"));
		supplier.setComment(request.getParameter("comment"));
		supplier.setCnpj(request.getParameter("cnpj"));
		dao.insertSupplier(supplier);
		response.sendRedirect("main");
	}

	protected void supplierList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Supplier> supplierList = dao.supplierList();
		request.setAttribute("fornecedores", supplierList);
		RequestDispatcher rd = request.getRequestDispatcher("supplier.jsp");
		rd.forward(request, response);
	}

	protected void editSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int idInt = Integer.parseInt(id);
		supplier.setId(idInt);
		dao.searchSupplier(supplier);
		request.setAttribute("id", supplier.getId());
		request.setAttribute("name", supplier.getName());
		request.setAttribute("email", supplier.getEmail());
		request.setAttribute("comment", supplier.getComment());
		request.setAttribute("cnpj", supplier.getCnpj());
		RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
		rd.forward(request, response);
	}

	protected void updateSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int idInt = Integer.parseInt(id);
		supplier.setId(idInt);
		supplier.setName(request.getParameter("name"));
		supplier.setEmail(request.getParameter("email"));
		supplier.setComment(request.getParameter("comment"));
		supplier.setCnpj(request.getParameter("cnpj"));

		dao.updateSupplier(supplier);
		response.sendRedirect("main");
	}

	protected void deleteSupplier(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int idInt = Integer.parseInt(id);
		supplier.setId(idInt);
		dao.deleteSupplier(supplier);
		response.sendRedirect("main");
	}
	
	public static boolean emailValidator(String email)
    {
		String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    	Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailPat.matcher(email);
		return matcher.find();
    }

	protected void validateParams(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean formIsValid = true;
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String comment = request.getParameter("comment");
			String cnpj = request.getParameter("cnpj");
			if (name.isEmpty() || email.isEmpty() || comment.isEmpty() || cnpj.isEmpty()) {
				request.setAttribute("erro", "Você não preencheu todos os campos!");
				formIsValid = false;
			}
		} catch (NullPointerException e) {
			request.setAttribute("erro", "Você não preencheu todos os campos!");
			formIsValid = false;
		} catch (NumberFormatException e) {
			request.setAttribute("erroConverter", "Apenas números são aceitos neste campo!");
			formIsValid = false;
		}

		boolean validateCnpj = dao.validateCnpj(request.getParameter("cnpj"));

		if (validateCnpj == false) {
			request.setAttribute("erro", "Cnpj já existente.");
			formIsValid = false;
		}
		
		Boolean emailValidate = emailValidator(request.getParameter("email"));
		if (emailValidate == false) {
			request.setAttribute("erro", "erro na formatação do email.");
			formIsValid = false;
		}

		if (formIsValid) {
			response.sendRedirect("supplier.jsp");
		} else {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}
}
