/**
 * 
 */
package home.saket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author saket
 *
 */
public class UserLoggedInPage extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loggedin);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(UserLoggedInPage.this, DatabaseActivity.class);
		startActivity(i);
		finish();
	}
	
	
	
}
