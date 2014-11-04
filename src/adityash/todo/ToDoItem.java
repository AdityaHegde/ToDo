package adityash.todo;

import java.util.List;

import android.database.Cursor;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "ToDoItem")
public class ToDoItem extends Model {
	@Column(name = "label")
	public String label;
	@Column(name = "checked")
	public boolean checked;
	
	public ToDoItem() {
		super();
	}
	
	public ToDoItem(String label) {
		super();
		this.label = label;
	}
	
	public ToDoItem(String label, boolean checked) {
		super();
		this.label = label;
		this.checked = checked;
	}
	
	public static List<ToDoItem> getAll() {
		return new Select().from(ToDoItem.class).orderBy("checked ASC").execute();
	}
	
	public static Cursor fetchResultCursor() {
		String tableName = Cache.getTableInfo(ToDoItem.class).getTableName();
		String resultRecords = new Select(tableName + ".*, " + tableName + ".Id as _id").from(ToDoItem.class).orderBy("checked ASC").toSql();
		Cursor cursor = Cache.openDatabase().rawQuery(resultRecords, null);
		return cursor;
	}
	
}
