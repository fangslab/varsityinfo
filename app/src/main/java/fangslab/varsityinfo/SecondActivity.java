package fangslab.varsityinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> varsitynames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listView = (ListView) findViewById(R.id.listView2);

        varsitynames = new ArrayList<>();
        varsitynames.add("Notice Board");
        varsitynames.add("Departments");
        varsitynames.add("Shovon");
        varsitynames.add("Ghurar Dim");
        varsitynames.add("Hatir Dim");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,varsitynames);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //if (position == 0) {
                    Intent intent = new Intent(view.getContext(), WebViewActivity.class);
                    startActivity(intent);
                //}
            }
        });
    }
}
