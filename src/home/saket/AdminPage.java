/**
 * 
 */
package home.saket;

import android.app.Activity;
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
public class AdminPage extends Activity implements OnClickListener {

	Button mAdminButton;
	Button mAdminCancel;
	EditText mUsername;
	EditText mPassword;
	DbHelper myDb = new DbHelper(AdminPage.this);
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin);
		
		mAdminButton = (Button)findViewById(R.id.buttonLogin);
		mAdminButton.setOnClickListener(this);
		
		mAdminCancel = (Button)findViewById(R.id.buttonCancel);
		mAdminCancel.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		
		switch(v.getId()){
		
		case R.id.buttonLogin:
			mPassword = (EditText)findViewById(R.id.editPassword);
			
			String pass = mPassword.getText().toString();
			if(pass.equals("") || pass == null){
				
				Toast.makeText(getApplicationContext(), "Password Missing", Toast.LENGTH_SHORT).show();
				
			}else{
				
				if(pass.equals("admin")){
					Toast.makeText(getApplicationContext(), "Retrieving Data", Toast.LENGTH_SHORT).show();
					retrieveEntries();
				}else{
					Toast.makeText(getApplicationContext(), "Admin Login Failed", Toast.LENGTH_SHORT).show();
				}
			}
			
			break;
			
		case R.id.buttonCancel:
			Intent i = new Intent(AdminPage.this, DatabaseActivity.class);
			startActivity(i);
			finish();
			break;
		}
	}
	
	public void retrieveEntries(){
		try{
			
			SQLiteDatabase db = myDb.getReadableDatabase();
			
			String[] columns = {"username","email"};
			
			Cursor cursor = db.query(DbHelper.SAKET_TABLE_NAME, columns, null, null, null, null, null);
			if(cursor != null){
				System.out.println("database showing");
				startManagingCursor(cursor);
				showDatabase(cursor);
			}
			System.out.println("Cursor NuLL");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void showDatabase(Cursor cursor) {
		StringBuilder ret = new StringBuilder("Database Values\n\n");
	    ret.append("\nUsername\t Email ID\n");
		while (cursor.moveToNext()) {
	      
	      String uname = cursor.getString(0);
	      
	      String email = cursor.getString(1);
	      ret.append(uname+"\t\t\t"+email+"\n");
	    }
		
		TextView result = new TextView(this);
		result.setText(ret);
		setContentView(result);
	}
	
	public void onDestroy(){
		super.onDestroy();
		myDb.close();
		finish();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		Intent i = new Intent(AdminPage.this, DatabaseActivity.class);
		startActivity(i);
		finish();
	}
}
