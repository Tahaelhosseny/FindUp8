package khaled.example.com.findup.UI.ViewModel.Activites;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.PComment;
import khaled.example.com.findup.Helper.Utility;

import khaled.example.com.findup.UI.adapters.PCommentAdapter;
import khaled.example.com.findup.models.PCommentModel;

class PCommentsViewModel extends Observable {
}