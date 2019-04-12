package com.arnaldo.moviekuy.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.arnaldo.moviekuy.R;
import com.arnaldo.moviekuy.event.TwoPaneEvent;
import com.arnaldo.moviekuy.util.Util;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private InterstitialAd mInterstitialAd;


    boolean twoPane;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.movies)
    ViewPager moviesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());



        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Util.setupToolbar(this, toolbar);

        if (findViewById(R.id.movie_detail) != null) {
            twoPane = true;
        }

        init();
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    protected void init() {
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        moviesView.setOffscreenPageLimit(2);
        moviesView.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(moviesView);
        EventBus.getDefault().postSticky(new TwoPaneEvent(twoPane));
    }

    public class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MoviesFragment.newInstance(MoviesFragment.Type.POPULAR, twoPane);
                case 1:
                    return MoviesFragment.newInstance(MoviesFragment.Type.TOP_RATED, twoPane);
                case 2:
                    return MoviesFragment.newInstance(MoviesFragment.Type.FAVORITES, twoPane);
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.popular);
                case 1:
                    return getString(R.string.top_rated);
                case 2:
                    return getString(R.string.favorites);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
