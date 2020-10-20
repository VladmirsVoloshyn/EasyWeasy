package com.example.myapplication.ui.customize;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class WeatherAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<WeatherListTag> objects;

    public WeatherAdapter(Context context, ArrayList<WeatherListTag> objects){
        this.context = context;
        this.objects = objects;
        layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.layout, parent, false);
        }

        WeatherListTag listTag = getTag(position);

        ((TextView) view.findViewById(R.id.TextViewDate)).setText(listTag.getDate());
        ((TextView) view.findViewById(R.id.TextViewTemp)).setText(listTag.getTemp() + "");
        switch (listTag.getDescriptionImage()){
            case ("Clear"):
                ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(R.drawable.clear);
                break;
            case ("Rain"):
                ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(R.drawable.rain);
                break;
            case ("Snow"):
                ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(R.drawable.snow);
                break;
            case ("Clouds"):
                ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(R.drawable.clouds);
                break;
            case ("Thunderstorm"):
                ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(R.drawable.thunder);
                break;
            case ("Mist"):
                ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(R.drawable.mist);
                break;
            case ("Drizzle"):
                ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(R.drawable.showerrain);
                break;
        }
        return view;
    }
    WeatherListTag getTag(int position) {
        return ((WeatherListTag) getItem(position));
    }
}
