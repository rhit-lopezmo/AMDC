package GUI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.LookAndFeel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import backendFeatures.BackendFeatures;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class main {

	public static void main(String[] args) {
		// sets up GUI, including: system file explorer
		setupGUI();
		
		// Pick excel file
		JFileChooser fc = chooseExcelFile();
		
		// Create Apache POI Workbook to Read + Edit Excel File
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(fc.getSelectedFile());
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get code length and generate codes
		System.out.print("How long should the code be? (Must be 6 or greater): ");
		Scanner lengthInput = new Scanner(System.in);
		int codeLength = lengthInput.nextInt();
		lengthInput.close();
		generateDiscountCodes(workbook, codeLength);
		
		
	}
	
	private static JFileChooser chooseExcelFile() {
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter xlsxFilter = new FileNameExtensionFilter("Microsoft Excel Files", "xlsx", "xls");
		fc.setFileFilter(xlsxFilter);
		fc.showOpenDialog(fc);
		return fc;
	}
	
	private static void generateDiscountCodes(Workbook workbook, int codeLength) {
		//FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		Sheet membersSheet = workbook.getSheetAt(0);
		for(int i = 1; i <= membersSheet.getLastRowNum(); i++) {
			
			// Grab current member info (current row)
			Row currMemInfo = membersSheet.getRow(i);
			
			// Use DataFormatter to grab name and id of current member as strings
			DataFormatter formatter = new DataFormatter();
			
			String memName = formatter.formatCellValue(currMemInfo.getCell(0));
			String memID = formatter.formatCellValue(currMemInfo.getCell(1));
			
			// Generate code for current member
			String currMemCode = BackendFeatures.randomCodeGen(codeLength, memName, memID);
			System.out.println(i + ": " + currMemCode);
			// Write code back into Excel file
			
			
		}
	}
	
	private static void setupGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
