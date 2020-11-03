## 实验四

实验内容：自定义WebView验证隐式Intent的使用

实验步骤：

1、第一个项目 exp04

MainActivity.java 获取输入、设置点击事件

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);

        final EditText editText = findViewById(R.id.urltext);
        final String[] url = {""};

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                url[0] = editText.getText().toString();
            }
        });


        //绑定点击事件
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Uri uri = Uri.parse(url[0]);
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(uri);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"该界面不存在",Toast.LENGTH_SHORT);
                }

            }
        });
    }
}
```

intent.setAction 设置系统操作，Intent.ACTION_VIEW 为 浏览

intent.setData 设置data，uri 为 输入的网址

最后启动 activity ：startActivity(intent)

布局界面 activity_main.xml

```java
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/urltext"
        android:layout_width="286dp"
        android:layout_height="43dp"
        android:ems="10"
        android:hint="@string/url_txt"
        android:inputType="textPersonName"
        android:textSize="18sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.496"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.063" />

    <Button
        android:id="@+id/btn"
        android:layout_width="135dp"
        android:layout_height="43dp"
        android:text="@string/btn"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.21" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

2、第二个项目 WebView

MainActivity.java 设置 输出页面

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView)findViewById(R.id.mywebview);

        webView.getSettings().getJavaScriptEnabled();
        String url = getIntent().getData().toString();



//        webView.loadUrl("http://www.baidu.com");

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

    }
}
```

获取 url = getIntent().getData().toString()

使用 webView.loadUrl(url) 方法加载页面

AndroidManifest.xml 设置 可作为第三方浏览器

```java
 <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"></action>
                <category android:name="android.intent.category.LAUNCHER"></category>

                <action android:name="android.intent.action.VIEW"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <category android:name="android.intent.category.BROWSABLE"></category>
                <data android:scheme="http"></data>
                <data android:scheme="https"></data>
            </intent-filter>
        </activity>
```

第一组 action android:name 与 category android:name 设置为 MAIN 与 LAUNCHER，安装到模拟机上

第二组 action android:name 与 category android:name  设置为VIEW 与 DEFAULT、BROWSABLE，设置为 第三方浏览器可供系统选择

data scheme作为匹配规则，匹配以http或https开头的uri

设置使用网络权限
```java
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```

运行结果：

![image](https://github.com/vency799/experiment_04/blob/master/exp04_page.png)

![image](https://github.com/vency799/experiment_04/blob/master/webview_page.png)
