package mandol.gamification.reward;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AccountTypeListAdapter extends RecyclerView.Adapter<AccountTypeListAdapter.ViewHolder> {
    private String[] mDataset;
    private LayoutInflater mInflater;
    private ArrayList<String> mData;

    public AccountTypeListAdapter(Context context, ArrayList<String> data){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AccountTypeListAdapter(Context context, String[] myDataset) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AccountTypeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.account_type_item, parent, false);
        return new ViewHolder(view);
        /*
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_type_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
        */
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.cv_title.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cv;
        public TextView cv_title;
        public View account;

        public ViewHolder(View v) {
            super(v);
            cv = v.findViewById(R.id.cv_account);
            cv_title = v.findViewById(R.id.cv_title);
        }
    }
}
