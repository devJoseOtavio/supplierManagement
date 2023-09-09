function validate() {
	let name = frmRegister.name.value
	let email = frmRegister.email.value
	let comment = frmRegister.comment.value
	let cnpj = frmRegister.cnpj.value

	if (name == "") {
		alert("Preencha o campo nome")
		frmRegister.name.focus()
		return false
	} else if (email == "") {
		alert("Preencha o campo email")
		frmRegister.email.focus()
		return false
	} else if (cnpj == "") {
		alert("Preencha o campo cnpj")
		frmRegister.cnpj.focus()
		return false
	} else {
		document.forms["frmRegister"].submit()
	}
}

function emailMask(field) {
	usuario = field.value.substring(0, field.value.indexOf("@"));
	dominio = field.value.substring(field.value.indexOf("@") + 1, field.value.length);
	if ((usuario.length >= 1) &&
		(dominio.length >= 3) &&
		(usuario.search("@") == -1) &&
		(dominio.search("@") == -1) &&
		(usuario.search(" ") == -1) &&
		(dominio.search(" ") == -1) &&
		(dominio.search(".") != -1) &&
		(dominio.indexOf(".") >= 1) &&
		(dominio.lastIndexOf(".") < dominio.length - 1)) {
		document.getElementById("msgemail").innerHTML = "E-mail válido";
		alert("email valido");
	}
	else {
		document.getElementById("email").innerHTML = "<font color='red'>Email inválido </font>";
		alert("E-mail invalido");
	}
}

function cnpjMask(cnpjField) {
	var cnpj = cnpjField.value.replace(/\D/g, '');

	if (cnpj.length > 14) {
		cnpjField.value = cnpjField.value.slice(0, 14);
		alert("Quantidade de caracteres para cnpj inválido")
	}

	if (cnpj.length === 14) {
		cnpjField.value = cnpj.replace(/^(\d{2})(\d)/, '$1.$2');
		cnpjField.value = cnpjField.value.replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3');
		cnpjField.value = cnpjField.value.replace(/\.(\d{3})(\d)/, '.$1/$2');
		cnpjField.value = cnpjField.value.replace(/(\d{4})(\d)/, '$1-$2');
	} else {
		cnpjField.value = cnpj;
	}
}
