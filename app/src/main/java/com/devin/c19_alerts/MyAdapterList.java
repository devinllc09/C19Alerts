package com.devin.c19_alerts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterList extends ArrayAdapter<CounterModel> {
    private Context context;
    private List<CounterModel> counterModelList;
    private List<CounterModel> counterModelListFiltred;




    public MyAdapterList( Context context, List<CounterModel> counterModelList) {
        super(context, R.layout.custom_item_list,counterModelList);
        this.context=context;
        this.counterModelList=counterModelList;
        this.counterModelListFiltred=counterModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_list,null,true);
        TextView countername=view.findViewById(R.id.tvCountryName);
        ImageView flage=view.findViewById(R.id.imageFlag);

        countername.setText(counterModelListFiltred.get(position).getCountery());
        Glide.with(context).load(counterModelListFiltred.get(position).getFlag()).into(flage);


        return view;
    }

    @Override
    public int getCount() {
        return counterModelListFiltred.size();
    }

    @Nullable
    @Override
    public CounterModel getItem(int position) {
        return counterModelListFiltred.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults=new FilterResults();
                if (constraint==null||constraint.length()==0){
                    filterResults.count=counterModelList.size();
                    filterResults.values=counterModelList;
                }else {
                    List<CounterModel> resultModel=new ArrayList<>();
                    String searchStr=constraint.toString().toLowerCase();
                    for (CounterModel itemModel:counterModelList){
                        if (itemModel.getCountery().toLowerCase().contains(searchStr)){
                            resultModel.add(itemModel);
                        }
                        filterResults.count=resultModel.size();
                        filterResults.values=resultModel;
                    }



                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                counterModelListFiltred=(List<CounterModel>)results.values;
                CountryActivity.counterModelList=(List<CounterModel>)results.values;
                notifyDataSetChanged();

            }
        };

        return filter;
    }
}
