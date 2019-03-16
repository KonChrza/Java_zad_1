package servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/credit_calc_serv")
public class credit_calc_serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public credit_calc_serv() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("/");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isNotNumeric(request.getParameter("creditValue")) &&  //sprawdzenie czy podane wartosci to liczby
				isNotNumeric(request.getParameter("numberOfInstallments")) && 
					isNotNumeric(request.getParameter("intrest")) && 
						isNotNumeric(request.getParameter("flatPayment"))) 
				{
					response.sendRedirect("/ErrorPage.jsp");
				}
		double amoutOfCredit = Double.parseDouble(request.getParameter("creditValue"));
		int instalments = Integer.parseInt(request.getParameter("numberOfInstallments"));
		double intrest = Double.parseDouble(request.getParameter("intrest"));
		double flatPayment = Double.parseDouble(request.getParameter("flatPayment"));

		String isInstallmentLower = request.getParameter("isInstallmentLower");
			DecimalFormat df = new DecimalFormat("#.##");
			
			response.getWriter().println("<table class=\"table\">");
			response.getWriter().println( "<tr>");
			response.getWriter().println( "<th>" + " Nr raty " +" </th>");
			response.getWriter().println( "<th>" + " Kwota Kapitalu " +" </th>");
			response.getWriter().println( "<th>" + " Kwota Odsetek " +" </th>");
			response.getWriter().println( "<th>" + " Oplaty Stale " +" </th>");
			response.getWriter().println( "<th>" + " Kwota Calkowita " +" </th>");
			response.getWriter().println( "</tr>");
			
			if (isInstallmentLower == null) {
				
				ArrayList<Instalment> listOfInstalments = intrestAreFlat(amoutOfCredit, instalments, intrest, flatPayment);
			for (int i = 1; i<=listOfInstalments.size();i++){
			
			
			response.getWriter().println( "<tr>");
			response.getWriter().println( "<td>" + i +" </td>");
			response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getCredit()) +" </td>");
			response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getIntrest()) +" </td>");
			response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getFlatPayment()) +" </td>");
			response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getWholePayment()) +" </td>");
			response.getWriter().println( "</tr>");
			}
			
			
		}
		else {
			
			ArrayList<Instalment> listOfInstalments = intrestsAreLower(amoutOfCredit, instalments, intrest, flatPayment);
			for (int i = 1; i<=listOfInstalments.size();i++){
				
				response.getWriter().println( "<tr>");
				response.getWriter().println( "<td>" + i +" </td>");
				response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getCredit()) +" </td>");
				response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getIntrest()) +" </td>");
				response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getFlatPayment()) +" </td>");
				response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getWholePayment()) +" </td>");
				response.getWriter().println( "</tr>");
				}
		}
		
			response.getWriter().println("</table>");
		
	}
	
	private static boolean isNotNumeric(String str) { 
		if (str.isEmpty()) return false;
		if (str == "") return false;
		if (str == null) return false;
		try {  
		    Double.parseDouble(str);  
		    return false;
		  } catch(NumberFormatException e){  
		    return true;  
		  }  
		}
	
	private static ArrayList<Instalment> intrestsAreLower(double credit, int instalments, double interst, double flatPaym) {
		ArrayList<Instalment> listOfInstalments = new ArrayList<Instalment>();
		for (int i = 1; i <= instalments; i++) {
			double singleCredit = credit/instalments;
			double instalment = singleCredit*(1+0.01*(interst/instalments)*(instalments-i+1));
			double costOfIntrest = instalment - singleCredit;
			Instalment instal = new Instalment(singleCredit,costOfIntrest,flatPaym,instalment);
			listOfInstalments.add(instal);
		}
		
		return listOfInstalments;
	}
	
	private static ArrayList<Instalment> intrestAreFlat(double credit, int instalments, double interst, double flatPaym){
		ArrayList<Instalment> listOfInstalments = new ArrayList<Instalment>();
		double tmp = 1 + 0.01*interst/instalments;
		for (int i =1; i <= instalments; i++) {
			
			double instalment = credit*Math.pow(tmp,instalments)*((tmp-1)/(-1+Math.pow(tmp,instalments)));
			double singleCredit = instalment - credit*(0.01*interst/instalments);
			double costOfIntrest = instalment - singleCredit;
			Instalment instal = new Instalment(singleCredit,costOfIntrest,flatPaym,instalment);
			listOfInstalments.add(instal);
			
		}
		
		return listOfInstalments;
	}
}
