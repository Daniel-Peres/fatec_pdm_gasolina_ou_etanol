package br.com.fatec_pdm_gasolina_ou_etanol;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private TextView gasolinaTextView;
    private TextView etanolTextView;
    public TextView resultadoTextView;

    private double gasolinaPrice = 0.0;
    private double etanolPrice = 0.0;
    public double percent = 0.70;

    protected SeekBar etanolSeekBar;
    protected SeekBar gasolinaSeekBar;

    private int currentStep = 100;

    TextInputLayout resultadoTextInputLayout;
    ImageView combustivelImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gasolinaTextView = findViewById(R.id.gasolinaTextView);
        gasolinaTextView.setText(R.string.preco);
        etanolTextView = findViewById(R.id.etanolTextView);
        etanolTextView.setText(R.string.preco);

        etanolSeekBar = findViewById(R.id.etanolSeekBar);
        gasolinaSeekBar = findViewById(R.id.gasolinaSeekBar);

        resultadoTextInputLayout = findViewById(R.id.resultadoTextInputLayout);
        resultadoTextView = findViewById(R.id.resultadoTextView);
        resultadoTextView.setText(R.string.app_name);

        combustivelImageView = findViewById(R.id.combustivelImageView);
        combustivelImageView.setImageDrawable(getDrawable(R.drawable.etanol_gasolina));

        gasolinaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gasolinaPrice = progress;
                gasolinaTextView.setText(currencyFormat.format(gasolinaPrice/currentStep));
                checarCombustivel();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        etanolSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etanolPrice = progress;
                etanolTextView.setText(currencyFormat.format(etanolPrice/currentStep));
                checarCombustivel();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void checarCombustivel(){
        if (etanolPrice / gasolinaPrice >= percent){
            resultadoTextView.setText(R.string.gasolina);
            resultadoTextView.setTextColor(getColor(R.color.resultadoGasolina));
            combustivelImageView.setImageDrawable(getDrawable(R.drawable.gasolina));

        }else{
            resultadoTextView.setText(R.string.etanol);
            resultadoTextView.setTextColor(getColor(R.color.resultadoEtanol));
            combustivelImageView.setImageDrawable(getDrawable(R.drawable.etanol));

        }
    }

}
