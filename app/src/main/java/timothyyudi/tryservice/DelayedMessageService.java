package timothyyudi.tryservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

public class DelayedMessageService extends IntentService {

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        showText("Doing something");
    }

    static final public String DELAYEDMESSAGESERVICE_ACTION = "timothyyudi.tryservice.DELAYEDMESSAGESERVICE_ACTION";

    private void showText(final String text) {
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

    private Handler handler;
    LocalBroadcastManager broadcaster;

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        broadcaster = LocalBroadcastManager.getInstance(this);
    }

}
