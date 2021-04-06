package com.example.demonho_customlistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtID, edtName, edtLop;
    Button btnThem, btnSua, btnXoa, btnChon;
    ListView lvStudent;
    CustomAdapterStudent adapter;
    DbStudent db;
    ArrayList<Student> lstStudent;
    int vitri=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtID=(EditText)findViewById(R.id.edtID);
        edtName=(EditText)findViewById(R.id.edtTen);
        edtLop=(EditText)findViewById(R.id.edtLop);
        btnThem=(Button)findViewById(R.id.btnThem);
        btnSua=(Button)findViewById(R.id.btnSua);
        btnXoa=(Button)findViewById(R.id.btnXoa);
        btnChon=(Button)findViewById(R.id.btnChon);
        lvStudent=(ListView)findViewById(R.id.lvStudent);
        db=new DbStudent(this);
        lstStudent=new ArrayList<Student>();
        adapter=new CustomAdapterStudent(MainActivity.this,R.layout.listview_student_custom,lstStudent);
        lvStudent.setAdapter(adapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student st= new Student();
                st.setMssv(edtID.getText().toString());
                st.setTen(edtName.getText().toString());
                st.setLop(edtLop.getText().toString());
                if(db.insertStudent(st)>0){
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vitri=i;
                Student st=lstStudent.get(i);
                edtID.setText(st.getMssv());
                edtName.setText(st.getTen());
                edtLop.setText(st.getLop());
            }
        });
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtID.getText().toString().length()==0){
                    lstStudent.clear();
                    lstStudent.addAll(db.getAllStudent());
                    adapter.notifyDataSetChanged();
                }
                else{
                    lstStudent.clear();
                    lstStudent.addAll(db.getStudentByID(edtID.getText().toString()));
                    if(lstStudent.size()>0){
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Không tìm thấy", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vitri!=-1){
                    AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Thông báo");
                    builder.setTitle("Có xóa sinh viên này không ?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(db.deleteStudent(edtID.getText().toString())>0){
                                Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                }
                else{
                    Toast.makeText(MainActivity.this, "aaaa", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student st=new Student();
                st.setMssv(edtID.getText().toString());
                st.setTen(edtName.getText().toString());
                st.setLop(edtLop.getText().toString());
                if(db.updateStudent(st)>0){
                    Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}