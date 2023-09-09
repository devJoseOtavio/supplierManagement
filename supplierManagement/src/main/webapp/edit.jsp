<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.Supplier"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Editar fornecedor</title>
<link rel="stylesheet" href="resources/css/bootstrap.css">
</head>
<body>
	<div class="container mt-4">
		<h1>Editar Fornecedor</h1>
		<form name="frmRegister" action="update">
			<div class="mb-3">
				<label for="id" class="form-label">ID:</label> <input type="text"
					class="form-control" name="id"
					value="<%out.print(request.getAttribute("id"));%>" readonly>
			</div>
			<div class="mb-3">
				<label for="name" class="form-label">Nome:</label> <input
					type="text" class="form-control" name="name"
					value="<%out.print(request.getAttribute("name"));%>">
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">Email:</label> <input
					type="text" class="form-control email" name="email"
					value="<%out.print(request.getAttribute("email"));%>">
			</div>
			<div class="mb-3">
				<label for="comment" class="form-label">Comentário:</label> <input
					type="text" class="form-control" name="comment"
					value="<%out.print(request.getAttribute("comment"));%>">
			</div>
			<div class="mb-3">
				<label for="cnpj" class="form-label">CNPJ:</label> <input
					type="text" class="form-control cnpj" name="cnpj"
					value="<%out.print(request.getAttribute("cnpj"));%>">
			</div>
			<button type="button" class="btn btn-primary buttonSubmit"
				onclick="validate()">Salvar</button>
		</form>
	</div>
	<script src="scripts/validate.js"></script>
</body>
</html>