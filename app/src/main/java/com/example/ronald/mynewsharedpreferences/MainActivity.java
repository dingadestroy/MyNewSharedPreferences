package com.example.ronald.mynewsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button save, reset;
    TextView textView;
    TextView changeColorText;
    SharedPreferences sharedpreferences;
    SeekBar seekBarRed, seekBarBlue, seekBarGreen;
    int red, blue, green;

    public void setColor(){
        changeColorText.setTextColor(Color.rgb(red,green,blue));
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        red = blue = green = 0;
        changeColorText = (TextView) findViewById(R.id.txtChangeColor);

        seekBarRed = (SeekBar) findViewById(R.id.skbRed);
        seekBarGreen = (SeekBar) findViewById(R.id.skbGreen);
        seekBarBlue  = (SeekBar) findViewById(R.id.skbBlue);



        sharedpreferences  = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        if (sharedpreferences.getInt("GREEN",0) != 0 ) {
            Toast.makeText(this,"Green progress: "+ sharedpreferences.getInt("GREEN",0), Toast.LENGTH_SHORT).show();
            seekBarGreen.setProgress(sharedpreferences.getInt("GREEN", 0));
            green = sharedpreferences.getInt("GREEN",0);
            setColor();
        }
        if (sharedpreferences.getInt("RED",0) != 0 ) {
            Toast.makeText(this,"RED progress: "+ sharedpreferences.getInt("GREEN",0), Toast.LENGTH_SHORT).show();
            seekBarRed.setProgress(sharedpreferences.getInt("RED", 0));
            red = sharedpreferences.getInt("RED",0);
            setColor();
        }
        if (sharedpreferences.getInt("BLUE",0) != 0 ) {
            Toast.makeText(this,"BLUE progress: "+ sharedpreferences.getInt("BLUE",0), Toast.LENGTH_SHORT).show();
            seekBarBlue.setProgress(sharedpreferences.getInt("BLUE", 0));
            blue = sharedpreferences.getInt("BLUE",0);
            setColor();
        }
       // changeColorText.setTextColor(Color.rgb(red, green, blue));


        seekBarGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("GREEN", progress);
                editor.commit();
                MainActivity.this.green = progress;
                setColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("BLUE", progress);
                editor.commit();
                MainActivity.this.blue = progress;
                setColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("RED", progress);
                editor.commit();
                MainActivity.this.red = progress;
                setColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        save = (Button) findViewById(R.id.btnSave);
        reset = (Button) findViewById(R.id.btnReset);
        reset.setVisibility(View.GONE);
        textView = (TextView) findViewById(R.id.txtEnterName);
        sharedpreferences  = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        /*reset.setVisibility(View.GONE);*/

        if (sharedpreferences.getString("USERNAME","").equals("") ) {
            save.setVisibility(View.VISIBLE);
        } else {

            textView.setText(sharedpreferences.getString("USERNAME",""));
            reset.setVisibility(View.VISIBLE);
            save.setVisibility(View.GONE);
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                reset.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("USERNAME", textView.getText().toString());
                editor.commit();
                save.setVisibility(View.GONE);
                reset.setVisibility(View.VISIBLE);
            }
        });
    }

}
