package com.anneheijink.mqttask;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    EditText etIp;
    EditText etTopic;
    EditText etMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etIp = (EditText)findViewById(R.id.etIp);
        etTopic = (EditText)findViewById(R.id.etTopic);
        etMessage = (EditText)findViewById(R.id.etMessage);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        // Prepare data intent
        Intent data = new Intent();
        Bundle bundle = new Bundle();

        bundle.putString("com.anneheijink.mqttask.extra.STRING_MESSAGE", etMessage.getText().toString());
        bundle.putString("com.anneheijink.mqttask.extra.STRING_IP", etIp.getText().toString());
        bundle.putString("com.anneheijink.mqttask.extra.STRING_TOPIC", etTopic.getText().toString());
        data.putExtra("com.twofortyfouram.locale.intent.extra.BUNDLE", bundle);
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        super.finish();
    }
}
