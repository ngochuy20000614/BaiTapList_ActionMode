package com.example.customlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public  class ListViewBaseAdapter extends BaseAdapter {
     public ArrayList<ListViewBean> arraylistListener;
     private List<ListViewBean> mListenerList;
     Context mContext;

     public ListViewBaseAdapter(List<ListViewBean> mListenerList, Context context){
         mContext = context;
         this.mListenerList = mListenerList;
         this.arraylistListener = new ArrayList<ListViewBean>();
         this.arraylistListener.addAll(mListenerList);
     }
     public class  ViewHolder{
         ImageView mItemPic;
         TextView mLangName;
     }
     @Override
    public int getCount(){
         return mListenerList.size();
     }
     @Override
    public Object getItem(int position){
        return mListenerList.size();
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.child, null);
            holder = new ViewHolder();
            holder.mItemPic = (ImageView) view.findViewById(R.id.im_test);
            holder.mLangName=(TextView)view.findViewById(R.id.tv_langName);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        try {
            int image = mListenerList.get(position).getImage();
            holder.mItemPic.setImageResource(image);
            holder.mLangName.setText(mListenerList.get(position).getCourse());
        }catch (Exception ex){
        }
        CheckBox checkBox = view.findViewById(R.id.cbmonhoc);
        checkBox.setTag(position);

        if(MainActivity.isActionMode){
            checkBox.setVisibility(View.VISIBLE);
        }
        else{
            checkBox.setVisibility(View.GONE);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               int position = (int)buttonView.getTag();

               if(MainActivity.UserSelection.contains(mListenerList.get(position))){
                    MainActivity.UserSelection.remove(mListenerList.get(position));
                }
               else
               {
                   MainActivity.UserSelection.add(mListenerList.get(position));
               }
               MainActivity.actionMode.setTitle(MainActivity.UserSelection.size()+" items selected..");
            }
        });
        return view;
    }
    public void removeItems(List<ListViewBean> items){
        for(ListViewBean item : items){
            mListenerList.remove(item);
        }
        notifyDataSetChanged();
    }
}
