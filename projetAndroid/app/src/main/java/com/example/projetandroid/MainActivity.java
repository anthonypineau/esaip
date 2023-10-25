package com.example.projetandroid;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;

import com.example.projetandroid.beans.MenuBean;
import com.example.projetandroid.beans.User;
import com.example.projetandroid.database.MenuDatabase;
import com.example.projetandroid.database.UserDatabase;
import com.example.projetandroid.utils.Manager;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        super.setActionBar(toolbar);

        /*
        UserDatabase ud = new UserDatabase(MainActivity.this);
        ud.openDatabase();
        ud.insertUser(new User(0, "admin", "admin", "admin"));
        ud.closeDatabase();

        MenusFragment fragment = new MenusFragment();
        FragmentManager monManager = getFragmentManager();
        FragmentTransaction transaction = monManager.beginTransaction();
        transaction.add(R.id.mainDynamicFragment, fragment);
        transaction.commit();
        //menuDatabase.insertMenu(new MenuBean(1, "Carottes rapées", "Poulet basque", "Mousse au chocolat"));
        //menuDatabase.insertMenu(new MenuBean(1, "Saucisson", "Boeuf bourguignon", "Tiramisu"));
        //menuDatabase.insertMenu(new MenuBean(1, "Rillettes", "Pâtes bolognaises", "Yaourt"));
         */
        MenuDatabase menuDatabase = new MenuDatabase(MainActivity.this);
        menuDatabase.openDatabase();
        List<MenuBean> menuBeans = menuDatabase.getMenus();
        menuDatabase.closeDatabase();

        final TableLayout table = findViewById(R.id.menusTable);
        TableRow rowHeader;
        TextView tv1,tv2, tv3, tv4;

        rowHeader = new TableRow(MainActivity.this);
        tv1 = new TextView(MainActivity.this);
        tv1.setText("Id");
        tv1.setGravity(Gravity.CENTER);
        tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
        tv1.setPadding(0,0,0,20);

        tv2 = new TextView(MainActivity.this);
        tv2.setText("Entrée");
        tv2.setGravity(Gravity.CENTER);
        tv2.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
        tv2.setPadding(0,0,0,20);

        tv3 = new TextView(MainActivity.this);
        tv3.setText("Plat");
        tv3.setGravity(Gravity.CENTER);
        tv3.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
        tv3.setPadding(0,0,0,30);

        tv4 = new TextView(MainActivity.this);
        tv4.setText("Dessert");
        tv4.setGravity(Gravity.CENTER);
        tv4.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
        tv4.setPadding(0,0,0,20);

        rowHeader.addView(tv1);
        rowHeader.addView(tv2);
        rowHeader.addView(tv3);
        rowHeader.addView(tv4);

        table.addView(rowHeader);

        for(MenuBean menuBean : menuBeans) {
            final TableRow row = new TableRow(MainActivity.this);

            tv1 = new TextView(MainActivity.this);
            tv1.setText(Integer.toString(menuBean.getId()));
            tv1.setGravity(Gravity.CENTER);
            tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv1.setPadding(0,0,0,20);

            tv2 = new TextView(MainActivity.this);
            tv2.setText(menuBean.getStarter());
            tv2.setGravity(Gravity.CENTER);
            tv2.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv2.setPadding(0,0,0,20);

            tv3 = new TextView(MainActivity.this);
            tv3.setText(menuBean.getMain());
            tv3.setGravity(Gravity.CENTER);
            tv3.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv3.setPadding(0,0,0,30);

            tv4 = new TextView(MainActivity.this);
            tv4.setText(menuBean.getDessert());
            tv4.setGravity(Gravity.CENTER);
            tv4.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv4.setPadding(0,0,0,20);

            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);
            row.addView(tv4);

            row.setClickable(true);
            row.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    intent.putExtra("menuBeanId", menuBean.getId());
                    intent.putExtra("indexPosition", table.indexOfChild(row));
                    startActivityForResult(intent, 2);
                }
            });
            table.addView(row);
        }

        Button buttonAddMenu = findViewById(R.id.buttonAddMenu);
        buttonAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMenuActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.connexionItem){
            if(item.getTitle().equals("Connexion")){
                Intent intent = new Intent(MainActivity.this, ConnexionActivity.class);
                startActivityForResult(intent, 0);
            }else{
                Manager.deconnect();
                item.setTitle("Connexion");
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Manager.isConnected()){
            try{
                menu.getItem(1);
            }catch (Exception e){
                menu.add("Voir liste commandes passées").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(MainActivity.this, OrdersActivity.class);
                        startActivity(intent);
                        return false;
                    }
                });
                /* si le temps
                menu.add("Voir panier").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(MainActivity.this, CartActivity.class);
                        startActivity(intent);
                        return false;
                    }
                });
                 */
                System.out.println(e.toString());
            }
        }else{
            try {
                menu.removeItem(menu.getItem(1).getItemId());
                //menu.removeItem(menu.getItem(2).getItemId());
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            if(resultCode==Activity.RESULT_OK){
                Toolbar toolbar = findViewById(R.id.toolbar);
                toolbar.getMenu().getItem(0).setTitle("Déconnexion");
            }else{
                Toast.makeText(MainActivity.this, "Nom d'utilisateur ou mot de passe incorrect", Toast.LENGTH_LONG);
            }
        }else if(requestCode==1){
            if(resultCode==Activity.RESULT_OK){
                MenuDatabase menuDatabase = new MenuDatabase(MainActivity.this);
                menuDatabase.openDatabase();
                MenuBean menuBean = menuDatabase.getMenu((int)(data.getLongExtra("idAddedMenu", 1)));
                menuDatabase.closeDatabase();
                final TableLayout table = findViewById(R.id.menusTable);
                TableRow row;
                TextView tv1,tv2, tv3, tv4;

                row = new TableRow(MainActivity.this);
                tv1 = new TextView(MainActivity.this);
                tv1.setText(Integer.toString(menuBean.getId()));
                tv1.setGravity(Gravity.CENTER);
                tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
                tv1.setPadding(0,0,0,20);

                tv2 = new TextView(MainActivity.this);
                tv2.setText(menuBean.getStarter());
                tv2.setGravity(Gravity.CENTER);
                tv2.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
                tv2.setPadding(0,0,0,20);

                tv3 = new TextView(MainActivity.this);
                tv3.setText(menuBean.getMain());
                tv3.setGravity(Gravity.CENTER);
                tv3.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
                tv3.setPadding(0,0,0,30);

                tv4 = new TextView(MainActivity.this);
                tv4.setText(menuBean.getDessert());
                tv4.setGravity(Gravity.CENTER);
                tv4.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
                tv4.setPadding(0,0,0,20);

                row.addView(tv1);
                row.addView(tv2);
                row.addView(tv3);
                row.addView(tv4);

                row.setClickable(true);
                row.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("menuBeanId", menuBean.getId());
                        intent.putExtra("indexPosition", table.indexOfChild(row));
                        startActivityForResult(intent, 2);
                    }
                });

                table.addView(row);
            }else if(resultCode==Activity.RESULT_CANCELED){
                try{
                    Toast.makeText(MainActivity.this, data.getStringExtra("messageNotAdmin"), Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }
        }else if(requestCode==2){
            if(resultCode==Activity.RESULT_OK){
                final TableLayout table = findViewById(R.id.menusTable);
                table.removeViewAt((int)(data.getLongExtra("indexPositionResult", 1)));
            }
        }
    }
}