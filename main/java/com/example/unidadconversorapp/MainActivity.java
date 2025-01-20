package com.example.unidadconversorapp;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    // Variables para los elementos de la UI
    private EditText editTextName;
    private TextView textViewName, textViewNumber;
    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button buttonReset, buttonConvertLength, buttonConvertWeight, buttonConvertTemperature, buttonToggleMode;
    private String currentInput = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar los elementos de la UI
        editTextName = findViewById(R.id.editTextName);
        textViewName = findViewById(R.id.textViewName);
        textViewNumber = findViewById(R.id.textViewNumber);

        // Botones de número
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        // Botones de conversión
        buttonConvertLength = findViewById(R.id.buttonConvertLength);
        buttonConvertWeight = findViewById(R.id.buttonConvertWeight);
        buttonConvertTemperature = findViewById(R.id.buttonConvertTemperature);

        // Botón de reiniciar
        buttonReset = findViewById(R.id.buttonReset);

        // Botón para cambiar entre modo claro y oscuro
        buttonToggleMode = findViewById(R.id.buttonToggleMode);

        // Deshabilitar los botones de conversión al inicio
        disableConversionButtons();

        // Listener para el EditText
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String name = editable.toString();
                textViewName.setText("Hola, " + name);

                // Habilitar los botones si el nombre no está vacío
                if (name.trim().isEmpty()) {
                    disableConversionButtons();
                } else {
                    enableConversionButtons();
                }
            }
        });

        // Listeners para los botones numéricos
        button0.setOnClickListener(v -> updateCurrentInput("0"));
        button1.setOnClickListener(v -> updateCurrentInput("1"));
        button2.setOnClickListener(v -> updateCurrentInput("2"));
        button3.setOnClickListener(v -> updateCurrentInput("3"));
        button4.setOnClickListener(v -> updateCurrentInput("4"));
        button5.setOnClickListener(v -> updateCurrentInput("5"));
        button6.setOnClickListener(v -> updateCurrentInput("6"));
        button7.setOnClickListener(v -> updateCurrentInput("7"));
        button8.setOnClickListener(v -> updateCurrentInput("8"));
        button9.setOnClickListener(v -> updateCurrentInput("9"));

        // Listener para el botón de reset
        buttonReset.setOnClickListener(v -> {
            currentInput = "";
            textViewNumber.setText("");
        });

        // Listener para la conversión de Longitud (metros a pies)
        buttonConvertLength.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                double convertedValue = value * 3.28084;
                textViewNumber.setText("Longitud convertida: " + convertedValue + " pies");
            }
        });

        // Listener para la conversión de Peso (kilogramos a libras)
        buttonConvertWeight.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                double convertedValue = value * 2.20462;
                textViewNumber.setText("Peso convertido: " + convertedValue + " lbs");
            }
        });

        // Listener para la conversión de Temperatura (celsius a fahrenheit)
        buttonConvertTemperature.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double value = Double.parseDouble(currentInput);
                double convertedValue = (value * 9/5) + 32;
                textViewNumber.setText("Temperatura convertida: " + convertedValue + " °F");
            }
        });

        // Cambiar entre modo claro y oscuro
        buttonToggleMode.setOnClickListener(v -> {
            int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                // Modo claro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                // Modo oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate(); // Recrear la actividad para aplicar el cambio
        });
    }

    // Método para actualizar el número ingresado por el usuario
    private void updateCurrentInput(String digit) {
        currentInput += digit;
        textViewNumber.setText(currentInput);
    }

    // Método para deshabilitar los botones de conversión
    private void disableConversionButtons() {
        buttonConvertLength.setEnabled(false);
        buttonConvertWeight.setEnabled(false);
        buttonConvertTemperature.setEnabled(false);
    }

    // Método para habilitar los botones de conversión
    private void enableConversionButtons() {
        buttonConvertLength.setEnabled(true);
        buttonConvertWeight.setEnabled(true);
        buttonConvertTemperature.setEnabled(true);
    }
}



