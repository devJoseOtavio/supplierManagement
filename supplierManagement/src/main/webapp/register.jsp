
<%
String erros = "";
String converter = "";
if (request.getAttribute("erro") != null && request.getAttribute("erroConverter") != null) {
	erros = (String) request.getAttribute("erro");
	converter = (String) request.getAttribute("erroConverter");
}
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Cadastro de fornecedores</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-4">
		<h1>Cadastro de Fornecedores</h1>
		<form name="frmRegister" action="register">
			<div class="mb-3">
				<input type="text" class="form-control" name="name"
					placeholder="Nome">
			</div>
			<div class="mb-3">
				<input type="text" class="form-control email" name="email"
					placeholder="Email" oninput="emailMask(this)">
			</div>
			<div class="mb-3">
				<input type="text" class="form-control" name="comment"
					placeholder="Comentário">
			</div>
			<div class="mb-3">
				<input type="text" class="form-control cnpj" name="cnpj"
					placeholder="CNPJ" oninput="cnpjMask(this)">
			</div>
			<button type="button" class="btn btn-primary buttonSubmit"
				onclick="validate()">Cadastrar</button>
		</form>
	</div>
	<script src="scripts/validate.js"></script>
</body>
</html>