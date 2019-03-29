package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.security.Provider;

public class PracticalTest01Var05Service extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Intent intent1 = new Intent();
        intent1.putExtra("message", "Victory");
        intent1.setAction("my.action");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getApplicationContext().sendBroadcast(intent1);

        return Service.START_REDELIVER_INTENT;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
