package com.chamud.cheziandsima.mysaytake4.Fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chamud.cheziandsima.mysaytake4.Adapters.BillRowAdapter;
import com.chamud.cheziandsima.mysaytake4.Model.BL;
import com.chamud.cheziandsima.mysaytake4.Model.Entities.Bill;
import com.chamud.cheziandsima.mysaytake4.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by CheziAndSima on 10/08/2015.
 */
public class BillsFragment extends ListFragment {

    ArrayList<Bill> arrayOfBills;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setDivider((Drawable) getResources().getDrawable(R.drawable.abc_ab_share_pack_mtrl_alpha));


//then set the height dynamically

        getListView().setDividerHeight(40);

        new GetAllBillsAsync().execute();
    }


    class GetAllBillsAsync extends AsyncTask<Void, Void, Void>

    {
        @Override
        protected void onPostExecute(Void aVoid) {

            BillRowAdapter adapter = new BillRowAdapter(getActivity().getApplicationContext(), arrayOfBills);
            setListAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... urls) {

            try {
                arrayOfBills = BL.getInstance().getAllBills();
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
