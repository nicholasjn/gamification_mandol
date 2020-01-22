package mandol.gamification.reward;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mandol.gamification.reward.Misi;
import mandol.gamification.reward.MisiListAdapter;

public class MissionActivity extends AppCompatActivity {

    private ArrayList<Misi> imageArry = new ArrayList<Misi>();
    private MisiListAdapter adapter;
    private String Activitytype;
    private DataBaseHandler db;
    private ListView dataList;
    private int count;
    private ImageView noQuotes;
    private Button btnClaim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getText(R.string.title_activity_mission));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        db = new DataBaseHandler(this);
        noQuotes = (ImageView)findViewById(R.id.NoQuotes);
        adapter = new MisiListAdapter(this, R.layout.misi_items, imageArry);
        dataList = (ListView) findViewById(R.id.misiList);
        Button btnLoadMore = new Button(this);

        btnLoadMore.setBackgroundResource(R.drawable.btn_green);
        btnLoadMore.setText(getResources().getText(R.string.btn_LoadMore));
        btnLoadMore.setTextColor(0xffffffff);
        Activitytype = getIntent().getExtras().getString("mode");

        List<Misi> quest = db.getMission(" LIMIT 50");
        for (Misi q : quest) {

            imageArry.add(q);

        }
        ;
        dataList.addFooterView(btnLoadMore);

        dataList.setAdapter(adapter);
        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long idInDB) {

                Intent i = new Intent(getApplicationContext(),
                        MisiActivity.class);
                Misi srr = imageArry.get(position);
                i.putExtra("id",position);
                i.putExtra("array", imageArry);
                i.putExtra("mode", "");

                startActivity(i);

            }

        });

        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Starting a new async task
                new loadMoreListView().execute();
            }
        });

    }
    private class loadMoreListView extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // Before starting background task
            // Show Progress Dialog etc,.
        }

        protected Void doInBackground(Void... unused) {
            runOnUiThread(new Runnable() {
                public void run() {
                    count += 50;
                    List<Misi> contacts = db.getMission(" LIMIT " + count + ",50");
                    for (Misi cn : contacts) {

                        imageArry.add(cn);

                    }
                    int currentPosition = dataList.getFirstVisiblePosition();
                    adapter = new MisiListAdapter(MissionActivity.this, R.layout.misi_items, imageArry);
                    dataList.setSelectionFromTop(currentPosition + 1, 0);
                }

            });
            return (null);
        }

        protected void onPostExecute(Void unused) {

        }
    }
    @Override
    public void onBackPressed()
    {
        finish();
        super.onBackPressed();  // optional depending on your needs
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quotes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
}

