package com.example.webviewdebugger;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView.setWebContentsDebuggingEnabled(true);

        button = (Button) findViewById(R.id.buttonToUrl);
        editText = (EditText) findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
                if (Patterns.WEB_URL.matcher(url).matches()) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra(Constants.URL_KEY, url);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Invalid URL", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
