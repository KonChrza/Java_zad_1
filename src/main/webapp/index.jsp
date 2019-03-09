<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>kalkulator</title>
	<form action="/credit_calc_serv" method="post">
	<label>Wartosc kredytu <input type="text" id="creditValue" name="creditValue"></label><br>
	<label>ilosc rat <input type="text" id="numberOfInstallments" name="numberOfInstallments"></label><br>
	<label>oprocentowanie roczne (Kwota w procentach) <input type="text" id="intrest" name="intrest"></label>%<br>
	<label>oplata stala <input type="text" id="flatPayment" name="flatPayment"></label><br>
	<label>Rata Malejaca <input type="checkbox" id="isInstallmentLower" name="isInstallmentLower"></label><br>
	<input type="submit" value="wyslij">
	</form>
</head>
<body>

</body>
</html>