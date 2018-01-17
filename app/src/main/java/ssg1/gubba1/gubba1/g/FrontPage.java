package ssg1.gubba1.gubba1.g;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FrontPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(FrontPage.this, Login.class);
                FrontPage.this.startActivity(mainIntent);
                FrontPage.this.finish();
            }
        }, 2000);
    }
}
