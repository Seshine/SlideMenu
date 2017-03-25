package yalantis.com.sidemenu.sample;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;

import yalantis.com.sidemenu.sample.fragment.Birthday;
import yalantis.com.sidemenu.sample.fragment.ContentFragment;
import yalantis.com.sidemenu.sample.fragment.Engagement;
import yalantis.com.sidemenu.sample.fragment.FatherMothers;
import yalantis.com.sidemenu.sample.fragment.Festivals;
import yalantis.com.sidemenu.sample.fragment.Friendship;
import yalantis.com.sidemenu.sample.fragment.Love;
import yalantis.com.sidemenu.sample.fragment.Others;
import yalantis.com.sidemenu.sample.fragment.Party;
import yalantis.com.sidemenu.sample.fragment.Valentines;
import yalantis.com.sidemenu.util.ViewAnimator;



public class MainActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ContentFragment contentFragment;
    private ViewAnimator viewAnimator;
    private int res = R.drawable.frontfrag;
    private LinearLayout linearLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentFragment = ContentFragment.newInstance(R.drawable.frontfrag);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });


        setActionBar();
        createMenuList();
   viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);
    }

    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);

        list.add(menuItem0);

        SlideMenuItem menuItem1 = new SlideMenuItem(Birthday.BIRTHDAY, R.drawable.birthday);
        list.add(menuItem1);
        SlideMenuItem menuItem2 = new SlideMenuItem(Love.LOVE, R.drawable.love);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(Engagement.ENGAGEMENT, R.drawable.enaggement);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(FatherMothers.FATHERMOTHERS, R.drawable.fathermother);
        list.add(menuItem4);
        SlideMenuItem menuItem5 = new SlideMenuItem(Friendship.FRIENDSHIP, R.drawable.friendshiphand);
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem(Party.PARTY, R.drawable.party);
        list.add(menuItem6);
        SlideMenuItem menuItem7 = new SlideMenuItem(Valentines.VALENTINES, R.drawable.valentines);
        list.add(menuItem7);
        SlideMenuItem menuItem8 = new SlideMenuItem(Festivals.FESTIVALS, R.drawable.festivals);
        list.add(menuItem8);
        SlideMenuItem menuItem9 = new SlideMenuItem(Others.OTHERS, R.drawable.others);
        list.add(menuItem9);

    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition, String type) {
        Toast.makeText(getApplication(), " "+topPosition, Toast.LENGTH_SHORT).show();
        if (topPosition==0){
            this.res = R.drawable.content_films;
            View view = findViewById(R.id.content_frame);
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
//            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//            animator.setInterpolator(new AccelerateInterpolator());
//            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//            animator.start();
            ContentFragment contentFragment = ContentFragment.newInstance(this.res);
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
            return contentFragment;
        }
        else  if(type.compareTo("BIRTHDAY")==0){
            Toast.makeText(getApplicationContext()," running ",Toast.LENGTH_LONG).show();
//            this.res = R.drawable.content_films;
            View view = findViewById(R.id.content_frame);
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
//            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//            animator.setInterpolator(new AccelerateInterpolator());
//            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//            animator.start();
            Birthday birthday = Birthday.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, birthday).addToBackStack(null).commit();
            return birthday;
        }


        else  if(type.compareTo("LOVE")==0){
          //  this.res = R.drawable.content_films;
            View view = findViewById(R.id.content_frame);
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
//            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//            animator.setInterpolator(new AccelerateInterpolator());
//            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//            animator.start();
            Love love = Love.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, love).commit();
            return love;
        }


        else  if(type.compareTo("ENGAGEMENT")==0){

            View view = findViewById(R.id.content_frame);
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
//            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//            animator.setInterpolator(new AccelerateInterpolator());
//            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//            animator.start();
            Engagement engagement = Engagement.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, engagement).commit();
            return engagement;
        }


        else  if(type.compareTo("FATHERMOTHERS")==0){

            View view = findViewById(R.id.content_frame);
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
//            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//            animator.setInterpolator(new AccelerateInterpolator());
//            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//            animator.start();
            FatherMothers fatherMothers = FatherMothers.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fatherMothers).commit();
            return contentFragment;
        }

        else  if(type.compareTo("FRIENDSHIP")==0){

            View view = findViewById(R.id.content_frame);
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
//            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//            animator.setInterpolator(new AccelerateInterpolator());
//            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//            animator.start();
            Friendship friendship = Friendship.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, friendship).commit();
            return contentFragment;
        }

        else  if(type.compareTo("PARTY")==0){

            View view = findViewById(R.id.content_frame);
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
//            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//            animator.setInterpolator(new AccelerateInterpolator());
//            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//            animator.start();
            Party party = Party.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, party).commit();
            return contentFragment;
        }

        else  if(type.compareTo("VALENTINES")==0){
          //  this.res = R.drawable.content_films;
            View view = findViewById(R.id.content_frame);
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
//            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//            animator.setInterpolator(new AccelerateInterpolator());
//            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//            animator.start();
            Valentines valentines = Valentines.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, valentines).commit();
            return contentFragment;
        }
        else  if(type.compareTo("FESTIVALS")==0){
           // this.res = R.drawable.content_films;
            View view = findViewById(R.id.content_frame);
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
//            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//            animator.setInterpolator(new AccelerateInterpolator());
//            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//            animator.start();
            Festivals festivals = Festivals.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, festivals).addToBackStack(null).commit();
            return contentFragment;
        }


        else {
            //this.res = R.drawable.content_films;
            View view = findViewById(R.id.content_frame);
            int finalRadius = Math.max(view.getWidth(), view.getHeight());
//            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
//            animator.setInterpolator(new AccelerateInterpolator());
//            animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);
//
//            findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
//            animator.start();
            Others others = Others.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, others).addToBackStack(null).commit();
            return others;
        }
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;
            case Birthday.BIRTHDAY:
                return replaceFragment(screenShotable, position, "BIRTHDAY");
            case Love.LOVE:
                return replaceFragment(screenShotable, position,"LOVE");
            case Engagement.ENGAGEMENT:
                return replaceFragment(screenShotable, position,"ENGAGEMENT");
            case FatherMothers.FATHERMOTHERS:
                return replaceFragment(screenShotable, position,"FATHERMOTHERS");
            case Friendship.FRIENDSHIP:
                return replaceFragment(screenShotable, position,"FRIENDSHIP");
            case Party.PARTY:
                return replaceFragment(screenShotable, position,"PARTY");
            case Valentines.VALENTINES:
                return replaceFragment(screenShotable, position,"VALENTINES");
            case Festivals.FESTIVALS:
                return replaceFragment(screenShotable, position,"FESTIVALS");
            case Others.OTHERS:
                return replaceFragment(screenShotable, position,"OTHERS");

            default:
                return replaceFragment(screenShotable, position,"NULL");

        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }
}
