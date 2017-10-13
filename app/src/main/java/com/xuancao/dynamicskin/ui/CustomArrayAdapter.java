package com.xuancao.dynamicskin.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.xuancao.dynamicskin.R;
import com.xuancao.dynamicskin.view.RoundImageView;


public class CustomArrayAdapter extends ArrayAdapter<CustomData> {
    private LayoutInflater mInflater;
    private int selectedPosition = -1;// 选中的位置

    public CustomArrayAdapter(Context context, CustomData[] values) {
        super(context, R.layout.custom_data_view, values);
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = mInflater.inflate(R.layout.custom_data_view, parent, false);
        RoundImageView imageView_color = view.findViewById(R.id.textView);
        ImageView imageView_select = view.findViewById(R.id.image_skin_color_select);
        imageView_color.setBackgroundResource(getItem(position).getmBackgroundResId());
        if (position == selectedPosition) {
            imageView_select.setVisibility(View.VISIBLE);
        } else {
            imageView_select.setVisibility(View.GONE);
        }
        return view;
    }
}
