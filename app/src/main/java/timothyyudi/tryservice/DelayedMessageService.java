package timothyyudi.tryservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

public class DelayedMessageService extends IntentService {

    static final public String DELAYEDMESSAGESERVICE_ACTION = "timothyyudi.tryservice.DELAYEDMESSAGESERVICE_ACTION";

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    private Handler handler;
    LocalBroadcastManager broadcaster;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        broadcaster = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Doing something", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Toast.makeText(this.getApplicationContext(), "Doing something", Toast.LENGTH_SHORT).show();
        showDelayedText("Done");
    }

    private void showDelayedText(final String text) {
        synchronized (this) {
            try {
                wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                broadcaster.sendBroadcast(new Intent(DELAYEDMESSAGESERVICE_ACTION));
            }
        });
    }

}
