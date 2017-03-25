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
public class FatherMothers extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";

    public static final String FATHERMOTHERS = "Fathermothers";

    private View containerView;

    protected int res;
    private Bitmap bitmap;
    private ImageView fm2,fm3,fm4,fm5,fm7,fm8,father1,fm10,fm11,fm13;

    public static FatherMothers newInstance() {
        FatherMothers contentFragment = new FatherMothers();

        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);


        fm2=(ImageView)view.findViewById(R.id.fm2);
        fm3=(ImageView)view.findViewById(R.id.fm3);
        fm4=(ImageView)view.findViewById(R.id.fm4);
        fm5=(ImageView)view.findViewById(R.id.fm5);
        fm7=(ImageView)view.findViewById(R.id.fm7);
        fm8=(ImageView)view.findViewById(R.id.fm8);
        fm10=(ImageView)view.findViewById(R.id.fm10);
        father1=(ImageView)view.findViewById(R.id.father1);
        fm11=(ImageView)view.findViewById(R.id.fm11);
        fm13=(ImageView)view.findViewById(R.id.fm13);


        fm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fm2");
                startActivity(intent);

            }
        });
        fm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fm3");
                startActivity(intent);

            }
        });
        fm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fm4");
                startActivity(intent);

            }
        });
        fm5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fm5");
                startActivity(intent);

            }
        });
        fm7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fm7");
                startActivity(intent);

            }
        });
        fm8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fm8");
                startActivity(intent);

            }
        });
        fm10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fm10");
                startActivity(intent);

            }
        });
        father1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","father1");
                startActivity(intent);

            }
        });
        fm11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fm11");
                startActivity(intent);

            }
        });
        fm13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fm13");
                startActivity(intent);

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fathermothers, container, false);
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
                FatherMothers.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

