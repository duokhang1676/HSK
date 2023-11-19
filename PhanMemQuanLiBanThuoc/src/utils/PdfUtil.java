package utils;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PdfUtil {
	
	public static PDDocument createPdfFile() throws Exception {
//		
        
        return null;
	}
	
	public static void export(String path, String fileName) throws IOException {
		PDDocument pdfdoc= new PDDocument();  
		pdfdoc.addPage(new PDPage());  
		
		pdfdoc.save(path);  
		pdfdoc.close();
	}
	public static void main(String[] args) {
		try {
			createPdfFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
