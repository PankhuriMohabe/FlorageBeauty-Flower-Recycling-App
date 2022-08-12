package com.example.smartlearncdsafcataptitude;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<ProfileViewHolder> {

    private Context mContext;
    private List<ProfileData> myProfileList;
    private int lastPosition=-1;

    public myAdapter(Context mContext, List<ProfileData> myProfileList) {
        this.mContext = mContext;
        this.myProfileList = myProfileList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row_profile,viewGroup,false);

        return  new ProfileViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileViewHolder profileViewHolder, int i ){
        Glide.with(mContext)
                .load(myProfileList.get(i).getUserImage())
                .into(profileViewHolder.imageView);
        profileViewHolder.mName.setText(myProfileList.get(i).getFullName());
        profileViewHolder.mQualification.setText(myProfileList.get(i).getOccupationoffamily());
        profileViewHolder.mDateOfBirth.setText(myProfileList.get(i).getBirthDate());
        profileViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("Image",myProfileList.get(profileViewHolder.getAdapterPosition()).getUserImage());
                intent.putExtra("fullname",myProfileList.get(profileViewHolder.getAdapterPosition()).getFullName());
                intent.putExtra("Username",myProfileList.get(profileViewHolder.getAdapterPosition()).getUsername());
                intent.putExtra("mobile",myProfileList.get(profileViewHolder.getAdapterPosition()).getMobile());
                intent.putExtra("phone",myProfileList.get(profileViewHolder.getAdapterPosition()).getPhone());
                intent.putExtra("address",myProfileList.get(profileViewHolder.getAdapterPosition()).getAddress());
                intent.putExtra("gender",myProfileList.get(profileViewHolder.getAdapterPosition()).getGender());
                intent.putExtra("height",myProfileList.get(profileViewHolder.getAdapterPosition()).getHeight());
                intent.putExtra("complexion",myProfileList.get(profileViewHolder.getAdapterPosition()).getComplexion());
                intent.putExtra("birthdate",myProfileList.get(profileViewHolder.getAdapterPosition()).getBirthDate());
                intent.putExtra("birthtime",myProfileList.get(profileViewHolder.getAdapterPosition()).getBirthTime());
                intent.putExtra("birthplace",myProfileList.get(profileViewHolder.getAdapterPosition()).getBirthPlace());
                intent.putExtra("bloodgroup",myProfileList.get(profileViewHolder.getAdapterPosition()).getBloodGroup());
                intent.putExtra("foodhabits",myProfileList.get(profileViewHolder.getAdapterPosition()).getFoodHabits());
                intent.putExtra("spacacles",myProfileList.get(profileViewHolder.getAdapterPosition()).getSpectacles());
                intent.putExtra("disability",myProfileList.get(profileViewHolder.getAdapterPosition()).getDisability());
                intent.putExtra("divorcee",myProfileList.get(profileViewHolder.getAdapterPosition()).getDivorcee());
                intent.putExtra("hobbies",myProfileList.get(profileViewHolder.getAdapterPosition()).getHobbies());
                intent.putExtra("qualification",myProfileList.get(profileViewHolder.getAdapterPosition()).getQualification());
                intent.putExtra("occupation",myProfileList.get(profileViewHolder.getAdapterPosition()).getOccupation());
                intent.putExtra("currentcity",myProfileList.get(profileViewHolder.getAdapterPosition()).getCurrentCity());
                intent.putExtra("income",myProfileList.get(profileViewHolder.getAdapterPosition()).getIncome());
                intent.putExtra("ownhome",myProfileList.get(profileViewHolder.getAdapterPosition()).getOwnHome());
                intent.putExtra("caste",myProfileList.get(profileViewHolder.getAdapterPosition()).getCaste());
                intent.putExtra("gotra",myProfileList.get(profileViewHolder.getAdapterPosition()).getGotra());
                intent.putExtra("shakha",myProfileList.get(profileViewHolder.getAdapterPosition()).getShakha());
                intent.putExtra("zodiacsign",myProfileList.get(profileViewHolder.getAdapterPosition()).getZodiacsign());
                intent.putExtra("nakshatra",myProfileList.get(profileViewHolder.getAdapterPosition()).getNakshatra());
                intent.putExtra("charan",myProfileList.get(profileViewHolder.getAdapterPosition()).getCharan());
                intent.putExtra("gan",myProfileList.get(profileViewHolder.getAdapterPosition()).getGan());
                intent.putExtra("naadi",myProfileList.get(profileViewHolder.getAdapterPosition()).getNaadi());
                intent.putExtra("mangal",myProfileList.get(profileViewHolder.getAdapterPosition()).getMangal());
                intent.putExtra("specialinformation",myProfileList.get(profileViewHolder.getAdapterPosition()).getSpecialinformation());
                intent.putExtra("occupationoffamilymember",myProfileList.get(profileViewHolder.getAdapterPosition()).getOccupationoffamily());
                intent.putExtra("expectation",myProfileList.get(profileViewHolder.getAdapterPosition()).getExpectations());
                mContext.startActivity(intent);
            }
        });
        setAnimation(profileViewHolder.itemView,i);
    }
    public void setAnimation(View viewToAnimate,int position){

        if(position>lastPosition){
            ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1000);
            viewToAnimate.startAnimation(animation);
            lastPosition=position;

        }
    }



    @Override
    public int getItemCount() {
        return myProfileList.size();
    }

    public void filteredList(ArrayList<ProfileData> filterList) {

        myProfileList=filterList;
        notifyDataSetChanged();
    }
}
class ProfileViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mName,mDateOfBirth,mQualification;
    CardView mCardView;

    public ProfileViewHolder( View itemView) {
        super(itemView);

        imageView =itemView.findViewById(R.id.ivImage);
        mName=itemView.findViewById(R.id.tvTitle);
        mDateOfBirth=itemView.findViewById(R.id.tvPrice);
        mQualification=itemView.findViewById(R.id.tvDescription);
        mCardView=itemView.findViewById(R.id.myCardView);
    }
}
