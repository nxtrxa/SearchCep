package com.example.app_extapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText i_cep;

    TextView tv_street, tv_neighborhood, tv_city, tv_state;
    Button btnSearch, btnClear;

    String cep, url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Var
        btnSearch = findViewById(R.id.pesquisar);
        btnClear = findViewById(R.id.limpar);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i_cep.getText().length() != 8) {
                    i_cep.setError("Insert a valid cep");
                } else {
                    cep = i_cep.getText().toString();
                    url = "https://brasilapi.com.br/api/cep/v1/"+cep+"json";
                    search(url);
                }
            }
        });
    }

    //btnClear.onClickLis

    public void search(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(
                Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("test", response);
                try {
                    JSONObject street = new JSONObject(response);
                    JSONObject city = new JSONObject(response);
                    JSONObject street = new JSONObject(response);
                    JSONObject street = new JSONObject(response);

                    tv_street.setText("Rua" + street.getString("street"));
                    tv_city.setText("Cidade" + city.getString("city"));
                    tv_neighborhood.setText("Bairro" + street.getString("neighborhood"));
                    tv_state.setText("Estado" + street.getString("estate"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener()
            {@Override
                public void onErrorResponse(VolleyError error) {

                }
            }
        );
    }

    public void cleanEdt() {
        i_cep.setText("");
    }

}