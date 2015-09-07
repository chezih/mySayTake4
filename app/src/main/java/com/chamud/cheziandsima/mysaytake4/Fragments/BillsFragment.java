package com.chamud.cheziandsima.mysaytake4.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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


    OnBillSelectedListener mListener;

    // Container Activity must implement this interface
    public interface OnBillSelectedListener {
        public void onBillSelected(Bill bill);
    }

    ArrayList<Bill> arrayOfBills;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setDivider((Drawable) getResources().getDrawable(R.drawable.abc_ab_share_pack_mtrl_alpha));


//then set the height dynamically

        getListView().setDividerHeight(40);

        this.getAllBills();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnBillSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Bill currentBill = arrayOfBills.get(position);
        mListener.onBillSelected(currentBill);
    }

    public void getAllBills() {
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
