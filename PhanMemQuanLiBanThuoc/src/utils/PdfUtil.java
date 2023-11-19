package utils;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PdfUtil {
	
	public static PDDocument createPdfFile() throws Exception {
		PDDocument doc;
        PDPage page;

        try {
	    	doc = new PDDocument();
	        page = new PDPage();

	        doc.addPage(page);
	  
	        
	        PDPageContentStream content = new PDPageContentStream(doc, page);
	        content.beginText();
	        content.setNonStrokingColor(Color.BLUE);
//	        content.moveTextPositionByAmount( 100, 700 );
//	        content.drawString("Hello It's me");

	        content.endText();
	        content.close();

	        doc.save("pdf_with_text.pdf");
	        doc.close();
	        
	        return doc;
	        
		} catch (Exception ex) {
			System.out.println(ex);
		}
        
        return null;
	}
	
	public static void export(String path, String fileName) throws IOException {
		PDDocument pdfdoc= new PDDocument();  
		pdfdoc.addPage(new PDPage());  
		
		pdfdoc.save(path);  
		pdfdoc.close();
	}
}
