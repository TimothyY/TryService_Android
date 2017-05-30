package timothyyudi.tryservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnTriggerService;
    Context mCtx;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCtx = this;
        btnTriggerService = (Button) findViewById(R.id.btnTriggerService);
        btnTriggerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for delayed message service
                Intent intent = new Intent(mCtx, DelayedMessageService.class);
                startService(intent);
                btnTriggerService.setText("Service is now doing something");
            }
        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                btnTriggerService.setText("Service is done doing something, retry?");
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver((broadcastReceiver),
                new IntentFilter(DelayedMessageService.DELAYEDMESSAGESERVICE_ACTION)
        );
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

}
