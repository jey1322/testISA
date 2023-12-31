// Generated by view binder compiler. Do not edit!
package com.strainteam.mispropinasisa.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.strainteam.mispropinasisa.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText etSearch;

  @NonNull
  public final ImageView ivAdd;

  @NonNull
  public final RecyclerView rvPropinas;

  @NonNull
  public final TextView tvAdd;

  @NonNull
  public final TextView tvHome;

  @NonNull
  public final TextView tvNew;

  private ActivityMainBinding(@NonNull LinearLayout rootView, @NonNull EditText etSearch,
      @NonNull ImageView ivAdd, @NonNull RecyclerView rvPropinas, @NonNull TextView tvAdd,
      @NonNull TextView tvHome, @NonNull TextView tvNew) {
    this.rootView = rootView;
    this.etSearch = etSearch;
    this.ivAdd = ivAdd;
    this.rvPropinas = rvPropinas;
    this.tvAdd = tvAdd;
    this.tvHome = tvHome;
    this.tvNew = tvNew;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.etSearch;
      EditText etSearch = ViewBindings.findChildViewById(rootView, id);
      if (etSearch == null) {
        break missingId;
      }

      id = R.id.ivAdd;
      ImageView ivAdd = ViewBindings.findChildViewById(rootView, id);
      if (ivAdd == null) {
        break missingId;
      }

      id = R.id.rvPropinas;
      RecyclerView rvPropinas = ViewBindings.findChildViewById(rootView, id);
      if (rvPropinas == null) {
        break missingId;
      }

      id = R.id.tvAdd;
      TextView tvAdd = ViewBindings.findChildViewById(rootView, id);
      if (tvAdd == null) {
        break missingId;
      }

      id = R.id.tvHome;
      TextView tvHome = ViewBindings.findChildViewById(rootView, id);
      if (tvHome == null) {
        break missingId;
      }

      id = R.id.tvNew;
      TextView tvNew = ViewBindings.findChildViewById(rootView, id);
      if (tvNew == null) {
        break missingId;
      }

      return new ActivityMainBinding((LinearLayout) rootView, etSearch, ivAdd, rvPropinas, tvAdd,
          tvHome, tvNew);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
