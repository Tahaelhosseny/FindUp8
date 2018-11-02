package findupproducts.example.com.findup.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.models.GetContact;

public class ChatStoreContactPicAdapter extends RecyclerView.Adapter<ChatStoreContactPicAdapter.ViewHolder> {

    private List<GetContact> contacts;
    private Context context;
    private int middle_element_position;

    public ChatStoreContactPicAdapter(Context context, List<GetContact> contacts, int middle_element_position) {
        this.context = context;
        this.contacts = contacts;
        this.middle_element_position = middle_element_position;
    }

    @NonNull
    @Override
    public ChatStoreContactPicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (0 == viewType)
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_stores_profile_item, parent, false);
        else
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_stores_profile_item_middle, parent, false);

        return new ChatStoreContactPicAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatStoreContactPicAdapter.ViewHolder holder, int position) {

        GetContact contact = contacts.get(position);

        holder.store_name.setText(contact.getName());
        if (!contact.getImage().isEmpty()) {
            Picasso.with(context).load(contact.getImage()).placeholder(R.drawable.near_by_place_holder).into(holder.store_img);
        }

    }

    public void setMiddle_element_position(int middle_element_position) {
        this.middle_element_position = middle_element_position;
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == middle_element_position)
            return 100;
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircularImageView store_img;
        TextView store_name;

        public ViewHolder(View view) {
            super(view);
            store_img = view.findViewById(R.id.store_prof_pic);
            store_name = view.findViewById(R.id.store_name);

        }
    }
}
