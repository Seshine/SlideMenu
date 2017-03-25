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
public class Valentines extends Fragment implements ScreenShotable {
    public static final String VALENTINES = "Valentines";
    private View containerView;
    protected ImageView mImageView;
    private Bitmap bitmap;
    private ImageView v1,v2,v3,v4,v5,v6,v7,v8,v9;

    public static Valentines newInstance() {
        Valentines contentFragment = new Valentines();

        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
        v1=(ImageView)view.findViewById(R.id.v1);
        v2=(ImageView)view.findViewById(R.id.v2);
        v3=(ImageView)view.findViewById(R.id.v3);
        v4=(ImageView)view.findViewById(R.id.v4);
        v5=(ImageView)view.findViewById(R.id.v5);
        v6=(ImageView)view.findViewById(R.id.v6);
        v7=(ImageView)view.findViewById(R.id.v7);
        v8=(ImageView)view.findViewById(R.id.v8);
        v9=(ImageView)view.findViewById(R.id.v9);


        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","v1");
                startActivity(intent);

                    }
                });

        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","v2");
                startActivity(intent);

            }
        });

        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","v3");
                startActivity(intent);

            }
        });

        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","v4");
                startActivity(intent);

            }
        });

        v5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","v5");
                startActivity(intent);

            }
        });

        v6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","v6");
                startActivity(intent);

            }
        });

        v7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","v7");
                startActivity(intent);

            }
        });

        v8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","v8");
                startActivity(intent);

            }
        });

        v9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","v9");
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
        View rootView = inflater.inflate(R.layout.valentines, container, false);

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
                Valentines.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

