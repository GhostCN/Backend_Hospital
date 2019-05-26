package org.sid.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.dao.ConsultationDao;
import org.sid.models.Consultation;
import org.sid.models.Patient;
import org.sid.models.Utilisateur;
import org.sid.service.Account;
import org.sid.service.ConsultationDto;
import org.sid.service.DossierMedical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
@CrossOrigin("*")
public class DocMedicRestController {
	@Autowired
	private DossierMedical doc;
	@Autowired
	private Account ac;
	@Autowired
	private ConsultationDao cdao;
	@PostMapping("/addCons")
	public int addConsultation(@RequestBody ConsultationDto cons) 
	{
		System.out.println("---------------------patient-----------------------");
		Patient p=new Patient();
		p.setNumerodossier(cons.getNumerodossier());
		p.setDatenaiss(cons.getDatenaiss());
		p.setTel(cons.getTel());
		p.setNom(cons.getNom());
		Consultation consult=new Consultation();
		consult.setDate(new Date());
		consult.setCommentaire(cons.getCommentaire());
		consult.setPrescription(cons.getPrescription());
		consult.setPatient(doc.addPatient(p));
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Utilisateur u=ac.findByMatricule(auth.getName());
		consult.setService(cons.getServices());
		consult.setUtilisateur(u);
		int ok=doc.addConsultation(consult);
		if(ok>0) {
			return ok;
		}
		else {
			return ok;
		}	
	}
	
	@PostMapping("/editCons/{id}")
	public Consultation editConsultation(@PathVariable Long id,@RequestBody Consultation cons) {
	
			Optional<Consultation> c=doc.getConsultation(id);
	
			cons.setId(id);
			cons.setDate(c.get().getDate());
			cons.setPatient(c.get().getPatient());
			cons.setUtilisateur(c.get().getUtilisateur());
			cons.setService(c.get().getService());
		return cdao.save(cons);
		
		
	}
	
	
	@GetMapping("/getCons/{id}")
	public  Optional<Consultation> getConsultation(@PathVariable Long id) {
		return doc.getConsultation(id);
		
	}
	@PostMapping("/getPatient")
	public Patient getPatient(@RequestBody String num) {
		return doc.findByNumerodossier(num);
	}
	@GetMapping("/listCons")
	public List<Consultation>listConsult(){
		return ac.getAllConsultation();
	}
	
	@PostMapping("/addPatient")
	public Patient addPatient(@RequestBody Patient p) {
		return doc.addPatient(p);
		
	}
	@GetMapping("/patients")
	public List<Patient>listePatient()
	{
		return doc.listPatient();
	}
	@GetMapping("/pdfOrdonnance/{id}")
	public void genererOrdonnance(@PathVariable Long id) throws DocumentException, IOException{
		SimpleDateFormat d= new SimpleDateFormat("dd-MM-yyyy");
	
		Optional<Consultation> c=doc.getConsultation(id);
        	Image image = Image.getInstance ("src/main/resources/static/medecin.jpg"); 
        	image.scaleAbsolute(80, 50);
        	image.scalePercent(70);
	        Document document = new Document(PageSize.A4.rotate());
	        PdfWriter.getInstance(document, new FileOutputStream("Ordonnance.pdf"));
	       /* response.setContentType("application/pdf");*/
	        document.open();
	        PdfPTable table1;
	        table1 = new PdfPTable(1);
	        table1.setWidthPercentage(10);
	        table1.addCell(new PdfPCell(image, true));
	        document.add(table1);
	        PdfPTable table;
	        table = new PdfPTable(2);
	        Paragraph p=new Paragraph();
	        PdfPCell cell;
	        cell = new PdfPCell();
	        p.add(new Phrase("\n"));
	        p.add(new Phrase("       service:  "+c.get().getService().getLibelle()));
	        p.add(new Phrase("\n"));
	        p.add(new Phrase("       medecin: "+c.get().getUtilisateur().getNom())+"  "+c.get().getUtilisateur().getService().getLibelle());
	        p.add(new Phrase("\n"));
	        p.add(new Phrase("       contact medecin: "+c.get().getUtilisateur().getTel()));
	        cell.addElement(p);
	        cell.setBorderWidthLeft(15);
	        cell.setBorderColorLeft(BaseColor.BLUE);
	        cell.setBorderWidthTop(8);
	        cell.setBorderColorTop(BaseColor.GRAY);
	        table.addCell(cell);
	        Paragraph p1=new Paragraph();
	        p1.add(new Phrase("\n"));
	        p1.add(new Phrase("\n"));
	        p1.add(new Phrase("  Adresse: Guediawaye\n"));
	        p1.add(new Phrase("  Tel: +221777667971\n"));
	        p1.add(new Phrase("  Email: ghost@gmail.com\n"));
	        cell = new PdfPCell(p1);
	        cell.setBorderWidthLeft(5);
	        cell.setBorderColorLeft(BaseColor.BLUE);
	        cell.setBorderWidthTop(8);
	        cell.setBorderColorTop(BaseColor.GRAY);
	        table.addCell(cell);
	        document.add(table);
	        Font blue = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
	        PdfPTable table2; 
	        PdfPTable table3;
	        table2 = new PdfPTable(2);
	        table2.addCell(new Phrase("  date consultation : "+d.format(c.get().getDate())));
	        table2.addCell(new Phrase("  date de delivrance : "+d.format(new Date())));
	        document.add(table2);
	        document.add(new Phrase("\n"));
	        document.add(new Phrase("\n"));
	        
	        table3 = new PdfPTable(1);
	        table3.addCell(new Phrase("                                                  __________________Patient____________________________",blue));
	        document.add(table3);
	        table3 = new PdfPTable(1);
	        table3.addCell(new Phrase("                                Nom : "+c.get().getPatient().getNom())+"  Numero identification : "+c.get().getPatient().getNumerodossier());
	        document.add(table3);
	        document.add(new Phrase("\n"));
	        document.add(new Phrase("\n"));
	   /*     document.add(new Phrase("                                                               **************************Prescription***************************************",blue));*/
	        document.add(new Phrase("\n"));
	        document.add(new Phrase("\n"));
	     /*   String pres[]=c.get().getPrescription().split(",");
	        table3 = new PdfPTable(3);
	        table3.addCell(new Phrase("MEDICAMENTS",blue));
	        table3.addCell(new Phrase("DOSAGE",blue));
	        table3.addCell(new Phrase("POSOLOGIE",blue));
				for (Prescription pc: c.getPrescriptions()) {
	        	 table3 = new PdfPTable(3);
	        		table3.addCell(pc.getMedicament());
					table3.addCell(pc.getDosage());
					table3.addCell(pc.getPosologie());
	        		document.add(table3);
		   }*/
	        table3 = new PdfPTable(1);
	        table3.addCell(new Phrase("                                                  __________________Prescription____________________________",blue));
	        document.add(table3);
	        table3 = new PdfPTable(1);
	        table3.addCell(new Phrase("                                Prescription: "+c.get().getPrescription())+"  Numero identification : "+c.get().getPatient().getNumerodossier());
	        table3.addCell(new Phrase("                                Commentaire: "+c.get().getCommentaire()));
	        document.add(table3);
	      /*  document.add(new Phrase("\n"));
	        Paragraph p3=new Paragraph();
	        document.add(new Phrase("       Prescription: "+c.get().getPrescription()));
	        document.add(new Phrase("       Commentaire: "+c.get().getCommentaire()));
	        document.add(new Phrase("\n"));
	        document.add(new Phrase("\n"));*/
	        
	        
	        document.add(new Phrase("                                                              signature du Medecin",blue));
	       document.close(); 
	}

}
