package info.rohanmorris.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class intro extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static String WELCOME = "info.rohanmorris.welcome";

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static Context mContext;
    private static LinearLayout mTabLayout;
    private static ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);
        mTabLayout = (LinearLayout) findViewById(R.id.viewPagerTabs);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mContext = this;

        drawPageSelectionIndicators(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                drawPageSelectionIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private int dotsCount=4;    //No of tabs or images
    private ImageButton[] dots;

    private void drawPageSelectionIndicators(int mPosition){
        if(mTabLayout!=null) {
            mTabLayout.removeAllViews();
        }

        dots = new ImageButton[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageButton(mContext);
            if(i==mPosition)
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
            else
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(0, 0, 0, 0);
            dots[i].setBackgroundColor(Color.argb(0,0,0,0));
            dots[i].setPadding(10, 10, 10, 10);
            mTabLayout.addView(dots[i],params);

            final int finalI = i;
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(finalI);
                }
            });
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {}

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = null;
            fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_intro, container, false);

            TextView introTitle = (TextView) rootView.findViewById(R.id.intro_header);
            TextView introBody  = (TextView) rootView.findViewById(R.id.intro_text);
            Button btn          = (Button) rootView.findViewById(R.id.btn);

            final int position  = getArguments().getInt(ARG_SECTION_NUMBER);
            String[] title      = getResources().getStringArray(R.array.intro_title);
            String[] btn_text   = getResources().getStringArray(R.array.btn_text);
            final String[] body = getResources().getStringArray(R.array.intro_body);

            introTitle.setText(title[position]);
            introBody.setText(body[position]);
            btn.setText(btn_text[position]);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int next = position+1;
                    if(next < 4) {
                        mViewPager.setCurrentItem(next,true);
                    }else{
                        /*try {
                            Class cl = Class.forName(className);
                            Intent intent = new Intent(mContext, cl);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            mContext.startActivity(intent);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }*/
                    }
                }
            });

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
