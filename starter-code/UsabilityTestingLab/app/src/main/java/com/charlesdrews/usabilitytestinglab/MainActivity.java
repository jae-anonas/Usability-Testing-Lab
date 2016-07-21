package com.charlesdrews.usabilitytestinglab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnZodiacSignSelectedListener {

    private boolean mScreenIsLargeEnoughForTwoPanes = false;
    private DetailFragment mDetailFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this class - MainActivity - implements the OnZodiacSignSelectedListener interface
        // defined in ListFragment - so "this" can be set as the listener for the list fragment
        ListFragment listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        listFragment.setListener(this);

        //TODO determine which layout file is being used (hint: is there an element in the large-screen
        //TODO  layout that's not in the regular layout?) and if the large screen layout is being used,
        //TODO  then load the detail fragment in MainActivity rather than using DetailActivity
        View view = findViewById(R.id.detail_fragment_container);
        if (view != null)
            mScreenIsLargeEnoughForTwoPanes = true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onZodiacSignSelected(String zodiacSignSelected) {

        Bundle args = new Bundle();
        args.putString(DetailActivity.SIGN_KEY, zodiacSignSelected);

        if (!mScreenIsLargeEnoughForTwoPanes){
            Log.i("asdasdasdasd", "mScreenIsLargeEnough is false");
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.SIGN_KEY, zodiacSignSelected);
            startActivity(intent);

        }
        else {
            //TODO - if the detail fragment is loaded into MainActivity, update it rather than launching
            //TODO      the DetailActivity
            Log.i("asdasdasdasd", "mScreenIsLargeEnough is true");
            mDetailFragment = DetailFragment.newInstance(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.detail_fragment_container, mDetailFragment)
                    .commit();


        }
    }
}
