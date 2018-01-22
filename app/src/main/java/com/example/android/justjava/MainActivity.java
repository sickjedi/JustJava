package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    boolean hasWhippedCream;
    boolean hasChocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        EditText nameField = (EditText) findViewById(R.id.name_field);

        hasWhippedCream = whippedCreamCheckBox.isChecked();
        hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice();

        String name = nameField.getText().toString();

        String orderSummary = createOrderSummary(price, hasWhippedCream, hasChocolate, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);
    }

    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int price = 5;

        if (hasWhippedCream)
            price = price + 1;
        if (hasChocolate)
            price = price + 2;

        return quantity * price;
    }

    /**
     * Create summary of the order
     *
     * @param orderSum price sum of order
     * @param hasWhippedCream order has whipped cream
     * @param hasChocolate order has chocolate
     * @param name name of the customer
     *
     * @return text summary
     */

    private String createOrderSummary(int orderSum, boolean hasWhippedCream, boolean hasChocolate, String name) {
        return "Name: " + name + "\n" +
                "Add whipped cream? " + hasWhippedCream + "\n" +
                "Add chocolate? " + hasChocolate + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total $" + orderSum + "\n" +
                "Thank you!";
    }

    public void increment(View view) {
        if (quantity >= 100)
            quantity = 100;
        else
            quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity > 0) {
            quantity--;
        } else {
            quantity = 0;
        }
        displayQuantity(quantity);
    }
}
