package mx.com.logydes.colegioarji.Pojos;

/**
 * Created by devch on 19/05/17.
 */

public class Sistema_Info {


    private static String Token;
    private static String Token_ID;
    private static int IdUser;

    private static Sistema_Info ourInstance = new Sistema_Info();
    public static Sistema_Info getInstance() {
        return ourInstance;
    }

    // private static Singleton ourInstance = new Singleton();
    /*
    public static Singleton getInstance() {
        return ourInstance;
    }
    */

    public Sistema_Info() { }

    public Sistema_Info(String token, String token_ID, int idUser) {
        Token = token;
        Token_ID = token_ID;
        IdUser = idUser;
    }

    public static String getToken() {
        return Token;
    }

    public static void setToken(String token) {
        Token = token;
    }

    public static String getToken_ID() {
        return Token_ID;
    }

    public static void setToken_ID(String token_ID) {
        Token_ID = token_ID;
    }

    public static int getIdUser() {
        return IdUser;
    }

    public static void setIdUser(int idUser) {
        IdUser = idUser;
    }
}
