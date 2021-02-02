package com.example.androidfundamentals.week6;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidfundamentals.R;

import static android.content.ContentValues.TAG;

public class HelloFragment extends Fragment {

    protected static final String EXTRA_KEY_INT = "extra_key_int";
    protected static final String EXTRA_KEY_STRING = "extra_key_string";

    private TheListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        return inflater.inflate(R.layout.fragment_hello, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView count = view.findViewById(R.id.tv_count);
        TextView label = view.findViewById(R.id.tv_label);

        Bundle bundle = getArguments();
        if(bundle != null) {
            count.setText(String.valueOf(bundle.getInt(EXTRA_KEY_INT, 0)));
            label.setText(bundle.getString(EXTRA_KEY_STRING, "Not found"));
        }

        view.findViewById(R.id.button_showInActivity).setOnClickListener(v -> {
            if(listener != null) {
                listener.doSomeWork("Sent from " +this.getClass().getSimpleName());
            }
            else {
                Log.w(TAG, "onViewCreated: invalid listener");
            }
        });
    }

    public void fragmentToast() {
        Toast.makeText(getContext(), "We are in " + this.getClass().getSimpleName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(getActivity() instanceof TheListener) {
            listener = (TheListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }
}
