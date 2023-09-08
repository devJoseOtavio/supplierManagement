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