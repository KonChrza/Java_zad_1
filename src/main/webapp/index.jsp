<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>kalkulator</title>
	
</head>
<body>
<form name="form1" action="/credit_calc_serv" method="post">
	<label>Wartosc kredytu <input type="text" id="creditValue" name="creditValue"></label><br>
	<label>ilosc rat <input type="text" id="numberOfInstallments" name="numberOfInstallments"></label><br>
	<label>oprocentowanie roczne (Kwota w procentach) <input type="text" id="intrest" name="intrest"></label>%<br>
	<label>oplata stala <input type="text" id="flatPayment" name="flatPayment"></label><br>
	<label>Rata Malejaca <input type="checkbox" id="isInstallmentLower" name="isInstallmentLower"></label><br>
	<input onclick="myFunction()" type="button" value="wyslij">
	</form>
</body>
</html>

<script>  

var a = document.getElementById("creditValue");

const myFunction = () =>
	{
		var empt1 = document.forms["form1"]["creditValue"].value;
		var empt2 = document.forms["form1"]["numberOfInstallments"].value;
		var empt3 = document.forms["form1"]["intrest"].value;
		var empt4 = document.forms["form1"]["flatPayment"].value;
		
		if (empt1 == "" || empt2=="" || empt3=="" || empt4=="")
		{
		alert("Prosze wprowadzic liczby do pol");
		
		
		}
		else 
		{
			document.forms["form1"].submit();
			
		}
	}


</script>