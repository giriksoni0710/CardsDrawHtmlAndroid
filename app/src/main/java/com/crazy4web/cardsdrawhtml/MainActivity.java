package com.crazy4web.cardsdrawhtml;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String m_input;

        web = findViewById(R.id.web);

        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);

        web.loadUrl("file:///android_asset/UI.html");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                final EditText input = new EditText(getApplicationContext());

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_NUMBER);
                alert.setView(input);

                alert.setMessage("Enter the number of Cards to be drawn").setCancelable(false)
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                String abc = "javascript:display('"+input+"')";

                                web.evaluateJavascript(abc,null);


                                Integer number = Integer.parseInt(input.getText().toString());

                                while(number>0) {
                                    web.loadUrl("javascript:addNode()");
                                    number--;

                                }
//                                finish();

                            }
                        });

                AlertDialog alertmain = alert.create();
                alert.setTitle("Number");
                alert.show();

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            web.loadUrl("file:///android_asset/UI.html");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
