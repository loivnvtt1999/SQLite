package com.example.demonho_customlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbStudent extends SQLiteOpenHelper {
    Context context;
    String query_student="create table tbl_student("+
            "id_student text primary key not null,"+
            "ten text,"+"" +
            "lop text)";
    public DbStudent(@Nullable Context context) {
        super(context, "dbstudent", null, 1);
        this.context=context;
    }
    public long insertStudent(Student st){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id_student",st.getMssv());
        values.put("ten",st.getTen());
        values.put("lop",st.getLop());
        long n=db.insert("tbl_student",null,values);
        db.close();
        return n;
    }
    public long deleteStudent(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        long n=db.delete("tbl_student","id_student=?",new String[]{id});
        db.close();
        return n;
    }
    public long updateStudent(Student student){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("ten",student.getTen());
        values.put("lop",student.getLop());
        long n=db.update("tbl_student",values,"id_student=?",new String[]{student.getMssv()});
        db.close();
        return n;
    }
    public ArrayList<Student> getAllStudent(){
        SQLiteDatabase db= this.getReadableDatabase();
        ArrayList<Student> lstStudent=new ArrayList<Student>();
        String query="select * from tbl_student";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Student st= new Student();
                st.setMssv(cursor.getString(0));
                st.setTen(cursor.getString(1));
                st.setLop(cursor.getString(2));
                lstStudent.add(st);
            }while (cursor.moveToNext());
        }
        return lstStudent;
    }
    public ArrayList<Student> getStudentByID(String ID){
        SQLiteDatabase db= this.getReadableDatabase();
        ArrayList<Student> lstStudent=new ArrayList<Student>();
        String query="select * from tbl_student where id_student="+"'"+ID+"'";
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Student st= new Student();
                st.setMssv(cursor.getString(0));
                st.setTen(cursor.getString(1));
                st.setLop(cursor.getString(2));
                lstStudent.add(st);
            }while (cursor.moveToNext());
        }
        return lstStudent;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(query_student);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists tbl_student");
        onCreate(sqLiteDatabase);
    }
}
