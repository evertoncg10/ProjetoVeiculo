/**
 * 
 */

function setList() {
	var table = '<thead>' +
		'<tr>' +
		'<td><b>Data de cadastro</b></td>' +
		'<td><b>Placa</b></td>' +
		'<td><b>Renavan</b></td>' +
		'<td><b>Modelo</b></td>' +
		'<td><b>Opcionais</b></td>' +
		'<td><b>Valor de venda</b></td>' +
		'</tr>' +
		'</thead>';
	document.getElementById("listTable").innerHTML = table;
	RecuperarUsuarios();
}


function salvarData() {
	var placa = document.getElementById("placa").value;
	var renavam = document.getElementById("renavam").value;
	var valorVenda = document.getElementById("valor").value;
	var modelo = document.getElementById("modelo").value;

	strOpcionais = "";
	var aChk = document.getElementsByName("opcionais");
	for (i = 0; i < aChk.length; i++) {
		checkBox = document.getElementById("opcionais" + (i + 1));
		if (checkBox.checked == true) {
			strOpcionais += " " + checkBox.value;
		}
	}
	var opcionais = strOpcionais;

	var strJsonVeiculo = '{';
	strJsonVeiculo += '\"id\":' + id + ',';
	strJsonVeiculo += '\"placa\":' + '\"' + placa + '\"' + ',';
	strJsonVeiculo += '\"renavam\":' + renavam + ',';
	strJsonVeiculo += '\"modelo\":' + '\"' + modelo + '\"' + ',';
	strJsonVeiculo += '\"opcionais\":' + '\"' + opcionais + '\"' + ',';
	strJsonVeiculo += '\"valorDeVenda\":' + valorVenda + '}';
	var obj = JSON.parse(strJsonVeiculo);

	jQuery.ajax({
		type : "POST",
		url : "http://localhost:8080/projeto-veiculo/rest/veiculos/",
		data : obj,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			alert("Veiculo salvo com sucesso");
			window.location.reload();
		},

		error : function(status) {
			alert("Erro ao salvar veiculo");
		}
	});

}


function setUpdate(id) {
	$.ajax({
		type : "GET",
		url : "http://localhost:8080/projeto-veiculo/rest/veiculos/" + id,
		success : function(veiculos) {

			document.getElementById("id").value = veiculos.id;
			document.getElementById("placa").value = veiculos.placa;
			document.getElementById("renavam").value = veiculos.renavam;
			document.getElementById("valor").value = veiculos.valorDeVenda;



		}
	});

	// Acessando propriedade CSS para fazer botão aparecer.
	document.getElementById("btnUpdate").style.display = "inline-block";
	// Acessando propriedade CSS para fazer botão desaparecer.
	document.getElementById("btnSave").style.display = "none";

}

function atualizarData() {
	var id = document.getElementById("id").value;
	var placa = document.getElementById("placa").value;
	var renavam = document.getElementById("renavam").value;
	var valorVenda = document.getElementById("valor").value;
	var modelo = document.getElementById("modelo").value;

	strOpcionais = "";
	var aChk = document.getElementsByName("opcionais");
	for (i = 0; i < aChk.length; i++) {
		checkBox = document.getElementById("opcionais" + (i + 1));
		if (checkBox.checked == true) {
			strOpcionais += " " + checkBox.value;
		}
	}
	var opcionais = strOpcionais;

	var strJsonVeiculo = '{';
	strJsonVeiculo += '\"id\":' + id + ',';
	strJsonVeiculo += '\"placa\":' + '\"' + placa + '\"' + ',';
	strJsonVeiculo += '\"renavam\":' + renavam + ',';
	strJsonVeiculo += '\"modelo\":' + '\"' + modelo + '\"' + ',';
	strJsonVeiculo += '\"opcionais\":' + '\"' + opcionais + '\"' + ',';
	strJsonVeiculo += '\"valorDeVenda\":' + valorVenda + '}';
	var obj = JSON.parse(strJsonVeiculo);

	jQuery.ajax({
		type : "PUT",
		url : "http://localhost:8080/projeto-veiculo/rest/veiculos/",
		data : obj,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			alert("Veiculo atualizado com sucesso");
			window.location.reload();
		},

		error : function(status) {
			alert("Erro ao atualizar veiculo");
		}
	});

}

