package com.charlesdrews.usabilitytestinglab;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * This fragment will show "detail" for the selected Zodiac sign by loading a relevant
 * website in a WebView.
 *
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    //the full URL will be this beginning part + the selected zodiac sign String at the end
    private static final String URL_BEGINNING ="http://www.horoscopedates.com/zodiac-signs/";
    private static String sign;

    private WebView mWebView;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param arguments - optional arguments bundle
     * @return A new instance of fragment DetailFragment.
     */
    public static DetailFragment newInstance(Bundle arguments) {
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(arguments);
        sign = arguments.getString(DetailActivity.SIGN_KEY);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mWebView = (WebView) view.findViewById(R.id.detail_web_view);
        mWebView.setWebViewClient(new WebViewClient()); // this prevents links from being opened externally in the browser
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateWebView(sign);
    }

    public void updateWebView(String selectedSign) {
        //TODO - look up how to load a URL in a webview
        mWebView.loadUrl(URL_BEGINNING + selectedSign);
    }
}
