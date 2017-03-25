package yalantis.com.sidemenu.sample;

import android.app.Dialog;
import android.app.MediaRouteButton;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.preference.DialogPreference;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.sample.fragment.GalleryAdapter;
import yalantis.com.sidemenu.sample.fragment.StickerView;

import static android.support.v7.widget.ListPopupWindow.MATCH_PARENT;
import static yalantis.com.sidemenu.sample.R.id.etSetText;
import static yalantis.com.sidemenu.sample.R.id.rel1;

public class Edit extends AppCompatActivity implements OnMenuItemClickListener{

    private String value;
    private RelativeLayout rl1;


    CircleMenu circleMenu;

    int mStatusBarHeight;
    int mToolBarHeight;


    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;


    // these matrices will be used to move and zoom image
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    // we can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    // remember some things for zooming
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;
    final Context context = this;
    List<StickerView> mStickers = new ArrayList<>();
    int[] mResIds = new int[]{R.mipmap.st1, R.mipmap.st2, R.mipmap.st5,
           R.mipmap.st6,R.mipmap.st7,R.mipmap.st8,R.mipmap.st11,R.mipmap.st12,R.mipmap.st13,R.mipmap.st14,
            R.mipmap.st15,R.mipmap.st16,R.mipmap.st17,R.mipmap.st18,R.mipmap.st19,R.mipmap.st20,R.mipmap.st123};

   RecyclerView mRecyclerView;
   GalleryAdapter mGalleryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_edit);
        fragmentManager = getSupportFragmentManager();
          initToolbar();
          initView();



         rl1 = (RelativeLayout) findViewById(rel1);

         Intent intent=getIntent() ;
         value = intent.getStringExtra("imageid");



        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setVisibility(View.GONE);






        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_menu, R.mipmap.icon_cancel);
        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.mipmap.icon_home)
                .addSubMenu(Color.parseColor("#30A400"), R.mipmap.fromtext)
                .addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.fromstik)
                .addSubMenu(Color.parseColor("#8A39FF"), R.mipmap.fromcam)
                .addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.fromgal);



        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index) {
                                                         case 0:

                                                             Toast.makeText(Edit.this, "Home Button Clicked", Toast.LENGTH_SHORT).show();
                                                             break;
                                                         case 1:
                                                             Toast.makeText(Edit.this, "Search button Clicked", Toast.LENGTH_SHORT).show();
                                                             AlertDialog.Builder builder=new AlertDialog.Builder(Edit.this);
                                                             View view=getLayoutInflater().inflate(R.layout.settext,null);
                                                             final EditText editText=(EditText)view.findViewById(R.id.etSetText);
                                                             builder.setTitle("Set Text");
                                                             builder.setView(view);
                                                             builder.setPositiveButton("O K", new DialogInterface.OnClickListener() {
                                                                 public void onClick(DialogInterface dialog, int id) {

                                                                     rl1.addView(createNewTextView(editText.getText().toString()));
                                                                 }

                                                             });
                                                             TextView textView = new TextView(Edit.this);
                                                             textView.setText("New text");



                                                             AlertDialog alertDalog=builder.create();
                                                             alertDalog.show();
                                                             break;
                                                         case 2:


                                                            // mGalleryAdapter = new GalleryAdapter(mResIds);
                                                             mRecyclerView.setVisibility(View.VISIBLE);

                                                             RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rl1.getLayoutParams();
                                                             params.height = 1200;
                                                             rl1.setLayoutParams(params);


                                                             break;
                                                         case 3:
                                                             new Handler().postDelayed(new Runnable() {
                                                                 @Override
                                                                 public void run() {
                                                                     Intent i2 = new Intent();
                                                                     i2.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                                                                     startActivityForResult(i2,11);

                                                                 }
                                                             },500);
                                                             break;
                                                         case 4:

                                                          new Handler().postDelayed(new Runnable() {


                                                               @Override
                                                               public void run() {
                                                                   Intent intent = new Intent();
                                                                   intent.setType("image/*");
                                                                   intent.setAction(Intent.ACTION_GET_CONTENT);

                                                                   startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);

                                                               }
                                                           },500);
                                                             break;

                                                     }

                                                 }
                                             }
        );

        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                                                     @Override
                                                     public void onMenuOpened() {

                                                     }

                                                     @Override
                                                     public void onMenuClosed() {

                                                     }
                                                 }
        );







        /////// for birthday


        if (value.compareTo("birthday1")==0)
            rl1.setBackgroundResource(R.drawable.birthday1);

        else if (value.compareTo("b2")==0)
            rl1.setBackgroundResource(R.drawable.b2);
        else if (value.compareTo("b3")==0)
            rl1.setBackgroundResource(R.drawable.b3);
        else if (value.compareTo("b4")==0)
            rl1.setBackgroundResource(R.drawable.b4);
        else if (value.compareTo("b5")==0)
            rl1.setBackgroundResource(R.drawable.b5);
        else if (value.compareTo("b6")==0)
            rl1.setBackgroundResource(R.drawable.b6);
        else if (value.compareTo("b8")==0)
            rl1.setBackgroundResource(R.drawable.b8);
        else if (value.compareTo("b9")==0)
            rl1.setBackgroundResource(R.drawable.b9);
        else if (value.compareTo("b10")==0)
            rl1.setBackgroundResource(R.drawable.b10);
        else if (value.compareTo("b11")==0)
            rl1.setBackgroundResource(R.drawable.b11);



