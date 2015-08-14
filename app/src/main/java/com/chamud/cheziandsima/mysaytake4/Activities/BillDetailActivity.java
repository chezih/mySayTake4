package com.chamud.cheziandsima.mysaytake4.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import  com.chamud.cheziandsima.mysaytake4.Adapters.BillCommentRowAdapter;
import  com.chamud.cheziandsima.mysaytake4.Adapters.BillRowAdapter;
import  com.chamud.cheziandsima.mysaytake4.Model.BL;
import  com.chamud.cheziandsima.mysaytake4.Model.Entities.Bill;
import  com.chamud.cheziandsima.mysaytake4.Model.Entities.BillComment;
import  com.chamud.cheziandsima.mysaytake4.Model.Entities.User;
import  com.chamud.cheziandsima.mysaytake4.Model.GlobalData;
import  com.chamud.cheziandsima.mysaytake4.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Choyzer on 13/07/2015.
 */
public class BillDetailActivity extends Activity {

    TextView billDetailSubjectTextView;
    TextView billDetailDescriptionTextView;
    Bill currentBill;
    ProgressDialog progress;
    ArrayList<BillComment> arrayOfBillComments;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        progress = new ProgressDialog(BillDetailActivity.this);
        progress.setTitle(getString(R.string.Login_dialog_head));
        progress.setMessage(getString(R.string.Login_dialog_message));
        progress.show();
        Intent intent = getIntent();
        currentBill = (Bill) intent.getSerializableExtra("currentBill");
        billDetailSubjectTextView = (TextView) findViewById(R.id.billDetailSubjectTextView);
        billDetailDescriptionTextView = (TextView) findViewById(R.id.billDetailDescriptionTextView);
       // ArrayList<User> users = ((GlobalData) this.getApplication()).getSavedUsers();
        billDetailSubjectTextView.setText(currentBill.getSubject());
        billDetailDescriptionTextView.setText(currentBill.getDescription());

        listView = (ListView) findViewById(R.id.billDetailCommentsListView);
        // Construct the data source
// Create the adapter to convert the array to views
        arrayOfBillComments = new ArrayList<BillComment>();
        new GetBillCommentsAsync().execute();
// Attach the adapter to a ListView

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


    class GetBillCommentsAsync extends AsyncTask<Void, Void, Void>

    {
        @Override
        protected void onPostExecute(Void aVoid) {

            BillCommentRowAdapter adapter = new BillCommentRowAdapter(BillDetailActivity.this, arrayOfBillComments);
            listView.setAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... urls) {

            try {
                arrayOfBillComments = BL.getInstance().getBillsComments(currentBill.getId());
                progress.dismiss();
//                Token = TokenGetter.executePost(new JSONObject("{\"password\": \"050788\", \"username\": \"chezi\"}"));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

    }


}
