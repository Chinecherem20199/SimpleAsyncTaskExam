package nigeriandailies.com.ng.simpleasynctaskexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private static final String TEXT_STATE = "currentText";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);

        // Restore TextView if there is a savedInstanceState
        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));

        }
    }

    public void startTask(View view) {
        // Put a message in the text view
        mTextView.setText(R.string.napping);

        // Start the AsyncTask.
        new SimpleAsyncTask(mTextView).execute();

    }
    public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {
        private WeakReference<TextView> mTextView;

        SimpleAsyncTask(TextView tv) {
            mTextView = new WeakReference<>(tv);
        }
        @Override
        protected String doInBackground(Void... voids) {
          // Generate a random number between 0 and 10
            Random r = new Random();
            int n = r.nextInt(11);

            // Make the task take long enough that we have
            // time to rotate the phone while it is running
            int s = n * 200;

            // Sleep for the random amount of time
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a String result
            return "Awake at last after sleeping for " + s + " milliseconds!";

        }
        protected void onPostExecute(String result) {
            mTextView.get().setText(result);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE,
                mTextView.getText().toString());
    }


}