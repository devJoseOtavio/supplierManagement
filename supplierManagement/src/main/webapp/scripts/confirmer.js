function confirmer(id) {
	let response = confirm("Deseja excluir este fornecedor?")
	
	if (response == true) {
		window.location.href= "delete?id=" + id
	} else {
		return null
	}
}