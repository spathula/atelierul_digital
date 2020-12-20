package com.example.androidfundamentals.week4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidfundamentals.R;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class RecyclerActivity extends AppCompatActivity {
    private RecyclerView student_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        this.student_recyclerView = findViewById(R.id.student_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        this.student_recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        this.student_recyclerView.addItemDecoration(divider);

        List<Student> studentList = getStudents(50);

        this.student_recyclerView.setAdapter(new StudentAdapter(studentList));
    }

    private List<Student> getStudents(int studentCount) {
        List<Student> studentList = new ArrayList<>(studentCount);

        for (int i = 0; i < studentCount; i++) {
            studentList.add(new Student("FirstName " + i, "Last Name" + i));
        }

        return studentList;
    }

    private class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView firstName, lastName;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.firstName = itemView.findViewById(R.id.tv_first_name);
            this.lastName = itemView.findViewById(R.id.tv_last_name);
        }

        public void bind(@NonNull Student student) {
            this.firstName.setText(student.getFirstName());
            this.lastName.setText(student.getLastName());
        }

        public void updateBackground(int position) {
            if (position % 2 == 1) {
                itemView.setBackgroundColor(Color.rgb(240, 240, 240));
            } else {
                itemView.setBackgroundColor(Color.rgb(250, 255, 255));
            }
        }

    }

    private class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {

        @NonNull
        private List<Student> studentList;

        StudentAdapter(@NonNull List<Student> studentList) {
            this.studentList = studentList;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.student_layout, parent, false);
            return new StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            holder.bind(this.studentList.get(position));
            holder.updateBackground(position);
        }

        @Override
        public int getItemCount() {
            return studentList.size();
        }
    }
}