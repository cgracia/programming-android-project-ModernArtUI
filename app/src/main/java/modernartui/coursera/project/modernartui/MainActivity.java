package modernartui.coursera.project.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static private final String URL = "http://www.moma.org";

    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private SeekBar mSeekBar;

    private DialogFragment mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views. Number two is grey and is never modified.
        mTextView1 = (TextView) findViewById(R.id.textView1);
        mTextView2 = (TextView) findViewById(R.id.textView3);
        mTextView3 = (TextView) findViewById(R.id.textView4);
        mTextView4 = (TextView) findViewById(R.id.textView5);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        // Attach listener to seekbar
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // When the progress changes this function is called, it changes all the colors
                setColors(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {// Create a new AlertDialogFragment
            createAlertDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setColors(int progress) {
        // Change the colors of the text views when progress changes
        // The colors have been chosen more or less randomly

        int red = 255 * (100 - progress) / 100;
        int green = 255 * progress / 100;
        int blue = 0;
        mTextView1.setBackgroundColor(Color.rgb(red, green, blue));

        red = 100 * progress / 100;
        green = 100 * progress / 100;
        blue = 255 * (100 - progress) / 100;
        mTextView2.setBackgroundColor(Color.rgb(red, green, blue));

        red = 255 * (100 - progress) / 100;
        green = 255 * progress / 100;
        blue = 255;
        mTextView3.setBackgroundColor(Color.rgb(red, green, blue));

        red = 255 * (100 - progress) / 100;
        green = 255 * (100 - progress) / 100;
        blue = 255 * progress / 100;
        mTextView4.setBackgroundColor(Color.rgb(red, green, blue));
    }

    private void createAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Inspired by the need to pass the course project.\nBut click below if you want to learn more!");

        builder.setCancelable(true);

        builder.setPositiveButton("Visit MOMA",
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            final DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                        startActivity(intent);
                    }
                });

        builder.setNegativeButton("Not now",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();

    }
}
