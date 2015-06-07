package co.navdeep.justjava;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    private static final int pricePerCup = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void decreaseQuantity(View view){
        int quantity = getCurrentQuantity();
        quantity = Math.max(--quantity, 0);
        updateQuantity(quantity);
        updateTotalPrice(quantity);
    }

    public void increaseQuantity(View view){
        int quantity = getCurrentQuantity();
        quantity++;
        updateQuantity(quantity);
        updateTotalPrice(quantity);
    }

    public void updateTotalPrice(int cups){
        TextView priceView = (TextView)findViewById(R.id.price);
        priceView.setText("Total :" + NumberFormat.getCurrencyInstance().format(cups * pricePerCup));
    }

    public void submitOrder(View view){
        addCoffeeCups(getCurrentQuantity());
    }

    public void updateQuantity(int number){
        TextView quantity = (TextView)findViewById(R.id.quantity);
        quantity.setText(Integer.toString(number));
    }

    private int getCurrentQuantity(){
        TextView quantityView = (TextView)findViewById(R.id.quantity);
        return Integer.parseInt(quantityView.getText().toString());
    }

    private void addCoffeeCups(int numberOfCups){
        ImageView img = getNewImageView();
        LinearLayout parent = (LinearLayout)findViewById(R.id.parentLayout);
        int imagesPerRow = parent.getWidth()/((BitmapDrawable)img.getBackground()).getBitmap().getWidth();
        LinearLayout verticalLayout = (LinearLayout)findViewById(R.id.cupHolder);
        verticalLayout.removeAllViews();
        for(int j = 0; j < numberOfCups;) {
            LinearLayout linearView = new LinearLayout(MainActivity.this);
            for (int i = 0; i < imagesPerRow && j < numberOfCups; i++, j++) {
                linearView.addView(getNewImageView());
            }
            verticalLayout.addView(linearView);
        }
    }

    private ImageView getNewImageView(){
        ImageView img = new ImageView(MainActivity.this);
        img.setBackgroundResource(R.drawable.ic_action_coffee);
        return img;
    }
}
