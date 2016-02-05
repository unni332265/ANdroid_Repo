package com.example.unni332265.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.common.api.GoogleApiClient;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    int additionalCharges=0;
    int noOfCoffees = 0;
    int priceOneCoffee = 50;
    boolean hasWhippedCream;
    boolean hasSprinkles;
    boolean hasMarshmallows;

    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(
                R.id.whipped_cream_checkBox);
        hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox sprinklesCheckBox = (CheckBox) findViewById(
                R.id.sprinkles_checkBox);
        hasSprinkles = sprinklesCheckBox.isChecked();
        CheckBox marshmallowsCheckBox = (CheckBox) findViewById(
                R.id.marshmallows_checkBox);
        hasMarshmallows = marshmallowsCheckBox.isChecked();

        display(noOfCoffees);
        displayPriceMessage(hasWhippedCream, hasSprinkles, hasMarshmallows);
        displayPrice(calculatePrice(noOfCoffees,hasWhippedCream,hasSprinkles,hasMarshmallows));

    }

    public void incrementOrder(View view) {
            noOfCoffees++;
            displayPrice();
        display(noOfCoffees);
    }

    public void decrementOrder(View view) {
        if (noOfCoffees > 0) {
            noOfCoffees--;
            displayPrice();
        }
        display(noOfCoffees);
    }

    private float calculatePrice(int noOfCoffees,boolean hasWhippedCream,boolean hasSprinkles,boolean hasMarshmallows) {
        if(hasWhippedCream)
            additionalCharges+=5*noOfCoffees;
        if(hasSprinkles)
            additionalCharges+=10*noOfCoffees;
        if(hasMarshmallows)
            additionalCharges+=15*noOfCoffees;
        float price = noOfCoffees*priceOneCoffee + additionalCharges;
        additionalCharges=0;
        return price;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(float number) {
        TextView priceTextView = (TextView) findViewById(
                R.id.price_text_view);
        TextView orderTextView = (TextView) findViewById(
                R.id.textView3);
        priceTextView.setText("The Total Price is " + NumberFormat.getCurrencyInstance().format(number) + "\n" + displayPriceMessage(hasWhippedCream, hasSprinkles, hasMarshmallows));
        orderTextView.setText("Order Summary");
    }

    private String displayPriceMessage(boolean a, boolean b, boolean c) {
        String newMessage;
        EditText nameText = (EditText) findViewById(R.id.text_field);
        String name = nameText.getText().toString();
        newMessage = "Heyy "+ name + "\n" + "Number of coffees :" + noOfCoffees + "\n" + "Has Whipped Cream = " + a + "\n" + "Has Sprinkles = " + b + "\n" +
                "Has Marshmallows = " + c + "\n" + "Total item Count is " + noOfCoffees + "\n" + "Visit Again" + "\n";
        return newMessage;
    }

    private void displayPrice() {
        TextView priceTextView = (TextView) findViewById(
                R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(0));
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
}
