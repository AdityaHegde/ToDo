package adityash.todo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


public class ToDoActivity extends ActionBarActivity {
 	private ArrayList<String> todoItems;
 	private ArrayAdapter<String> todoItemsAdapter;
 	private ListView listToDoItems;
 	private EditText etAddNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        setupAdaptor();
    }


    private void setupAdaptor() {
		setupItems();
		todoItemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
		listToDoItems = (ListView) findViewById(R.id.listToDoItems);
		listToDoItems.setAdapter(todoItemsAdapter);
		etAddNew = (EditText) findViewById(R.id.etAddNew);
		listToDoItems.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				todoItems.remove(position);
				todoItemsAdapter.notifyDataSetChanged();
				saveItems();
				return true;
			}
			
		});
    }


	private void setupItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			todoItems = new ArrayList<String>(FileUtils.readLines(todoFile));
		} catch (IOException e) {
			todoItems = new ArrayList<String>();
		}
	}
	
	public void addItem(View v) {
		String item = etAddNew.getText().toString();
 		todoItemsAdapter.add(item);
 		etAddNew.setText("");
 		saveItems();
 	}
	
	private void saveItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			FileUtils.writeLines(todoFile, todoItems);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
