package mandol.gamification.reward;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RewardsActivityCopy extends AppCompatActivity {

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
                Intent intent = new Intent(RewardsActivityCopy.this,
                        QuotesActivity.class);
                intent.putExtra("mode", "allQuotes");
                startActivity(intent);
            }
        });

        btn_authors.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent author = new Intent(RewardsActivityCopy.this,
                        AuteursActivity.class);
                startActivity(author);
            }
        });

        btn_favorites.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent favorites = new Intent(RewardsActivityCopy.this,
                        QuotesActivity.class);
                favorites.putExtra("mode", "isFavorite");
                startActivity(favorites);
            }
        });

        btn_categories.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent category = new Intent(RewardsActivityCopy.this,
                        CategoryActivity.class);
                startActivity(category);
            }
        });

        btn_qteday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                preferences = PreferenceManager
                        .getDefaultSharedPreferences(context);

                Intent qteDay = new Intent(RewardsActivityCopy.this,
                        QuoteActivity.class);
                qteDay.putExtra("id",
                        preferences.getInt("id", IntialQteOfDayId));
                qteDay.putExtra("mode", "qteday");
                startActivity(qteDay);
            }
        });

        btn_rateus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        RewardsActivityCopy.this);
                builder.setMessage(getResources().getString(
                        R.string.ratethisapp_msg));
                builder.setTitle(getResources().getString(
                        R.string.ratethisapp_title));
                builder.setPositiveButton(
                        getResources().getString(R.string.rate_it),
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                Intent fire = new Intent(
                                        Intent.ACTION_VIEW,
                                        //Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName()));           //"));
                                        Uri.parse("https://masamalas.com"));
                                startActivity(fire);

                            }
                        });

                builder.setNegativeButton(
                        getResources().getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();

                            }
                        });

            }
        });
    }
}
