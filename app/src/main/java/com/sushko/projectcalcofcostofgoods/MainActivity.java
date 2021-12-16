package com.sushko.projectcalcofcostofgoods;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sushko.projectcalcofcostofgoods.DataAddressRequest.AddressData;
import com.sushko.projectcalcofcostofgoods.DataAddressRequest.RetrofitAPI;
import com.sushko.projectcalcofcostofgoods.DataPriceRequest.PriceData;
import com.sushko.projectcalcofcostofgoods.DataPriceRequest.RetrofitAPI_2;

import java.io.IOException;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{

    Spinner spin_ServiceType, spin_CargoType;
    EditText ET_Weight, ET_Sender, ET_Recipient, ET_Length, ET_Width, ET_Height;
    Button btnCalculate, btnClear;
    TextView tvPrice;

    //ApiKey Новой Почты для запрсоов
    final String ApiKey = "5273c0b22d5c0bd8fc90b16254173e49";

    String RefSender;
    String RefRecipient;
    int RefFlag = 0;

    int cost;
    boolean ExNull = false;

    String[] TypesOfCargo = { "Вантаж", "Документи", "Посилка"};
    String[] TypesOfService = { "Двері-Двері", "Двері-Склад", "Склад-Склад", "Склад-Двері"};
    String CargoType;
    String ServiceType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ET_Weight = findViewById(R.id.ET_Weight);
        ET_Sender = findViewById(R.id.ET_Sender);
        ET_Recipient = findViewById(R.id.ET_Recipient);
        ET_Length = findViewById(R.id.ET_Length);
        ET_Width = findViewById(R.id.ET_Width);
        ET_Height = findViewById(R.id.ET_Height);
        spin_CargoType = findViewById(R.id.spin_CargoType);
        spin_ServiceType = findViewById(R.id.spin_ServiceType);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        tvPrice = findViewById(R.id.tvPrice);

        ArrayAdapter<String> adapter_Cargo = new ArrayAdapter(this, android.R.layout.simple_spinner_item, TypesOfCargo);
        ArrayAdapter<String> adapter_Service = new ArrayAdapter(this, android.R.layout.simple_spinner_item, TypesOfService);

        adapter_Cargo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_Service.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_CargoType.setAdapter(adapter_Cargo);
        spin_ServiceType.setAdapter(adapter_Service);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

                switch(parent.getId())
                {
                    case R.id.spin_CargoType:
                        switch((String)parent.getItemAtPosition(position))
                        {
                            case "Вантаж":
                                CargoType = "Cargo";
                                break;
                            case "Документи":
                                CargoType = "Documents";
                                break;
                            case "Посилка":
                                CargoType = "Parcel";
                                break;
                        }
                        break;
                    case R.id.spin_ServiceType:
                        switch((String)parent.getItemAtPosition(position))
                        {
                            case "Двері-Двері":
                                ServiceType = "DoorsDoors";
                                break;
                            case "Двері-Склад":
                                ServiceType = "DoorsWarehouse";
                                break;
                            case "Склад-Склад":
                                ServiceType = "WarehouseWarehouse";
                                break;
                            case "Склад-Двері":
                                ServiceType = "WarehouseDoors";
                                break;
                        }
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        };
        spin_CargoType.setOnItemSelectedListener(itemSelectedListener);
        spin_ServiceType.setOnItemSelectedListener(itemSelectedListener);

        btnCalculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //Валидация полей
                if (ET_Sender.getText().toString().isEmpty() || ET_Recipient.getText().toString().isEmpty() || ET_Weight.getText().toString().isEmpty() || ET_Length.getText().toString().isEmpty() || ET_Width.getText().toString().isEmpty() || ET_Height.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Будь ласка, заповніть усі поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(ET_Weight.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "У полі Ваги має бути ціле число", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(ET_Length.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "У полі Довжини має бути ціле число", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(ET_Width.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "У полі Ширини має бути ціле число", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(ET_Height.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "У полі Висоти має бути ціле число", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isDigitsOnly(ET_Sender.getText().toString()) || TextUtils.isDigitsOnly(ET_Recipient.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "У полях маршруту не повинно бути чисел", Toast.LENGTH_SHORT).show();
                    return;
                }

                AsyncTask.execute(new Runnable() //Поток для запросов
                {
                    @Override
                    public void run()
                    {
                        postData("Address", "getCities", ApiKey, ET_Sender.getText().toString());
                        postData("Address", "getCities", ApiKey, ET_Recipient.getText().toString());
                        postDataPrice(ApiKey, RefSender, RefRecipient, Integer.parseInt(ET_Weight.getText().toString()), ServiceType, CargoType, Integer.parseInt(ET_Width.getText().toString()), Integer.parseInt(ET_Length.getText().toString()), Integer.parseInt(ET_Height.getText().toString()));
                    }
                });

                try
                {
                    Thread.sleep(2200);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                //Обработка исключений связанных с запросами
                if(ExNull)
                {
                    Toast.makeText(MainActivity.this, "Помилка при надсиланні запиту, будь ласка, перевірте введені дані та спробуйте ще раз", Toast.LENGTH_SHORT).show();
                    ExNull = false;
                    return;
                }
                tvPrice.setText(cost + " грн");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ET_Weight.setText("");
                ET_Sender.setText("");
                ET_Recipient.setText("");
                ET_Length.setText("");
                ET_Width.setText("");
                ET_Height.setText("");
                tvPrice.setText("");
            }
        });
    }

    //AddressRequest
    private void postData(String modelName, String calledMethod, String apiKey, String FindByString)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.novaposhta.ua/v2.0/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        AddressData modal = new AddressData(modelName, calledMethod, apiKey, FindByString);

        Call<AddressData> call = retrofitAPI.createPost(modal);

        //Response
        AddressData responseFromAPI = null;
        try
        {
            responseFromAPI = call.execute().body();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        //Достаем данные с ответа
        if (RefFlag == 0)
        {
            try
            {
                Optional<String> optional1 = Optional.of(responseFromAPI.getData().get(0).getRef());
            }
            catch(Exception e)
            {
                Log.d("RequestAdressSender", "Error: Catch null!");
                ExNull = true;
                return;
            }
            RefSender = responseFromAPI.getData().get(0).getRef();
            RefFlag = 1;
        }
        else
        {
            try
            {
                Optional<String> optional2 = Optional.of(responseFromAPI.getData().get(0).getRef());
            }
            catch(Exception e)
            {
                Log.d("RequestAdressRecipient", "Error: Catch null!");
                ExNull = true;
                return;
            }
            RefRecipient = responseFromAPI.getData().get(0).getRef();
            RefFlag = 0;
        }
    }

    //PriceRequest
    private void postDataPrice(String apiKey, String RefSender, String RefRecipient, int Weight, String ServiceType, String CargoType, int volumetricWidth, int volumetricLength,int volumetricHeight)
    {
        String modelName = "InternetDocument";
        String calledMethod = "getDocumentPrice";
        int SeatsAmount = 1;
        int Cost = 100;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.novaposhta.ua/v2.0/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI_2 retrofitAPI_2 = retrofit.create(RetrofitAPI_2.class);

        PriceData modal = new PriceData(modelName, calledMethod, apiKey, RefSender, RefRecipient, Weight, ServiceType, CargoType, Cost, SeatsAmount, volumetricWidth, volumetricLength, volumetricHeight);

        Call<PriceData> call = retrofitAPI_2.createPost(modal);

        //Response
        PriceData responseFromAPI = null;
        try
        {
            responseFromAPI = call.execute().body();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        //Достаем цену с ответа
        try
        {
            Optional<Integer> optional13 = Optional.of(responseFromAPI.getData().get(0).getCost());
        }
        catch(Exception e)
        {
            Log.d("RequestCost", "Error: Catch null!");
            ExNull = true;
            return;
        }
        cost = responseFromAPI.getData().get(0).getCost();
    }

}
