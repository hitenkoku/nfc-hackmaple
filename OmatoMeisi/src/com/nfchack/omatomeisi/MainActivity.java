package com.nfchack.omatomeisi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String LOG_TAG = MainActivity.class.getSimpleName();

	private NfcAdapter mNfcAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(LOG_TAG, "onCreate");
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		Log.d(LOG_TAG, "onResume");
		
		// ▼▼▼▼ここから
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

		// ▼NFCの機能判定
		// NFC機能なし機種
		if (mNfcAdapter == null) {
			Toast.makeText(getApplicationContext(), "no Nfc feature",
					Toast.LENGTH_SHORT).show();
			finish();
			return;
		}

		// NFC通信OFFモード
		if (!mNfcAdapter.isEnabled()) {
			Toast.makeText(getApplicationContext(), "off Nfc feature",
					Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		// ▲NFCの機能判定

		// NFCを見つけたときに反応させる
		// PendingIntent→タイミング（イベント発生）を指定してIntentを発生させる
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, getClass()), 0);

		// タイミングは、タグ発見時とする。
		IntentFilter[] intentFilter = new IntentFilter[] { new IntentFilter(
				NfcAdapter.ACTION_TAG_DISCOVERED) };

		// 反応するタグの種類を指定。
		String[][] techList = new String[][] { {
				android.nfc.tech.NfcA.class.getName(),
				android.nfc.tech.NfcB.class.getName(),
				android.nfc.tech.IsoDep.class.getName(),
				android.nfc.tech.MifareClassic.class.getName(),
				android.nfc.tech.MifareUltralight.class.getName(),
				android.nfc.tech.NdefFormatable.class.getName(),
				android.nfc.tech.NfcV.class.getName(),
				android.nfc.tech.NfcF.class.getName(), } };
		mNfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter,
				techList);
		// ▲▲▲▲▲ここまで
	}

	@Override
	public void onPause() {
		super.onPause();
		
		Log.d(LOG_TAG, "onPause");

		// ▼▼▼▼ここから
		// アプリが表示されてない時は、NFCに反応しなくてもいいようにする
		mNfcAdapter.disableForegroundDispatch(this);
		// ▲▲▲▲▲ここまで
	}

	// NFCをタッチした後の処理
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		Log.d(LOG_TAG, "NFCタグ読み込みスタート");
		
		// ▼▼▼▼ここから
		String action = intent.getAction();
		if (TextUtils.isEmpty(action)) {
			return;
		}

		if (!action.equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
			return;
		}

		byte[] rawId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
		String id = "nothing";
		id = bytesToString(rawId);
		Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();

		// ▼NFCの中身を取得する
		// NdefMessageをParcelable型で取得。NdefMessageが並列でいくつかあるパターンがある。
		Parcelable[] rawMessage = intent
				.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

		if (rawMessage != null) {
			// Parcelable型からNdefMessage型に入れ直す。
			NdefMessage[] msgs = new NdefMessage[rawMessage.length];
			String str = "";

			for (int i = 0; i < rawMessage.length; i++) {
				msgs[i] = (NdefMessage) rawMessage[i];
				// NdefMessageをNdefRecordにバラす。
				for (NdefRecord record : msgs[i].getRecords()) {
					// データ本体のPayload部を取り出す。バイト配列。
					byte[] payload = record.getPayload();
					for (byte data : payload) {
						// 負の値が入ってる場合があるので"& 0xff"をつける
						str += String.format("%c", data & 0xff);
					}
				}
				Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT)
						.show();
			}
		} else {
			Toast.makeText(getApplicationContext(), "中身のないタグです",
					Toast.LENGTH_SHORT).show();
		}

		// データ持って画面遷移
		int[] intList = byteToInt(rawId);

		Intent intent2 = new Intent(MainActivity.this, ResultActivity.class);
		intent2.putExtra("idIntList", intList);
		startActivity(intent2);
	}

	// byte配列をStringにして返す
	public String bytesToString(byte[] bytes) {
		StringBuilder buffer = new StringBuilder();
		boolean isFirst = true;

		for (byte b : bytes) {
			if (isFirst) {
				isFirst = false;
			} else {
				buffer.append("-");
			}
			buffer.append(Integer.toString(b & 0xff));
		}
		return buffer.toString();
	}

	// byte配列をInt配列にして返す
	public int[] byteToInt(byte[] bytes) {
		int[] result = new int[bytes.length];

		for (int i = 0; i < bytes.length; i++) {
			result[i] = bytes[i] & 0xff;
		}
		return result;
	}

}
