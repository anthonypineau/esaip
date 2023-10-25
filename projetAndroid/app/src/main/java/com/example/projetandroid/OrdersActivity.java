package com.example.projetandroid;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetandroid.beans.MenuBean;
import com.example.projetandroid.beans.Order;
import com.example.projetandroid.database.MenuDatabase;
import com.example.projetandroid.database.OrderDatabase;
import com.example.projetandroid.utils.Manager;

import java.util.List;

public class OrdersActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        OrderDatabase orderDatabase = new OrderDatabase(OrdersActivity.this);
        orderDatabase.openDatabase();
        List<Order> orders = orderDatabase.getOrders(Manager.getId());
        orderDatabase.closeDatabase();

        ScrollView scrollViewOrders = findViewById(R.id.scrollViewOrders);

        final TableLayout table = findViewById(R.id.ordersTable);
        TableRow rowHeader;
        TextView tv1,tv2, tv3, tv4;

        rowHeader = new TableRow(OrdersActivity.this);
        tv1 = new TextView(OrdersActivity.this);
        tv1.setText("Id");
        tv1.setGravity(Gravity.CENTER);
        tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
        tv1.setPadding(0,0,0,20);

        tv2 = new TextView(OrdersActivity.this);
        tv2.setText("User");
        tv2.setGravity(Gravity.CENTER);
        tv2.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
        tv2.setPadding(0,0,0,20);

        tv3 = new TextView(OrdersActivity.this);
        tv3.setText("Créneau");
        tv3.setGravity(Gravity.CENTER);
        tv3.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
        tv3.setPadding(0,0,0,30);

        tv4 = new TextView(OrdersActivity.this);
        tv4.setText("Menu");
        tv4.setGravity(Gravity.CENTER);
        tv4.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
        tv4.setPadding(0,0,0,20);

        rowHeader.addView(tv1);
        rowHeader.addView(tv2);
        rowHeader.addView(tv3);
        rowHeader.addView(tv4);

        table.addView(rowHeader);

        for(Order order : orders){
            /* si plusieurs menus par commande
            LinearLayout linearLayoutOrder = new LinearLayout(this);
            linearLayoutOrder.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutOrder.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView textViewIdOrder = new TextView(this);
            textViewIdOrder.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textViewIdOrder.setText("Id commande : "+order.getId());

            linearLayoutOrder.addView(textViewIdOrder);
            scrollViewOrders.addView(linearLayoutOrder);
            */

            final TableRow row = new TableRow(OrdersActivity.this);

            tv1 = new TextView(OrdersActivity.this);
            tv1.setText(Integer.toString(order.getId()));
            tv1.setGravity(Gravity.CENTER);
            tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv1.setPadding(0,0,0,20);

            tv2 = new TextView(OrdersActivity.this);
            tv2.setText(order.getUser().getName());
            tv2.setGravity(Gravity.CENTER);
            tv2.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv2.setPadding(0,0,0,20);

            tv3 = new TextView(OrdersActivity.this);
            tv3.setText(order.getTimeSlot());
            tv3.setGravity(Gravity.CENTER);
            tv3.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv3.setPadding(0,0,0,30);

            tv4 = new TextView(OrdersActivity.this);
            tv4.setText(order.getMenu().getStarter()+"-"+order.getMenu().getMain()+"-"+order.getMenu().getDessert()+"-");
            tv4.setGravity(Gravity.CENTER);
            tv4.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );
            tv4.setPadding(0,0,0,20);

            row.addView(tv1);
            row.addView(tv2);
            row.addView(tv3);
            row.addView(tv4);

            row.setClickable(true);
            /*
            row.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                }
            });
             */

            row.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    orderDatabase.openDatabase();
                    orderDatabase.removeOrder(order.getId());
                    orderDatabase.closeDatabase();
                    table.removeViewAt(table.indexOfChild(row));
                    Toast.makeText(OrdersActivity.this, "Commande supprimée", Toast.LENGTH_LONG).show();
                    return true;
                }
            });

            table.addView(row);
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