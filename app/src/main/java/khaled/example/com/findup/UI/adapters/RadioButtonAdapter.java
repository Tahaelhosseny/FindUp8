package khaled.example.com.findup.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.ViewModel.Activites.CurrencyViewModel;
import khaled.example.com.findup.UI.activities.NotificationsActivity;
import khaled.example.com.findup.models.Currency;

public class RadioButtonAdapter extends RecyclerView.Adapter<RadioButtonAdapter.ViewHolder> {
    private List<Currency> currencyList;
    private Context context;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;

    public RadioButtonAdapter(Context context, List<Currency> currencyList) {
        this.context = context;
        this.currencyList = currencyList;
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
        Currency currency = currencyList.get(position);
        holder.radioButton.setText(currency.getCurrency_title());holder.radioButton.setId(currency.getCurrency_id());
        holder.radioButton.setChecked(currency.isSelected());
        holder.radioButton.setTag(new Integer(position));
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
//                    SharedPrefManger.setPermentCurrency(cb.getId());
//                    Toast.makeText(context, "Last Checked : "+SharedPrefManger.getPermantCurrency(), Toast.LENGTH_SHORT).show();
                }
                else
                    lastChecked = null;

                currencyList.get(clickedPos).setSelected(cb.isChecked());
            }
        });
//        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                CurrencyViewModel currencyViewModel = new CurrencyViewModel(context);
//                holder.radioButton.setChecked(b);
//                currencyViewModel.setCurrency(holder.radioButton.getId() , SharedPrefManger.getStore_ID());
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RadioButton radioButton;

        public ViewHolder(View view) {
            super(view);
            radioButton = view.findViewById(R.id.radio_adapter);
        }
    }
}
