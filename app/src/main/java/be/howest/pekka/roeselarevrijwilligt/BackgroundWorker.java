package be.howest.pekka.roeselarevrijwilligt;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by peksu on 11.12.2016.
 */

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    // Backgroundworker which controls the info received from the database, using PHP files

    Context ctx;
    AlertDialog alertDialog;
    String login_url;
    String json_url;
    String JSON_STRING;
    private static String paramid = "";
   NavigationDrawerActivity navi;

    BackgroundWorker(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        paramid = params[1];
        setId(paramid);

        // This switch controls the actions with the Database, which PHP files are used etc. Every case has it's own method

        String result ="";
        switch (type) {

            case "login":

                String username = params[2];
                String password = params[3];
                result = getLogin(username, password);
                break;

            case "getusers":

                String moi = "moi";
                String test = "test";
                String hei = "hei";
               result = getdata();

                break;

        }
        ;
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    public static String getId() {
        return paramid;
    }

    public static void setId(String id) {
        BackgroundWorker.paramid = id;
    }


    protected String getdata() {

        // This method gets the data from the database by using Php files
        // Url where the php file is located
        login_url = "http://10.0.2.2/jsongetdata.php";
        String test = "";
        String moi = "";
        String hei = "";
        String postid ="2";

        try {

           // Establishin connection to the database
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String postdata = URLEncoder.encode("test", "UTF-8") + "=" + URLEncoder.encode(test, "UTF-8") + "&" +
                    URLEncoder.encode("moi", "UTF-8") + "=" + URLEncoder.encode(moi, "UTF-8") + "&" +
                    URLEncoder.encode("hei", "UTF-8") + "=" + URLEncoder.encode(hei, "UTF-8") + "$" +
                    URLEncoder.encode("postid", "UTF-8") + "=" + URLEncoder.encode(postid, "UTF-8");

            bufferedWriter.write(postdata);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            StringBuilder result2 = new StringBuilder();
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                //result += line;
                result2.append(line + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            result = result2.toString().trim();
            Log.d("MySql", "connection done " + result);
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.d("MySql", "ERROR1 " + e);

            return e.toString();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("MySql", "ERROR2 " + e);
            return e.toString();


        }
    }



    protected String getLogin(String user, String pass)
    {

        // This method gets the user credentials from the database, working same as the previous

        String username = user;
        String password = pass;
        String postid = "1";
        login_url = "http://10.0.2.2/login.php";
        try {

            Log.d("try start ", "login");

            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                    URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                    URLEncoder.encode("postid", "UTF-8") + "=" + URLEncoder.encode(postid, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }

            bufferedReader.close();
            inputStream.close();

            httpURLConnection.disconnect();
            if (result.equals("Login success"))
            {

               navi = new NavigationDrawerActivity();
                Log.d("result equals", "väli");
              navi.saveuser(username, password);
                Log.d("Background login", "onnistu");
            }
                return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }


    }

}
