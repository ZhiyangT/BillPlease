package sg.edu.rp.c346.billplease;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView price;
    TextView pax;
    TextView discount;
    CheckBox svc;
    CheckBox gst;
    Button calc;
    Button reset;
    TextView nPrice;
    TextView perPax;
    TextView tvMessage;

    double n = 0.0;
    double d = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        price = findViewById(R.id.cost);
        pax = findViewById(R.id.pax);
        discount = findViewById(R.id.discount);
        svc = findViewById(R.id.svc);
        gst = findViewById(R.id.gst);
        calc = findViewById(R.id.calc);
        reset = findViewById(R.id.reset);
        nPrice = findViewById(R.id.nPrice);
        perPax = findViewById(R.id.pperson);
        tvMessage = findViewById(R.id.message);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (price.length() > 0 && pax.length() > 0) {


                    if (gst.isChecked() && svc.isChecked()) {

                        double dprice = Double.parseDouble(price.getText().toString());
                        double formula = dprice * 1.07 * 1.1;
                        n = formula;

                    } else if (gst.isChecked()) {
                        double dprice = Double.parseDouble(price.getText().toString());
                        double formula = dprice * 1.07;
                        n = formula;
                    } else if (svc.isChecked()) {
                        double dprice = Double.parseDouble(price.getText().toString());
                        double formula = dprice * 1.1;
                        n = formula;
                    } else {

                        n = Double.parseDouble(price.getText().toString());
                    }
                    double nPax = Double.parseDouble(pax.getText().toString());


                    if (discount.length() == 0) {
                        n = n;
                    } else {
                        Double nDiscount = Double.parseDouble(discount.getText().toString());
                        double dAmt = n * nDiscount / 100;
                        n = n - dAmt;

                    }

                    if (nPax > 1) {
                        d = n / nPax;
                    } else {
                        d = n;
                    }

                    nPrice.setText(String.format("%s", n));
                    perPax.setText(String.format("%s", d));
                }
                else{
                    tvMessage.setText("Please fill up all fields.");
                }
            }



        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price.setText("");
                pax.setText("");
                discount.setText("");
                svc.setChecked(false);
                gst.setChecked(false);
                nPrice.setText("");
                perPax.setText("");
                tvMessage.setText("");

            }

        });


    }

}