package utils;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class HKTDFileChooser extends JFileChooser{
	public static String fileButtonActionPerformed() {
	    JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("Text", "txt");
	    fileChooser.setFileFilter(imgFilter);
	    fileChooser.setMultiSelectionEnabled(false);
	    
	    int x = fileChooser.showDialog(null, "Lưu file");
	    if (x == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        if (selectedFile != null) {
	            return selectedFile.getAbsolutePath();
	        }
	    }
	    
	    return null; // Trả về null nếu không có tập tin được chọn hoặc nếu hành động bị hủy.
	}
}
