package mx.com.logydes.colegioarji.Pojos;

import java.util.PriorityQueue;

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
    private String urlWeb;
    private String body;
    private String label;
    private String Directorio;
    private String PDF;
    private String Mes;
    private String Concepto;
    private String FechaPago;
    private int Vencido;
    private int status_movto;
    
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

    public Lista_Elementos(String label, int idTarea, int idTareaDestinatario, int tipo) {
        this.label = label;
        IdTarea = idTarea;
        IdTareaDestinatario = idTareaDestinatario;
        Tipo = tipo;
    }

    public Lista_Elementos(int idComMensaje, int idComMensajeDestinatario, String label, int tipo) {
        IdComMensaje = idComMensaje;
        IdComMensajeDestinatario = idComMensajeDestinatario;
        this.label = label;
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

    public Lista_Elementos(String PDF, String directorio, int idFactura, int tipo) {
        this.PDF = PDF;
        Directorio = directorio;
        IdFactura = idFactura;
        Tipo = tipo;
    }

    public Lista_Elementos(int status_movto, int vencido, String fechaPago, String concepto, String mes, int IdEdoCta, int tipo) {
        this.status_movto = status_movto;
        label = concepto + " " + mes;
        Vencido = vencido;
        FechaPago = fechaPago;
        Concepto = concepto;
        Mes = mes;
        IdElemento = IdEdoCta;
        this.IdEdoCta = IdEdoCta;
        Tipo = tipo;
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

    public int getStatus_movto() {
        return status_movto;
    }

    public void setStatus_movto(int status_movto) {
        this.status_movto = status_movto;
    }
}