//////////for engagement


       else if (value.compareTo("e1")==0)
            rl1.setBackgroundResource(R.drawable.e1);
        else if (value.compareTo("e2")==0)
            rl1.setBackgroundResource(R.drawable.e2);
        else if (value.compareTo("e4")==0)
            rl1.setBackgroundResource(R.drawable.e4);
        else if (value.compareTo("e5")==0)
            rl1.setBackgroundResource(R.drawable.e5);
        else if (value.compareTo("e6")==0)
            rl1.setBackgroundResource(R.drawable.e6);
        else if (value.compareTo("e7")==0)
            rl1.setBackgroundResource(R.drawable.e7);
        else if (value.compareTo("e8")==0)
            rl1.setBackgroundResource(R.drawable.e8);
        else if (value.compareTo("e9")==0)
            rl1.setBackgroundResource(R.drawable.e9);
        else if (value.compareTo("e11")==0)
            rl1.setBackgroundResource(R.drawable.e11);
        else if (value.compareTo("e12")==0)
            rl1.setBackgroundResource(R.drawable.e12);



        /////////for father & Mother's Day

        else if (value.compareTo("fm2")==0)
            rl1.setBackgroundResource(R.drawable.fm2);
        else if (value.compareTo("fm3")==0)
            rl1.setBackgroundResource(R.drawable.fm3);
        else if (value.compareTo("fm4")==0)
            rl1.setBackgroundResource(R.drawable.fm4);
        else if (value.compareTo("fm5")==0)
            rl1.setBackgroundResource(R.drawable.fm5);
        else if (value.compareTo("fm7")==0)
            rl1.setBackgroundResource(R.drawable.fm7);
        else if (value.compareTo("fm8")==0)
            rl1.setBackgroundResource(R.drawable.fm8);
        else if (value.compareTo("father1")==0)
            rl1.setBackgroundResource(R.drawable.father1);
        else if (value.compareTo("fm10")==0)
            rl1.setBackgroundResource(R.drawable.fm10);
        else if (value.compareTo("fm11")==0)
            rl1.setBackgroundResource(R.drawable.fm11);
        else if (value.compareTo("fm13")==0)
            rl1.setBackgroundResource(R.drawable.fm13);


//////////for festivals


        else if (value.compareTo("holi")==0)
            rl1.setBackgroundResource(R.drawable.holi);
        else if (value.compareTo("f1")==0)
            rl1.setBackgroundResource(R.drawable.f1);
        else if (value.compareTo("f2")==0)
            rl1.setBackgroundResource(R.drawable.f2);
        else if (value.compareTo("f3")==0)
            rl1.setBackgroundResource(R.drawable.f3);
        else if (value.compareTo("f4")==0)
            rl1.setBackgroundResource(R.drawable.f4);
        else if (value.compareTo("f5")==0)
            rl1.setBackgroundResource(R.drawable.f5);
        else if (value.compareTo("f6")==0)
            rl1.setBackgroundResource(R.drawable.f6);
        else if (value.compareTo("f7")==0)
            rl1.setBackgroundResource(R.drawable.f7);


