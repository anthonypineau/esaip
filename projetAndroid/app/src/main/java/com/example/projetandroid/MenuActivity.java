package com.example.projetandroid;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetandroid.beans.MenuBean;
import com.example.projetandroid.beans.Order;
import com.example.projetandroid.database.MenuDatabase;
import com.example.projetandroid.database.OrderDatabase;
import com.example.projetandroid.utils.Manager;

import java.util.List;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        int idMenuBean = getIntent().getIntExtra("menuBeanId", 1);

        MenuDatabase menuDatabase = new MenuDatabase(MenuActivity.this);
        menuDatabase.openDatabase();
        MenuBean menuBean = menuDatabase.getMenu(idMenuBean);
        menuDatabase.closeDatabase();

        TextView textViewDisplayId = (TextView) findViewById(R.id.textViewDisplayId);
        textViewDisplayId.setText(" "+menuBean.getId());
        TextView textViewDsiplayStarter = (TextView) findViewById(R.id.textViewDsiplayStarter);
        textViewDsiplayStarter.setText(" "+menuBean.getStarter());
        TextView textViewDisplayMain = (TextView)findViewById(R.id.textViewDisplayMain);
        textViewDisplayMain.setText(" "+menuBean.getMain());
        TextView textViewDisplayDessert = (TextView)findViewById(R.id.textViewDisplayDessert);
        textViewDisplayDessert.setText(" "+menuBean.getDessert());

        if(Manager.isConnected()){
            LinearLayout layout_menu = findViewById(R.id.layout_menu);

            LinearLayout linearLayoutOrder = new LinearLayout(this);
            linearLayoutOrder.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutOrder.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView textViewCreneau = new TextView(this);
            textViewCreneau.setText(R.string.textViewCreneau);
            textViewCreneau.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            EditText editTextCreneau = new EditText(this);
            editTextCreneau.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            editTextCreneau.setInputType(InputType.TYPE_CLASS_TEXT);

            linearLayoutOrder.addView(textViewCreneau);
            linearLayoutOrder.addView(editTextCreneau);

            layout_menu.addView(linearLayoutOrder, 5);

            Button buttonAddToCart = new Button(this);
            buttonAddToCart.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            buttonAddToCart.setText(R.string.buttonAddToCart);
            layout_menu.addView(buttonAddToCart, 6);

            buttonAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderDatabase orderDatabase = new OrderDatabase(MenuActivity.this);
                    orderDatabase.openDatabase();
                    orderDatabase.insertOrder(new Order(0, Manager.getUser(), editTextCreneau.getText().toString(), menuBean));
                    orderDatabase.closeDatabase();
                    //Intent intent = new Intent();
                    //intent.putExtra("indexPositionResult", Long.valueOf(Integer.toString(getIntent().getIntExtra("indexPosition", 1))));
                    //setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            });
            if(Manager.getUsername().equals("admin")){
                Button buttonDeleteMenu = new Button(this);
                buttonDeleteMenu.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                buttonDeleteMenu.setText(R.string.buttonDeleteMenu);
                layout_menu.addView(buttonDeleteMenu, 7);

                buttonDeleteMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MenuDatabase menuDatabase = new MenuDatabase(MenuActivity.this);
                        menuDatabase.openDatabase();
                        menuDatabase.removeMenu(idMenuBean);
                        menuDatabase.closeDatabase();
                        Intent intent = new Intent();
                        intent.putExtra("indexPositionResult", Long.valueOf(Integer.toString(getIntent().getIntExtra("indexPosition", 1))));
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                });
            }
        }


        Button buttonBackHome = (Button) findViewById(R.id.buttonBackHome);
        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}