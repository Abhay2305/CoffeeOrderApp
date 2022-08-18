/**

 Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 This is the package name our example uses:
 *
 package com.example.android.justjava;
 */
package com.example.justjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**  This app displays an order form to order coffee.  */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SubmitOrder(View view) {
        // find user name
        EditText nameField= (EditText) findViewById(R.id.name_field);
        String name = nameField.getText() .toString();
        Log.v("MainActivity","Name:" + name);

        // if the user want whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "has whippedCream:" + hasWhippedCream);


        // if the user want chocolate topping
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();
        Log.v("MainActivity", "has Chocolate:" + hasChocolate);


            int price = calculatePrice(hasWhippedCream, hasChocolate);
            String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

//            Intent intent = new Intent(Intent.ACTION_SENDTO);
//            intent.setData(Uri.parse("mailto:"));  //email will show up here
//            intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for " +name);
//            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//            if (intent.resolveActivity(getPackageManager()) !=null)  {
//                startActivity(intent);
//        }
            displayMessage(priceMessage);

    }

    /**  calculate the price of order
     *
     * @param addWhippedCream
     * @param addChocolate
     * @return quantity
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate)
    {
        int basePrice = 50;
        if (addWhippedCream) {
            basePrice = basePrice + 20;
        }

        if (addChocolate) {
            basePrice = basePrice + 30;
        }
        return quantity * basePrice;
    }

    /* Create the summary of the order.
    price of the order
    return text summary
     */

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + name ;
        priceMessage += "\nAdd Whipped cream?" + addWhippedCream;
        priceMessage += "\nAdd Chocolate?" + addChocolate;
        priceMessage = priceMessage + "\nQuantity :" + quantity;
        priceMessage += "\nTotal: " + price;
        priceMessage += "\nThank you:)";
        return priceMessage;
    }
    /**  This method is called when the increment button is clicked.*/
    public void increment(View view) {
        if (quantity==10) {
            // to show a error message.
            Toast.makeText(this, "you can't have more than 10 coffee",Toast.LENGTH_LONG).show();
            return;
        }
        quantity = quantity +1;
        display(quantity);
    }

    /**  This method is called when the decrement button is clicked.*/
    public void decrement(View view) {
        if (quantity==1) {
            // to show a error message.
            Toast.makeText(this, "You can't have less than one coffee", Toast.LENGTH_SHORT).show();
            return;
        }
            quantity =quantity- 1;
        display(quantity);
    }

    /**  This method displays the given quantity value on the screen.  */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
////    /**  * This method displays the given price on the screen.  */
            private void displayMessage(String message) {
            TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_price_text_view);
            orderSummaryTextView.setText(message);

    }
}


