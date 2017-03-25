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
public class Engagement extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String ENGAGEMENT = "Engagement";
    private View containerView;

    protected int res;
    private Bitmap bitmap;


    private ImageView e1,e2,e4,e5,e6,e7,e8,e9,e11,e12;

    public static Engagement newInstance() {
        Engagement contentFragment = new Engagement();

        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);

        e1=(ImageView)view.findViewById(R.id.e1);
        e2=(ImageView)view.findViewById(R.id.e2);
        e4=(ImageView)view.findViewById(R.id.e4);
        e5=(ImageView)view.findViewById(R.id.e5);
        e6=(ImageView)view.findViewById(R.id.e6);
        e7=(ImageView)view.findViewById(R.id.e7);
        e8=(ImageView)view.findViewById(R.id.e8);
        e9=(ImageView)view.findViewById(R.id.e9);
        e11=(ImageView)view.findViewById(R.id.e11);
        e12=(ImageView)view.findViewById(R.id.e12);


        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","e1");
                startActivity(intent);

            }
        });

        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","e2");
                startActivity(intent);

            }
        });

        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","e4");
                startActivity(intent);

            }
        });

        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","e5");
                startActivity(intent);

            }
        });
        e6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","e6");
                startActivity(intent);

            }
        });
        e7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","e7");
                startActivity(intent);

            }
        });
        e8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","e8");
                startActivity(intent);

            }
        });

        e9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","e9");
                startActivity(intent);

            }
        });
        e11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","e11");
                startActivity(intent);

            }
        });
        e12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","e12");
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
        View rootView = inflater.inflate(R.layout.engagement, container, false);

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
                Engagement.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

