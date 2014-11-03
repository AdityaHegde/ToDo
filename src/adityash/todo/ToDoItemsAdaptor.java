package adityash.todo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ToDoItemsAdaptor extends ArrayAdapter<ToDoItem> {
	ArrayList<ToDoItem> todoItems;
	
	public ToDoItemsAdaptor(Context context, ArrayList<ToDoItem> todoItems) {
		super(context, 0, todoItems);
		this.todoItems = todoItems;
	}
	
	@Override
    public View getView(final int position, View convertView, ViewGroup parent) {
		final ToDoItem item = getItem(position);
		final ToDoItemsAdaptor that = this;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
		}
		
		CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.cbDone);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				item.checked = isChecked;
				((ToDoActivity) that.getContext()).saveItems();
			}
			
		});
		checkBox.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				that.todoItems.remove(position);
				that.notifyDataSetChanged();
				((ToDoActivity) that.getContext()).saveItems();
				return true;
			}
			
		});
		
		checkBox.setText(item.label);
		checkBox.setChecked(item.checked);
		
		return convertView;
	}
}