function deleteData(id) {
	jQuery.ajax({
		url : 'http://localhost:8080/projeto-veiculo/rest/veiculos/' + id,
		type : 'DELETE',
		success : function(data) {
			alert("Veiculo deletado com Sucesso");
			window.location.reload();
		}
	});
}

function resetForm() {
	document.getElementById("id").value = "";
	document.getElementById("placa").value = "";
	document.getElementById("renavam").value = "";
	document.getElementById("valor").value = "";
	// Acessando propriedade CSS para fazer botão desaparecer..
	document.getElementById("btnUpdate").style.display = "none";
	// Acessando propriedade CSS para fazer botão aparecer.
	document.getElementById("btnSave").style.display = "inline-block";
	document.getElementById("inputIdUpdate").innerHTML = "";
}

/**
 * A partir de um valor float ela retorna o valor formatado com separador de milhar e vírgula nos centavos.
 */
function float2moeda(num) {
	x = 0;
	if (num < 0) {
		num = Math.abs(num);
		x = 1;
	}
	if (isNaN(num))
		num = "0";
	cents = Math.floor((num * 100 + 0.5) % 100);
	num = Math.floor((num * 100 + 0.5) / 100).toString();
	if (cents < 10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + '.'
		+ num.substring(num.length - (4 * i + 3));
	ret = num + ',' + cents;
	if (x == 1)
		ret = ' - ' + ret;
	return ret;
}

/**
 * Pega um valor formatado com virgula e separador de milha e o transforma em float
 */
function moeda2float(moeda) {
	moeda = moeda.replace(".", "");
	moeda = moeda.replace(",", ".");
	return parseFloat(moeda);

}

function formataMoedaDuasCasasDecimais(z) {
	v = z.value;
	v = v.replace(/\D/g, "") // permite digitar apenas numero
	v = v.replace(/(\d{1})(\d{14})$/, "$1.$2") // coloca ponto antes dos ultimos digitos
	v = v.replace(/(\d{1})(\d{11})$/, "$1.$2") // coloca ponto antes dos ultimos 11 digitos
	v = v.replace(/(\d{1})(\d{8})$/, "$1.$2") // coloca ponto antes dos ultimos 8 digitos
	v = v.replace(/(\d{1})(\d{5})$/, "$1.$2") // coloca ponto antes dos ultimos 5 digitos
	v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2") // coloca virgula antes dos ultimos 2 digitos
	z.value = v;
}


function num(dom) {
	dom.value = dom.value.replace(/\D/g, '');
}


function RecuperarUsuarios() {
	$.ajax({
		type : "GET",
		url : "http://localhost:8080/projeto-veiculo/rest/veiculos/",
		success : function(veiculos) {
			var table = '<tbody>';
			$.each(veiculos, function(indice, veiculos) {
				var id = veiculos.id;
				var dataCadastro = veiculos.dataDeCadastro;
				var placa = veiculos.placa;
				var renavam = veiculos.renavam;
				var modelo = veiculos.modelo;
				var opcionais = veiculos.opcionais;
				var valorVenda = veiculos.valorDeVenda;

				table += '<tr>' +
					'<td>' + dataCadastro + '</td>' +
					'<td>' + placa + '</td>' +
					'<td>' + renavam + '</td>' +
					'<td>' + modelo + '</td>' +
					'<td>' + opcionais + '</td>' +
					'<td>' + float2moeda(valorVenda) + '</td>' +
					'<td><button class="btn btn-warning" onclick="setUpdate(' + veiculos.id + ');">Edit</button> <button class="btn btn-danger" onclick="deleteData(' + veiculos.id + ');">Delete</button></td>' +
					'</tr>';
			})
			table += '</tbody>';
			document.getElementById("listTable").innerHTML = table;
		}
	});

}
setList();