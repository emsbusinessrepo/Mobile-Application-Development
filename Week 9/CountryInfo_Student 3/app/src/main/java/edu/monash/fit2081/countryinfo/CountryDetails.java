package edu.monash.fit2081.countryinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Handler;
import android.os.Looper;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
// w9
public class CountryDetails extends AppCompatActivity {

    private TextView name;
    private TextView capital;
    private TextView code;
    private TextView population;
    private TextView area;
    // added new
    private TextView currencies;
    private TextView languages;
    private TextView region;
    private ImageView flag;
    private Button button;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        getSupportActionBar().setTitle(R.string.title_activity_country_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String selectedCountry = getIntent().getStringExtra("country");

        name = findViewById(R.id.country_name);
        capital = findViewById(R.id.capital);
        code = findViewById(R.id.country_code);
        population = findViewById(R.id.population);
        area = findViewById(R.id.area);
        // finding the id
        currencies = findViewById(R.id.currencies);
        languages = findViewById(R.id.language);
        flag = findViewById(R.id.countryflag);
        button = findViewById(R.id.wikibtn);
        region = findViewById(R.id.region);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        //Executor handler = ContextCompat.getMainExecutor(this);
        Handler uiHandler=new Handler(Looper.getMainLooper());



        executor.execute(() -> {
            //Background work here
            CountryInfo countryInfo = new CountryInfo();

            try {
                // Create URL
                URL webServiceEndPoint = new URL("https://restcountries.com/v2/name/" + selectedCountry); //

                // Create connection
                HttpsURLConnection myConnection = (HttpsURLConnection) webServiceEndPoint.openConnection();

                if (myConnection.getResponseCode() == 200) {
                    //JSON data has arrived successfully, now we need to open a stream to it and get a reader
                    InputStream responseBody = myConnection.getInputStream();
                    InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");

                    //now use a JSON parser to decode data
                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                    jsonReader.beginArray(); //consume arrays's opening JSON brace
                    String keyName;
                    // countryInfo = new CountryInfo(); //nested class (see below) to carry Country Data around in
                    boolean countryFound = false;
                    while (jsonReader.hasNext() && !countryFound) { //process array of objects
                        jsonReader.beginObject(); //consume object's opening JSON brace
                        while (jsonReader.hasNext()) {// process key/value pairs inside the current object
                            keyName = jsonReader.nextName();
                            if (keyName.equals("name")) {
                                countryInfo.setName(jsonReader.nextString());
                                if (countryInfo.getName().equalsIgnoreCase(selectedCountry)) {
                                    countryFound = true;
                                }
                            } else if (keyName.equals("alpha2Code")) {
                                countryInfo.setAlpha2Code(jsonReader.nextString());
                                String request = "https://countryflagsapi.com/png/" + countryInfo.getAlpha2Code().toLowerCase();
                                // String request = "https://flagcdn.com/144x108/" + countryInfo.getAlpha2Code().toLowerCase() + ".png";
                                java.net.URL url = new java.net.URL(request);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.connect();
                                InputStream input = connection.getInputStream();
                                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                                uiHandler.post(() -> {flag.setImageBitmap(myBitmap);});
                            }    else if (keyName.equals("alpha3Code")) {
                                countryInfo.setAlpha3Code(jsonReader.nextString());
                            } else if (keyName.equals("capital")) {
                                countryInfo.setCapital(jsonReader.nextString());
                            } else if (keyName.equals("population")) {
                                countryInfo.setPopulation(jsonReader.nextInt());
                            } else if (keyName.equals("area")) {
                                countryInfo.setArea(jsonReader.nextDouble());
                                // Added currencies, languages and region
                            } else if (keyName.equals("currencies")) {
                                ArrayList<String> currencies = new ArrayList<String>();
                                jsonReader.beginArray();
                                while (jsonReader.hasNext()) {
                                    jsonReader.beginObject();
                                    while (jsonReader.hasNext()) {
                                        String name = jsonReader.nextName();
                                        if (name.equals("name")) {
                                            currencies.add(jsonReader.nextString());
                                        } else {
                                            jsonReader.skipValue();
                                        }
                                    }
                                    jsonReader.endObject();
                                }
                                jsonReader.endArray();
                                countryInfo.setCurrencies(currencies);
                            } else if (keyName.equals("languages")) {
                                ArrayList<String> languages = new ArrayList<String>();
                                jsonReader.beginArray();
                                while (jsonReader.hasNext()) {
                                    jsonReader.beginObject();
                                    while (jsonReader.hasNext()) {
                                        String name = jsonReader.nextName();
                                        if (name.equals("name")) {
                                            languages.add(jsonReader.nextString());
                                        } else {
                                            jsonReader.skipValue();
                                        }
                                    }
                                    jsonReader.endObject();
                                }
                                jsonReader.endArray();
                                countryInfo.setLanguages(languages);
                            } else if (keyName.equals("region")) {
                                countryInfo.setRegion(jsonReader.nextString());
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                    }
                    jsonReader.endArray();
                    uiHandler.post(()->{
                        name.setText(countryInfo.getName());
                        capital.setText(countryInfo.getCapital());
                        // changed from alpha 3 to alpha 2 cuz the task requires that.
                        code.setText(countryInfo.getAlpha2Code());
                        population.setText(Integer.toString(countryInfo.getPopulation()));
                        area.setText(Double.toString(countryInfo.getArea()));
                        // set text for currency, languages, region and lastly button
//                        currencies.setText(countryInfo.getCurrencies().stream().collect(Collectors.joining(", ")));
                        currencies.setText(String.join(",",countryInfo.getCurrencies()));
                        languages.setText(countryInfo.getlanguages().stream().collect(Collectors.joining(", ")));
                        button.setText("Wiki " + countryInfo.getName());
                        region.setText(countryInfo.getRegion());
                    });


                } else {
                    Log.i("INFO", "Error:  No response");
                }

                // All your networking logic should be here
            } catch (Exception e) {
                Log.i("INFO", "Error " + e.toString());
            }

        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // button, followed from ppt video 3
    public void wikiBtn(View v) {
        Intent myIntent = new Intent(CountryDetails.this, WebWiki.class);
        myIntent.putExtra("url", "https://en.wikipedia.org/wiki/" + name.getText());
        CountryDetails.this.startActivity(myIntent);
    }


    private class CountryInfo {
        private String name;
        private String alpha3Code;
        private String alpha2Code;
        private String capital;
        private int population;
        private double area;
        // Arraylist string
        private ArrayList<String> currencies = new ArrayList<String>();
        private ArrayList<String> languages = new ArrayList<String>();
        private String region;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }



        public String getAlpha3Code() {
            return alpha3Code;
        }

        public void setAlpha3Code(String alpha3Code) {
            this.alpha3Code = alpha3Code;
        }

        public String getCapital() {
            return capital;
        }

        public void setCapital(String capital) {
            this.capital = capital;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public double getArea() {
            return area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        // newly added get and setter for currency, language, region and alpha2code
        public ArrayList<String> getCurrencies() {return currencies;}

        public void setCurrencies(ArrayList<String> currencies) {this.currencies = currencies;}

        public ArrayList<String> getlanguages() {return languages;}

        public void setLanguages(ArrayList<String> languages) {this.languages = languages;}

        public String getRegion() {return region;}

        public void setRegion(String region) {this.region = region;}

        public String getAlpha2Code() {return alpha2Code;}

        public void setAlpha2Code(String alpha2Code) {this.alpha2Code = alpha2Code;}

    }
}

