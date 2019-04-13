 package servlets;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


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
		if (isNotNumeric(request.getParameter("creditValue")) ||  //sprawdzenie czy podane wartosci to liczby
				isNotNumeric(request.getParameter("numberOfInstallments")) || 
					isNotNumeric(request.getParameter("intrest")) || 
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
			
			response.getWriter().println("<div id=\"divTable\">");
			response.getWriter().println("<table class=\"table\" id=\"table\">");
			response.getWriter().println( "<tr style = 'background:#ccc'>");
			response.getWriter().println( "<th>" + " Nr raty " +" </th>");
			response.getWriter().println( "<th>" + " Kwota Kapitalu " +" </th>");
			response.getWriter().println( "<th>" + " Kwota Odsetek " +" </th>");
			response.getWriter().println( "<th>" + " Oplaty Stale " +" </th>");
			response.getWriter().println( "<th>" + " Kwota Calkowita " +" </th>");
			response.getWriter().println( "</tr>");
			
			if (isInstallmentLower == null) {
				
				ArrayList<Instalment> listOfInstalments = intrestAreFlat(amoutOfCredit, instalments, intrest, flatPayment);
			for (int i = 1; i<=listOfInstalments.size();i++){
			
			
			response.getWriter().println( "<tr style = 'background:#ccc'>");
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
				
				response.getWriter().println( "<tr style = 'background:#ccc'>");
				response.getWriter().println( "<td>" + i +" </td>");
				response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getCredit()) +" </td>");
				response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getIntrest()) +" </td>");
				response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getFlatPayment()) +" </td>");
				response.getWriter().println( "<td>" + df.format(listOfInstalments.get(i-1).getWholePayment()) +" </td>");
				response.getWriter().println( "</tr>");
				}
		}
				response.getWriter().println("</table>");
				response.getWriter().println("</div>");
				response.getWriter().println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js\"></script>" + 
											"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js\"></script>" + 
											"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js\"></script>");
				
				response.getWriter().println("<script>"
						+ " function toPDF() {" + 
						"    var pdf = new jsPDF('p', 'pt', 'letter');" + 
						"    source = $('#divTable')[0];" + 
						"    specialElementHandlers = {" + 
						"        '#bypassme': function (element, renderer) {" + 
						"            return true" + 
						"        }" + 
						"    };" + 
						"    margins = {" + 
						"        top: 80," + 
						"        bottom: 60," + 
						"        left: 40," + 
						"        width: 522" + 
						"    };" + 
						"    pdf.fromHTML(" + 
						"    source," + 
						"    margins.left, " + 
						"    margins.top, { " + 
						"        'width': margins.width, " + 
						"        'elementHandlers': specialElementHandlers" + 
						"    }," +  
						"    function (dispose) {" + 
						"        pdf.save('Test.pdf');" + 
						"    }, margins);" + 
						"}</script>");
				response.getWriter().println("<button onclick=\"javascript:toPDF()\">esportuj do PDF</button>");
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
	private static boolean isNotNumericInt(String str) { 
		if (str.isEmpty()) return false;
		if (str == "") return false;
		if (str == null) return false;
		try {  
			Integer.parseInt(str); 
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
	
