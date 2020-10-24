package com.example.customlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lview;
    ListViewBaseAdapter adapter;
    ArrayList<ListViewBean> arr_bean;

    Button capNhap, btsua;
    RelativeLayout manHinh;
    LinearLayout linearLayout;
    EditText sua;
    public static boolean isActionMode = false;
    public static List<ListViewBean> UserSelection = new ArrayList<>();
    public static ActionMode actionMode = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lview = (ListView)findViewById(R.id.listview);
        lview.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        lview.setMultiChoiceModeListener(modeListener);

        arr_bean=new ArrayList<ListViewBean>();
        arr_bean.add(new ListViewBean(R.drawable.ic_android, "Android"));
        arr_bean.add(new ListViewBean(R.drawable.ic_app,"Python"));
        arr_bean.add(new ListViewBean(R.drawable.ic_photo, "MySQL"));
        arr_bean.add(new ListViewBean(R.drawable.ic_user, "PHP"));
        arr_bean.add(new ListViewBean(R.drawable.ic_language, "Java"));
        adapter = new ListViewBaseAdapter(arr_bean,this);
        lview.setAdapter(adapter);
        getDataFromAddActivity();



        //context menu thêm, sửa, xóa
        capNhap = (Button)findViewById(R.id.btupdate);
        sua = (EditText)findViewById(R.id.edtsua);
        btsua = (Button)findViewById(R.id.btsua);
        manHinh = (RelativeLayout) findViewById(R.id.manhinh_main);

        // đăng kí view cho context menu
        registerForContextMenu(capNhap);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }
    public void UpdateData(){
        String chuoi = sua.getText().toString();
        sua.setVisibility(View.VISIBLE);
        btsua.setVisibility(View.VISIBLE);
    }
    public void getDataFromAddActivity(){
        //Update data from AddActivity
        Intent intent = getIntent();
        String subject = intent.getStringExtra(AddActivity.SUBJECT);
        arr_bean.add(new ListViewBean(R.drawable.ic_face,subject));
        adapter = new ListViewBaseAdapter(arr_bean,this);
        lview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context,menu);
        menu.setHeaderTitle("Cập nhập");
        menu.setHeaderIcon(R.mipmap.ic_launcher_round);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.them:{
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.sua:{
                UpdateData();
            }
        }
        return super.onContextItemSelected(item);
    }
    AbsListView.MultiChoiceModeListener modeListener = new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_actionmode,menu);
            isActionMode = true;
            actionMode = mode;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.delete:
                    adapter.removeItems(UserSelection);
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            isActionMode = false;
            actionMode = null;
            UserSelection.clear();
        }
    };
}
