package adityash.todo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;


public class ToDoActivity extends ActionBarActivity {
 	private ToDoItemsAdaptor todoItemsAdapter;
 	private ListView listToDoItems;
 	private EditText etAddNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        setupAdaptor();
    }


    private void setupAdaptor() {
		todoItemsAdapter = new ToDoItemsAdaptor(this, ToDoItem.fetchResultCursor());
		listToDoItems = (ListView) findViewById(R.id.listToDoItems);
		listToDoItems.setAdapter(todoItemsAdapter);
		etAddNew = (EditText) findViewById(R.id.etAddNew);
    }
	
	public void addItem(View v) {
		String item = etAddNew.getText().toString();
 		ToDoItem todoItem = new ToDoItem(item);
 		todoItem.save();
 		todoItemsAdapter.reloadCursor();
 		etAddNew.setText("");
 	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.to_do, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
