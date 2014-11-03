package adityash.todo;

public class ToDoItem {
	public String label;
	public boolean checked;
	
	public ToDoItem(String label) {
		this.label = label;
	}
	
	public ToDoItem(String label, boolean checked) {
		this.label = label;
		this.checked = checked;
	}
	
	public String toString() {
		return (checked ? "1":"0")+label;
	}
	
}
