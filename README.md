# 实验四-Intent

实验准备：

创建两个新的Project，分别命名为MyWebView和IntentProject。

## 一、自定义一个浏览器MyWebView

1、在布局文件activity_main.xml中定义一个webView

```
<WebView
        android:id="@+id/webview"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

2、在MainActivity.java中：

```
webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        //得到鼠标点击之后的intent
        Intent intent = getIntent();
        Uri uri = intent.getData();//获取data属性
        String urlString = null;
        try {
            urlString = new URL(uri.getScheme(), uri.getHost(), uri.getPath()).toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        webView.loadUrl(urlString);//加载
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return false;
                }
                view.loadUrl(url);
                return true;
            }
        });
```

3、测试截图

<img src="https://i.loli.net/2020/11/30/rsGFx8iHyOVb5zN.jpg" width="200" height="400"/>

## 二、通过IntentProject工程调用浏览器

1、布局文件

```
<EditText
        android:id="@+id/editText"
        android:layout_width="300dp"
        android:layout_height="60dp"
        android:layout_marginTop="20dp"
        android:layout_gravity="center"
        android:hint="@string/editText"
        />

    <Button
        android:id="@+id/btn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:layout_gravity="center"
        android:text="@string/btn"
        tools:ignore="MissingConstraints" />
```

2、MainActivity.java

```
public class MainActivity extends AppCompatActivity {

    private EditText urlEditText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.button = findViewById(R.id.btn);//按钮
        this.urlEditText = findViewById(R.id.editText);//文本框
        button.setOnClickListener(new View.OnClickListener() {//点击事件
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();//获取url
                Intent intent = new Intent(Intent.ACTION_VIEW);//为Intent设置Action
                intent.setData(Uri.parse(url));
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory("my_category");
                startActivity(intent);
            }
        });
    }
}

```

3、AndroidManifest.xml文件：

```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    package="com.example.intentproject">
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:usesCleartextTraffic="true"
        android:theme="@style/AppTheme"
        tools:ignore="GoogleAppIndexingWarning">

        <activity android:name=".MyWebView">
            <intent-filter>
                <action android:name="android.intent.action.VIEW"/>
                <category android:name="my_category"/>
                <category android:name="android.intent.category.DEFAULT"/>
            </intent-filter>
        </activity>

        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

    </application>

</manifest>
```

4、实验效果截图

<br/>

<img src="https://i.loli.net/2020/11/30/6jSH5vnxkLzu8KV.jpg" width="200" height="400"/>
