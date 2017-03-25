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
public class Friendship extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";

    public static final String FRIENDSHIP = "Friendship";

    private View containerView;
    protected ImageView mImageView;

    private Bitmap bitmap;


    private ImageView friendship,fs1,fs2,fs3,fs4,fs5,fs6,fs7,fs8,fs9;

    public static Friendship newInstance() {
        Friendship contentFragment = new Friendship();
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);

        friendship=(ImageView)view.findViewById(R.id.friendship);
        fs1=(ImageView)view.findViewById(R.id.fs1);
        fs2=(ImageView)view.findViewById(R.id.fs2);
        fs3=(ImageView)view.findViewById(R.id.fs3);
        fs4=(ImageView)view.findViewById(R.id.fs4);
        fs5=(ImageView)view.findViewById(R.id.fs5);
        fs6=(ImageView)view.findViewById(R.id.fs6);
        fs7=(ImageView)view.findViewById(R.id.fs7);
        fs8=(ImageView)view.findViewById(R.id.fs8);
        fs9=(ImageView)view.findViewById(R.id.fs9);


        friendship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","friendship");
                startActivity(intent);

            }
        });
        fs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fs1");
                startActivity(intent);

            }
        });
        fs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fs2");
                startActivity(intent);

            }
        });
        fs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fs3");
                startActivity(intent);

            }
        });
        fs4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fs4");
                startActivity(intent);

            }
        });
        fs5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fs5");
                startActivity(intent);

            }
        });
        fs6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("imageid","fs6");
                startActivity(intent);

            }
        });
        fs7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("f","fs7");
                startActivity(intent);

            }
        });
        fs8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("f","fs8");
                startActivity(intent);

            }
        });
        fs9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Edit.class);
                intent.putExtra("f","fs9");
                startActivity(intent);

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.friendship, container, false);
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
                Friendship.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

