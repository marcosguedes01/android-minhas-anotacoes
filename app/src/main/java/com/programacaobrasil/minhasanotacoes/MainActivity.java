package com.programacaobrasil.minhasanotacoes;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {
    private static final String NOME_ARQUIVO = "anotacao.txt";
    private EditText txtConteudo;
    private ImageView btnSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        initializeEventsComponents();
    }

    private void initializeComponents()
    {
        txtConteudo = (EditText)findViewById(R.id.txtConteudo);
        btnSalvar = (ImageView)findViewById(R.id.btnSalvar);
    }

    private void initializeEventsComponents()
    {
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoDigitado = txtConteudo.getText().toString();
                gravarNoArquivo(textoDigitado);
                Toast.makeText(getApplicationContext(), "Anotação salva com sucesso.", Toast.LENGTH_SHORT).show();
            }
        });

        String conteudo = lerArquivo();
        if (!conteudo.isEmpty())
        {
            txtConteudo.setText(conteudo);
        }
    }

    private void gravarNoArquivo(String textoDigitado)
    {
        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(NOME_ARQUIVO, Context.MODE_PRIVATE));
            outputStreamWriter.write(textoDigitado);
            outputStreamWriter.close();
            Toast.makeText(getApplicationContext(), "Anotação salva com sucesso!", Toast.LENGTH_SHORT);
        }
        catch (IOException e)
        {
            Log.v("MainActivity", e.toString());
        }
        catch (Exception ex)
        {
            Log.v("MainActivity", ex.toString());
        }
    }

    private String lerArquivo(){
        String resultado = "";

        try
        {
            InputStream arquivo = openFileInput(NOME_ARQUIVO);
            if (arquivo!=null)
            {
                InputStreamReader inputStreamReader = new InputStreamReader(arquivo);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String linhaArquivo = "";
                while ((linhaArquivo = bufferedReader.readLine()) != null)
                {
                    resultado += linhaArquivo + "\n";
                }
            }
            arquivo.close();
        }
        catch (IOException e)
        {
            Log.v("MainActivity", e.toString());
        }
        catch (Exception ex)
        {
            Log.v("MainActivity", ex.toString());
        }

        return  resultado;
    }
}
