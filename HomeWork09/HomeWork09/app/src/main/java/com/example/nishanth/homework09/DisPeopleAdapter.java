package com.example.nishanth.homework09;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nishanth on 3/9/2017.
 */

public class DisPeopleAdapter extends RecyclerView.Adapter<DisPeopleAdapter.DisPeopleViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<User_Table> usersTableList;
    private ButtonClickCallBack buttonClickCallBack;
    private User_Table current_user;

    public User_Table getCurrent_user() {
        return current_user;
    }

    public void setCurrent_user(User_Table current_user) {
        this.current_user = current_user;
    }

    public void setButtonClickCallBack(final ButtonClickCallBack buttonClickCallBack){
        this.buttonClickCallBack = buttonClickCallBack;
    }

    public DisPeopleAdapter(Context context,List<User_Table> usersTableList) {
        this.context = context;
        layoutInflater =LayoutInflater.from(context);
        this.usersTableList = usersTableList;

    }

    public List<User_Table> getUsersTableList() {
        return usersTableList;
    }

    public void setUsersTableList(List<User_Table> usersTableList) {
        this.usersTableList = usersTableList;
    }

    @Override
    public DisPeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DisPeopleViewHolder disPeopleViewHolder;

        View view =layoutInflater.inflate(R.layout.list_discover_people,parent,false);
        disPeopleViewHolder= new DisPeopleViewHolder(view);
        return disPeopleViewHolder;
    }

    @Override
    public void onBindViewHolder( DisPeopleViewHolder holder, int position) {
        Picasso.with(context).load(usersTableList.get(position).getProfile_pic()).into(holder.im);
        holder.title.setText(usersTableList.get(position).getLast_name()+", "+usersTableList.get(position).getFirst_name() );

        holder.req.setTag(Profile.SENDREQUEST);


        for(String s: current_user.getSentRequests().keySet()){

            if(usersTableList.get(position).getUser_id().equals(s)) {
                holder.req.setTag("Friend Request Sent");
                holder.req.setText("Friend Request Sent");
                holder.req.setEnabled(false);
            }

        }

        for(String s: current_user.getFriends().keySet()){

            if(usersTableList.get(position).getUser_id().equals(s)) {
                holder.req.setTag("You are now Friends");
                holder.req.setText("You are now Friends");
                holder.req.setEnabled(false);
            }

        }


        for(String s: current_user.getAcceptRequests().keySet()){

            if(usersTableList.get(position).getUser_id().equals(s)) {
                holder.req.setTag(Profile.AcceptREQUEST);
                holder.req.setText(Profile.AcceptREQUEST);
            }

        }

    }

    @Override
    public int getItemCount() {
        return usersTableList.size();
    }

    class  DisPeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView im;
        TextView title;;
        Button req;
        public  DisPeopleViewHolder(View itemView) {
            super(itemView);
            im = (ImageView) itemView.findViewById(R.id.imageViewFinPPL);
            title = (TextView) itemView.findViewById(R.id.textViewFnPPL);
            req = (Button) itemView.findViewById(R.id.buttonReqPPL);
            req.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
                buttonClickCallBack.onButtonClickSetData(getAdapterPosition(),v.getTag());

       }
    }


}
