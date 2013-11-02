package com.nfchack.omatomeisi;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity {

	private static final String LOG_TAG = MainActivity.class.getSimpleName();

	// �f���p�̌Œ��NFC��ID�̒l
	private final String demoFirst = "123";
	private final String demoSecond = "abc";

	private ImageView image = null;
	private ArrayAdapter<String> dataList = null;
	private ListView lv = null;
	private TextView tv = null;
	private TextView tv2 = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Intent intent = getIntent();

		lv = (ListView) findViewById(R.id.listView1);
		tv = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		image = (ImageView) findViewById(R.id.imageView1);
		dataList = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
    
        if(){
            image.setImageResource(R.drawable.pronama_s);
    
            tv.setText("蒼井慧");
            tv2.setText("私が蒼井慧です！");
            dataList.add("Facebook : KureiKei");
            dataList.add("Twitter : @pronama");
            dataList.add("niconico : コミュニティco9320");
            datalist.add("今一番ほしいもの：Surface2 Pro");
        } else{
            tv.setText("Anna Takao");
            tv2.setText("Brilliant Service Co.Ltd.");
            dataList.add("Facebook:");
            dataList.add("Twitter : @meco300");
            dataList.add("Linkedin:");
            dataList.add("Latest favorite bar:村さ来　品川店");
        }
        lv.setAdapter(dataList);

		// NFCのIdと日付をこねこねして乱数ぽいのをつくる
		int[] idIntList = intent.getIntArrayExtra("idIntList");

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int fortuneNum = Integer.parseInt(sdf.format(date));

		String userId = "";
		StringBuilder stringBuilder = new StringBuilder();
		for (int id : idIntList) {
			fortuneNum += id;
		}

		// 割り切れたとこが今日の運勢
		String fortune = "運勢";
		if (fortuneNum % 5 == 0) {
			fortune = "大吉";
			// image.setImageResource(R.drawable.daikichi);

		} else if (fortuneNum % 4 == 0) {
			fortune = "小吉";
			// image.setImageResource(R.drawable.daikichi);

		} else if (fortuneNum % 3 == 0) {
			fortune = "末吉";
			// image.setImageResource(R.drawable.daikichi);

		} else if (fortuneNum % 2 == 0) {
			fortune = "中吉";
			// image.setImageResource(R.drawable.daikichi);

		} else if (fortuneNum % 6 == 0) {
			fortune = "凶";
			// image.setImageResource(R.drawable.daikichi);

		} else {
			fortune = "未知数";
			// image.setImageResource(R.drawable.daikichi);

		}
		Toast.makeText(getApplicationContext(), "今日の運勢は" + fortune + "です！",
				Toast.LENGTH_SHORT).show();
	}
}
