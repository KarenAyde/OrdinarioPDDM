package com.example.ordinariopddm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.zxing.client.result.ISBNResultParser;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button btnescanear;
    Button btnBuscar;
    TextView txtResultados;
    TextView txtDatos;

    ImageView imgPortada;
    private LibroOrganizer libroOrganizer;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Accesos a los botones y cajas de texto
        btnescanear = findViewById(R.id.btnEscanear);
        txtResultados = findViewById(R.id.txtResultados);
        txtDatos = findViewById(R.id.txtDatos);
        btnBuscar = findViewById(R.id.btnBuscar);
        imgPortada = findViewById(R.id.imgPortadas);

        libroOrganizer = new LibroOrganizer();

        btnescanear.setOnClickListener(new View.OnClickListener() {
            //Método que permite abrir y escanear el código QR y el lector de código de barras
            @Override
            public void onClick(View v) {
                IntentIntegrator integrador = new IntentIntegrator(MainActivity.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Lector - CDB");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();
            }
        });

        //Método que permite buscar en la clase Libro y LibroOrganizer si se encuentra el libro o no
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Libro libroEncontrado = libroOrganizer.buscarLibroPorISBN(txtResultados.getText().toString());
                if (libroEncontrado != null) {
                    mostrarDatosLibro(libroEncontrado);

                } else {
                    mostrarLibroNoEncontrado();
                }
            }

            //Método que permite borrar la caja de texto de los dato y colocar el mensaje de libro no encontrado en rojo
            private void mostrarLibroNoEncontrado() {
                txtDatos.setTextColor(Color.RED);
                txtDatos.setText("Libro no encontrado");
                imgPortada.setImageDrawable(null);
            }

            //Método que permite mostrar datos en la caja de texto de datos y en la imagen permite agregar la imagen extraida del libro correspondiente
            private void mostrarDatosLibro(Libro libro) {
                txtDatos.setTextColor(Color.BLACK);
                txtDatos.setText("ISBN: " + libro.getIsbn() +
                        "\nTítulo: " + libro.getTitulo() +
                        "\nNo. de Páginas: " + libro.getNoDePaginas() +
                        "\nEditorial: " + libro.getEditorial() +
                        "\nAutores: " + libro.getAutores());
                mostrarPortada(libro.getPortada());
            }

            //Método que permite buscar la imagen del libro
            private void mostrarPortada(String portada) {
                try {
                    InputStream stream = getAssets().open(portada);
                    Drawable drawable = Drawable.createFromStream(stream, null);
                    imgPortada.setImageDrawable(drawable);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Método que permite validar si falla el lector de CDB y manda una señal en la pantalla, ademas de extraer los datos y colocarlos en la caja de texto de txtResultados
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);

        if(result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Lector cancelado", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                txtResultados.setText(result.getContents());
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}