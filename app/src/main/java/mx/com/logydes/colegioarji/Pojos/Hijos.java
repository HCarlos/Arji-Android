package mx.com.logydes.colegioarji.Pojos;

/**
 * Created by devch on 23/06/16.
 */
public class Hijos {
    private int data;
    private String label;
    private String grupo;
    private String msg;
    private int IdUserAlu;
    private String urlBoleta;
    private String logoEmp;
    private String logoIB;
    private int IsBoleta;
    private int IdGruAlu;
    public int Clave_Nivel;
    public int Genero;

    public Hijos(int data, String label, String grupo, String msg, int idgrualu, String _urlBoleta, String _logoEmp, String _logoIB, int _IsBoleta, int _IdGruAlu, int _Clave_Nivel, int _Genero) {
        this.data = data;
        this.label = label;
        this.grupo = grupo;
        this.msg = msg;
        IdUserAlu = idgrualu;
        urlBoleta = _urlBoleta;
        logoEmp = _logoEmp;
        logoIB = _logoIB;
        IsBoleta = _IsBoleta;
        IdGruAlu = _IdGruAlu;
        Clave_Nivel = _Clave_Nivel;
        Genero = _Genero;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getIdUserAlu() {
        return IdUserAlu;
    }

    public void setIdUserAlu(int idGruAlu) {
        IdUserAlu = idGruAlu;
    }

    public String getUrlBoleta() {
        return urlBoleta;
    }

    public void setUrlBoleta(String urlBoleta) {
        this.urlBoleta = urlBoleta;
    }

    public String getLogoEmp() {
        return logoEmp;
    }

    public void setLogoEmp(String logoEmp) {
        this.logoEmp = logoEmp;
    }

    public String getLogoIB() {
        return logoIB;
    }

    public void setLogoIB(String logoIB) {
        this.logoIB = logoIB;
    }

    public int getIsBoleta() {
        return IsBoleta;
    }

    public void setIsBoleta(int isBoleta) {
        IsBoleta = isBoleta;
    }

    public int getIdGruAlu() {
        return IdGruAlu;
    }

    public void setIdGruAlu(int idGruAlu) {
        IdGruAlu = idGruAlu;
    }

    public int getClave_Nivel() {
        return Clave_Nivel;
    }

    public void setClave_Nivel(int clave_Nivel) {
        Clave_Nivel = clave_Nivel;
    }

    public int getGenero() {
        return Genero;
    }

    public void setGenero(int genero) {
        Genero = genero;
    }
}
