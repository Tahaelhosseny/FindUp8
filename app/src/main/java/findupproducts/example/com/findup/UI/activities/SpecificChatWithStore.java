package findupproducts.example.com.findup.UI.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.Presenter.Activities.SpecificChatPresenter;
import findupproducts.example.com.findup.UI.ViewModel.Activites.EditProfileViewModel;
import findupproducts.example.com.findup.UI.ViewModel.Activites.SpecificChatViewModel;
import findupproducts.example.com.findup.databinding.ActivitySpecificChatWithStoreBinding;

public class SpecificChatWithStore extends AppCompatActivity {

    ActivitySpecificChatWithStoreBinding binding;
    SpecificChatViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new SpecificChatViewModel(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_specific_chat_with_store);
        String store_id = getIntent().getStringExtra("store_id");
        String name  = getIntent().getStringExtra("store_name");
        //Toast.makeText(this, store_id, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        binding.btnBack.setText(name);
        viewModel.getFullChat(binding.reyclerviewMessageList , Integer.parseInt(store_id));
        binding.setPresenter(new SpecificChatPresenter() {
            @Override
            public void backFromChat() {
                finish();
            }
            @Override
            public void sendMessage() {
                viewModel.sendMessageToStore(Integer.parseInt(store_id), binding.chatboxEdit);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
