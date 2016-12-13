package com.jaime.examendeportes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaime.examendeportes.R;
import com.jaime.examendeportes.Repository;
import com.jaime.examendeportes.model.Sport;

import java.util.ArrayList;

/**
 * Created by jaime on 8/12/16.
 */

public class SportAdapter extends ArrayAdapter<Sport> {
    private Context context;


    public SportAdapter(Context context) {
        super(context, R.layout.sport_item, new ArrayList<Sport>(Repository.getInstance()));
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        SportHolder sportHolder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.sport_item, null);
            sportHolder = new SportHolder();

            sportHolder.sportImage = (ImageView) view.findViewById(R.id.img_sport);
            sportHolder.sportName = (TextView) view.findViewById(R.id.txv_sportName);
            sportHolder.like = (CheckBox) view.findViewById(R.id.cbx_sport);

            view.setTag(sportHolder);
        } else
            sportHolder = (SportHolder)view.getTag();

        sportHolder.like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                getItem(position).setLike(b);
            }
        });

        sportHolder.sportImage.setImageResource(getItem(position).getImage());
        sportHolder.sportName.setText(getItem(position).getName());
        sportHolder.like.setChecked(getItem(position).isLike());

        return view;
    }

    public void showFilteredContent(String letter) {
        ArrayList<Sport> filteredSports = new ArrayList<>();

        for (int i = 0; i < getCount(); i++) {
            if (getItem(i).getName().toLowerCase().startsWith(letter.toLowerCase()))
                filteredSports.add(getItem(i));
        }

        clear();
        addAll(filteredSports);
    }

    class SportHolder {
        ImageView sportImage;
        TextView sportName;
        CheckBox like;
    }
}
