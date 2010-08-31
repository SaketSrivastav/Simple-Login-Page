/**
 * 
 */
package home.saket;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author saket
 *
 */
public class NewUserActivity extends Activity implements OnClickListener {

	private Button mRegister;
	private Button mCancel;
	private EditText mUsername;
	private EditText mPassword;
	private EditText mEmail;
	private DbHelper myDb = new DbHelper(NewUserActivity.this);
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		
		mRegister = (Button)findViewById(R.id.buttonRegister);
		mRegister.setOnClickListener(this);
		
		mCancel = (Button)findViewById(R.id.buttonCancel);
		mCancel.setOnClickListener(this);
	}
	
	public void onClick(View v) {
	
		switch(v.getId()){
			
			case R.id.buttonCancel:
				Intent i = new Intent(NewUserActivity.this, DatabaseActivity.class);
				startActivity(i);
				finish();
				break;
				
			case R.id.buttonRegister:
				
				mUsername = (EditText)findViewById(R.id.editUsername);
				mPassword = (EditText)findViewById(R.id.editPassword);
				mEmail = (EditText)findViewById(R.id.editEmail);
				
				String uname = mUsername.getText().toString();
				String pass = mPassword.getText().toString();
				String email = mEmail.getText().toString();
				boolean invalid = false;
				
				if(uname.equals("")){
					invalid = true;
					Toast.makeText(getApplicationContext(), "Username Missing", Toast.LENGTH_SHORT).show();
				}else if(pass.equals("")){
					invalid = true;
					Toast.makeText(getApplicationContext(), "Password Missing", Toast.LENGTH_SHORT).show();
				}else if(email.equals("")){
					invalid = true;
					Toast.makeText(getApplicationContext(), "Email ID Missing", Toast.LENGTH_SHORT).show();
				}
				
				if(invalid == false){
					addEntry(uname, pass, email);
					Intent i_register = new Intent(NewUserActivity.this, DatabaseActivity.class);
					startActivity(i_register);
					finish();
				}
				
				break;
		}
	}
	
	public void onDestroy(){
		super.onDestroy();
		myDb.close();
	}
	
	public void addEntry(String uname, String pass, String email){
		
		SQLiteDatabase db = myDb.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("username", uname);
		values.put("password", pass);
		values.put("email", email);
		
		try{
			db.insert(DbHelper.SAKET_TABLE_NAME, null, values);
			Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