///////////for friendship


        else if (value.compareTo("friendship")==0)
            rl1.setBackgroundResource(R.drawable.friendship);
        else if (value.compareTo("fs1")==0)
            rl1.setBackgroundResource(R.drawable.fs1);
        else if (value.compareTo("fs2")==0)
            rl1.setBackgroundResource(R.drawable.fs2);
        else if (value.compareTo("fs3")==0)
            rl1.setBackgroundResource(R.drawable.fs3);
        else if (value.compareTo("fs4")==0)
            rl1.setBackgroundResource(R.drawable.fs4);
        else if (value.compareTo("fs5")==0)
            rl1.setBackgroundResource(R.drawable.fs5);
        else if (value.compareTo("fs6")==0)
            rl1.setBackgroundResource(R.drawable.fs6);
        else if (value.compareTo("fs7")==0)
            rl1.setBackgroundResource(R.drawable.fs7);
        else if (value.compareTo("fs8")==0)
            rl1.setBackgroundResource(R.drawable.fs8);
        else if (value.compareTo("fs9")==0)
            rl1.setBackgroundResource(R.drawable.fs9);


       /////////////for love



        else if (value.compareTo("l1")==0)
            rl1.setBackgroundResource(R.drawable.l1);
        else if (value.compareTo("l2")==0)
            rl1.setBackgroundResource(R.drawable.l2);
        else if (value.compareTo("l3")==0)
            rl1.setBackgroundResource(R.drawable.l3);
        else if (value.compareTo("l6")==0)
            rl1.setBackgroundResource(R.drawable.l6);
        else if (value.compareTo("l4")==0)
            rl1.setBackgroundResource(R.drawable.l4);
        else if (value.compareTo("l8")==0)
            rl1.setBackgroundResource(R.drawable.l8);
        else if (value.compareTo("l9")==0)
            rl1.setBackgroundResource(R.drawable.l9);
        else if (value.compareTo("l10")==0)
            rl1.setBackgroundResource(R.drawable.l10);
        else if (value.compareTo("l11")==0)
            rl1.setBackgroundResource(R.drawable.l11);
        else if (value.compareTo("l12")==0)
            rl1.setBackgroundResource(R.drawable.l12);
        else if (value.compareTo("l13")==0)
            rl1.setBackgroundResource(R.drawable.l13);
        else if (value.compareTo("l14")==0)
            rl1.setBackgroundResource(R.drawable.l14);


        ///////////for others:

        else if (value.compareTo("o1")==0)
            rl1.setBackgroundResource(R.drawable.o1);
        else if (value.compareTo("o2")==0)
            rl1.setBackgroundResource(R.drawable.o2);
        else if (value.compareTo("o3")==0)
            rl1.setBackgroundResource(R.drawable.o3);
        else if (value.compareTo("o4")==0)
            rl1.setBackgroundResource(R.drawable.o4);
        else if (value.compareTo("o6")==0)
            rl1.setBackgroundResource(R.drawable.o6);
        else if (value.compareTo("o7")==0)
            rl1.setBackgroundResource(R.drawable.o7);
        else if (value.compareTo("o8")==0)
            rl1.setBackgroundResource(R.drawable.o8);
        else if (value.compareTo("o9")==0)
            rl1.setBackgroundResource(R.drawable.o9);

        //////////for valentine


        else if (value.compareTo("v1")==0)
            rl1.setBackgroundResource(R.drawable.v1);
        else if (value.compareTo("v2")==0)
            rl1.setBackgroundResource(R.drawable.v2);
        else if (value.compareTo("v3")==0)
            rl1.setBackgroundResource(R.drawable.v3);
        else if (value.compareTo("v4")==0)
            rl1.setBackgroundResource(R.drawable.v4);
        else if (value.compareTo("v5")==0)
            rl1.setBackgroundResource(R.drawable.v5);
        else if (value.compareTo("v6")==0)
            rl1.setBackgroundResource(R.drawable.v6);
        else if (value.compareTo("v7")==0)
            rl1.setBackgroundResource(R.drawable.v7);
        else if (value.compareTo("v8")==0)
            rl1.setBackgroundResource(R.drawable.v8);
        else if (value.compareTo("v9")==0)
            rl1.setBackgroundResource(R.drawable.v9);


        ////////////for party

        else if (value.compareTo("p1")==0)
            rl1.setBackgroundResource(R.drawable.p1);
        else if (value.compareTo("p2")==0)
            rl1.setBackgroundResource(R.drawable.p2);
        else if (value.compareTo("p4")==0)
            rl1.setBackgroundResource(R.drawable.p4);
        else if (value.compareTo("p5")==0)
            rl1.setBackgroundResource(R.drawable.p5);
        else if (value.compareTo("p6")==0)
            rl1.setBackgroundResource(R.drawable.p6);
        else if (value.compareTo("p8")==0)
            rl1.setBackgroundResource(R.drawable.p8);
        else if (value.compareTo("p9")==0)
            rl1.setBackgroundResource(R.drawable.p9);
        initEvent();

        rl1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mRecyclerView.setVisibility(View.GONE);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rl1.getLayoutParams();
                params.height = MATCH_PARENT;
                rl1.setLayoutParams(params);

                return true;
            }
        });
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
       // TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationIcon(R.drawable.btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Toast.makeText(getApplicationContext()," working",Toast.LENGTH_SHORT).show();
            }
        });
      //  mToolBarTextView.setText("Abhinav");
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                Intent myIntent = new Intent(Edit.this, FinalImageShare.class);
                myIntent.putExtra("key", value); //Optional parameters
                Edit.this.startActivity(myIntent);
