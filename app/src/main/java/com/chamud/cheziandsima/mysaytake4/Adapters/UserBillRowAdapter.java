package com.chamud.cheziandsima.mysaytake4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chamud.cheziandsima.mysaytake4.Model.Entities.Bill;
import com.chamud.cheziandsima.mysaytake4.Model.Entities.UserBill;
import com.chamud.cheziandsima.mysaytake4.R;

import java.util.ArrayList;

/**
 * Created by CheziAndSima on 07/09/2015.
 */
public class UserBillRowAdapter extends ArrayAdapter<UserBill> {


    public UserBillRowAdapter(Context context, ArrayList<UserBill> bills) {
        super(context, 0, bills);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        UserBill bill = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bill_row_template, parent, false);
        }
        // Lookup view for data population
        TextView billSubjectTextView = (TextView) convertView.findViewById(R.id.billSubjectTextView);
        TextView billDescriptionTextView = (TextView) convertView.findViewById(R.id.billDescriptionTextView);
        // Populate the data into the template view using the data object
        billSubjectTextView.setText(bill.getSubject());
        billDescriptionTextView.setText(bill.getDescription());
        // Return the completed view to render on screen
        return convertView;
    }
}
