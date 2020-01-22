package mandol.gamification.reward;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AchievementListAdapter extends ArrayAdapter<Achievement> {
    Context context;
    int layoutResourceId;
    private int lastPosition = -1;
    private RoundImage roundedImage;
    ArrayList<Achievement> data = new ArrayList<Achievement>();

    public AchievementListAdapter(Context context, int layoutResourceId,
                             ArrayList<Achievement> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        AchievementListAdapter.ImageHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new AchievementListAdapter.ImageHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.imgIcon = (ImageView) row.findViewById(R.id.imgIcon);
            holder.txtTitleDesc = (TextView) row.findViewById(R.id.txtTitleDesc);
            holder.txtTier = (TextView) row.findViewById(R.id.txtTier);

            Typeface font = Typeface.createFromAsset(context.getAssets(),
                    "fonts/Roboto-Light.ttf");
            holder.txtTitle.setTypeface(font);
            holder.txtTitle.setTextSize(16);
            holder.txtTitleDesc.setTypeface(font);
            holder.txtTitleDesc.setTextSize(16);
            holder.txtTier.setTypeface(font);
            holder.txtTier.setTextSize(14);
            row.setTag(holder);
        } else {
            holder = (AchievementListAdapter.ImageHolder) row.getTag();
        }

        Animation animation = AnimationUtils.loadAnimation(getContext(),
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        row.startAnimation(animation);
        lastPosition = position;

        Achievement picture = data.get(position);
        holder.txtTitle.setText(picture.getTitle());
        holder.txtTitleDesc.setText(picture.getTitleDesc());
//        holder.txtCategory.setText("  " + picture.getCategory() + "  ");

        boolean isExist = false;
        AssetManager assetManager = context.getAssets();
        InputStream imageStream = null;
//        try {
//            imageStream = assetManager.open("authors/"+picture.getFileName()+".jpg");
//
//            isExist =true;
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        if (isExist != false){
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            roundedImage = new RoundImage(theImage);
            holder.imgIcon.setImageDrawable(roundedImage );
        }
        else {
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(),R.mipmap.author);
            roundedImage = new RoundImage(bm);
            holder.imgIcon.setImageDrawable(roundedImage);
        }
        return row;
    }

    static class ImageHolder {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtTitleDesc;
        TextView txtTier;
    }

}
