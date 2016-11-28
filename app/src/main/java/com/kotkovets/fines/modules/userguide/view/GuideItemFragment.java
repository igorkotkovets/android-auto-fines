package com.kotkovets.fines.modules.userguide.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.kotkovets.fines.R;

/**
 * Created by igork on 8/31/16.
 */
public class GuideItemFragment extends Fragment {
    private String title;
    private String resourceUrl;
    private WebView webView;

    private static class Contract {
        private static String TITLE = "title";
        private static String URL = "url";
    }

    public static GuideItemFragment newInstance(String title, String url) {
        GuideItemFragment fragment = new GuideItemFragment();
        Bundle args = new Bundle();
        args.putString(Contract.TITLE, title);
        args.putString(Contract.URL, url);
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = getArguments().getString(Contract.TITLE);
        resourceUrl = getArguments().getString(Contract.URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_guide_item, container,false);
        webView = (WebView) rootView.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(resourceUrl);

        return rootView;
    }


}
