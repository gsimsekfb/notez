package com.gman.notez;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Color;

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

        Log.d("--- Main", "onCreate()");


        listView = (DragSortListView) findViewById(R.id.listView);
        // String[] names = getResources().getStringArray(R.array.random_names);
        String[] names = new String[]{"1", "2", "3", "", "5", "6", "7", "8"};
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(names));
        adapter = new ArrayAdapter<String>(this, R.layout.row_item, R.id.textView, list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View itemView = super.getView(position, convertView, parent);
                TextView tv = (TextView) itemView.findViewById(R.id.textView);
                CharSequence cc = tv.getText(); // todo: use var
                if(cc.length() == 0) {
                    // itemView.setVisibility(View.GONE);
                    itemView.setBackgroundColor(Color.LTGRAY);
                }

                return itemView;
            }
        };

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

    //// Del Button
    public void onDelButtonClick(View v){
        // Log.d("--- Main", "onDelButtonClick()");
        listView.removeItem(0);
    }
}
