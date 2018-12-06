package leonix.com.br.chatnattor;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import leonix.com.br.model.Status;
import leonix.com.br.model.Usuario;

public class LeonixApi {

    String json = null;
    HttpURLConnection urlConnection = null;

    public void getUsuarios(final String restUrl, final Runnable runnable) {
        // Instanciando Thread.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(restUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    InputStream inputStream = urlConnection.getInputStream();

                    String json = getStringFromInputStream(inputStream);

                    Log.d("JsonObtido", json);

                    Type listType = new TypeToken<ArrayList<Usuario>>() {
                    }.getType();
                    Gson gson = new Gson();
                    Statics.usuarios = gson.fromJson(json, listType);
                    gson = null;

                    Log.d("Usuarios", String.valueOf(Statics.usuarios.size()));

                    new Thread(runnable).start();

                } catch (ProtocolException e1) {
                    Log.e("ChatNattor", "Falha ao acessar Web service", e1);
                    e1.printStackTrace();
                } catch (MalformedURLException e1) {
                    Log.e("ChatNattor", "Falha ao acessar Web service", e1);
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        // Iniciando Thread.
        thread.start();
    }

    public void logar(final String restUrl, final Runnable runnable) {
        // Instanciando Thread.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(restUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    InputStream inputStream = urlConnection.getInputStream();

                    String json = getStringFromInputStream(inputStream);

                    Log.d("JsonObtido", json);

                    Type type = new TypeToken<Status>() {}.getType();
                    Gson gson = new Gson();
                    Statics.status = gson.fromJson(json, type);
                    gson = null;

                    runnable.run();

                } catch (ProtocolException e1) {
                    Log.e("ChatNattor", "Falha ao acessar Web service", e1);
                    e1.printStackTrace();
                } catch (MalformedURLException e1) {
                    Log.e("ChatNattor", "Falha ao acessar Web service", e1);
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        // Iniciando Thread.
        thread.start();
    }

    public void cadastrar(final String restUrl, final Runnable runnable) {
        // Instanciando Thread.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(restUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    InputStream inputStream = urlConnection.getInputStream();

                    String json = getStringFromInputStream(inputStream);

                    Log.d("JsonObtido", json);

                    Type type = new TypeToken<Status>() {}.getType();
                    Gson gson = new Gson();
                    Statics.status = gson.fromJson(json, type);
                    gson = null;

                    runnable.run();

                } catch (ProtocolException e1) {
                    Log.e("ChatNattor", "Falha ao acessar Web service", e1);
                    e1.printStackTrace();
                } catch (MalformedURLException e1) {
                    Log.e("ChatNattor", "Falha ao acessar Web service", e1);
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        // Iniciando Thread.
        thread.start();
    }

    private static String getStringFromInputStream(InputStream inputStream) {
        String line = null;
        BufferedReader reader = null;
        StringBuilder strBuilder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                strBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return strBuilder.toString();
    }

}
