package com.testapp.forcodes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentList extends AppCompatActivity {
    private RecyclerView mStudent_RecyclerView;
    private List<User> mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        mStudent_RecyclerView = (RecyclerView) findViewById(R.id.students_recycleview);
        mStudent_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUser = new ArrayList<>();
        for(int i=0;i<100;i++){
            User user = new User();
            user.setFullname("User #"+i);
            user.setEmail("user"+i+"@hotmail.com");
            mUser.add(user);
        }
        mStudent_RecyclerView.setAdapter(new UsersAdapter(mUser));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, ProfileEdit.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                Intent intent1 = new Intent(this, ItemsList.class);
                startActivity(intent1);
                return true;
            case R.id.item3:
                Intent intent3 = new Intent(this,StudentList.class);
                startActivity(intent3);
                return true;
            case R.id.item4:
                Intent intent4= new Intent(this,Home.class);
                startActivity(intent4);
                return true;
            case R.id.item5:
                Intent intent5= new Intent(this,MainActivity.class);
                Toast.makeText(this, "Bye Bye...", Toast.LENGTH_SHORT).show();
                startActivity(intent5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    class UsersAdapter extends RecyclerView.Adapter<UserViewHolder>{
        private List<User> mUsers;
        public UsersAdapter(List<User> users){
            super();
            this.mUsers = users;
        }
        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            holder.Bind(this.mUsers.get(position));
        }

        @Override
        public int getItemCount() {
            return this.mUsers.size();
        }
    }
    class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView fullname;
        private TextView email;

        public UserViewHolder(ViewGroup container){
            super(LayoutInflater.from(StudentList.this).inflate(R.layout.student_list_it,container, false));
            fullname = (TextView) itemView.findViewById(R.id.name_txt);
            email = (TextView) itemView.findViewById(R.id.email_txt);
        }
        public void Bind(User user){
            fullname.setText(user.getFullname());
            email.setText(user.getEmail());
        }


    }
}
