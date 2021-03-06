package mx.com.logydes.colegioarji.Pojos;

/**
 * Created by devch on 22/06/16.
 */
public class Lista_Elementos {
    private int IdElemento;
    private int IdObjAlu;
    private int IdObjMenu;
    private int IdObj;
    private int IdTareaDestinatario;
    private int IdTarea;
    private int IdFactura;
    private int IdEdoCta;
    private int IdComMensaje;
    private int IdComMensajeDestinatario;
    private int IdMobileMensaje;
    private int Status_Read;
    private String urlWeb;
    private String body;
    private String label;
    private String Profesor;
    private String Director;
    private String Directorio;
    private String PDF;
    private String XML;
    private String Mes;
    private String Concepto;
    private String FechaPago;
    private String Fecha;
    private String Mensaje;
    private String Vencimiento;
    private int Vencido;
    private int status_movto;
    private int IdConcepto;
    private int PagosDiv;

    private int Tipo;


    public int getIdElemento() {
        return IdElemento;
    }

    public Lista_Elementos() {
    }

    public Lista_Elementos(int _IdElemento, String _label) {
        IdElemento = _IdElemento;
        label = _label;
    }

    public Lista_Elementos(String label, String profesor, int idTarea, int idTareaDestinatario, int tipo, int status_read) {
        this.label = label;
        IdTarea = idTarea;
        IdTareaDestinatario = idTareaDestinatario;
        Tipo = tipo;
        Profesor = profesor;
        Status_Read = status_read;
    }

    public Lista_Elementos(int idComMensaje, int idComMensajeDestinatario, String label, String Remitente, int tipo, int status_Read) {
        IdComMensaje = idComMensaje;
        IdComMensajeDestinatario = idComMensajeDestinatario;
        this.label = label;
        Tipo = tipo;
        Director = Remitente;
        Status_Read = status_Read;
    }

    public Lista_Elementos(int idElemento, int IdMobileMensaje, int Status_Read, String fecha, String mensaje, String _label, int tipo) {
        IdElemento = idElemento;
        this.IdMobileMensaje = IdMobileMensaje;
        this.Status_Read = Status_Read;
        Fecha = fecha;
        Mensaje = mensaje;
        label = _label;
        Tipo = tipo;
    }


    public int getIdTareaDestinatario() {
        return IdTareaDestinatario;
    }


    public void setIdTareaDestinatario(int idTareaDestinatario) {
        IdTareaDestinatario = idTareaDestinatario;
    }

    public int getIdComMensajeDestinatario() {
        return IdComMensajeDestinatario;
    }

    public void setIdComMensajeDestinatario(int idComMensajeDestinatario) {
        IdComMensajeDestinatario = idComMensajeDestinatario;
    }

    public Lista_Elementos(String PDF, String XML, String directorio, int idFactura, int tipo) {
        this.PDF = PDF;
        this.XML = XML;
        Directorio = directorio;
        IdFactura = idFactura;
        Tipo = tipo;
    }

    public Lista_Elementos(int status_movto, int vencido, String fechaPago, String vencimiento, String concepto, String mes, int IdEdoCta, int tipo, int pagosDiv, int idconcepto) {
        this.status_movto = status_movto;
        label = concepto + " " + mes;
        Vencido = vencido;
        FechaPago = fechaPago;
        Vencimiento = vencimiento;
        Concepto = concepto;
        Mes = mes;
        IdElemento = IdEdoCta;
        this.IdEdoCta = IdEdoCta;
        Tipo = tipo;
        PagosDiv = pagosDiv;
        IdConcepto = idconcepto;
    }

    public void setIdElemento(int idElemento) {
        IdElemento = idElemento;
    }

    public int getIdObjAlu() {
        return IdObjAlu;
    }

    public void setIdObjAlu(int idObjAlu) {
        IdObjAlu = idObjAlu;
    }

    public int getIdObjMenu() {
        return IdObjMenu;
    }

    public void setIdObjMenu(int idObjMenu) {
        IdObjMenu = idObjMenu;
    }

    public int getIdObj() {
        return IdObj;
    }

    public void setIdObj(int idObj) {
        IdObj = idObj;
    }

    public int getIdTarea() {
        return IdTarea;
    }

    public void setIdTarea(int idTarea) {
        IdTarea = idTarea;
    }

    public int getIdComMensaje() {
        return IdComMensaje;
    }

    public void setIdComMensaje(int idComMensaje) {
        IdComMensaje = idComMensaje;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(String urlWeb) {
        this.urlWeb = urlWeb;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public int getIdFactura() {
        return IdFactura;
    }

    public void setIdFactura(int idFactura) {
        IdFactura = idFactura;
    }

    public int getIdEdoCta() {
        return IdEdoCta;
    }

    public void setIdEdoCta(int IdEdoCta) {
        IdEdoCta = IdEdoCta;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }

    public String getXML() {
        return XML;
    }

    public void setXML(String XML) {
        this.XML = XML;
    }

    public String getDirectorio() {
        return Directorio;
    }

    public void setDirectorio(String directorio) {
        Directorio = directorio;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String mes) {
        Mes = mes;
    }

    public String getConcepto() {
        return Concepto;
    }

    public void setConcepto(String concepto) {
        Concepto = concepto;
    }

    public String getFechaPago() {
        return FechaPago;
    }

    public void setFechaPago(String fechaPago) {
        FechaPago = fechaPago;
    }

    public int getVencido() {
        return Vencido;
    }

    public void setVencido(int vencido) {
        Vencido = vencido;
    }

    public String getVencimiento() {
        return Vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        Vencimiento = vencimiento;
    }

    public int getStatus_movto() {
        return status_movto;
    }

    public void setStatus_movto(int status_movto) {
        this.status_movto = status_movto;
    }

    public int getIdConcepto() {
        return IdConcepto;
    }

    public void setIdConcepto(int idConcepto) {
        IdConcepto = idConcepto;
    }

    public int getPagosDiv() {
        return PagosDiv;
    }

    public void setPagosDiv(int pagosDiv) {
        PagosDiv = pagosDiv;
    }

    public String getProfesor() {
        return Profesor;
    }

    public void setProfesor(String profesor) {
        Profesor = profesor;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public int getIdMobileMensaje() { return IdMobileMensaje;}

    public void setIdMobileMensaje(int IdMobileMensaje) {this.IdMobileMensaje = IdMobileMensaje;}

    public int getStatus_Read() {return Status_Read;}

    public void setStatus_Read(int status_Read) {this.Status_Read = status_Read;}

    public String getFecha() {return Fecha;}

    public void setFecha(String fecha) {Fecha = fecha;}

    public String getMensaje() {return Mensaje;}

    public void setMensaje(String mensaje) {Mensaje = mensaje;}
}
