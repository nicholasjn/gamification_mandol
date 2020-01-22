package mandol.gamification.reward;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class RewardsActivity extends AppCompatActivity {

    DataBaseHandler db;
    public static final int IntialQteOfDayId = 8;
    final Context context = this;
    SharedPreferences preferences;
    private static final int RESULT_SETTINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btn_quotes, btn_authors, btn_favorites, btn_categories, btn_qteday, btn_rateus ;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getText(R.string.title_activity_rewards));
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue_mandol)));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.blue_mandol));
        }

        db = new DataBaseHandler(this);
        db.openDataBase() ;

        btn_quotes= (Button) findViewById(R.id.btn_quotes);
        btn_authors= (Button) findViewById(R.id.btn_authors);
        btn_favorites= (Button) findViewById(R.id.btn_favorites);
        btn_categories= (Button) findViewById(R.id.btn_categories);
        btn_qteday= (Button) findViewById(R.id.btn_qteday);
        btn_rateus= (Button) findViewById(R.id.btn_rateus);

        btn_quotes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RewardsActivity.this,
                        QuotesActivity.class);
                intent.putExtra("mode", "allQuotes");
                startActivity(intent);
            }
        });

        btn_authors.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent author = new Intent(RewardsActivity.this,
                        AuteursActivity.class);
                startActivity(author);
            }
        });

        btn_favorites.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent favorites = new Intent(RewardsActivity.this,
                        QuotesActivity.class);
                favorites.putExtra("mode", "isFavorite");
                startActivity(favorites);
            }
        });

        btn_categories.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent category = new Intent(RewardsActivity.this,
                        CategoryActivity.class);
                startActivity(category);
            }
        });

        btn_qteday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RewardsActivity.this,
                        QuotesActivity.class);
                intent.putExtra("mode", "allRedeem");
                startActivity(intent);
            }
        });

        btn_rateus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent achievement = new Intent(RewardsActivity.this,
                        AchievementActivity.class);
                startActivity(achievement);
            }
        });
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
