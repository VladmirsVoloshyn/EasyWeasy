package com.example.myapplication.ui.customize.city;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.database.citiesfavoritebase.CitiesBaseManager;

public class CityAdapter extends BaseAdapter {

    private final LayoutInflater layoutInflater;
    private final CitiesBaseManager citiesBaseManager;

    public CityAdapter(Context context, CitiesBaseManager citiesBaseManager){
        this.citiesBaseManager = citiesBaseManager;
        layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return citiesBaseManager.getCitiesList().size();
    }

    @Override
    public Object getItem(int position) {
        return citiesBaseManager.getCitiesList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.city_list_layout, parent, false);
        }

        citiesBaseManager.getCitiesList().get(position);

        ((TextView) view.findViewById(R.id.TextViewCity)).setText(  citiesBaseManager.getCitiesList().get(position).getCityName());
        ((TextView) view.findViewById(R.id.TextViewTemp)).setText(  citiesBaseManager.getCitiesList().get(position).getTemp() + "");
        switch (  citiesBaseManager.getCitiesList().get(position).getDescriptionImage()){
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
}
