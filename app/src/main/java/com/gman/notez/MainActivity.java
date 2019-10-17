package com.gman.notez;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.mobeta.android.dslv.DragSortController;
import com.mobeta.android.dslv.DragSortListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    DragSortListView listView;
    ArrayAdapter<String> adapter;

    private DragSortListView.DropListener onDrop = new DragSortListView.DropListener()
    {
        @Override
        public void drop(int from, int to)
        {
            if (from != to)
            {
                String item = adapter.getItem(from);
                adapter.remove(item);
                adapter.insert(item, to);
            }
        }
    };

    private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener()
    {
        @Override
        public void remove(int which)
        {
            adapter.remove(adapter.getItem(which));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (DragSortListView) findViewById(R.id.listView);
        // String[] names = getResources().getStringArray(R.array.random_names);
        String[] names = new String[]{"Tr", "In", "Ch", "UA", "US", "UK", "Ca", "Au", "Af"};
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(names));
        adapter = new ArrayAdapter<String>(this, R.layout.row_item, R.id.textView, list);
        listView.setAdapter(adapter);
        listView.setDropListener(onDrop);
        listView.setRemoveListener(onRemove);

        DragSortController controller = new DragSortController(listView);
        controller.setDragHandleId(R.id.imageView);
        //controller.setClickRemoveId(R.id.);
        controller.setRemoveEnabled(false);
        controller.setSortEnabled(true);
        controller.setDragInitMode(1);
        //controller.setRemoveMode(removeMode);

        listView.setFloatViewManager(controller);
        listView.setOnTouchListener(controller);
        listView.setDragEnabled(true);

    }
}
