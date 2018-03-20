package Week_7;

import java.util.*;

public interface Item_Inventory {

	public void intialize_Item_Inventory();

	public void addItem(Item i, int no);

	public void removeItem(Item i, int no) throws OutOfStockException;
}
