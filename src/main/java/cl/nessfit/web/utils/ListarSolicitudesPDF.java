package cl.nessfit.web.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
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
		PdfPTable tablaSolicitud = new PdfPTable(7);
		listaSol.forEach(solicitud ->{
			tablaSolicitud.addCell(String.valueOf(solicitud.getId()));
			tablaSolicitud.addCell(solicitud.getFechaCompra());
			tablaSolicitud.addCell(solicitud.getNombreCentro());
			tablaSolicitud.addCell(String.valueOf(solicitud.getTotalPagar()));
			tablaSolicitud.addCell(solicitud.getUsuario().getRut());
			tablaSolicitud.addCell(solicitud.getUsuario().getNombre() + ' ' + solicitud.getUsuario().getApellido());
			tablaSolicitud.addCell(String.valueOf(solicitud.getEstado()));
		});
		document.add(tablaSolicitud);
	}
	
}
