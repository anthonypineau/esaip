package com.example.projetandroid;

import android.app.Activity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetandroid.beans.MenuBean;
import com.example.projetandroid.beans.User;
import com.example.projetandroid.database.DatabaseAccess;
import com.example.projetandroid.database.MenuDatabase;
import com.example.projetandroid.database.UserDatabase;
import com.example.projetandroid.utils.Functions;
import com.example.projetandroid.utils.Manager;

import java.util.List;

public class ConnexionActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        Button buttonConnexion = (Button)findViewById(R.id.buttonConnexion);
        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextLogin = (EditText)findViewById(R.id.editTextLogin);
                String username = editTextLogin.getText().toString();
                EditText editTextMdp = (EditText)findViewById(R.id.editTextMdp);
                String password = editTextMdp.getText().toString();
                password = Functions.md5(password);

                UserDatabase userDatabase = new UserDatabase(ConnexionActivity.this);
                userDatabase.openDatabase();
                try{
                    User user = userDatabase.login(username, password);
                    if(user!=null){
                        new Manager(user.getId(), user.getUsername(), user.getName(), user);
                        setResult(Activity.RESULT_OK);
                    }else{
                        setResult(Activity.RESULT_CANCELED);
                    }
                }catch (Exception e){
                    System.out.println(e.toString());
                    setResult(Activity.RESULT_CANCELED);
                }
                userDatabase.closeDatabase();
                finish();
            }
        });
        Button btnRetour = (Button)findViewById(R.id.buttonRetour);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}