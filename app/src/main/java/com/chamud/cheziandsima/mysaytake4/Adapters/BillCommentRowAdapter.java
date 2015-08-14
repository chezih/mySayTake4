package com.chamud.cheziandsima.mysaytake4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chamud.cheziandsima.mysaytake4.Activities.BillDetailActivity;
import com.chamud.cheziandsima.mysaytake4.MainActivity;
import com.chamud.cheziandsima.mysaytake4.Model.Entities.Bill;
import com.chamud.cheziandsima.mysaytake4.Model.Entities.BillComment;
import com.chamud.cheziandsima.mysaytake4.Model.Entities.User;
import com.chamud.cheziandsima.mysaytake4.Model.GlobalData;
import com.chamud.cheziandsima.mysaytake4.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Choyzer on 13/07/2015.
 */
public class BillCommentRowAdapter extends ArrayAdapter<BillComment> {
    public BillCommentRowAdapter(Context context, ArrayList<BillComment> billComments) {
        super(context, 0, billComments);
        this.context = context;
    }

    Context context;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BillComment billComment = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bill_comment_row_template, parent, false);
        }

        ArrayList<User> users = ((GlobalData) (((BillDetailActivity) context).getApplication())).getSavedUsers();
        User userThatWroteThisComment = null;
        for (User user : users) {
            if (user.getId() == billComment.getUser_id()) {
                userThatWroteThisComment = user;
            }
        }
        // Lookup view for data population
        TextView commentContentTextView = (TextView) convertView.findViewById(R.id.commentContentTextView);
        TextView commentUserNameTextView = (TextView) convertView.findViewById(R.id.commentUserNameTextView);
        TextView commentDateTextView = (TextView) convertView.findViewById(R.id.commentDateTextView);
        if (userThatWroteThisComment != null) {
            commentUserNameTextView.setText(userThatWroteThisComment.getName());
        } else {
            commentUserNameTextView.setText(String.valueOf(billComment.getUser_id()));
        }

        // Populate the data into the template view using the data object
        commentContentTextView.setText(billComment.getContent());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        commentDateTextView.setText(sdf.format(billComment.getDate()));
        // Return the completed view to render on screen
        return convertView;
    }


}
