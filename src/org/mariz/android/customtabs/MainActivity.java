package org.mariz.android.customtabs;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

    private TabHost mTabHost;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);

        Resources res = getResources();

        setupTab(new TextView(this),
                "home",
                "Home",
                res.getDrawable(R.drawable.icon_home),
                new Intent().setClass(this, FirstTabActivity.class));

        setupTab(new TextView(this),
                "favorites",
                "Favorites",
                res.getDrawable(R.drawable.icon_favorite),
                new Intent().setClass(this, SecondTabActivity.class));
    }

    private void setupTab(final View view, final String tag, String text, Drawable icon, Intent intent) {
        View tabView = createTabView(mTabHost.getContext(), text, icon);
        TabSpec tabSpec = mTabHost.newTabSpec(tag);

        tabSpec.setIndicator(tabView);
        tabSpec.setContent(intent);
        mTabHost.addTab(tabSpec);
    }

    private static View createTabView(final Context context, final String text, final Drawable icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_bg, null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(text);

        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        imageView.setImageDrawable(icon);
        return view;
    }
}
