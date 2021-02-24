package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Controller {

        @FXML
        private TextField city;

        @FXML
        private Button getData;

        @FXML
        private Text temp_info;

        @FXML
        private Text temp_temp;

        @FXML
        private Text temp_feels;

        @FXML
        private Text temp_max;

        @FXML
        private Text temp_min;

        @FXML
        private Text temp_pressure;

        @FXML
        void initialize()
        {
            getData.setOnAction( event ->{
                String getUserCity = city.getText().trim();
                if(!getUserCity.equals(""))
                {
                String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q="+getUserCity+"&appid=acf7b30fd78d3021d4ae5e449095690c&units=metric");
                if(!output.isEmpty())
                {
                    JSONObject obj = new JSONObject(output);
                    temp_temp.setText("Temperature: "+obj.getJSONObject("main").getDouble("temp"));
                    temp_feels.setText("Feels: "+obj.getJSONObject("main").getDouble("feels_like"));
                    temp_max.setText("Maximum: "+obj.getJSONObject("main").getDouble("temp_max"));
                    temp_min.setText("Minimum: "+obj.getJSONObject("main").getDouble("temp_min"));
                    temp_pressure.setText("Pressure: "+obj.getJSONObject("main").getDouble("pressure"));
                }
                }
            });
//        assert city !=null : "fx:id=\"city\" was not injected: check your FXML file 'sample/fxml'";
//        assert getData !=null : "fx:id=\"getData\" was not injected: check your FXML file 'sample/fxml'";
//        assert temp_feels !=null : "fx:id=\"temp_feels\" was not injected: check your FXML file 'sample/fxml'";
//        assert temp_info !=null : "fx:id=\"temp_info\" was not injected: check your FXML file 'sample/fxml'";
//        assert temp_temp !=null : "fx:id=\"temp_temp\" was not injected: check your FXML file 'sample/fxml'";
//        assert temp_max !=null : "fx:id=\"temp_max\" was not injected: check your FXML file 'sample/fxml'";
//        assert temp_min !=null : "fx:id=\"temp_min\" was not injected: check your FXML file 'sample/fxml'";
//        assert temp_pressure !=null : "fx:id=\"temp_pressure\" was not injected: check your FXML file 'sample/fxml'";
        }

        private static String getUrlContent(String urlAddress)
        {
            StringBuffer content = new StringBuffer();
            try
            {
                    URL url = new URL(urlAddress);
                    URLConnection urlConnection = url.openConnection();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line;

                    while((line = bufferedReader.readLine()) != null)
                    {
                         content.append(line + "\n");
                    }
                    bufferedReader.close();

            }
            catch (Exception e)
            {
                System.out.println("No such city found...");
            }
            return content.toString();
        }


}

