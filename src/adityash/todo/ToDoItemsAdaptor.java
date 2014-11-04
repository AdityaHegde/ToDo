package adityash.todo;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.activeandroid.Cache;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

public class ToDoItemsAdaptor extends CursorAdapter {
	
	public ToDoItemsAdaptor(Context context, Cursor cursor) {
		super(context, cursor, 0);
	}
	
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
	    return LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false);
    }
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		CheckBox checkBox = (CheckBox) view.findViewById(R.id.cbDone);
	    // Extract properties from cursor
	    String label = cursor.getString(cursor.getColumnIndexOrThrow("label"));
	    short checked = cursor.getShort(cursor.getColumnIndexOrThrow("checked"));
	    final int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
	    final ToDoItemsAdaptor that = this;
	    
	    checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String tableName = Cache.getTableInfo(ToDoItem.class).getTableName();
				ToDoItem item = (ToDoItem) new Select().from(ToDoItem.class).where(tableName+".Id = ?", id).execute().toArray()[0];
				item.checked = isChecked;
				item.save();
			}
			
		});
	    checkBox.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				String tableName = Cache.getTableInfo(ToDoItem.class).getTableName();
				new Delete().from(ToDoItem.class).where(tableName+".Id = ?", id).execute();
				that.reloadCursor();
				return true;
			}
			
		});
	    
	    checkBox.setText(label);
		checkBox.setChecked(checked == 1);
	}
	
	public void reloadCursor() {
		this.swapCursor(ToDoItem.fetchResultCursor()).close();
	}
}
