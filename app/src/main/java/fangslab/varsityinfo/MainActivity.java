package fangslab.varsityinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String>varsitynames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.UniversitylistViewMainActivityView);

        varsitynames = new ArrayList<>();
        varsitynames.add("Green University");
        varsitynames.add("Ahsanullah University");
        varsitynames.add("Dhaka University");
        varsitynames.add("Khulna University");
        varsitynames.add("Chittagong University");

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,varsitynames);

        listView.setAdapter(adapter);
    }

    public void SearchAction(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}
