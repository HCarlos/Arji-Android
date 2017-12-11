package mx.com.logydes.colegioarji.Utils;

/**
 * Created by devch on 15/06/16.
 */
public class AppConfig {
    // Server user login url

    public static String TEXT_HTML = "text/html";
    public static String UTF_8 = "UTF-8";

    public static String URL_HOME = "https://platsource.mx/";

    public static String URL_LOGIN = URL_HOME + "getLoginUserMobile/";

    // Server user register url
    // public static String URL_REGISTER = "http://192.168.0.102/android_login_api/register.php";

    public static String URL_BOLETIN = URL_HOME + "getBoletin/";
    public static int URL_BOLETIN_TYPE = 0;

    public static String URL_QUIENES_SOMOS = URL_HOME + "getQuienesSomos/";
    public static int URL_QUIENES_SOMOS_TYPE = 1;

    public static String URL_CERTIFICACIONES = URL_HOME + "getCertificaciones/";
    public static int URL_CERTIFICACIONES_TYPE = 2;

    public static String URL_DIRECTORIO = URL_HOME + "getDirectorio/";
    public static int URL_DIRECTORIO_TYPE = 3;

    public static String URL_PROCESO_ADMISION = URL_HOME + "getProcesoAdmision/";
    public static int URL_PROCESO_ADMISION_TYPE = 4;

    public static String URL_MAPA = "https://www.google.com/maps/place/17%C2%B058'07.6%22N+92%C2%B056'59.8%22W/@17.968768,-92.9521257,17z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d17.968768!4d-92.949937";
    // https://maps.googleapis.com/maps/api/staticmap?center=47.5952,-122.3316&zoom=16&size=640x400&path=weight:3%7Ccolor:blue%7Cenc:{coaHnetiVjM??_SkM??~R&key=YOUR_API_KEY
    public static int URL_MAPA_TYPE = 5;

    public static String URL_BENEFICIOS = URL_HOME + "getBeneficios/";
    public static int URL_BENEFICIOS_TYPE = 6;

    public static String URL_DESARROLLADOR = URL_HOME + "getContacto/";
    public static int URL_DESARROLLADOR_TYPE = 7;

    public static String URL_GET_HIJOS = URL_HOME + "getListaHijosAndroid/";

    public static String URL_TAREAS_CIRCULARES = URL_HOME + "getListaTutorTareasAndroid/";

    public static String URL_TAREA = URL_HOME + "getHTMLTemplateAndroid/";
    public static String URL_CIRCULARES = URL_HOME + "getCircularesHTMLTemplateAndroid/";

    public static String URL_VIEW_PAGOS = URL_HOME + "getPagosPost/";
    public static String URL_VIEW_BOLETAS = URL_HOME + "getBoletasPost/";

    public static String URL_CALENDARIO = URL_HOME + "getCalendario/";
    public static int URL_CALENDARIO_TYPE = 8;

    public static String URL_AVISO_PRIVACIDAD = URL_HOME + "getAvisoPrivacidad/";
    public static int URL_AVISO_PRIVACIDAD_TYPE = 9;

    public static String URL_NOTIFICACIONES = URL_HOME + "getMensajesAndroid/";
    public static int URL_NOTIFICACIONES_TYPE = 10;

    public static String URL_NOTIFICACION = URL_HOME + "getMensajeAndroid/";
    public static int URL_NOTIFICACION_TYPE = 11;

    public static int INDICE_CIRCULAR = 1;
    public static int INDICE_FACTURAS = 2;
    public static int INDICE_NOTIFICACIONES = 6;


}
