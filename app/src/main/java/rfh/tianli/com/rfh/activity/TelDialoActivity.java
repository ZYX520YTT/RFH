package rfh.tianli.com.rfh.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import rfh.tianli.com.rfh.R;

public class TelDialoActivity extends Activity {

    @ViewInject(R.id.btn_cancel)
    private Button btn_cancel;
    @ViewInject(R.id.btn_confrim)
    private Button btn_confrim;
    @ViewInject(R.id.tv_content)
    private TextView tv_content;
    @ViewInject(R.id.tv_info)
    private TextView tv_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel_dialo);
        ViewUtils.inject(this);
        Init();
    }

    private void Init() {
        tv_info.setVisibility(View.VISIBLE);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:4001178877"));
                if (ActivityCompat.checkSelfPermission(TelDialoActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                TelDialoActivity.this.startActivity(intent);
                finish();
            }
        });
    }
}
