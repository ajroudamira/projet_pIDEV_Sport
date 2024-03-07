package tn.esprit.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tn.esprit.entities.Equipement;
import tn.esprit.services.ServiceType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportToExcel {
    public static void exportToExcel(List<Equipement> list, File file){
        ServiceType st=new ServiceType();
        XSSFWorkbook workbook=new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("Equipements");
        int row=0;
        Row headerRow=sheet.createRow(row++);
        Cell cell=headerRow.createCell(0, CellType.STRING);
        cell.setCellValue("Nom");
        cell=headerRow.createCell(1, CellType.STRING);
        cell.setCellValue("Description");
        cell=headerRow.createCell(2, CellType.STRING);
        cell.setCellValue("Marque");
        cell=headerRow.createCell(3, CellType.STRING);
        cell.setCellValue("Modele");
        cell=headerRow.createCell(4, CellType.STRING);
        cell.setCellValue("Couleur");
        cell=headerRow.createCell(5, CellType.STRING);
        cell.setCellValue("Matiere");
        cell=headerRow.createCell(6, CellType.STRING);
        cell.setCellValue("Taille");
        cell=headerRow.createCell(7, CellType.STRING);
        cell.setCellValue("Date de fabrication");
        cell=headerRow.createCell(8, CellType.STRING);
        cell.setCellValue("Image");
        cell=headerRow.createCell(9, CellType.STRING);
        cell.setCellValue("Etat");
        cell=headerRow.createCell(10, CellType.STRING);
        cell.setCellValue("Type");
        for(Equipement e:list){
            Row fillrow=sheet.createRow(row++);
            Cell cellr=fillrow.createCell(0, CellType.STRING);
            cellr.setCellValue(e.getNom());
            cellr=fillrow.createCell(1, CellType.STRING);
            cellr.setCellValue(e.getDescription());
            cellr=fillrow.createCell(2, CellType.STRING);
            cellr.setCellValue(e.getMarque());
            cellr=fillrow.createCell(3, CellType.STRING);
            cellr.setCellValue(e.getModele());
            cellr=fillrow.createCell(4, CellType.STRING);
            cellr.setCellValue(e.getCouleur());
            cellr=fillrow.createCell(5, CellType.STRING);
            cellr.setCellValue(e.getMatiere());
            cellr=fillrow.createCell(6, CellType.STRING);
            cellr.setCellValue(e.getTaille());
            cellr=fillrow.createCell(7, CellType.STRING);
            cellr.setCellValue(String.valueOf(e.getDate_de_fabrication()));
            cellr=fillrow.createCell(8, CellType.STRING);
            cellr.setCellValue(e.getImage());
            cellr=fillrow.createCell(9, CellType.BOOLEAN);
            cellr.setCellValue(e.isEtat());
            cellr=fillrow.createCell(10, CellType.STRING);
            cellr.setCellValue(st.getTypeParId(e.getIdType()).getNom());
        }
        FileOutputStream outputStream= null;
        try {
            outputStream = new FileOutputStream(file);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
