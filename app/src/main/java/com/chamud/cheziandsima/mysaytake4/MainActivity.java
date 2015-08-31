package com.chamud.cheziandsima.mysaytake4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.chamud.cheziandsima.mysaytake4.Activities.BillDetailActivity;
import com.chamud.cheziandsima.mysaytake4.Activities.LoginActivity;
import com.chamud.cheziandsima.mysaytake4.Activities.UserProfileActivity;
import com.chamud.cheziandsima.mysaytake4.Fragments.BillsFragment;
import com.chamud.cheziandsima.mysaytake4.Model.BL;
import com.chamud.cheziandsima.mysaytake4.Model.Entities.Bill;
import com.chamud.cheziandsima.mysaytake4.Model.Entities.User;
import com.chamud.cheziandsima.mysaytake4.Model.GlobalData;
import com.chamud.cheziandsima.mysaytake4.Utils.CredentialsStorage;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity implements BillsFragment.OnBillSelectedListener{


    TextView tv;
    String loggedInUserName;
    String loggedToken;
    User currentUser;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To retrieve values back
        loggedInUserName = CredentialsStorage.getFromPrefs(MainActivity.this, CredentialsStorage.PREFS_LOGIN_USERNAME_KEY, "");
        loggedToken = CredentialsStorage.getFromPrefs(MainActivity.this, CredentialsStorage.PREFS_LOGIN_TOKEN_KEY, "");



        if (loggedToken == "") {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            BL.getInstance().setToken(loggedToken);
            BillsFragment billsFragment = (BillsFragment) getFragmentManager().findFragmentById(R.id.billFragment);
            billsFragment.getAllBills();
            new GetAllUsersAsync().execute();
        }

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
        if (id == R.id.action_userProfile) {
//            try {
//                getAllUsers(this.findViewById(android.R.id.content));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
            intent.putExtra("currentUser", currentUser);
            startActivity(intent);
        }
        if (id == R.id.action_logOut) {
            loggedInUserName = "";
            loggedToken = "";
            CredentialsStorage.saveToPrefs(MainActivity.this, CredentialsStorage.PREFS_LOGIN_USERNAME_KEY, "");
            CredentialsStorage.saveToPrefs(MainActivity.this, CredentialsStorage.PREFS_LOGIN_TOKEN_KEY, "");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    ArrayList<User> users;

    @Override
    public void onBillSelected(Bill bill) {
        Intent intent = new Intent(MainActivity.this, BillDetailActivity.class);
        intent.putExtra("currentBill",bill);
        startActivity(intent);
    }

    class GetAllUsersAsync extends AsyncTask<Void, Void, Void>

    {
        @Override
        protected void onPostExecute(Void aVoid) {

//            for (int i = 0; i < users.size(); i++) {
//                result += "USER #" + i + "\n";
//                result += "Id: " + users.get(i).getId() + "\n";
//                result += "Name: " + users.get(i).getName() + "\n\n";
//            }
//
//            result += loggedToken;
//            tv.setText(result);
        }

        @Override
        protected Void doInBackground(Void... urls) {

            try {
                users = BL.getInstance().getAllUsers();
                ((GlobalData) MainActivity.this.getApplication()).setSavedUsers(users);
                for (User user : users) {

                    if (loggedInUserName.equals(user.getUserName())) {
                        currentUser = user;
                    }
                    // 1 - can call methods of element

                    // ...
                }
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
