package com.example.nishanth.frangmentsinclass;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class AFragment extends Fragment  {

    OnFrangmentTextChange mListener;

    public AFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d("Demo " , " AFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        /*view.findViewById(R.id.buttonClickMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity()," Clicked onCreateView ",Toast.LENGTH_SHORT).show();


            }
        });*/

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Demo " , " AFragment: onCreate() ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Demo " , " AFragment: onResume() ");

    }

    public void changeColor(int color){
            getView().setBackgroundColor(color);
     //   getActivity().findViewById(R.id.fragment_root).setBackgroundColor(color);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("Demo " , " AFragment: onAttach() ");

        try{
            mListener = (OnFrangmentTextChange) activity;

        }catch (ClassCastException e){
            throw new ClassCastException(e.toString());
            //e.printStackTrace();

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Demo " , " AFragment: onActivityCreated ");
        /*getActivity().findViewById(R.id.buttonClickMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity()," Clicked ",Toast.LENGTH_SHORT).show();
            }
        });
        */

      /*  getActivity().findViewById(R.id.buttonInFrag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et= (EditText) getActivity().findViewById(R.id.editTextInFrag);
                mListener.onTextChange(et.getText().toString());
            }
        }); */

        getView().findViewById(R.id.buttonInFrag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et= (EditText) getView().findViewById(R.id.editTextInFrag);
                mListener.onTextChange(et.getText().toString());
            }
        });



    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Demo " , " AFragment: onStart ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Demo " , " AFragment: onDestroy ");
    }

    public interface OnFrangmentTextChange{

        public void onTextChange(String txt);

    }


}
