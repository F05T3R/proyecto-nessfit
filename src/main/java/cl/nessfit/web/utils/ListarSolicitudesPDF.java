package cl.nessfit.web.utils;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import cl.nessfit.web.model.Solicitud;

@Component("cliente/verSolicitudes")
public class ListarSolicitudesPDF extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Solicitud> listaSol = (List<Solicitud>) model.get("lista");
		
		Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.BLACK);
		Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.BLACK);
		Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER, 10, Color.BLACK);
		
		document.setPageSize(PageSize.LETTER.rotate());
		document.setMargins(-20, -20, 30, 20);
		document.open();
		PdfPCell celda = null;
		
		PdfPTable tablaTitulo = new PdfPTable(1);
		
		celda = new PdfPCell (new Phrase ("LISTADO DE SOLICITUDES", fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(32, 198, 122));
		celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		celda.setPadding(30);
		
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(30);
		
		PdfPTable tablaSolicitud = new PdfPTable(7);
		tablaSolicitud.setWidths(new float[] {0.8f, 2f, 2f, 1.5f, 1.5f, 2f, 1.5f});
		
		celda = new PdfPCell (new Phrase ("ID", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaSolicitud.addCell(celda);
		
		celda = new PdfPCell (new Phrase ("FECHA", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaSolicitud.addCell(celda);
		
		celda = new PdfPCell (new Phrase ("CENTRO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaSolicitud.addCell(celda);
		
		celda = new PdfPCell (new Phrase ("COSTO TOTAL", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaSolicitud.addCell(celda);
		
		celda = new PdfPCell (new Phrase ("RUT", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaSolicitud.addCell(celda);
		
		celda = new PdfPCell (new Phrase ("NOMBRE CLIENTE", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaSolicitud.addCell(celda);
		
		celda = new PdfPCell (new Phrase ("ESTADO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaSolicitud.addCell(celda);
		
		for (Solicitud solicitud : listaSol) {
			celda = new PdfPCell (new Phrase (String.valueOf(solicitud.getId()), fuenteDataCeldas));
			celda.setPadding(5);
			tablaSolicitud.addCell(celda);
			
			celda = new PdfPCell (new Phrase (solicitud.getFechaCompra(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaSolicitud.addCell(celda);
			
			celda = new PdfPCell (new Phrase (solicitud.getNombreCentro(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaSolicitud.addCell(celda);
			
			celda = new PdfPCell (new Phrase (String.valueOf(solicitud.getTotalPagar()), fuenteDataCeldas));
			celda.setPadding(5);
			tablaSolicitud.addCell(celda);
			
			celda = new PdfPCell (new Phrase (solicitud.getUsuario().getRut(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaSolicitud.addCell(celda);
			
			celda = new PdfPCell (new Phrase (solicitud.getUsuario().getNombre() + ' ' + solicitud.getUsuario().getApellido(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaSolicitud.addCell(celda);
			
			celda = new PdfPCell (new Phrase (String.valueOf(solicitud.getEstado()), fuenteDataCeldas));
			celda.setPadding(5);
			tablaSolicitud.addCell(celda);
		}
		document.add(tablaTitulo);
		document.add(tablaSolicitud);
	}
	
}
