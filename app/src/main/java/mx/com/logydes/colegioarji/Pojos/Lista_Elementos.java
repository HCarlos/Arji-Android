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
    private int IdComMensaje;
    private int IdComMensajeDestinatario;
    private String urlWeb;
    private String body;
    private String label;
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
}
