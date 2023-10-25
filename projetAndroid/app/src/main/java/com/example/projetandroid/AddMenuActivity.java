package com.example.projetandroid;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetandroid.beans.MenuBean;
import com.example.projetandroid.database.MenuDatabase;
import com.example.projetandroid.utils.Manager;

public class AddMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        Button buttonAddMenuAddMenu = (Button) findViewById(R.id.buttonAddMenuAddMenu);
        buttonAddMenuAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextStarter = (EditText)findViewById(R.id.editTextStarter);
                String starter = editTextStarter.getText().toString();
                EditText editTextMain = (EditText)findViewById(R.id.editTextMain);
                String main = editTextMain.getText().toString();
                EditText editTextDessert = (EditText)findViewById(R.id.editTextDessert);
                String dessert = editTextDessert.getText().toString();

                MenuBean menu = new MenuBean(0, starter, main, dessert);

                MenuDatabase menuDatabase = new MenuDatabase(AddMenuActivity.this);
                menuDatabase.openDatabase();
                if(Manager.isConnected()){
                    if(Manager.getUsername().equals("admin")) {
                        if (!starter.trim().equals("") && !main.trim().equals("") && !dessert.trim().equals("")) {
                            try {
                                long idAddedMenu = menuDatabase.insertMenu(menu);
                                Intent intent = new Intent();
                                intent.putExtra("idAddedMenu", idAddedMenu);
                                setResult(Activity.RESULT_OK, intent);
                            } catch (Exception e) {
                                System.out.println(e.toString());
                                setResult(Activity.RESULT_CANCELED);
                            }
                        } else {
                            setResult(Activity.RESULT_CANCELED);
                        }
                        menuDatabase.closeDatabase();
                    }else{
                        Intent intent = new Intent();
                        intent.putExtra("messageNotAdmin", "Vous n'avez pas les droits pour ajouter un menu");
                        setResult(Activity.RESULT_CANCELED, intent);
                    }
                }else{
                    Intent intent = new Intent();
                    intent.putExtra("messageNotAdmin", "Vous n'avez pas les droits pour ajouter un menu");
                    setResult(Activity.RESULT_CANCELED, intent);
                }
                finish();
            }
        });


        Button buttonBackHome = (Button) findViewById(R.id.buttonBackHome);
        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}