package yalantis.com.sidemenu.sample.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.sample.Edit;
import yalantis.com.sidemenu.sample.R;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class Party extends Fragment implements ScreenShotable {


    public static final String PARTY = "Party";

    private ImageView p1,p2,p4,p5,p6,p8,p9;

    private View containerView;
    protected ImageView mImageView;
   // protected int res;
    private Bitmap bitmap;

    public static Party newInstance() {
        Party contentFragment = new Party();
//        Bundle bundle = new Bundle();
//        bundle.putInt(Integer.class.getName(), resId);
//        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
        p1=(ImageView)view.findViewById(R.id.p1);
        p2=(ImageView)view.findViewById(R.id.p2);
        p4=(ImageView)view.findViewById(R.id.p4);
        p5=(ImageView)view.findViewById(R.id.p5);
        p6=(ImageView)view.findViewById(R.id.p6);
        p8=(ImageView)view.findViewById(R.id.p8);
        p9=(ImageView)view.findViewById(R.id.p9);


        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","p1");
                startActivity(intent);

            }
        });
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","p2");
                startActivity(intent);

            }
        });
        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","p4");
                startActivity(intent);

            }
        });
        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","p5");
                startActivity(intent);

            }
        });
        p6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","p6");
                startActivity(intent);

            }
        });
        p8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","p8");
                startActivity(intent);

            }
        });
        p9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","p9");
                startActivity(intent);

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.party, container, false);
//        mImageView = (ImageView) rootView.findViewById(R.id.image_content);
//        mImageView.setClickable(true);
//        mImageView.setFocusable(true);
//        mImageView.setImageResource(res);
        return rootView;
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                Party.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

