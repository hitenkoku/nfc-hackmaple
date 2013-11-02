package com.nfchack.omatomeisi;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
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

		// NFC縺ｮId縺ｨ譌･莉倥ｒ縺薙�縺薙�縺励※荵ｱ謨ｰ縺ｽ縺��繧偵▽縺上ｋ
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
                
            tv.setText("闥ｼ莠墓�");
            tv2.setText("遘√′闥ｼ莠墓�縺ｧ縺呻ｼ�);
                
            dataList.add("Facebook : KureiKei");
            dataList.add("Twitter : @pronama");
            dataList.add("niconico : 繧ｳ繝溘Η繝九ユ繧｣co9320");
            dataList.add("莉贋ｸ�分縺ｻ縺励＞繧ゅ��售urface2 Pro");
            successAudioStart();
                
         } else{
            tv.setText("Anna Takao");
            tv2.setText("Brilliant Service Co.Ltd.");
            dataList.add("Facebook:");
            dataList.add("Twitter : @meco300");
            dataList.add("Linkedin:");
            dataList.add("Latest favorite bar:譚代＆譚･縲�刀蟾晏ｺ�);
            successAudioStart();
        }
        lv.setAdapter(dataList);
	}
}


private void successAudioStart(){
    mp = MediaPlayer.create(this,R.raw.success);
    mp.start();
}
