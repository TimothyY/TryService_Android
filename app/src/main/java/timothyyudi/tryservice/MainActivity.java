package timothyyudi.tryservice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnTriggerService;
    Context mCtx;

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
                intent.putExtra(DelayedMessageService.EXTRA_MESSAGE,"Doing something");
                startService(intent);

            }
        });
    }
}
