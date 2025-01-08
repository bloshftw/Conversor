import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;

public class ConversorDeMonedas {

    private static final String URL_API = "https://v6.exchangerate-api.com/v6/6c061c3c679802dc8e56fde2/latest/";

    public static double obtenerTasaDeCambio(String monedaBase, String monedaDestino) throws Exception {
        String urlString = URL_API + monedaBase;
        URL url = new URL(urlString);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");

        BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        StringBuilder respuesta = new StringBuilder();
        String linea;
        while ((linea = lector.readLine()) != null) {
            respuesta.append(linea);
        }
        lector.close();

        Gson gson = new Gson();
        JsonObject jsonRespuesta = gson.fromJson(respuesta.toString(), JsonObject.class);

        JsonObject tasas = jsonRespuesta.getAsJsonObject("conversion_rates");
        return tasas.get(monedaDestino).getAsDouble();
    }

    public static double convertirMoneda(double cantidad, double tasaDeCambio) {
        return cantidad * tasaDeCambio;
    }
}
