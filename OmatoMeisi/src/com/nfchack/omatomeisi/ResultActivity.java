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

public class ResultActivity extends Activity {

	private static final String LOG_TAG = MainActivity.class.getSimpleName();

	private final String demoFirst = "41832522027540128";
	private final String demoSecond = "abc";

	private ImageView image = null;
	private ArrayAdapter<String> dataList = null;
	private ListView lv = null;
	private TextView tv = null;
	private TextView tv2 = null;
    private MediaPlayer mp;
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Intent intent = getIntent();

		// NFCのIdと日付をこねこねして乱数ぽいのをつくる
		int[] idIntList = intent.getIntArrayExtra("idIntList");

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int fortuneNum = Integer.parseInt(sdf.format(date));

		String userId = "";
		StringBuilder stringBuilder = new StringBuilder();
		for (int id : idIntList) {
			stringBuilder.append(id);
			fortuneNum += id;
		}
		Log.d(LOG_TAG, "ID = "+ stringBuilder);

		lv = (ListView) findViewById(R.id.listView1);
		tv = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		image = (ImageView) findViewById(R.id.imageView1);
		dataList = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
    
         if(stringBuilder.equals(demoFirst)){
            image.setImageResource(R.drawable.pronama_s);
                
            tv.setText("蒼井慧");
            tv2.setText("私が蒼井慧です！");
                
            dataList.add("Facebook : KureiKei");
            dataList.add("Twitter : @pronama");
            dataList.add("niconico : コミュニティco9320");
            dataList.add("今一番ほしいもの：Surface2 Pro");
            successAudioStart();
                
         } else{
            tv.setText("Anna Takao");
            tv2.setText("Brilliant Service Co.Ltd.");
            dataList.add("Facebook:");
            dataList.add("Twitter : @meco300");
            dataList.add("Linkedin:");
            dataList.add("Latest favorite bar:村さ来　品川店");
            successAudioStart();
        }
        lv.setAdapter(dataList);
	}
}


private void successAudioStart(){
    mp = MediaPlayer.create(this,R.raw.success);
    mp.start();
}
