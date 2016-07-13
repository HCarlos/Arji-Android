package mx.com.logydes.colegioarji.Helper;

import android.util.Log;

import java.util.ArrayList;

import mx.com.logydes.colegioarji.Pojos.Lista_Elementos;
import mx.com.logydes.colegioarji.Pojos.Hijos;

/**
 * Created by devch on 22/06/16.
 */

public class Singleton {
    private static Singleton ourInstance = new Singleton();
    public static Singleton getInstance() {
        return ourInstance;
    }

    private static String pathPList;
    private static int Modulo;
    private static int IdUser;
    private static int IdEmp;
    private static int IdUserNivelAcceso;
    private static int Clave;
    private static int Param1;
    private static int RegistrosPorPagina;
    private static boolean IsDelete;
    private static String Empresa;
    private static String Username;
    private static String Password;
    private static String NombreCompletoUsuario;
    private static ArrayList<Hijos> rsHijos;
    private static int rsHijosSize;
    private static int IdAlu;
    private static int IdUserAlu;
    private static String urlBoleta;
    private static String logoEmp;
    private static String logoIB;
    private static int IsBoleta;
    private static int IdGruAlu;
    private static int Clave_Nivel;

    private static ArrayList<Lista_Elementos> rsElementos;
    private static int rsElementosSize;

    public Singleton() { }

    public Singleton(int modulo, int idUser, int idEmp, int idUserNivelAcceso, int clave, int param1, int registrosPorPagina, boolean isDelete, String empresa, String username, String password, String nombreCompletoUsuario) {
        Modulo = modulo;
        IdUser = idUser;
        IdEmp = idEmp;
        IdUserNivelAcceso = idUserNivelAcceso;
        Clave = clave;
        Param1 = param1;
        RegistrosPorPagina = registrosPorPagina;
        IsDelete = isDelete;
        Empresa = empresa;
        Username = username;
        Password = password;
        NombreCompletoUsuario = nombreCompletoUsuario;
        rsHijos = new ArrayList<Hijos>();
        rsHijosSize = 0;
        IdAlu = 0;
        IdUserAlu = 0;
        rsElementos = new ArrayList<Lista_Elementos>();
        rsElementosSize = 0;
    }

    public static String getPathPList() {
        return pathPList;
    }

    public static void setPathPList(String pathPList) {
        Singleton.pathPList = pathPList;
    }

    public static int getModulo() {
        return Modulo;
    }

    public static void setModulo(int modulo) {
        Modulo = modulo;
    }

    public static int getIdUser() {
        return IdUser;
    }

    public static void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public static int getIdEmp() {
        return IdEmp;
    }

    public static void setIdEmp(int idEmp) {
        IdEmp = idEmp;
    }

    public static int getIdUserNivelAcceso() {
        return IdUserNivelAcceso;
    }

    public static void setIdUserNivelAcceso(int idUserNivelAcceso) {
        IdUserNivelAcceso = idUserNivelAcceso;
    }

    public static int getClave() {
        return Clave;
    }

    public static void setClave(int clave) {
        Clave = clave;
    }

    public static int getParam1() {
        return Param1;
    }

    public static void setParam1(int param1) {
        Param1 = param1;
    }

    public static int getRegistrosPorPagina() {
        return RegistrosPorPagina;
    }

    public static void setRegistrosPorPagina(int registrosPorPagina) {
        RegistrosPorPagina = registrosPorPagina;
    }

    public static boolean isDelete() {
        return IsDelete;
    }

    public static void setIsDelete(boolean isDelete) {
        IsDelete = isDelete;
    }

    public static String getEmpresa() {
        return Empresa;
    }

    public static void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public static String getUsername() {
        return Username;
    }

    public static void setUsername(String username) {
        Username = username;
    }

    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String password) {
        Password = password;
    }

    public static String getNombreCompletoUsuario() {
        return NombreCompletoUsuario;
    }

    public static int getIdUserAlu() {
        return IdUserAlu;
    }

    public static void setIdUserAlu(int idGruAlu) {
        IdUserAlu = idGruAlu;
    }

    public static void setNombreCompletoUsuario(String nombreCompletoUsuario) {
        NombreCompletoUsuario = nombreCompletoUsuario;
    }

    public static ArrayList<Hijos> getRsHijos() {
        return rsHijos;
    }

    public static void setRsHijos(ArrayList<Hijos> rsHijos) {
        Singleton.rsHijos = rsHijos;
        Singleton.setRsHijosSize();
    }

    public static int getRsHijosSize() {
        return rsHijosSize;
    }

    private static void setRsHijosSize() {
        if (rsHijos != null) {
            Singleton.rsHijosSize = rsHijos.size();
        }else{
            Singleton.rsHijosSize = 0;
        }
        Log.e("rsHijosSize", String.valueOf(rsHijosSize)  );
    }

    public static int getIdAlu() {
        return IdAlu;
    }

    public static void setIdAlu(int idAlu) {
        IdAlu = idAlu;
    }

    public static String getUrlBoleta() {
        return urlBoleta;
    }

    public static void setUrlBoleta(String urlBoleta) {
        Singleton.urlBoleta = urlBoleta;
    }

    public static String getLogoEmp() {
        return logoEmp;
    }

    public static void setLogoEmp(String logoEmp) {
        Singleton.logoEmp = logoEmp;
    }

    public static String getLogoIB() {
        return logoIB;
    }

    public static void setLogoIB(String logoIB) {
        Singleton.logoIB = logoIB;
    }

    public static int getIsBoleta() {
        return IsBoleta;
    }

    public static void setIsBoleta(int isBoleta) {
        IsBoleta = isBoleta;
    }

    public static int getIdGruAlu() {
        return IdGruAlu;
    }

    public static void setIdGruAlu(int idGruAlu) {
        IdGruAlu = idGruAlu;
    }

    public static int getClave_Nivel() {
        return Clave_Nivel;
    }

    public static void setClave_Nivel(int clave_Nivel) {
        Clave_Nivel = clave_Nivel;
    }

    public static ArrayList<Lista_Elementos> getRsElementos() {
        return rsElementos;
    }

    public static void setRsElementos(ArrayList<Lista_Elementos> rsElementos) {
        Singleton.rsElementos = rsElementos;
        Singleton.setRsElementosSize();
    }

    public static int getRsElementosSize() {
        return rsElementosSize;
    }

    private static void setRsElementosSize() {
        if (rsElementos != null) {
            Singleton.rsElementosSize = rsElementos.size();
        }
        Log.e("rsElementosSize", String.valueOf(rsElementosSize)  );
    }



    public static void reset() {
        setRsHijos(null);
        setRsElementos(null);
        ourInstance = new Singleton();
    }


}
