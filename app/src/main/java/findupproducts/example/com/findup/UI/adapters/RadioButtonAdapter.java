package findupproducts.example.com.findup.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.ViewModel.Activites.CurrencyViewModel;
import findupproducts.example.com.findup.UI.activities.NotificationsActivity;
import findupproducts.example.com.findup.models.City;
import findupproducts.example.com.findup.models.Country;
import findupproducts.example.com.findup.models.Currency;

public class RadioButtonAdapter extends RecyclerView.Adapter<RadioButtonAdapter.ViewHolder> {
    private List<Currency> currencyList;
    private List<Country> countryList;
    private List<City> cityList;
    private Context context;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;
    int type;
    int listSize = 0;

    public RadioButtonAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
        /*for (int i = 0;i<list.size();i++){
            switch (type){
                case 1:{
                    this.currencyList.add((Currency) list.get(i));
                    listSize = list.size();
                    break;
                }
                case 2:{
                    this.countryList.add((Country) list.get(i));
                    listSize = list.size();
                    break;
                }
                case 3:{
                    this.cityList.add((City) list.get(i));
                    listSize = list.size();
                    break;
                }
            }
        }*/
    }

    public void setCurrencyList(List<Currency> currency){
        this.currencyList = currency;
        listSize = currency.size();
        notifyDataSetChanged();
    }
    public void setCountryList(List<Country> countryList){
        this.countryList = countryList;
        listSize = countryList.size();
        notifyDataSetChanged();
    }
    public void setCityList(List<City> cityList){
        this.cityList = cityList;
        listSize = cityList.size();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RadioButtonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_radio_button, parent, false);
        return new RadioButtonAdapter.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (type){
            case 1:{
                Currency item = currencyList.get(position);
                holder.radioButton.setText(item.getCurrency_title());
                holder.radioButton.setId(item.getCurrency_id());
                holder.radioButton.setChecked(item.isSelected());
                holder.radioButton.setTag(new Integer(position));
                int id = SharedPrefManger.getCurrencyIdStore();
                if(holder.radioButton.getId() == id){
                    holder.radioButton.setChecked(true);
                    lastChecked = holder.radioButton;
                }
                if(position == 0 && currencyList.get(0).isSelected() && holder.radioButton.isChecked())
                {
                    lastChecked = holder.radioButton;
                    lastCheckedPos = 0;
                }
                holder.radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        RadioButton cb = (RadioButton) v;
                        int clickedPos = ((Integer)cb.getTag()).intValue();

                        if(cb.isChecked())
                        {
                            if(lastChecked != null)
                            {
                                lastChecked.setChecked(false);
                                currencyList.get(lastCheckedPos).setSelected(false);
                            }

                            lastChecked = cb;
                            lastCheckedPos = clickedPos;
                            SharedPrefManger.setPermentCurrency(cb.getId());
                        }
                        else
                            lastChecked = null;

                        currencyList.get(clickedPos).setSelected(cb.isChecked());
                    }
                });
                break;
            }
            case 2:{
                Log.e("MyData", ""+countryList.size());
                Country item = countryList.get(position);
                holder.radioButton.setText(item.getName_en());
                holder.radioButton.setId(item.getCountry_id());
                holder.radioButton.setChecked(item.isSelected());
                holder.radioButton.setTag(new Integer(position));
                if(position == 0 && countryList.get(0).isSelected() && holder.radioButton.isChecked()) {
                    lastChecked = holder.radioButton;
                    lastCheckedPos = 0;
                }
                holder.radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        RadioButton cb = (RadioButton) v;
                        int clickedPos = ((Integer)cb.getTag()).intValue();

                        if(cb.isChecked())
                        {
                            if(lastChecked != null)
                            {
                                lastChecked.setChecked(false);
                                countryList.get(lastCheckedPos).setSelected(false);
                            }

                            lastChecked = cb;
                            lastCheckedPos = clickedPos;
                        }
                        else
                            lastChecked = null;

                        countryList.get(clickedPos).setSelected(cb.isChecked());
                    }
                });
                break;
            }
            case 3:{
                City item = cityList.get(position);
                holder.radioButton.setText(item.getName_en());
                holder.radioButton.setId(item.getCity_id());
                holder.radioButton.setChecked(item.isSelected());
                holder.radioButton.setTag(new Integer(position));
                if(position == 0 && cityList.get(0).isSelected() && holder.radioButton.isChecked())
                {
                    lastChecked = holder.radioButton;
                    lastCheckedPos = 0;
                }
                holder.radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        RadioButton cb = (RadioButton) v;
                        int clickedPos = ((Integer)cb.getTag()).intValue();

                        if(cb.isChecked())
                        {
                            if(lastChecked != null)
                            {
                                lastChecked.setChecked(false);
                                cityList.get(lastCheckedPos).setSelected(false);
                            }

                            lastChecked = cb;
                            lastCheckedPos = clickedPos;
                        }
                        else
                            lastChecked = null;

                        cityList.get(clickedPos).setSelected(cb.isChecked());
                    }
                });
                break;
            }
        }
    }
    @Override
    public int getItemCount() {

        return listSize;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        RadioButton radioButton;
        public ViewHolder(View view) {
            super(view);
            radioButton = view.findViewById(R.id.radio_adapter);
        }
    }

}
