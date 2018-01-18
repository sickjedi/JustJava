package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        int numberOfCoffees = 5;
        display(numberOfCoffees);
        displayPrice(numberOfCoffees * 5);
    }

    private void display(int number) {
        TextView quantitiyTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantitiyTextView.setText("" + number);
    }

    private void displayPrice (int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void increment(View view) {
        int quantity = 3;
        display(quantity);
    }

    public void decrement(View view) {
        int quantity = 1;
        display(quantity);
    }
}
