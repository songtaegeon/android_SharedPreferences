package kr.co.neostack.www.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //적용해보기:https://abhiandroid.com/programming/shared-preference
    public SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //SharedPreferences init
        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

        String name = myPrefs.getString("nameKey","No name");
        int age = myPrefs.getInt("ageKey",0);

        TextView label = (TextView) findViewById(R.id.labelID);
        label.setText(name + " " + age);
        
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View v){

        //get reference to TextView
        TextView label = (TextView) findViewById(R.id.labelID);

        //get references to Name and Age EditTexts
        EditText nameEditText = (EditText) findViewById(R.id.nameID);
        EditText ageEditText = (EditText) findViewById(R.id.numberID);

        //set up SharedPreferences
        //prefID 는 Preference의 이름, MODE_PRIVATE 는 자신의 앱에서만 사용하도록 설정 하는 기본 값
        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);

        //pREFERENCE내에 있는 데이터 수정을 위해 Editor 객체 선언
        SharedPreferences.Editor editor = myPrefs.edit();

        //editor를 통해서 key-value 형식으로 값 저장
        editor.putString("nameKey", nameEditText.getText().toString());
        editor.putInt("ageKey", Integer.parseInt(ageEditText.getText().toString()));

        //적용 및 저장
        editor.apply();

        label.setText("Saved");
    }


    @Override
    public void onClick(View v) {

    }
}
