<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.Supplier"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<Supplier> supplierList = (ArrayList<Supplier>) request.getAttribute("fornecedores");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Lista de fornecedores</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
</head>
<body>
	<script src="scripts/confirmer.js"></script>
	<div class="container mt-4">
		<h1>Lista de Fornecedores</h1>
		<a href="register.jsp" class="btn btn-primary mb-3">Novo
			Fornecedor</a>

		<%
		if (supplierList != null && !supplierList.isEmpty()) {
		%>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Email</th>
					<th>Comentário</th>
					<th>CNPJ</th>
					<th>Opções</th>
					<th>Excluir</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < supplierList.size(); i++) {
				%>
				<tr>
					<td><%=supplierList.get(i).getId()%></td>
					<td><%=supplierList.get(i).getName()%></td>
					<td><%=supplierList.get(i).getEmail()%></td>
					<td><%=supplierList.get(i).getComment()%></td>
					<td><%=supplierList.get(i).getCnpj()%></td>
					<td><a href="edit?id=<%=supplierList.get(i).getId()%>"
						class="btn btn-warning btn-sm">Editar</a></td>
					<td><a
						href="javascript: confirmer(<%=supplierList.get(i).getId()%>)"
						class="btn btn-danger btn-sm">Excluir</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
		<%
		} else {
		%>
		<p>Não há fornecedores para exibir.</p>
		<%
		}
		%>
	</div>
	</tbody>
	</table>
</body>
</html>