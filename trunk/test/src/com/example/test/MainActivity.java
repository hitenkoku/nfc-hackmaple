package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	//�f���p�̌Œ��NFC��ID�̒l
	private final String demoFirst = "123";
	private final String demoSecond = "abc";
    
	private ImageView image = null;
	private ArrayAdapter<String> dataList = null;
	private ListView lv = null;
	private TextView tv = null;
	private TextView tv2 = null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        lv = (ListView)findViewById(R.id.listView1);
        tv = (TextView)findViewById(R.id.textView1);
        tv2 = (TextView)findViewById(R.id.textView2); 
        
        //dataList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        
        image.setImageResource(R.drawable.pronama_s);
        //Intent it = new Intent();
        //String getID =it.getStringExtra("nfcID");        
        //if(getID == demoFirst){
    	  	tv.setText("����@�b");
    	  	tv2.setText("�v��������񂾂�I�݂�Ȃ�낵���ˁI");
        	//dataList.add("Facebook : �v���������");
        	//dataList.add("Twitter : @pronama");
        	//dataList.add("niconico�R�~���j�e�B�@�F�@co9320");
        	//lv.setAdapter(dataList);
    	  
    	//  if(getID == demoFirst){} else if(getID == demoSecond){
    	 // dataList.add("Twitter : @mid_f"); 
       //}
    }

   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }   
}
