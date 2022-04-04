package com.example.androidstuidolab_07.lab7b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.androidstuidolab_07.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private int idLayout;
    private List<Place> places;

    public CustomAdapter(Context context, int idLayout, List<Place> places) {
        this.context = context;
        this.idLayout = idLayout;
        this.places = places;
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Object getItem(int i) {
        return places.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout, viewGroup, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.viewItem);
        Place place = places.get(i);
        TextView placeIdView = (TextView) view.findViewById(R.id.sttPlace);
        TextView placeNameView = (TextView) view.findViewById(R.id.namePlace);
        ImageView placeEditView = (ImageView) view.findViewById(R.id.placeEdit);
        ImageView placeRemoveView = (ImageView) view.findViewById(R.id.placeRemove);
        placeIdView.setText(""+place.getPlaceId());
        placeNameView.setText(place.getPlaceName());
        placeEditView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Bạn chọn edit icon: "+view, Toast.LENGTH_LONG).show();
            }
        });
        placeRemoveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Bạn chọn remove icon: "+ view, Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
