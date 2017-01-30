/**
 * 
 */

var list = [
	{
		"id" : 1,
		"dataDeCadastro" : "11/01/2017 00:00:00",
		"placa" : "OOO-6666",
		"renavam" : 1234567890,
		"modelo" : "Chevrolet Prisma LT 1.4",
		"opcionais" : "Air Bag",
		"valorDeVenda" : 15000.00
	},
	{
		"id" : 5,
		"dataDeCadastro" : "18/05/2017 00:00:00",
		"placa" : "BBB-0000",
		"renavam" : 1234567890,
		"modelo" : "Wolksvagem Jetta Trendline 1.4 TSI",
		"opcionais" : "Ar Condicionado",
		"valorDeVenda" : 30000.00
	},
	{
		"id" : 6,
		"dataDeCadastro" : "14/07/2017 00:00:00",
		"placa" : "MCO-1234",
		"renavam" : 1234567890,
		"modelo" : "Chevrolet Prisma LT 1.4",
		"opcionais" : "Ar Condicionado;Vidros Elétricos;Air Bag;",
		"valorDeVenda" : 12000.00
	}
];

function setList(list) {
	var table = '<thead>' +
		'<tr>' +
		'<td><b>Data de cadastro</b></td>' +
		'<td><b>Placa</b></td>' +
		'<td><b>Renavan</b></td>' +
		'<td><b>Modelo</b></td>' +
		'<td><b>Opcionais</b></td>' +
		'<td><b>Valor de venda</b></td>' +
		'</tr>' +
		'</thead>' +
		'<tbody>';
	for (var key in list) {
		table += '<tr>' +
			'<td>' + list[key].dataDeCadastro + '</td>' +
			'<td>' + list[key].placa + '</td>' +
			'<td>' + list[key].renavam + '</td>' +
			'<td>' + list[key].modelo + '</td>' +
			'<td>' + list[key].opcionais + '</td>' +
			'<td>' + float2moeda(list[key].valorDeVenda) + '</td>' +
			'<td><button class="btn btn-warning" onclick="setUpdate(' + key + ');">Edit</button> <button class="btn btn-danger" onclick="deleteData(' + key + ');">Delete</button></td>' +
			'</tr>';
	}
	table += '</tbody>';
	document.getElementById("listTable").innerHTML = table;

	$(document).ready(function() {
		$.getJSON('http://localhost:8080/projeto-veiculo/rest/veiculos/', function(data) {

			alert(data.veiculos.id);

		});
	});
}


function salvar() {
	var placa = document.getElementById("placa").value;
	var renavan = document.getElementById("renavam").value;
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

	$(function() {
		$('form#formulario').submit(function() {
			$.ajax({
				type : 'POST',
				url : '',
				data : {
					placa : document.getElementById("placa").value,
				}
			}).done(function(e) {});

		});
	});




}


function setUpdate(id) {
	var obj = list[id];
	document.getElementById("id").value = obj.id;
	document.getElementById("placa").value = obj.placa;
	document.getElementById("renavam").value = obj.renavam;
	document.getElementById("valor").value = obj.valorDeVenda;

	// Acessando propriedade CSS para fazer botão aparecer.
	document.getElementById("btnUpdate").style.display = "inline-block";
	// Acessando propriedade CSS para fazer botão desaparecer.
	document.getElementById("btnSave").style.display = "none";

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

setList(list);