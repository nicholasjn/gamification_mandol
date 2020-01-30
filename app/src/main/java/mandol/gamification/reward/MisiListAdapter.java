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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MisiListAdapter extends ArrayAdapter<Misi> {
    Context context;
    int layoutResourceId;
    private int lastPosition = -1;
    private RoundImage roundedImage;
    ArrayList<Misi> data = new ArrayList<Misi>();
    private DataBaseHandler db;

    public MisiListAdapter(Context context, int layoutResourceId,
                             ArrayList<Misi> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        db = new DataBaseHandler(context);

        MisiListAdapter.ImageHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new MisiListAdapter.ImageHolder();
            holder.txtTipe = (TextView) row.findViewById(R.id.txtTipe);
            holder.imgIconMisi = (ImageView) row.findViewById(R.id.imgIconMisi);
            holder.txtDesc = (TextView) row.findViewById(R.id.txtDesc);
            holder.txtFP = (TextView) row.findViewById(R.id.txtFP);
            holder.claim = (Button) row.findViewById(R.id.btn_claim);

            Typeface font = Typeface.createFromAsset(context.getAssets(),
                    "fonts/Roboto-Light.ttf");
            holder.txtTipe.setTypeface(font);
            holder.txtTipe.setTextSize(16);
            holder.txtDesc.setTypeface(font);
            holder.txtDesc.setTextSize(16);
            holder.txtFP.setTypeface(font);
            holder.txtFP.setTextSize(14);
            row.setTag(holder);
        } else {
            holder = (MisiListAdapter.ImageHolder) row.getTag();
        }

        Animation animation = AnimationUtils.loadAnimation(getContext(),
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        row.startAnimation(animation);
        lastPosition = position;

        Misi picture = data.get(position);
        holder.txtTipe.setText(picture.getTipe());
        holder.txtDesc.setText(picture.getDesc());
        String reward = "  " + picture.getFP() + " Fiesta Poin";
        holder.txtFP.setText(reward);

        if(!holder.txtTipe.getText().equals("harian")){
            holder.claim.setVisibility(View.GONE);

        }

        holder.claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence msg = "Reward telah di klaim";
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                db.updatePoint(1,1, db.getPoints(1));
            }
        });

        boolean isExist = false;
        AssetManager assetManager = context.getAssets();
        InputStream imageStream = null;
//        try {
//            imageStream = assetManager.open("authors/"+picture.getFileName()+".jpg");
//
//            iImageButtonsExist =true;
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        if (isExist != false){
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            roundedImage = new RoundImage(theImage);
            holder.imgIconMisi.setImageDrawable(roundedImage );
        }
        else {
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(),R.mipmap.author);
            roundedImage = new RoundImage(bm);
            holder.imgIconMisi.setImageDrawable(roundedImage);
        }
        return row;
    }

    static class ImageHolder {
        ImageView imgIconMisi;
        TextView txtTipe;
        TextView txtDesc;
        TextView txtFP;
        Button claim;

    }
}
