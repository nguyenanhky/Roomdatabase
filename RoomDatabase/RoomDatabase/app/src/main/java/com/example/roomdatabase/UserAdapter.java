package com.example.roomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.entities.User;

import java.util.List;

public class UserAdapter  extends  RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private List<User> mlist;
    private IClickitemUser iClickitemUser;
    // ham tao

    public void SetData(List<User>list){
        this.mlist = list;
        notifyDataSetChanged();
    }

    // update and delete
    public interface IClickitemUser{
        void  update(User user);
        void delete(User user);
    }
    // ham tao chua interfacce

    public UserAdapter(IClickitemUser iClickitemUser) {
        this.iClickitemUser = iClickitemUser;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_user,parent,false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mlist.get(position);
        if(user==null){
            return;
        }
        holder.txtuser.setText(user.getFirstname());
        holder.txtadd.setText(user.getAddress());

        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickitemUser.update(user);
            }
        });

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickitemUser.delete(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView txtuser,txtadd;
        private Button btnupdate, btndelete;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtuser = itemView.findViewById(R.id.txtuser);
            txtadd = itemView.findViewById(R.id.txtadd);
            btnupdate = itemView.findViewById(R.id.btnupdate);
            btndelete = itemView.findViewById(R.id.btndelete);
        }
    }


}
