package com.erkuai.expandabletextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableTextView textView = (ExpandableTextView)findViewById(R.id.tv_text);
        textView.setText("        Java是一门面向对象编程语言，不仅吸收了" +
                "C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，" +
                "因此Java语言具有功能强大和简单易用两个特征。\n" +
                "        Java语言作为静态面向对象编程语言的代表，" +
                "极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程。\n" +
                "        Java具有简单性、面向对象、分布式、健壮性、安全性、" +
                "平台独立与可移植性、多线程、动态性等特点 。\n" +
                "        Java可以编写桌面应用程序、Web应用程序、分布式系统和" +
                "嵌入式系统应用程序等。");

/*       textView.setText("123");*/

    }
}
