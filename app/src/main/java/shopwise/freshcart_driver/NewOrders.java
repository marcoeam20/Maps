package shopwise.freshcart_driver;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewOrders extends AppCompatActivity {

    public DatabaseReference mRef;
    public List<NewOrdersInformation> NOlistOrders;
    public NewOrdersListAdapter NOadapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_orders);

        mRef = FirebaseDatabase.getInstance().getReference("Orders");
        lv = (ListView) findViewById(R.id.NO_listview);
        NOlistOrders = new ArrayList<>();


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    NewOrdersInformation noi = snapshot.getValue(NewOrdersInformation.class);
                    NOlistOrders.add(noi);
                }

                NOadapter = new NewOrdersListAdapter(NewOrders.this, R.layout.order_layout, NOlistOrders);
                lv.setAdapter(NOadapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
