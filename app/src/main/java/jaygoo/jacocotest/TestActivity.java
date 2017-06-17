package jaygoo.jacocotest;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import jaygoo.jacocohelper.JacocoHelper;




/**
 * 手动测试代码覆盖率
 * Useage:
 *
 * 1、可以直接编译运行或者安装应用
 * 2、./gradlew jacocoInit 初始化
 * 3、开始测试，测试结束后点击生成报告将ec文件pull到本地项目 PROJECT_PATH 路径中，具体命令可查看日志Loge<Pull>
 * PROJECT_PATH '项目路径/app/build/' + 'outputs/code-coverage/'
 * 4、./gradlew jacocoTestReport 然后到 /app/build/reports里查看相关报告
 */
public class TestActivity extends AppCompatActivity {

    //请修改为自己的项目路径，后面的文件夹如果没有可以通过./gradlew jacocoInit创建
    private static String PROJECT_PATH = "/Users/mac/Github/JacocoTestHelper/app/build/outputs/code-coverage/";
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        JacocoHelper.init(PROJECT_PATH,true);
        Dexter.withActivity(this).withPermissions(PERMISSIONS)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()){
                            Toast.makeText(getApplication(),"权限获取成功！",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                }).check();


        findViewById(R.id.function1_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function1();
            }
        });

        findViewById(R.id.function2_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function2();
            }
        });

        findViewById(R.id.function3_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function3();
            }
        });

        findViewById(R.id.function4_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function4();
            }
        });


        findViewById(R.id.report_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.adb_pull_tv)).setText(JacocoHelper.getAdbPullCmd());
                JacocoHelper.generateEcFile(true);
            }
        });
    }

    private void function1(){
        Toast.makeText(getApplication(),"方法一",Toast.LENGTH_SHORT).show();
    }

    private void function2(){
        Toast.makeText(getApplication(),"方法二",Toast.LENGTH_SHORT).show();
    }

    private void function3(){
        Toast.makeText(getApplication(),"方法三",Toast.LENGTH_SHORT).show();
    }

    private void function4() {
        Toast.makeText(getApplication(),"方法四",Toast.LENGTH_SHORT).show();
    }
}