//                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
//                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
//                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            finish();
        }

        if (circleMenu.isOpened())
            circleMenu.closeMenu();
        else
            finish();
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }



    public void doSetting(View view ){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Drawable d = new BitmapDrawable(getResources(), bitmap);


                rl1.setBackground(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (requestCode == 11 && resultCode == RESULT_OK && data != null){

           //  Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Drawable d = new BitmapDrawable(getResources(), photo);
            rl1.setBackground(d);

        }
    }

    private void initEvent() {
        mGalleryAdapter.setOnItemClickListener(new GalleryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int resId) {
                addStickerItem(resId);

            }
        });
    }

    private void addStickerItem(int resId) {
        resetStickersFocus();
        StickerView stickerView = new StickerView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.rel1);
        params.addRule(RelativeLayout.ALIGN_TOP, R.id.rel1);
        ((ViewGroup) rl1.getParent()).addView(stickerView, params);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        stickerView.setWaterMark(bitmap);
        mStickers.add(stickerView);
        stickerView.setOnStickerDeleteListener(new StickerView.OnStickerDeleteListener() {
            @Override
            public void onDelete(StickerView stickerView) {
                if (mStickers.contains(stickerView))
                    mStickers.remove(stickerView);
            }
        });

        stickerView.setOnStickerDoneListener(new StickerView.OnStickerDoneListener() {
            @Override
            public void onDone(StickerView stickerView) {
                if (mStickers.contains(stickerView))
                    mStickers.add(stickerView);
            }

        });
    }
    private void resetStickersFocus() {
        for (StickerView stickerView : mStickers) {
            stickerView.setFocusable(false);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) ev.getX();
            //calculate action point Y apart from Container layout origin
            int y = (int) ev.getY() - mStatusBarHeight - mToolBarHeight;
            for (StickerView stickerView : mStickers) {
                // dispatch focus to the sticker based on Coordinate
                boolean isContains = stickerView.getContentRect().contains(x, y);
                if (isContains) {
                    resetStickersFocus();
                    stickerView.setFocusable(true);
                }

            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
    private void initView() {
        mStatusBarHeight = getStatusBarHeight();
        mToolBarHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mGalleryAdapter = new GalleryAdapter(mResIds);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGalleryAdapter);
    }


    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText( text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builders=new AlertDialog.Builder(Edit.this);
               ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Edit.this,android.R.layout.simple_list_item_single_choice);
                arrayAdapter.add("Edit");
                arrayAdapter.add("Change Color");
                arrayAdapter.add("Change Font");
                arrayAdapter.add("Delete ");


                builders.setTitle("Set Text");
                builders.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==1){}
                            else if(i==2){




                        }
                        Toast.makeText(getApplicationContext(),""+i,Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDalogs=builders.create();
                alertDalogs.show();
            }
        });
        return textView;
    }


}



