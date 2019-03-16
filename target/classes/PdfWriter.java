import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;

import servlets.Instalment;


public class PdfWriter {
	
	PdfWriter(){
		
		
	}
	
	public void WriteTableToPdf(ArrayList<Instalment> list) 
	
	{	 
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("Doc.pdf"));
		document.open();
		PdfPTable table = new PdfPTable(5);
		addTableHeader(table);
		
		for (instalment instal : list){
			
			table.addCell((list.indexOf(instalment).ToString);
			table.addCell(instalment.getCredit().ToString());
			table.addCell(instalment.getIntrest().ToString());
			table.addCell(instalment.getFlatPayment().ToString());
			table.addCell(instalment.getWholePayment().ToString());
			
		}
		
		document.close();
		
	}
	
	private void addTableHeader(PdfPTable table) {
	    Stream.of("Nr Raty", "Kwota Kapitalu", "Kwota Odsetek","Oplaty Stale","Kwota Calkowita")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}

}
