package com.charlesdrews.usabilitytestinglab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * This fragment will show a list of Zodiac signs.
 *
 * Activities that contain this fragment must implement the
 * {@link OnZodiacSignSelectedListener} interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    private OnZodiacSignSelectedListener mListener;
    private ListView mListView;

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow a user interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnZodiacSignSelectedListener {
        void onZodiacSignSelected(String zodiacSignSelected);
    }

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param arguments - optional arguments bundle
     * @param listener - an object from a class that implements the OnZodiacSignSelectedListener interface
     * @return a new instance of ListFragment
     */
    public static ListFragment newInstance(Bundle arguments, OnZodiacSignSelectedListener listener) {
        ListFragment fragment = new ListFragment();
        fragment.setArguments(arguments);
        fragment.mListener = listener;
        return fragment;
    }

    public void setListener(OnZodiacSignSelectedListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mListView = (ListView) view.findViewById(R.id.signs_list_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // The strings representing each Zodiac sign are saved in strings.xml in an array
        String[] values = getResources().getStringArray(R.array.signs);

        // Use those string as data for an array adapter for the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mListView.setAdapter(adapter);

        // Handle click events for the list of zodiac signs
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sign = (String) mListView.getItemAtPosition(i);

                // HERE IS WHERE YOU CALL THE CALLBACK METHOD THAT YOU FORCED THE ACTIVITY
                // TO IMPLEMENT VIA THE INTERFACE!!!
                mListener.onZodiacSignSelected(sign);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null; // don't keep a reference to the activity after the fragment is detached
    }
}
